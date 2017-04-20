package com.exam.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.scenes.SceneManager;

public class Main extends ApplicationAdapter {

    //screen width and height
    public static final int WIDTH = 720, HEIGHT = 1280;

    private SceneManager _sceneManager;
    private SpriteBatch _batch;

    @Override
    public void create() {
	//initialize
	Gdx.graphics.setDisplayMode(Main.WIDTH / 2, Main.HEIGHT / 2, false);
	Gdx.graphics.setTitle("Honey? where's my phone?");
	_batch = new SpriteBatch();
	_sceneManager = new SceneManager();
	_sceneManager.init();
    }

    @Override
    public void render() {
	// main rendering
	Gdx.gl.glClearColor(1, 1, 1, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	_batch.begin();
	_sceneManager.render(_batch); // rendering current active scene.
	_batch.end();
    }

    @Override
    public void dispose() {
	// When closing application
	_sceneManager.dispose();
    }
}
