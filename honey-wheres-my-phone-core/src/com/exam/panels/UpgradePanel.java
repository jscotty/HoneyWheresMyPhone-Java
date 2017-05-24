package com.exam.panels;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.font.FontType;
import com.exam.gui.GUIManager;
import com.exam.gui.Gui;
import com.exam.gui.GuiButton;
import com.exam.gui.GuiText;
import com.exam.gui.UpgradeProcess;
import com.exam.managers.GameManager;
import com.exam.project.Main;
import com.exam.scenes.SceneManager;
import com.exam.toolbox.SpriteType;
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

public class UpgradePanel extends Panel {
	
	private final float OFFSET = 300;

	private final int UPGRADE_DEPTH_INCREASE = 0;
	private final int UPGRADE_BOOST = 1;
	private final int UPGRADE_MONEY_INCREASE = 2;
	private GUIManager guiManager;

	private Gui background;
	private Gui backgroundOverlay;
	private GuiText cashText;
	
	// upgrade visuals
	private Gui[] fadeBackgrounds;
	private Gui[] icons;
	private GuiText[] valueTexts;
	private GuiButton[] buyButtons;
	private UpgradeProcess[] processes;

	private GuiButton continueButton;
	private GuiButton closeButton;
	
	private float startAnimationTime = 0.3f;
	
	private boolean isActive = false;
	private SceneManager sceneManager;
	
