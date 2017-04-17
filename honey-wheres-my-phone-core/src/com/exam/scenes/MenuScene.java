package com.exam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.exam.gui.GUIButton;
import com.exam.gui.GUITexture;
import com.exam.project.Main;
import com.exam.renderers.GUIRenderer;
import com.exam.toolbox.SpriteType;

public class MenuScene extends Scene{
	
	private Stage stage;
	private GUIButton startButton;
	private GUIButton upgradeButton;
	private GUIButton trophiesButton;
	private GUITexture logo;
	private GUITexture background;
	private GUIRenderer uiRenderer;
	
	private float count = 0;
	
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
		uiRenderer = new GUIRenderer(stage);
		
		startButton = new GUIButton(SpriteType.BUTTON_PLAY_IDLE, SpriteType.BUTTON_PLAY_PRESSED, 50, 50);
		startButton.centerButton();
		upgradeButton = new GUIButton(SpriteType.BUTTON_UPGRADES_IDLE, SpriteType.BUTTON_UPGRADES_PRESSED, 50, 35);
		upgradeButton.centerButton();
		trophiesButton = new GUIButton(SpriteType.BUTTON_TROPHIES_IDLE, SpriteType.BUTTON_TROPHIES_PRESSED, 50, 20);
		trophiesButton.centerButton();

		logo = new GUITexture(SpriteType.BUTTON_PLAY_SMALL_IDLE, 50, 80);
		background = (GUITexture) new GUITexture(SpriteType.BACKGOUND_PLYAY_01.getSpriteName(), 0, 0).setObjectOrigin(0, 0);
		

		uiRenderer.processGUITexture(background);
		uiRenderer.processGUIButton(startButton);
		uiRenderer.processGUIButton(upgradeButton);
		uiRenderer.processGUIButton(trophiesButton);
		uiRenderer.processGUITexture(logo);
		
		addButtonListeners();
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(SpriteBatch batch) {
		uiRenderer.render(batch);
		background.getSprite().setV(count);
		count+=0.001f;
	}
	
	private void addButtonListeners(){
		startButton.getButton().addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	SceneManager.loadScene(new MainScene(sceneManager));
	        }
	    });
	}

	@Override
	public void dispose() {
		uiRenderer.dispose();
		stage.clear();
	}

}
