package com.exam.scenes;

import com.exam.gui.GuiButton;
import com.exam.assets.AudioType;
import com.exam.assets.SpriteType;
import com.exam.gui.GuiManager;
import com.exam.gui.GuiToggleButton;
import com.exam.managers.SoundManager;
import com.exam.gui.Gui;
import com.exam.panels.UpgradePanel;
import com.exam.project.Main;
import com.exam.tween.AccessorReferences;
import com.exam.tween.GuiAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class MainMenuScene extends Scene{
	
	private GuiManager _guiManager;
	
	// not sure why I got this warning.. because I register them inside the given guiManager in the constructor which does all the magic...
	private Gui _background;
	private Gui _logo;
	
	private GuiButton _startButton;
	private GuiButton _trophieButton;
	private GuiButton _upgradeButton;

	private GuiToggleButton _muteButton;
	
	private TweenManager _tweenManager;
	private float _startTweeningAnimationTime = 0.2f;
	
	private UpgradePanel _upgradePanel;
	

	/**
	 * Constructor for initialization
	 * @param manager
	 */
	protected MainMenuScene(SceneManager manager) {
		super(manager);
		_tweenManager = new TweenManager();
		Tween.registerAccessor(Gui.class, new GuiAccessor());
		
		_guiManager = new GuiManager();

		_background = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGROUND, _guiManager).setIndex(-2);
		_logo = new Gui(Main.WIDTH/2, 950, SpriteType.LOGO_01, _guiManager);
		
		_startButton = new GuiButton(Main.WIDTH/2, 600, SpriteType.BUTTON_PLAY_IDLE, SpriteType.BUTTON_PLAY_PRESSED, pHudCamera, _guiManager);
		_upgradeButton = new GuiButton(Main.WIDTH/2, 400, SpriteType.BUTTON_UPGRADES_IDLE, SpriteType.BUTTON_UPGRADES_PRESSED, pHudCamera, _guiManager);
		_trophieButton = new GuiButton(Main.WIDTH/2, 200, SpriteType.BUTTON_TROPHIES_IDLE, SpriteType.BUTTON_TROPHIES_PRESSED, pHudCamera, _guiManager);
		_muteButton = new GuiToggleButton(600, 1200, SpriteType.BUTTON_AUDIO_ON_IDLE, SpriteType.BUTTON_AUDIO_ON_PRESSED,SpriteType.BUTTON_AUDIO_OFF_IDLE, SpriteType.BUTTON_AUDIO_OFF_PRESSED,
			pCamera, _guiManager);
		if(SoundManager.isMuted)
			_muteButton.toggle();
		
		
		_guiManager.sortGuis();
		
		_upgradePanel = new UpgradePanel(pHudCamera, pSceneManager);
		
		SoundManager.playAudio(AudioType.MAIN_MENU);
		startAnimation();
	}
	
	/**
	 * Start animation 
	 */
	private void startAnimation() {
		Timeline.createSequence()
		//set tweens
		.push(Tween.set(_startButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(_trophieButton, AccessorReferences.SCALE).target(0,0))
		.push(Tween.set(_upgradeButton, AccessorReferences.SCALE).target(0,0))
		// wait half second before starting animation
		.pushPause(0.5f)
		// scale up and down all tweens after each other.
		.push(Tween.to(_startButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1.4f,1.8f).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				SoundManager.playAudio(AudioType.BUTTON_CLICK); // play sound if button has showed up
				
			}
		}))
		.push(Tween.to(_startButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1,1))
		
		.push(Tween.to(_upgradeButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1.4f,1.8f).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				SoundManager.playAudio(AudioType.BUTTON_CLICK); // play sound if button has showed up
				
			}
		}))
		.push(Tween.to(_upgradeButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1,1))
		
		.push(Tween.to(_trophieButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1.4f,1.8f).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				SoundManager.playAudio(AudioType.BUTTON_CLICK); // play sound if button has showed up
				
			}
		}))
		.push(Tween.to(_trophieButton, AccessorReferences.SCALE, _startTweeningAnimationTime).target(1,1))
		.start(_tweenManager);
	}
	
	/**
	 * End animation
	 * @param scene to go to at the end of the animation.
	 */
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
						SoundManager.stopAudio(AudioType.MAIN_MENU);
						pSceneManager.setScene(scene); // load scene after this tween.
						
					}
				}))
		.end()
		.start(_tweenManager);
	}

	@Override
	public void handleInput() {
		if(_upgradePanel.isActive()) return;
		if(_startButton.isClicked()){
			endAnimation(SceneManager.PLAY);
		}
		
		if(_upgradeButton.isClicked())
			_upgradePanel.startAnimation();
		
		if(_muteButton.isClicked()){
			SoundManager.muteToggle();
		}
	}

	@Override
	public void update(float deltaTime) {
		handleInput();
		_upgradePanel.update(deltaTime);
		_tweenManager.update(deltaTime);
		_guiManager.update(deltaTime);
	}

	@Override
	public void render() {
		pSpriteBatch.setProjectionMatrix(pHudCamera.combined);
		pSpriteBatch.begin();
		_guiManager.render(pSpriteBatch);
		pSpriteBatch.end();
		_upgradePanel.render(pSpriteBatch);
	}

	@Override
	public void dispose() {
		
	}

}
