package com.exam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.exam.entity.Background;
import com.exam.entity.Hook;
import com.exam.gui.GUIButton;
import com.exam.managers.ItemManager;
import com.exam.renderers.EntityRenderer;
import com.exam.renderers.GUIRenderer;
import com.exam.toolbox.SpriteType;

public class MainScene extends Scene {

	private Stage stage;
	private Hook hook;
	private EntityRenderer entityRenderer;
	private GUIRenderer guiRenderer;
	private ItemManager itemManager;
	private GUIButton quitButton;
	private Background background;
	
	/**
	 * Initialization
	 * @param sceneManager
	 */
	public MainScene(SceneManager sceneManager) {
		super(sceneManager);
		
	}

	@Override
	public void init() {
		stage = new Stage(new ScreenViewport());
		hook = new Hook(SpriteType.PROPS_ARM,50, 80);

		entityRenderer = new EntityRenderer(stage);
		guiRenderer = new GUIRenderer(stage);
		background = (Background) new Background(SpriteType.BACKGOUND_PLYAY_01.getSpriteName(), 0, 0).setObjectOrigin(0, 0);
		entityRenderer.Init();
		guiRenderer.Init();
		quitButton = new GUIButton(SpriteType.BUTTON_QUIT_IDLE,  SpriteType.BUTTON_QUIT_PRESSED, 90, 80);

		quitButton.centerButton();

		entityRenderer.processEntity(background);
		entityRenderer.processEntity(hook);

		guiRenderer.processGUIButton(quitButton);

		addButtonListeners();
		
		Gdx.input.setInputProcessor(stage);
		
	}
	
	private void addButtonListeners(){
		quitButton.getButton().addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	Gdx.app.exit();
	        }
	    });
	}

	@Override
	public void render(SpriteBatch batch) {
		entityRenderer.render(batch);
		guiRenderer.render(batch);
		background.scroll(1);
	}

	@Override
	public void dispose() {
		entityRenderer.dispose();
		stage.clear();
	}
	
}