	public UpgradePanel(OrthographicCamera camera, SceneManager sceneManager) {
		super();
		this.sceneManager = sceneManager;
		guiManager = new GUIManager();
		Tween.registerAccessor(Gui.class, new GuiAccessor());
		Tween.registerAccessor(UpgradeProcess.class, new UpgradeProcessAccesor());
		Tween.registerAccessor(GuiText.class, new GuiTextAccessor());

		background = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGOUND_PLYAY_01, guiManager);
		backgroundOverlay = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGOUND_PLYAY_01_OVERLAY, guiManager);
		cashText = new GuiText(400, 1200, guiManager, FontType.SUPERCELL_MAGIC);
		cashText.setText("$ Moneyz");
		
		fadeBackgrounds = new Gui[]{
			new Gui(Main.WIDTH/2, (Main.HEIGHT/2)+ OFFSET, SpriteType.BACKGROUND_FADE_01, guiManager),
			new Gui(Main.WIDTH/2, Main.HEIGHT/2.0f, SpriteType.BACKGROUND_FADE_01, guiManager),
			new Gui(Main.WIDTH/2, (Main.HEIGHT/2)- OFFSET, SpriteType.BACKGROUND_FADE_01, guiManager)
		};
		
		icons = new Gui[]{
			new Gui(140, (Main.HEIGHT/2)+ OFFSET, SpriteType.UPGRADE_ARM_LENGTH, guiManager),
			new Gui(140, Main.HEIGHT/2.0f, SpriteType.UPGRADE_STARTDEPTH_INCREASER, guiManager),
			new Gui(140, (Main.HEIGHT/2)- OFFSET, SpriteType.UPGRADE_ITEMVALUE_INCREASER, guiManager)
		};
		
		valueTexts = new GuiText[]{
				new GuiText(275, (Main.HEIGHT/2)+ OFFSET-10, guiManager, FontType.SUPERCELL_MAGIC, "100$"),
				new GuiText(275, (Main.HEIGHT/2)-10, guiManager, FontType.SUPERCELL_MAGIC, "100$"),
				new GuiText(275, (Main.HEIGHT/2)- OFFSET-10, guiManager, FontType.SUPERCELL_MAGIC, "100$")
		};
		
		buyButtons = new GuiButton[]{
				new GuiButton(600, (Main.HEIGHT/2)+OFFSET-50, SpriteType.BUTTON_BUY_IDLE, SpriteType.BUTTON_BUY_PRESSED, camera, guiManager),
				new GuiButton(600, (Main.HEIGHT/2)-50, SpriteType.BUTTON_BUY_IDLE, SpriteType.BUTTON_BUY_PRESSED, camera, guiManager),
				new GuiButton(600, (Main.HEIGHT/2)-OFFSET-50, SpriteType.BUTTON_BUY_IDLE, SpriteType.BUTTON_BUY_PRESSED, camera, guiManager),
		};
		
		processes = new UpgradeProcess[]{
			new UpgradeProcess(guiManager, Main.WIDTH/2.5f, (Main.HEIGHT/2)+ OFFSET+ 60, "Increase depth", 0),
			new UpgradeProcess(guiManager, Main.WIDTH/2.5f, (Main.HEIGHT/2)+ 60, "Increase head start", 1),
			new UpgradeProcess(guiManager, Main.WIDTH/2.5f, (Main.HEIGHT/2)- OFFSET + 60, "Increase item value", 2),
		};

		continueButton = new GuiButton(600, 100, SpriteType.BUTTON_PLAY_SMALL_IDLE, SpriteType.BUTTON_PLAY_SMALL_PRESSED, camera, guiManager);
		closeButton = new GuiButton(100, 1200, SpriteType.BUTTON_QUIT_IDLE, SpriteType.BUTTON_QUIT_PRESSED, camera, guiManager);

		//set tweens
		Timeline.createSequence()
		.beginParallel()
		.push(Tween.set(background, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(backgroundOverlay, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(fadeBackgrounds[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(fadeBackgrounds[UPGRADE_BOOST], AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(fadeBackgrounds[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE).target(0,0))
		
		.push(Tween.set(icons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(icons[UPGRADE_BOOST], AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(icons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE).target(0,0))
		
		.push(Tween.set(processes[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(processes[UPGRADE_BOOST], AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(processes[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE).target(0,0))
		
		.push(Tween.set(valueTexts[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(valueTexts[UPGRADE_BOOST], AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(valueTexts[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE).target(0,0))
		
		.push(Tween.set(buyButtons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(buyButtons[UPGRADE_BOOST], AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(buyButtons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE).target(0,0))
		
		.push(Tween.set(continueButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(closeButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(cashText, AccessorReferences.SCALE).target(0,0))
		.end()
		.start(tweenManager);
	}


	@Override
	public void startAnimation() {
		isActive = true;
		Timeline.createSequence()
		.pushPause(0.1f)
		
		.beginParallel()
		.push(Tween.to(background, AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(backgroundOverlay, AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.end()
		
		.beginParallel()
		.push(Tween.to(fadeBackgrounds[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(fadeBackgrounds[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(fadeBackgrounds[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		
		.push(Tween.to(icons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(icons[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(icons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		
		.push(Tween.to(processes[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(processes[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(processes[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		
		.push(Tween.to(valueTexts[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(valueTexts[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(valueTexts[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		
		.push(Tween.to(buyButtons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(buyButtons[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(buyButtons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(1,1))

		.push(Tween.to(continueButton, AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(closeButton, AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.push(Tween.to(cashText, AccessorReferences.SCALE,startAnimationTime).target(1,1))
		.end()
		.start(tweenManager);
	}

	@Override
	public void endAnimation() {
		Timeline.createSequence()
		
		.pushPause(0.1f)
		
		.beginParallel()
		.push(Tween.to(fadeBackgrounds[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(fadeBackgrounds[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(fadeBackgrounds[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		
		.push(Tween.to(icons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(icons[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(icons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		
		.push(Tween.to(processes[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(processes[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(processes[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		
		.push(Tween.to(valueTexts[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(valueTexts[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(valueTexts[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		
		.push(Tween.to(buyButtons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(buyButtons[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(buyButtons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))

		.push(Tween.to(continueButton, AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(closeButton, AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(cashText, AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.end()
		
		.beginParallel()
		.push(Tween.to(background, AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(backgroundOverlay, AccessorReferences.SCALE,startAnimationTime).target(0,0).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				isActive = false;
				
			}
		}))
		.end()
		.start(tweenManager);
	}
	
	private void AnimateToGame(){
		Timeline.createSequence()
		
		.pushPause(0.1f)
		
		.beginParallel()
		.push(Tween.to(fadeBackgrounds[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(fadeBackgrounds[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(fadeBackgrounds[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		
		.push(Tween.to(icons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(icons[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(icons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		
		.push(Tween.to(processes[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(processes[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(processes[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		
		.push(Tween.to(valueTexts[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(valueTexts[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(valueTexts[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		
		.push(Tween.to(buyButtons[UPGRADE_DEPTH_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(buyButtons[UPGRADE_BOOST], AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(buyButtons[UPGRADE_MONEY_INCREASE], AccessorReferences.SCALE,startAnimationTime).target(0,0))

		.push(Tween.to(continueButton, AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(closeButton, AccessorReferences.SCALE,startAnimationTime).target(0,0))
		.push(Tween.to(cashText, AccessorReferences.SCALE,startAnimationTime).target(0,0).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				sceneManager.setScene(SceneManager.PLAY);
			}
		}))
		.end()
		.start(tweenManager);
	}

	@Override
	public void update(float deltaTime) {
		if(!isActive) return;
		tweenManager.update(deltaTime);
		guiManager.update(deltaTime);
		
		if(closeButton.isClicked()) endAnimation();
		if(continueButton.isClicked()) {
			GameManager.reset();
			AnimateToGame();
		}
		
		if(buyButtons[UPGRADE_DEPTH_INCREASE].isClicked()){
			processes[UPGRADE_DEPTH_INCREASE].upgrade();
		}
		if(buyButtons[UPGRADE_BOOST].isClicked()){
			processes[UPGRADE_BOOST].upgrade();
		}
		if(buyButtons[UPGRADE_MONEY_INCREASE].isClicked()){
			processes[UPGRADE_MONEY_INCREASE].upgrade();
		}
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
	
	public boolean isActive() {
		return isActive;
	}

}
