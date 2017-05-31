package com.exam.panels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.gui.GuiToggleButton;
import com.exam.assets.SpriteType;
import com.exam.gui.Gui;
import com.exam.gui.GuiButton;
import com.exam.managers.GameManager;
import com.exam.managers.SoundManager;
import com.exam.project.Main;
import com.exam.scenes.SceneManager;
import com.exam.tween.AccessorReferences;
import com.exam.tween.GuiAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class PausePanel extends Panel {

	// Visualization
	private Gui _background;
	private Gui _gamePausedText;
	private GuiButton _resumeButton;
	private GuiButton _menuButton;
	private GuiToggleButton _muteButton;

	private float _animationTime = 0.2f;

	private SceneManager _sceneManager; // to manage scene switching.

	public PausePanel(OrthographicCamera camera, SceneManager sceneManager) {
		super();
		this._sceneManager = sceneManager;
		Tween.registerAccessor(Gui.class, new GuiAccessor());
		_background = new Gui(Main.WIDTH / 2, Main.HEIGHT / 2, SpriteType.BACKGROUND_FADE_02, pGuiManager);
		_gamePausedText = new Gui(Main.WIDTH / 2, Main.HEIGHT / 2 + _background.getHeight() / 2,
				SpriteType.GAME_PAUSED_TEXT, pGuiManager);

		_resumeButton = new GuiButton(Main.WIDTH / 2, Main.HEIGHT / 2 + 90, SpriteType.BUTTON_RESUME_IDLE,
				SpriteType.BUTTON_RESUME_PRESSED, camera, pGuiManager);
		_menuButton = new GuiButton(Main.WIDTH / 2, Main.HEIGHT / 2 - 90, SpriteType.BUTTON_MENU_IDLE,
				SpriteType.BUTTON_MENU_PRESSED, camera, pGuiManager);
		_muteButton = new GuiToggleButton(600, 1200, SpriteType.BUTTON_AUDIO_ON_IDLE, SpriteType.BUTTON_AUDIO_ON_PRESSED,SpriteType.BUTTON_AUDIO_OFF_IDLE, SpriteType.BUTTON_AUDIO_OFF_PRESSED,
				camera, pGuiManager);
		if(SoundManager.isMuted)
			_muteButton.toggle();

		Timeline.createSequence()
				//set tweens
				.push(Tween.set(_background, AccessorReferences.SCALE).target(0, 0))
				.push(Tween.set(_gamePausedText, AccessorReferences.SCALE).target(0, 0))
				.push(Tween.set(_resumeButton, AccessorReferences.SCALE).target(0, 0))
				.push(Tween.set(_menuButton, AccessorReferences.SCALE).target(0, 0))
				.push(Tween.set(_muteButton, AccessorReferences.SCALE).target(0, 0)).start(pTweenManager);
	}

	@Override
	public void startAnimation() {
		pIsActive = true;
		GameManager.isPaused = true;
		Timeline.createSequence()
			.pushPause(0.1f)
			.beginParallel().push(Tween.to(_background, AccessorReferences.SCALE, _animationTime).target(1, 1))
			.end() // next tween
			.beginParallel() // tween all at once.
			.push(Tween.to(_gamePausedText, AccessorReferences.SCALE, _animationTime).target(1, 1))
			.push(Tween.to(_resumeButton, AccessorReferences.SCALE, _animationTime).target(1, 1))
			.push(Tween.to(_menuButton, AccessorReferences.SCALE, _animationTime).target(1, 1))
			.push(Tween.to(_muteButton, AccessorReferences.SCALE, _animationTime).target(1, 1))
			.end()
		.start(pTweenManager);
	}

	@Override
	public void endAnimation() {
		Timeline.createSequence().pushPause(0.1f).beginParallel() // tween all at once
				.push(Tween.to(_gamePausedText, AccessorReferences.SCALE, _animationTime).target(0, 0))
				.push(Tween.to(_resumeButton, AccessorReferences.SCALE, _animationTime).target(0, 0))
				.push(Tween.to(_menuButton, AccessorReferences.SCALE, _animationTime).target(0, 0))
				.push(Tween.to(_muteButton, AccessorReferences.SCALE, _animationTime).target(0, 0)).end() // next tween.

				.beginParallel().push(Tween.to(_background, AccessorReferences.SCALE, _animationTime).target(0, 0)
						.setCallback(new TweenCallback() {

							@Override
							public void onEvent(int arg0, BaseTween<?> arg1) {
								// tween ended.
								pIsActive = false;
								GameManager.isPaused = false;
							}
						}))
				.end()

				.start(pTweenManager);
	}

	/**
	 * Handling buttons input.
	 */
	private void handleInput() {
		if (_menuButton.isClicked()) {
			pIsActive = false;
			GameManager.reset();
			_sceneManager.setScene(SceneManager.MENU);
		}
		if (_resumeButton.isClicked())
			endAnimation();
		if(_muteButton.isClicked())
			SoundManager.muteToggle();
	}

	@Override
	public void update(float deltaTime) {
		if (!pIsActive)
			return; // no need to update if we are not active.
		handleInput();
		pTweenManager.update(deltaTime);
		pGuiManager.update(deltaTime);
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		if (!pIsActive)
			return; // no need to render if we are not active.
		spriteBatch.begin();
		pGuiManager.render(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void dispose() {
	}

}
