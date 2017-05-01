package com.exam.scenes;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.exam.project.Main;

public class SceneManager {

    private static Stack<Scene> _scenes; // stack current scene
    private Stage _stage;
    private Main _main;

    /**
     * Constructor for initialization
     */
    public SceneManager(Stage stage, Main main) {
	this._stage = stage;
	this._main = main;
	_scenes = new Stack<Scene>();
	_scenes.push(new MenuScene(this));
    }

    /**
     * Load new scene
     * @param scene
     */
    public static void loadScene(Scene scene) {
	_scenes.peek().dispose();
	_scenes.clear();
	_scenes.push(scene);
	
	_scenes.peek().init();
    }

    /**
     * Initialize scene
     */
    public void init() {
	_scenes.peek().init();
    }

    /**
     * Render scene
     * @param sprite batch
     */
    public void render(SpriteBatch batch) {
	_scenes.peek().render(batch);
    }

    /**
     * Dispose scene
     */
    public void dispose() {
	_scenes.peek().dispose();
    }

    public Stage getStage() {
        return _stage;
    }
    
    
}
