package com.exam.scenes;

import com.exam.gui.Button;
import com.exam.gui.Gui;
import com.exam.handlers.GUIManager;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;

public class MainMenuScene extends Scene{
	
	private GUIManager guiManager;
	
	// not sure why I got this warning.. because I register them inside the given guiManager in the constructor.
	
	private Gui background;
	private Gui backgroundOverlay;
	private Gui logo;
	
	private Button startButton;
	private Button trophieButton;
	private Button upgradeButton;

	protected MainMenuScene(SceneManager manager) {
		super(manager);
		
		guiManager = new GUIManager();

		background = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGOUND_PLYAY_01, guiManager);
		backgroundOverlay = new Gui(Main.WIDTH/2, Main.HEIGHT/2, SpriteType.BACKGOUND_PLYAY_01_OVERLAY, guiManager);
		logo = new Gui(Main.WIDTH/2, 1000, SpriteType.LOGO_01, guiManager);
		
		startButton = new Button(Main.WIDTH/2, 600, SpriteType.BUTTON_PLAY_IDLE, SpriteType.BUTTON_PLAY_PRESSED, hudCamera, guiManager);
		trophieButton = new Button(Main.WIDTH/2, 400, SpriteType.BUTTON_TROPHIES_IDLE, SpriteType.BUTTON_TROPHIES_PRESSED, hudCamera, guiManager);
		upgradeButton = new Button(Main.WIDTH/2, 200, SpriteType.BUTTON_UPGRADES_IDLE, SpriteType.BUTTON_UPGRADES_PRESSED, hudCamera, guiManager);
	}

	@Override
	public void handleInput() {
		if(startButton.isClicked()){
			sceneManager.pushScene(SceneManager.PLAY);
		}
	}

	@Override
	public void update(float deltaTime) {
		handleInput();
		guiManager.update(deltaTime);
	}

	@Override
	public void render() {
		spriteBatch.setProjectionMatrix(hudCamera.combined);
		spriteBatch.begin();
		guiManager.render(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void dispose() {
		
	}

}
