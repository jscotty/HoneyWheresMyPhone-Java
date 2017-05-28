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

public class EndPanel extends Panel {

private GUIManager guiManager;
	
	private Gui background;
	private Gui gamePausedText;
	private GuiButton resumeButton;
	private GuiButton menuButton;
	private GuiButton upgradeButton;
	private GuiButton trophiesButton;
	private float animationTime = 0.2f;
	
	private UpgradePanel _upgradePanel;
	
	private boolean isActive = false;
	private int scene = 0;
	private SceneManager sceneManager;

	public EndPanel(OrthographicCamera camera, SceneManager sceneManager) {
		super();
		this.sceneManager = sceneManager;
		Tween.registerAccessor(Gui.class, new GuiAccessor());
		guiManager = new GUIManager();
		background = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGROUND_FADE_02, guiManager).setIndex(10);
		gamePausedText = new Gui(Main.WIDTH/2, Main.HEIGHT/2+background.getHeight()/2, SpriteType.GAME_ENDED_TEXT, guiManager).setIndex(10);

		resumeButton = (GuiButton) new GuiButton(Main.WIDTH/2+ 200, Main.HEIGHT/ 2 - background.getHeight()/2 + 20, SpriteType.BUTTON_REPLAY_IDLE, SpriteType.BUTTON_REPLAY_PRESSED, camera, guiManager).setIndex(11);
		upgradeButton = (GuiButton) new GuiButton(Main.WIDTH/2, Main.HEIGHT/2 - background.getHeight()/2, SpriteType.BUTTON_UPGRADES_IDLE_SMALL, SpriteType.BUTTON_UPGRADES_PRESSED_SMALL, camera, guiManager).setIndex(11);
		menuButton = (GuiButton) new GuiButton(Main.WIDTH/2-200, Main.HEIGHT/2 - background.getHeight()/2 + 20, SpriteType.BUTTON_HOME_IDLE, SpriteType.BUTTON_HOME_PRESSED, camera, guiManager).setIndex(11);
		trophiesButton = (GuiButton) new GuiButton(600, 1200, SpriteType.BUTTON_TROPHIES_IDLE_SMALL, SpriteType.BUTTON_TROPHIES_PRESSED_SMALL, camera, guiManager).setIndex(11);

		_upgradePanel = new UpgradePanel(camera, sceneManager);
		
		Timeline.createSequence()
		//set tweens
		.push(Tween.set(background, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(gamePausedText, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(resumeButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(menuButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(upgradeButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(trophiesButton, AccessorReferences.SCALE).target(0,0))
		.start(tweenManager);
	}

	@Override
	public void startAnimation() {
		isActive = true;
		Timeline.createSequence()
		.pushPause(0.1f)
		
		.beginParallel()
		.push(Tween.to(background, AccessorReferences.SCALE,animationTime).target(1,1))
		.end()
		
		.beginParallel()
		.push(Tween.to(gamePausedText, AccessorReferences.SCALE,animationTime).target(1,1))
		.push(Tween.to(resumeButton, AccessorReferences.SCALE,animationTime).target(1,1))
		.push(Tween.to(menuButton, AccessorReferences.SCALE,animationTime).target(1,1))
		.push(Tween.to(upgradeButton, AccessorReferences.SCALE,animationTime).target(1,1))
		.push(Tween.to(trophiesButton, AccessorReferences.SCALE,animationTime).target(1,1))
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
		.push(Tween.to(upgradeButton, AccessorReferences.SCALE,animationTime).target(0,0))
		.push(Tween.to(menuButton, AccessorReferences.SCALE,animationTime).target(0,0))
		.push(Tween.to(trophiesButton, AccessorReferences.SCALE,animationTime).target(0,0))
		.end()
		
		.beginParallel()
		.push(Tween.to(background, AccessorReferences.SCALE,animationTime).target(0,0).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				isActive = false;
				GameManager.isPaused = false;
				sceneManager.setScene(scene);
			}
		}))
		.end()
		
		.start(tweenManager);
	}
	
	private void handleInput(){
		if(menuButton.isClicked()){
			GameManager.reset();
			scene = 0;
			endAnimation();
		}
		if(resumeButton.isClicked()) {
			GameManager.reset();
			scene = 1;
			endAnimation();
		}
		if(upgradeButton.isClicked()) {
			_upgradePanel.startAnimation();
		}
	}

	@Override
	public void update(float deltaTime) {
		if(!isActive) return;
		if(!_upgradePanel.isActive())
			handleInput();
		tweenManager.update(deltaTime);
		guiManager.update(deltaTime);
		_upgradePanel.update(deltaTime);
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		if(!isActive) return;
		spriteBatch.begin();
		guiManager.render(spriteBatch);
		spriteBatch.end();
		_upgradePanel.render(spriteBatch);
	}

	@Override
	public void dispose() {

	}
	
	public boolean isActive() {
		return isActive;
	}

}
