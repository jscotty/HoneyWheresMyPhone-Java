package com.exam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.exam.gui.GUIButton;
import com.exam.gui.GUITexture;
import com.exam.renderers.GUIRenderer;
import com.exam.toolbox.SpriteType;

public class UpgradeScene extends Scene {

    private Stage _stage;
    private GUIRenderer _guiRenderer;
    private GUITexture _background;
    private GUITexture _backgroundOverlay;
    
    private GUIButton _backButton;
    
    public UpgradeScene(SceneManager sceneManager){
	super(sceneManager);
    }

    private void addButtonListeners() {
	_backButton.getButton().addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		SceneManager.loadScene(new MenuScene(pSceneManager));
	    }
	});
    }

    @Override
    public void show() {
	_stage = pSceneManager.getStage();
	_guiRenderer = new GUIRenderer(pSceneManager.getStage());
	_backButton = new GUIButton(SpriteType.BUTTON_REPLAY_IDLE, SpriteType.BUTTON_REPLAY_PRESSED, 10, 10);
	_background = (GUITexture) new GUITexture(SpriteType.BACKGOUND_PLYAY_01, 0, 0).setObjectOrigin(0, 0);
	_backgroundOverlay = (GUITexture) new GUITexture(SpriteType.BACKGOUND_PLYAY_01_OVERLAY, 0, 0).setObjectOrigin(0, 0);

	_guiRenderer.processGUITexture(_background);
	_guiRenderer.processGUITexture(_backgroundOverlay);
	_guiRenderer.processGUIButton(_backButton);
	
	addButtonListeners();
    }

    @Override
    public void render(float delta) {
	_guiRenderer.render();
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

    @Override
    public void dispose() {
	_guiRenderer.dispose();
    }

}
