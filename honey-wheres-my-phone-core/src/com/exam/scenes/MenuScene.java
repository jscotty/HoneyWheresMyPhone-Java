package com.exam.scenes;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.exam.gui.GUIButton;
import com.exam.gui.GUITexture;
import com.exam.renderers.GUIRenderer;
import com.exam.toolbox.SpriteType;
import com.exam.tween.AccessorReferences;
import com.exam.tween.GUIButtonAccessor;
import com.exam.tween.GUITextureAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
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

    private void addButtonListeners() {
	_startButton.getButton().addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		endAnimation(new MainScene(pSceneManager));
	    }
	});
	_upgradeButton.getButton().addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		endAnimation(new UpgradeScene(pSceneManager));
	    }
	});
    }

    @Override
    public void show() {
	_stage = pSceneManager.getStage();
	_tweenManager = new TweenManager();
	Tween.registerAccessor(GUIButton.class, new GUIButtonAccessor());
	Tween.registerAccessor(GUITexture.class, new GUITextureAccessor());

	_uiRenderer = new GUIRenderer(_stage);

	_startButton = new GUIButton(SpriteType.BUTTON_PLAY_IDLE, SpriteType.BUTTON_PLAY_PRESSED, 50, 45);
	_upgradeButton = new GUIButton(SpriteType.BUTTON_UPGRADES_IDLE, SpriteType.BUTTON_UPGRADES_PRESSED, 50, 30);
	_trophiesButton = new GUIButton(SpriteType.BUTTON_TROPHIES_IDLE, SpriteType.BUTTON_TROPHIES_PRESSED, 50, 15);

	_logo = (GUITexture) new GUITexture(SpriteType.LOGO_01, 50, 75);
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

    @Override
    public void render(float delta) {
	_uiRenderer.render();
	_tweenManager.update(delta);
    }

    @Override
    public void resize(int width, int height) {
	
    }

    @Override
    public void pause() {
	
    }

    @Override
    public void resume() {
	
    }

    @Override
    public void hide() {
	
    }

    @Override
    public void dispose() {
	
    }

    
    //region animations
    private void startAnimation() {
	System.out.println("e");
	float time = 0.3f;
	Timeline.createSequence()
	.push(Tween.set(_startButton, AccessorReferences.SCALE).target(0))
	.push(Tween.set(_upgradeButton, AccessorReferences.SCALE).target(0))
	.push(Tween.set(_trophiesButton, AccessorReferences.SCALE).target(0))
	.pushPause(0.1f)
	.push(Tween.to(_startButton, AccessorReferences.SCALE, time).target(1f))
	.push(Tween.to(_upgradeButton, AccessorReferences.SCALE, time).target(1f))
	.push(Tween.to(_trophiesButton, AccessorReferences.SCALE, time).target(1f))
	.setCallback(new TweenCallback() {

	    @Override
	    public void onEvent(int arg0, BaseTween<?> arg1) {
		logoAnimation();
	    }
	})
	
	.start(_tweenManager);
    }
    
    private void logoAnimation(){
	float time = 0.1f;
	float rotate = 10.0f;
	Timeline.createSequence()
	.pushPause(1f)
	.push(Tween.set(_logo, AccessorReferences.ROTATE).target(0))
	.push(Tween.to(_logo, AccessorReferences.ROTATE, time).target(rotate))
	.push(Tween.to(_logo, AccessorReferences.ROTATE, time).target(-rotate))
	.push(Tween.to(_logo, AccessorReferences.ROTATE, time).target(0))
	.repeat(99999, 3f)
	.start(_tweenManager);
	Tween.set(_logo, AccessorReferences.ROTATE).target(0);
    }

    /**
     * end animation. This animation is called to load a new scene
     */
    private void endAnimation(final Scene scene) {
	float time = 0.2f;
	Timeline.createSequence()
	.push(Tween.set(_startButton, AccessorReferences.SCALE).target(1f))
	.push(Tween.set(_upgradeButton, AccessorReferences.SCALE).target(1f))
	.push(Tween.set(_trophiesButton, AccessorReferences.SCALE).target(1f))
	.pushPause(0.1f)
	.push(Tween.to(_startButton, AccessorReferences.SCALE, time).target(0f))
	.push(Tween.to(_upgradeButton, AccessorReferences.SCALE, time).target(0f))
	.push(Tween.to(_trophiesButton, AccessorReferences.SCALE, time).target(0f))
	.setCallback(new TweenCallback() {

	    @Override
	    public void onEvent(int arg0, BaseTween<?> arg1) {
		_tweenManager.killAll();
		SceneManager.loadScene(scene);
	    }
	})
	.start(_tweenManager);
    }
    //endregion

}
