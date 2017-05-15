package com.exam.scenes;

import com.exam.gui.GuiButton;
import com.exam.gui.Gui;
import com.exam.handlers.GUIManager;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;
import com.exam.tween.AccessorReferences;
import com.exam.tween.GuiAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class MainMenuScene extends Scene{
	
	private GUIManager _guiManager;
	
	// not sure why I got this warning.. because I register them inside the given guiManager in the constructor.
	
	private Gui _background;
	private Gui _backgroundOverlay;
	private Gui _logo;
	
	private GuiButton _startButton;
	private GuiButton _trophieButton;
	private GuiButton _upgradeButton;
	
	private TweenManager _tweenManager;
	private float _startTweeningAnimationTime = 0.2f;
	private float _tweenAnimationDelay = 0.0f;

	protected MainMenuScene(SceneManager manager) {
		super(manager);
		_tweenManager = new TweenManager();
		Tween.registerAccessor(Gui.class, new GuiAccessor());
		
		_guiManager = new GUIManager();

		_background = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGOUND_PLYAY_01, _guiManager).setIndex(-2);
		_backgroundOverlay = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGOUND_PLYAY_01_OVERLAY, _guiManager).setIndex(-1);
		_logo = new Gui(Main.WIDTH/2, 950, SpriteType.LOGO_01, _guiManager);
		
		_startButton = new GuiButton(Main.WIDTH/2, 600, SpriteType.BUTTON_PLAY_IDLE, SpriteType.BUTTON_PLAY_PRESSED, pHudCamera, _guiManager);
		_upgradeButton = new GuiButton(Main.WIDTH/2, 400, SpriteType.BUTTON_UPGRADES_IDLE, SpriteType.BUTTON_UPGRADES_PRESSED, pHudCamera, _guiManager);
		_trophieButton = new GuiButton(Main.WIDTH/2, 200, SpriteType.BUTTON_TROPHIES_IDLE, SpriteType.BUTTON_TROPHIES_PRESSED, pHudCamera, _guiManager);
		
		_guiManager.sortGuis();
		startAnimation();
	}
	
	private void startAnimation() {
		Timeline.createSequence()
		//set tweens
		.push(Tween.set(_startButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(_trophieButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(_upgradeButton, AccessorReferences.SCALE).target(0,0))
		// wait half second before starting animation
		.pushPause(0.5f)
		// scale up and down all tweens after each other.
		.push(Tween.to(_startButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1.4f,1.8f))
		.push(Tween.to(_startButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1,1))
		
		.push(Tween.to(_upgradeButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1.4f,1.8f))
		.push(Tween.to(_upgradeButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1,1))
		
		.push(Tween.to(_trophieButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1.4f,1.8f))
		.push(Tween.to(_trophieButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1,1))
		.start(_tweenManager);
	}
	
	private void endAnimation(final int scene){
		Timeline.createSequence()
		//set tweens
		.push(Tween.set(_startButton, AccessorReferences.SCALE).target(1,1))
		.push(Tween.set(_trophieButton, AccessorReferences.SCALE).target(1,1))
		.push(Tween.set(_upgradeButton, AccessorReferences.SCALE).target(1,1))
		//start up scaling all buttons at the same time
		.beginParallel()
		.push(Tween.to(_trophieButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1.5f,1.5f))
		.push(Tween.to(_upgradeButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1.5f,1.5f))
		.push(Tween.to(_startButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1.5f,1.5f))
		.end()
		//start down scaling all buttons at the same time
		.beginParallel()
		.push(Tween.to(_startButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(0,0))
		.push(Tween.to(_trophieButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(0,0))
		.push(Tween.to(_upgradeButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(0,0)
				.setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						pSceneManager.pushScene(scene); // load scene after this tween.
						
					}
				}))
		.end()
		.start(_tweenManager);
	}

	@Override
	public void handleInput() {
		if(_startButton.isClicked()){
			endAnimation(SceneManager.PLAY);
		}
	}

	@Override
	public void update(float deltaTime) {
		handleInput();
		_tweenManager.update(deltaTime);
		_guiManager.update(deltaTime);
	}

	@Override
	public void render() {
		pSpriteBatch.setProjectionMatrix(pHudCamera.combined);
		pSpriteBatch.begin();
		_guiManager.render(pSpriteBatch);
		pSpriteBatch.end();
	}

	@Override
	public void dispose() {
		
	}

}
