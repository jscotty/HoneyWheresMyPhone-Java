package com.exam.panels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.assets.SpriteType;
import com.exam.gui.Gui;
import com.exam.gui.GuiButton;
import com.exam.managers.GameManager;
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
public class EndPanel extends Panel {

	//Visualization
	private Gui _background;
	private Gui _gamePausedText;
	private GuiButton _resumeButton;
	private GuiButton _menuButton;
	private GuiButton _upgradeButton;
	private GuiButton _trophiesButton;
	private float _animationTime = 0.2f;

	private UpgradePanel _upgradePanel;
	private SceneManager _sceneManager;

	private int scene = 0; // current scene to switch at.

	/**
	 * Constructor for initialization.
	 * @param camera for button initialization.
	 * @param sceneManager for scene switching.
	 */
	public EndPanel(OrthographicCamera camera, SceneManager sceneManager) {
		super();
		this._sceneManager = sceneManager;
		Tween.registerAccessor(Gui.class, new GuiAccessor());

		//initialize, process and register guis.
		_background = new Gui(Main.WIDTH / 2, Main.HEIGHT / 2, SpriteType.BACKGROUND_FADE_02, pGuiManager).setIndex(10);
		_gamePausedText = new Gui(Main.WIDTH / 2, Main.HEIGHT / 2 + _background.getHeight() / 2,
				SpriteType.GAME_ENDED_TEXT, pGuiManager).setIndex(10);

		_resumeButton = (GuiButton) new GuiButton(Main.WIDTH / 2 + 200,
				Main.HEIGHT / 2 - _background.getHeight() / 2 + 20, SpriteType.BUTTON_REPLAY_IDLE,
				SpriteType.BUTTON_REPLAY_PRESSED, camera, pGuiManager).setIndex(11);
		_upgradeButton = (GuiButton) new GuiButton(Main.WIDTH / 2, Main.HEIGHT / 2 - _background.getHeight() / 2,
				SpriteType.BUTTON_UPGRADES_IDLE_SMALL, SpriteType.BUTTON_UPGRADES_PRESSED_SMALL, camera, pGuiManager)
						.setIndex(11);
		_menuButton = (GuiButton) new GuiButton(Main.WIDTH / 2 - 200,
				Main.HEIGHT / 2 - _background.getHeight() / 2 + 20, SpriteType.BUTTON_HOME_IDLE,
				SpriteType.BUTTON_HOME_PRESSED, camera, pGuiManager).setIndex(11);
		_trophiesButton = (GuiButton) new GuiButton(600, 1200, SpriteType.BUTTON_TROPHIES_IDLE_SMALL,
				SpriteType.BUTTON_TROPHIES_PRESSED_SMALL, camera, pGuiManager).setIndex(11);

		_upgradePanel = new UpgradePanel(camera, sceneManager);

		// set animations
		Timeline.createSequence()
				//set tweens
				.push(Tween.set(_background, AccessorReferences.SCALE).target(0, 0))
				.push(Tween.set(_gamePausedText, AccessorReferences.SCALE).target(0, 0))
				.push(Tween.set(_resumeButton, AccessorReferences.SCALE).target(0, 0))
				.push(Tween.set(_menuButton, AccessorReferences.SCALE).target(0, 0))
				.push(Tween.set(_upgradeButton, AccessorReferences.SCALE).target(0, 0))
				.push(Tween.set(_trophiesButton, AccessorReferences.SCALE).target(0, 0)).start(pTweenManager);
	}

	@Override
	public void startAnimation() {
		// tween scale to 1,1 targets
		pIsActive = true;
		Timeline.createSequence().pushPause(0.1f)

				.beginParallel() // tween all at once.
				.push(Tween.to(_background, AccessorReferences.SCALE, _animationTime).target(1, 1)).end() // start next parallel

				.beginParallel() // tween all at once.
				.push(Tween.to(_gamePausedText, AccessorReferences.SCALE, _animationTime).target(1, 1))
				.push(Tween.to(_resumeButton, AccessorReferences.SCALE, _animationTime).target(1, 1))
				.push(Tween.to(_menuButton, AccessorReferences.SCALE, _animationTime).target(1, 1))
				.push(Tween.to(_upgradeButton, AccessorReferences.SCALE, _animationTime).target(1, 1))
				.push(Tween.to(_trophiesButton, AccessorReferences.SCALE, _animationTime).target(1, 1)).end() // finished.
				.start(pTweenManager);
	}

	@Override
	public void endAnimation() {
		// tween scale to 0,0 targets.
		Timeline.createSequence().pushPause(0.1f).beginParallel() // tween all at once
				.push(Tween.to(_gamePausedText, AccessorReferences.SCALE, _animationTime).target(0, 0))
				.push(Tween.to(_resumeButton, AccessorReferences.SCALE, _animationTime).target(0, 0))
				.push(Tween.to(_upgradeButton, AccessorReferences.SCALE, _animationTime).target(0, 0))
				.push(Tween.to(_menuButton, AccessorReferences.SCALE, _animationTime).target(0, 0))
				.push(Tween.to(_trophiesButton, AccessorReferences.SCALE, _animationTime).target(0, 0)).end() // start next tween

				.beginParallel() // tween all at once
				.push(Tween.to(_background, AccessorReferences.SCALE, _animationTime).target(0, 0)
						.setCallback(new TweenCallback() {

							@Override
							public void onEvent(int arg0, BaseTween<?> arg1) {
								// if animation has ended.
								pIsActive = false;
								GameManager.isPaused = false;
								_sceneManager.setScene(scene);
							}
						}))
				.end()

				.start(pTweenManager);
	}

	/**
	 * Handling button input.
	 */
	private void handleInput() {
		if (_menuButton.isClicked()) {
			GameManager.reset();
			scene = 0;
			endAnimation();
		}
		if (_resumeButton.isClicked()) {
			GameManager.reset();
			scene = 1;
			endAnimation();
		}
		if (_upgradeButton.isClicked()) {
			_upgradePanel.startAnimation();
		}
	}

	@Override
	public void update(float deltaTime) {
		if (!pIsActive)
			return;
		if (!_upgradePanel.isActive())
			handleInput();
		pTweenManager.update(deltaTime);
		pGuiManager.update(deltaTime);
		_upgradePanel.update(deltaTime);
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		if (!pIsActive)
			return;
		spriteBatch.begin();
		pGuiManager.render(spriteBatch);
		spriteBatch.end();
		_upgradePanel.render(spriteBatch);
	}

	@Override
	public void dispose() {
	}

}
