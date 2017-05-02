package com.exam.scenes;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Scene implements Screen{

    protected SceneManager pSceneManager; // all scenes can acces scenemanager.

    /**
     * Empty constructor.
     */
    public Scene() {
    }

    /**
     * Constructor with sceneManager definition
     * @param sceneManager
     */
    public Scene(SceneManager sceneManager) {
	this.pSceneManager = sceneManager;
    }

    /**
     * Add sceneManager to define sceneManager after initialization.
     * @param sceneManager
     */
    public void AddSceneManager(SceneManager sceneManager) {
	this.pSceneManager = sceneManager;
    }
}
