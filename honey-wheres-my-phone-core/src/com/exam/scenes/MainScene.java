package com.exam.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.renderers.EntityRenderer;

public class MainScene extends Scene {

	private Texture texture;
	private float counter;
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
		texture = new Texture("badlogic.jpg");
		
		entityRenderer = new EntityRenderer();
		
	}

	@Override
	public void render(SpriteBatch batch) {
		float cos = (float)Math.cos(counter)*100;
		entityRenderer.render(batch);
		batch.draw(texture,cos,0);
		counter += 0.1f;
	}

	@Override
	public void dispose() {
		
	}
	
}
