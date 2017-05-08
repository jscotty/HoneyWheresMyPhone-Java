package com.exam.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.project.Main;

public abstract class Scene {

	protected SceneManager sceneManager;
	protected Main main;

	protected SpriteBatch spriteBatch;
	protected OrthographicCamera camera;
	protected OrthographicCamera hudCamera;

	/**
	 * Constructor to initialize all variables
	 * @param sceneManager
	 */
	protected Scene(SceneManager manager) {
		this.sceneManager = manager;
		main = manager.getMain();
		spriteBatch = main.getBatch();
		camera = main.getCamera();
		hudCamera = main.getHudCamera();
	}

	/**
	 * Handle input in this method. This is most likely called in the first line of the update method.
	 */
	public abstract void handleInput();

	/**
	 * This method is called every frame.
	 * @param deltaTime time between every frame.
	 */
	public abstract void update(float deltaTime);

	/**
	 * Render in this method.
	 */
	public abstract void render();

	/**
	 * The dispose method get called when the application quits.
	 */
	public abstract void dispose();

}
