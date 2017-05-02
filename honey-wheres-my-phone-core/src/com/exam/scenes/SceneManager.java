package com.exam.scenes;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.exam.project.Main;

public class SceneManager {

    private Stage _stage;
    private static Main _main;

    /**
     * Constructor for initialization
     */
    public SceneManager(Stage stage, Main main) {
	this._stage = stage;
	SceneManager._main = main;
	
	_main.setScreen(new MenuScene(this));
    }

    /**
     * Load new scene
     * @param scene
     */
    public static void loadScene(Scene scene) {
	_main.setScreen(scene);
    }

    public Stage getStage() {
        return _stage;
    }
    
    
}
