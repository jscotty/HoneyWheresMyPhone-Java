package com.exam.panels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.gui.GUIManager;
import com.exam.gui.Gui;
import com.exam.gui.GuiButton;
import com.exam.managers.GameManager;
import com.exam.project.Main;
import com.exam.scenes.SceneManager;
import com.exam.toolbox.SpriteType;
import com.exam.tween.AccessorReferences;
import com.exam.tween.GuiAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

public class PausePanel extends Panel {
	
	private GUIManager guiManager;
	
	private Gui background;
	private Gui gamePausedText;
	private GuiButton resumeButton;
	private GuiButton menuButton;
	private GuiButton muteButton;
	private float animationTime = 0.2f;
	
	private boolean isActive = false;
	private SceneManager sceneManager;

	public PausePanel(OrthographicCamera camera, SceneManager sceneManager) {
		super();
		this.sceneManager = sceneManager;
		Tween.registerAccessor(Gui.class, new GuiAccessor());
		guiManager = new GUIManager();
		background = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGROUND_FADE_02, guiManager);
		gamePausedText = new Gui(Main.WIDTH/2, Main.HEIGHT/2+background.getHeight()/2, SpriteType.GAME_PAUSED_TEXT, guiManager);

		resumeButton = new GuiButton(Main.WIDTH/2, Main.HEIGHT/ 2 + 90, SpriteType.BUTTON_RESUME_IDLE, SpriteType.BUTTON_RESUME_PRESSED, camera, guiManager);
		menuButton = new GuiButton(Main.WIDTH/2, Main.HEIGHT/2 - 90, SpriteType.BUTTON_MENU_IDLE, SpriteType.BUTTON_MENU_PRESSED, camera, guiManager);
		muteButton = new GuiButton(600, 1200, SpriteType.BUTTON_AUDIO_ON_IDLE, SpriteType.BUTTON_AUDIO_ON_PRESSED, camera, guiManager);
	}

	@Override
	public void startAnimation() {
		isActive = true;
		GameManager.isPaused = true;
		Timeline.createSequence()
		//set tweens
		.push(Tween.set(background, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(gamePausedText, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(resumeButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(menuButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(muteButton, AccessorReferences.SCALE).target(0,0))
		
		.pushPause(0.1f)
		
		.beginParallel()
		.push(Tween.to(background, AccessorReferences.SCALE,animationTime).target(1,1))
		.end()
		
		.beginParallel()
		.push(Tween.to(gamePausedText, AccessorReferences.SCALE,animationTime).target(1,1))
		.push(Tween.to(resumeButton, AccessorReferences.SCALE,animationTime).target(1,1))
		.push(Tween.to(menuButton, AccessorReferences.SCALE,animationTime).target(1,1))
		.push(Tween.to(muteButton, AccessorReferences.SCALE,animationTime).target(1,1))
		.end()
		.start(tweenManager);
	}

	@Override
	public void endAnimation() {
		Timeline.createSequence()
		.pushPause(0.1f)
		.beginParallel()
		.push(Tween.to(gamePausedText, AccessorReferences.SCALE,animationTime).target(0,0))
		.push(Tween.to(resumeButton, AccessorReferences.SCALE,animationTime).target(0,0))
		.push(Tween.to(menuButton, AccessorReferences.SCALE,animationTime).target(0,0))
		.push(Tween.to(muteButton, AccessorReferences.SCALE,animationTime).target(0,0))
		.end()
		
		.beginParallel()
		.push(Tween.to(background, AccessorReferences.SCALE,animationTime).target(0,0).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				isActive = false;
				GameManager.isPaused = false;
			}
		}))
		.end()
		
		.start(tweenManager);
	}
	
	private void handleInput(){
		if(menuButton.isClicked()){
			isActive = false;
			GameManager.isPaused = false;
			sceneManager.setScene(SceneManager.MENU);
		}
		if(resumeButton.isClicked()) endAnimation();
	}

	@Override
	public void update(float deltaTime) {
		if(!isActive) return;
		handleInput();
		tweenManager.update(deltaTime);
		guiManager.update(deltaTime);
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		if(!isActive) return;
		spriteBatch.begin();
		guiManager.render(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void dispose() {

	}

}
