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
import com.exam.project.Main;
import com.exam.renderers.GUIRenderer;
import com.exam.toolbox.SpriteType;

public class MenuScene extends Scene{
	
	private Stage stage;
	private GUIButton startButton;
	private GUIButton upgradeButton;
	private GUIRenderer uiRenderer;
	
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
		
		startButton = new GUIButton(SpriteType.BUTTON_PLAY_IDLE, SpriteType.BUTTON_PLAY_PRESSED, Main.WIDTH/2,Main.HEIGHT/2-50);
		startButton.centerButton();
		upgradeButton = new GUIButton(SpriteType.BUTTON_UPGRADES_IDLE, SpriteType.BUTTON_UPGRADES_PRESSED, Main.WIDTH/2,400);
		upgradeButton.centerButton();

		uiRenderer.processGUIButton(startButton);
		uiRenderer.processGUIButton(upgradeButton);
		
		addButtonListeners();
		
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(SpriteBatch batch) {
		uiRenderer.render(batch);
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
		
	}

}
