package com.exam.panels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.assets.SpriteType;
import com.exam.font.FontType;
import com.exam.gui.GuiManager;
import com.exam.gui.Gui;
import com.exam.gui.GuiButton;
import com.exam.gui.GuiText;
import com.exam.gui.UpgradeProcess;
import com.exam.managers.GameManager;
import com.exam.project.Main;
import com.exam.scenes.SceneManager;
import com.exam.tween.AccessorReferences;
import com.exam.tween.GuiAccessor;
import com.exam.tween.GuiTextAccessor;
import com.exam.tween.UpgradeProcessAccesor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Quint;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class UpgradePanel extends Panel {

	private final float OFFSET = 300; // offset in pixels between every upgrade panel.

	// Identifiers.
	private final int UPGRADE_DEPTH_INCREASE = 0;
	private final int UPGRADE_BOOST = 1;
	private final int UPGRADE_MONEY_INCREASE = 2;

	private GuiManager _guiManager;

	// Visualization
	private Gui _background;
	private Gui _backgroundOverlay;
	private GuiText _cashText;

	// upgrade visuals
	private Gui[] _fadeBackgrounds; // background of each upgrade.
	private Gui[] _icons; // icon of each upgrade.
	private GuiText[] _valueTexts; // value of upgrade in text.
	private GuiButton[] _buyButtons; // buy button to upgrade an upgrade.
	private UpgradeProcess[] _processes; // upgrade process bars

	private GuiButton _continueButton;
	private GuiButton _closeButton;

	private float _startAnimationTime = 0.3f;
	private SceneManager _sceneManager;

	public UpgradePanel(OrthographicCamera camera, SceneManager sceneManager) {
		super();
		this._sceneManager = sceneManager;
		_guiManager = new GuiManager();
		Tween.registerAccessor(Gui.class, new GuiAccessor());
		Tween.registerAccessor(UpgradeProcess.class, new UpgradeProcessAccesor());
		Tween.registerAccessor(GuiText.class, new GuiTextAccessor());

		_background = new Gui(Main.WIDTH / 2, Main.HEIGHT / 2, SpriteType.BACKGOUND_PLYAY_01, _guiManager);
		_backgroundOverlay = new Gui(Main.WIDTH / 2, Main.HEIGHT / 2, SpriteType.BACKGOUND_PLYAY_01_OVERLAY, _guiManager);
		_cashText = new GuiText(400, 1200, _guiManager, FontType.SUPERCELL_MAGIC);
		_cashText.setText("$ 0");

		//initializing processing and registering gui arrays
		_fadeBackgrounds = new Gui[] {
			new Gui(Main.WIDTH / 2, (Main.HEIGHT / 2) + OFFSET, SpriteType.BACKGROUND_FADE_01, _guiManager),
			new Gui(Main.WIDTH / 2, Main.HEIGHT / 2.0f, SpriteType.BACKGROUND_FADE_01, _guiManager),
			new Gui(Main.WIDTH / 2, (Main.HEIGHT / 2) - OFFSET, SpriteType.BACKGROUND_FADE_01, _guiManager) 
		};

		_icons = new Gui[] { 
			new Gui(140, (Main.HEIGHT / 2) + OFFSET, SpriteType.UPGRADE_ARM_LENGTH, _guiManager),
			new Gui(140, Main.HEIGHT / 2.0f, SpriteType.UPGRADE_STARTDEPTH_INCREASER, _guiManager),
			new Gui(140, (Main.HEIGHT / 2) - OFFSET, SpriteType.UPGRADE_ITEMVALUE_INCREASER, _guiManager) 
		};

		_valueTexts = new GuiText[] {
			new GuiText(275, (Main.HEIGHT / 2) + OFFSET - 10, _guiManager, FontType.SUPERCELL_MAGIC, "100$"),
			new GuiText(275, (Main.HEIGHT / 2) - 10, _guiManager, FontType.SUPERCELL_MAGIC, "100$"),
			new GuiText(275, (Main.HEIGHT / 2) - OFFSET - 10, _guiManager, FontType.SUPERCELL_MAGIC, "100$") 
		};

		_buyButtons = new GuiButton[] {
			new GuiButton(600, (Main.HEIGHT / 2) + OFFSET - 50, SpriteType.BUTTON_BUY_IDLE, SpriteType.BUTTON_BUY_PRESSED, camera, _guiManager),
			new GuiButton(600, (Main.HEIGHT / 2) - 50, SpriteType.BUTTON_BUY_IDLE, SpriteType.BUTTON_BUY_PRESSED, camera, _guiManager),
			new GuiButton(600, (Main.HEIGHT / 2) - OFFSET - 50, SpriteType.BUTTON_BUY_IDLE, SpriteType.BUTTON_BUY_PRESSED, camera, _guiManager)
		};

		_processes = new UpgradeProcess[] {
			new UpgradeProcess(_guiManager, Main.WIDTH / 2.5f, (Main.HEIGHT / 2) + OFFSET + 60, "Increase depth", 0),
			new UpgradeProcess(_guiManager, Main.WIDTH / 2.5f, (Main.HEIGHT / 2) + 60, "Increase head start", 1),
			new UpgradeProcess(_guiManager, Main.WIDTH / 2.5f, (Main.HEIGHT / 2) - OFFSET + 60, "Increase item value", 2)
		};

		_continueButton = new GuiButton(600, 100, SpriteType.BUTTON_PLAY_SMALL_IDLE,
			SpriteType.BUTTON_PLAY_SMALL_PRESSED, camera, _guiManager);
		_closeButton = new GuiButton(100, 1200, SpriteType.BUTTON_QUIT_IDLE, SpriteType.BUTTON_QUIT_PRESSED, camera,
			_guiManager);

		//set tweens
		Timeline.createSequence().beginParallel().push(Tween.set(_background, AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_backgroundOverlay, AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_fadeBackgrounds[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_fadeBackgrounds[UPGRADE_BOOST], AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_fadeBackgrounds[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE).target(0, 0))

			.push(Tween.set(_icons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_icons[UPGRADE_BOOST], AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_icons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE).target(0, 0))

			.push(Tween.set(_processes[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_processes[UPGRADE_BOOST], AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_processes[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE).target(0, 0))

			.push(Tween.set(_valueTexts[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_valueTexts[UPGRADE_BOOST], AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_valueTexts[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE).target(0, 0))

			.push(Tween.set(_buyButtons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_buyButtons[UPGRADE_BOOST], AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_buyButtons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE).target(0, 0))

			.push(Tween.set(_continueButton, AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_closeButton, AccessorReferences.SCALE).target(0, 0))
			.push(Tween.set(_cashText, AccessorReferences.SCALE).target(0, 0)).end().start(pTweenManager);
	}

	@Override
	public void startAnimation() {
		pIsActive = true;
		Timeline.createSequence()
			.pushPause(0.1f) // wait one second before animating

			.beginParallel() // tween all at once
			.push(Tween.to(_background, AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_backgroundOverlay, AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.end() // next tween

			.beginParallel() // tween all at once
			.push(Tween.to(_fadeBackgrounds[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_fadeBackgrounds[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_fadeBackgrounds[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))

			.push(Tween.to(_icons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_icons[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_icons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))

			.push(Tween.to(_processes[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_processes[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_processes[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))

			.push(Tween.to(_valueTexts[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_valueTexts[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_valueTexts[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))

			.push(Tween.to(_buyButtons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_buyButtons[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_buyButtons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(1, 1))

			.push(Tween.to(_continueButton, AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_closeButton, AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.push(Tween.to(_cashText, AccessorReferences.SCALE, _startAnimationTime).target(1, 1))
			.end() 
			.start(pTweenManager);
	}

	@Override
	public void endAnimation() {
		Timeline.createSequence()

			.pushPause(0.1f) // wait 1 millisecond before animation.

			.beginParallel() // tween all at once
			.push(Tween.to(_fadeBackgrounds[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_fadeBackgrounds[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_fadeBackgrounds[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))

			.push(Tween.to(_icons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_icons[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_icons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))

			.push(Tween.to(_processes[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_processes[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_processes[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))

			.push(Tween.to(_valueTexts[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_valueTexts[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_valueTexts[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))

			.push(Tween.to(_buyButtons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_buyButtons[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_buyButtons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))

			.push(Tween.to(_continueButton, AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_closeButton, AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_cashText, AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.end() // next tween

			.beginParallel() // tween all at once
			.push(Tween.to(_background, AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_backgroundOverlay, AccessorReferences.SCALE, _startAnimationTime).target(0, 0)
				.setCallback(new TweenCallback() {

					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						// tween finished
						pIsActive = false;

					}
				}))
			.end().start(pTweenManager);
	}

	private void AnimateToGame() {
		Timeline.createSequence()

			.pushPause(0.1f) // wait 1 millisecond before animating

			.beginParallel() // tween all at once
			.push(Tween.to(_fadeBackgrounds[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_fadeBackgrounds[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_fadeBackgrounds[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))

			.push(Tween.to(_icons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_icons[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_icons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))

			.push(Tween.to(_processes[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_processes[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_processes[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))

			.push(Tween.to(_valueTexts[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_valueTexts[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_valueTexts[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))

			.push(Tween.to(_buyButtons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_buyButtons[UPGRADE_BOOST], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_buyButtons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE, _startAnimationTime).target(0, 0))

			.push(Tween.to(_continueButton, AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_closeButton, AccessorReferences.SCALE, _startAnimationTime).target(0, 0))
			.push(Tween.to(_cashText, AccessorReferences.SCALE, _startAnimationTime).target(0, 0)
				.setCallback(new TweenCallback() {

					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						_sceneManager.setScene(SceneManager.PLAY);
					}
				}))
			.end().start(pTweenManager);
	}
	
	/**
	 * Handle button input.
	 */
	private void handleInput(){
		if (_closeButton.isClicked())
			endAnimation();
		if (_continueButton.isClicked()) {
			GameManager.reset();
			AnimateToGame();
		}

		if (_buyButtons[UPGRADE_DEPTH_INCREASE].isClicked()) {
			_processes[UPGRADE_DEPTH_INCREASE].upgrade();
		}
		if (_buyButtons[UPGRADE_BOOST].isClicked()) {
			_processes[UPGRADE_BOOST].upgrade();
		}
		if (_buyButtons[UPGRADE_MONEY_INCREASE].isClicked()) {
			_processes[UPGRADE_MONEY_INCREASE].upgrade();
		}
	}

	@Override
	public void update(float deltaTime) {
		if (!pIsActive)
			return;
		handleInput();
		pTweenManager.update(deltaTime);
		_guiManager.update(deltaTime);

		for (int i = 0; i < _valueTexts.length; i++) {
			_valueTexts[i].setText(_processes[i].getCost() + "$");
		}
		_cashText.setText("$ " + GameManager.getMoney());
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		if (!pIsActive)
			return;
		spriteBatch.begin();
		_guiManager.render(spriteBatch); // we'll let the manager render everything.
		spriteBatch.end();
	}

	@Override
	public void dispose() {
	}

}
