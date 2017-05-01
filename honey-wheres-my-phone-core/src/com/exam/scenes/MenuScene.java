package com.exam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.exam.gui.GUIButton;
import com.exam.gui.GUITexture;
import com.exam.renderers.GUIRenderer;
import com.exam.toolbox.SpriteType;
import com.exam.tween.AccessorReferences;
import com.exam.tween.GUIButtonAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class MenuScene extends Scene {

    private Stage _stage;
    private GUIButton _startButton;
    private GUIButton _upgradeButton;
    private GUIButton _trophiesButton;
    private GUITexture _logo;
    private GUITexture _background;
    private GUITexture _backgroundOverlay;
    private GUIRenderer _uiRenderer;

    private TweenManager _tweenManager;

    /**
     * Initialization
     * @param sceneManager
     */
    public MenuScene(SceneManager sceneManager) {
	super(sceneManager);
    }

    @Override
    public void init() {
	_stage = pSceneManager.getStage();
	_tweenManager = new TweenManager();
	Tween.registerAccessor(GUIButton.class, new GUIButtonAccessor());

	_uiRenderer = new GUIRenderer(_stage);

	_startButton = new GUIButton(SpriteType.BUTTON_PLAY_IDLE, SpriteType.BUTTON_PLAY_PRESSED, 50, 45);
	_upgradeButton = new GUIButton(SpriteType.BUTTON_UPGRADES_IDLE, SpriteType.BUTTON_UPGRADES_PRESSED, 50, 30);
	_trophiesButton = new GUIButton(SpriteType.BUTTON_TROPHIES_IDLE, SpriteType.BUTTON_TROPHIES_PRESSED, 50, 15);

	_logo = new GUITexture(SpriteType.LOGO_01, 50, 75);
	_background = (GUITexture) new GUITexture(SpriteType.BACKGOUND_PLYAY_01, 0, 0).setObjectOrigin(0, 0);
	_backgroundOverlay = (GUITexture) new GUITexture(SpriteType.BACKGOUND_PLYAY_01_OVERLAY, 0, 0).setObjectOrigin(0, 0);

	_uiRenderer.processGUITexture(_background);
	_uiRenderer.processGUITexture(_backgroundOverlay);
	_uiRenderer.processGUIButton(_startButton);
	_uiRenderer.processGUIButton(_upgradeButton);
	_uiRenderer.processGUIButton(_trophiesButton);
	_uiRenderer.processGUITexture(_logo);

	startAnimation();
	addButtonListeners();
    }

    private void startAnimation() {
	System.out.println("e");
	float time = 0.3f;
	Tween.set(_startButton, AccessorReferences.SCALE).target(0).start(_tweenManager);
	Tween.to(_startButton, AccessorReferences.SCALE, 0.1f).target(1f).delay(time).start(_tweenManager);

	Tween.set(_upgradeButton, AccessorReferences.SCALE).target(0).start(_tweenManager);
	Tween.to(_upgradeButton, AccessorReferences.SCALE, 0.1f).target(1f).delay(time * 2).start(_tweenManager);

	Tween.set(_trophiesButton, AccessorReferences.SCALE).target(0).start(_tweenManager);
	Tween.to(_trophiesButton, AccessorReferences.SCALE, 0.1f).target(1f).delay(time * 3).start(_tweenManager);
    }

    private void endAnimation() {
	float time = 0.2f;
	Tween.set(_startButton, AccessorReferences.SCALE).target(1).start(_tweenManager);
	Tween.to(_startButton, AccessorReferences.SCALE, time).target(0f).delay(time).start(_tweenManager);

	Tween.set(_upgradeButton, AccessorReferences.SCALE).target(1).start(_tweenManager);
	Tween.to(_upgradeButton, AccessorReferences.SCALE, time).target(0f).delay(time * 2).start(_tweenManager);

	Tween.set(_trophiesButton, AccessorReferences.SCALE).target(1).start(_tweenManager);
	Tween.to(_trophiesButton, AccessorReferences.SCALE, time).target(0).delay(time * 3).setCallback(new TweenCallback() {

	    @Override
	    public void onEvent(int arg0, BaseTween<?> arg1) {
		SceneManager.loadScene(new MainScene(pSceneManager));
	    }
	}).start(_tweenManager);
    }

    private void addButtonListeners() {
	_startButton.getButton().addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		endAnimation();
	    }
	});
	_upgradeButton.getButton().addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		SceneManager.loadScene(new UpgradeScene(pSceneManager));
	    }
	});
    }

    @Override
    public void render(SpriteBatch batch) {
	_uiRenderer.render(batch);
	_tweenManager.update(Gdx.graphics.getDeltaTime());

    }

    @Override
    public void dispose() {
	_uiRenderer.dispose();
	_stage.clear();
    }

    @Override
    public void show() {
	System.out.println("show");
    }

    @Override
    public void render(float delta) {
	System.out.println("render");
    }

    @Override
    public void resize(int width, int height) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void pause() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void resume() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void hide() {
	// TODO Auto-generated method stub
	
    }

}
