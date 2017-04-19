package com.exam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.exam.gui.GUIButton;
import com.exam.gui.GUITexture;
import com.exam.renderers.GUIRenderer;
import com.exam.toolbox.SpriteType;
import com.exam.tween.AccessorReferences;
import com.exam.tween.ButtonAccessor;
import com.exam.tween.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class MenuScene extends Scene{
	
	private Stage stage;
	private GUIButton startButton;
	private GUIButton upgradeButton;
	private GUIButton trophiesButton;
	private GUIButton quitButton;
	private GUITexture logo;
	private GUITexture background;
	private GUITexture backgroundOverlay;
	private GUIRenderer uiRenderer;
	
	private TweenManager tweenManager;
	
	/**
	 * Initialization
	 * @param sceneManager
	 */
	public MenuScene(SceneManager sceneManager) {
		super(sceneManager);
	}
	
	@Override
	public void init() {
		stage = new Stage(new ScreenViewport());
		tweenManager = new TweenManager();
		Tween.registerAccessor(GUIButton.class, new ButtonAccessor());
		
		uiRenderer = new GUIRenderer(stage);
		
		startButton = new GUIButton(SpriteType.BUTTON_PLAY_IDLE, SpriteType.BUTTON_PLAY_PRESSED, 50, 50);
		upgradeButton = new GUIButton(SpriteType.BUTTON_UPGRADES_IDLE, SpriteType.BUTTON_UPGRADES_PRESSED, 50, 35);
		trophiesButton = new GUIButton(SpriteType.BUTTON_TROPHIES_IDLE, SpriteType.BUTTON_TROPHIES_PRESSED, 50, 20);
		quitButton = (GUIButton) new GUIButton(SpriteType.BUTTON_QUIT_IDLE,  SpriteType.BUTTON_QUIT_PRESSED, 90, 90);

		logo = new GUITexture(SpriteType.BUTTON_PLAY_SMALL_IDLE, 50, 70);
		background = (GUITexture) new GUITexture(SpriteType.BACKGOUND_PLYAY_01, 0, 0).setObjectOrigin(0, 0);
		backgroundOverlay = (GUITexture) new GUITexture(SpriteType.BACKGOUND_PLYAY_01_OVERLAY, 0, 0).setObjectOrigin(0, 0);
		

		uiRenderer.processGUITexture(background);
		uiRenderer.processGUITexture(backgroundOverlay);
		uiRenderer.processGUIButton(startButton);
		uiRenderer.processGUIButton(upgradeButton);
		uiRenderer.processGUIButton(trophiesButton);
		uiRenderer.processGUITexture(logo);
		uiRenderer.processGUIButton(quitButton);
		
		startAnimation();
		addButtonListeners();
		
		Gdx.input.setInputProcessor(stage);
	}
	
	private void startAnimation(){
		float time = 0.3f;
		Tween.set(startButton, AccessorReferences.SCALE).target(0).start(tweenManager);
		Tween.to(startButton, AccessorReferences.SCALE, time).target(1.3f).start(tweenManager);
		Tween.to(startButton, AccessorReferences.SCALE, 0.1f).target(1f).delay(time).start(tweenManager);
		
		Tween.set(upgradeButton, AccessorReferences.SCALE).target(0).start(tweenManager);
		Tween.to(upgradeButton, AccessorReferences.SCALE, time).target(1.3f).delay(time).start(tweenManager);
		Tween.to(upgradeButton, AccessorReferences.SCALE, 0.1f).target(1f).delay(time*2).start(tweenManager);
		
		Tween.set(trophiesButton, AccessorReferences.SCALE).target(0).start(tweenManager);
		Tween.to(trophiesButton, AccessorReferences.SCALE, time).target(1.3f).delay(time*2).start(tweenManager);
		Tween.to(trophiesButton, AccessorReferences.SCALE, 0.1f).target(1f).delay(time*3).start(tweenManager);
	}
	
	private void endAnimation(){
		float time = 0.2f;
		Tween.set(startButton, AccessorReferences.SCALE).target(1).start(tweenManager);
		Tween.to(startButton, AccessorReferences.SCALE, 0.1f).target(1.3f).start(tweenManager);
		Tween.to(startButton, AccessorReferences.SCALE, time).target(0f).delay(time).start(tweenManager);
		
		Tween.set(upgradeButton, AccessorReferences.SCALE).target(1).start(tweenManager);
		Tween.to(upgradeButton, AccessorReferences.SCALE, 0.1f).target(1.3f).delay(time).start(tweenManager);
		Tween.to(upgradeButton, AccessorReferences.SCALE, time).target(0f).delay(time*2).start(tweenManager);
		
		Tween.set(trophiesButton, AccessorReferences.SCALE).target(1).start(tweenManager);
		Tween.to(trophiesButton, AccessorReferences.SCALE, 0.1f).target(1.3f).delay(time*2).start(tweenManager);
		Tween.to(trophiesButton, AccessorReferences.SCALE, time).target(-0.1f).delay(time*3).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				SceneManager.loadScene(new MainScene(sceneManager));			
			}
	    }).start(tweenManager);
	}

	@Override
	public void render(SpriteBatch batch) {
		uiRenderer.render(batch);
		tweenManager.update(Gdx.graphics.getDeltaTime());
		
	}
	
	private void addButtonListeners(){
		startButton.getButton().addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
				SceneManager.loadScene(new MainScene(sceneManager));
	        }
	    });
		quitButton.getButton().addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	Gdx.app.exit();
	        }
	    });
	}

	@Override
	public void dispose() {
		uiRenderer.dispose();
		stage.clear();
	}

}
