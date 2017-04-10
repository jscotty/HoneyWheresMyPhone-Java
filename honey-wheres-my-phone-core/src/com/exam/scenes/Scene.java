package com.exam.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Scene {
	
	protected SceneManager sceneManager; // all scenes can acces scenemanager.

	/**
	 * Empty constructor.
	 */
	public Scene(){ }
	
	/**
	 * Constructor with sceneManager definition
	 * @param sceneManager
	 */
	public Scene(SceneManager sceneManager){
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Add sceneManager to define sceneManager after initialization.
	 * @param sceneManager
	 */
	public void AddSceneManager(SceneManager sceneManager){
		this.sceneManager = sceneManager;
	}
	
	/**
	 * Initialize scene
	 */
	public abstract void init();
	/**
	 * Render scene
	 * @param sprite batch
	 */
	public abstract void render(SpriteBatch batch);
	/**
	 * Dispose scene
	 */
	public abstract void dispose();
}
