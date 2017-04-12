package com.exam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.exam.entity.Hook;
import com.exam.project.Main;
import com.exam.renderers.EntityRenderer;

public class MainScene extends Scene {

	private Stage stage;
	private Hook hook;
	private EntityRenderer entityRenderer;
	
	/**
	 * Initialization
	 * @param sceneManager
	 */
	public MainScene(SceneManager sceneManager) {
		super(sceneManager);
	}

	@Override
	public void init() {
		hook = new Hook("hand.png", Main.WIDTH/2, Main.HEIGHT-200);

		entityRenderer = new EntityRenderer();
		entityRenderer.Init();
		entityRenderer.processEntity(hook);
		

		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void render(SpriteBatch batch) {
		entityRenderer.render(batch);
	}

	@Override
	public void dispose() {
		
	}
	
}
