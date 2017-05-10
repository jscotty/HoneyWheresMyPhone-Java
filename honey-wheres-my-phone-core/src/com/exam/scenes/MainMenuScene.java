package com.exam.scenes;

import com.exam.gui.Button;
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
	
	private GUIManager guiManager;
	
	// not sure why I got this warning.. because I register them inside the given guiManager in the constructor.
	
	private Gui background;
	private Gui backgroundOverlay;
	private Gui logo;
	
	private Button startButton;
	private Button trophieButton;
	private Button upgradeButton;
	
	private TweenManager tweenManager;
	private float startTweeningAnimationTime = 0.2f;
	private float tweenAnimationDelay = 0.0f;

	protected MainMenuScene(SceneManager manager) {
		super(manager);
		tweenManager = new TweenManager();
		Tween.registerAccessor(Gui.class, new GuiAccessor());
		
		guiManager = new GUIManager();

		background = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGOUND_PLYAY_01, guiManager).setIndex(-2);
		backgroundOverlay = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGOUND_PLYAY_01_OVERLAY, guiManager).setIndex(-1);
		logo = new Gui(Main.WIDTH/2, 950, SpriteType.LOGO_01, guiManager);
		
		startButton = new Button(Main.WIDTH/2, 600, SpriteType.BUTTON_PLAY_IDLE, SpriteType.BUTTON_PLAY_PRESSED, hudCamera, guiManager);
		trophieButton = new Button(Main.WIDTH/2, 400, SpriteType.BUTTON_TROPHIES_IDLE, SpriteType.BUTTON_TROPHIES_PRESSED, hudCamera, guiManager);
		upgradeButton = new Button(Main.WIDTH/2, 200, SpriteType.BUTTON_UPGRADES_IDLE, SpriteType.BUTTON_UPGRADES_PRESSED, hudCamera, guiManager);
		
		guiManager.sortGuis();
		startAnimation();
	}
	
	private void startAnimation() {
		Timeline.createSequence()
		//set tweens
		.push(Tween.set(startButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(trophieButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(upgradeButton, AccessorReferences.SCALE).target(0,0))
		// wait half second before starting animation
		.pushPause(0.5f)
		// scale up and down all tweens after each other.
		.push(Tween.to(startButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(1.4f,1.8f))
		.push(Tween.to(startButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(1,1))
		
		.push(Tween.to(trophieButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(1.4f,1.8f))
		.push(Tween.to(trophieButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(1,1))
		
		.push(Tween.to(upgradeButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(1.4f,1.8f))
		.push(Tween.to(upgradeButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(1,1))
		.start(tweenManager);
	}
	
	private void endAnimation(final int scene){
		Timeline.createSequence()
		//set tweens
		.push(Tween.set(startButton, AccessorReferences.SCALE).target(1,1))
		.push(Tween.set(trophieButton, AccessorReferences.SCALE).target(1,1))
		.push(Tween.set(upgradeButton, AccessorReferences.SCALE).target(1,1))
		//start up scaling all buttons at the same time
		.beginParallel()
		.push(Tween.to(upgradeButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(1.5f,1.5f))
		.push(Tween.to(trophieButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(1.5f,1.5f))
		.push(Tween.to(startButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(1.5f,1.5f))
		.end()
		//start down scaling all buttons at the same time
		.beginParallel()
		.push(Tween.to(startButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(0,0))
		.push(Tween.to(trophieButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(0,0))
		.push(Tween.to(upgradeButton, AccessorReferences.SCALE, startTweeningAnimationTime).target(0,0)
				.setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						sceneManager.pushScene(scene); // load scene after this tween.
						
					}
				}))
		.end()
		.start(tweenManager);
	}

	@Override
	public void handleInput() {
		if(startButton.isClicked()){
			endAnimation(SceneManager.PLAY);
		}
	}

	@Override
	public void update(float deltaTime) {
		handleInput();
		tweenManager.update(deltaTime);
		guiManager.update(deltaTime);
	}

	@Override
	public void render() {
		spriteBatch.setProjectionMatrix(hudCamera.combined);
		spriteBatch.begin();
		guiManager.render(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void dispose() {
		
	}

}
