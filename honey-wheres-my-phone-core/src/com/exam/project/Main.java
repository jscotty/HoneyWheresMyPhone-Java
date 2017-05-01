package com.exam.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.exam.scenes.SceneManager;

public class Main extends ApplicationAdapter {

    //screen width and height
    public static final int WIDTH = 720, HEIGHT = 1280;

    private SceneManager _sceneManager;
    private SpriteBatch _batch;
    private Stage _stage;

    @Override
    public void create() {
	//initialize
	Gdx.graphics.setDisplayMode(Main.WIDTH / 2, Main.HEIGHT / 2, false);
	Gdx.graphics.setTitle("Honey? where's my phone?");
	
	_stage = new Stage(new ScreenViewport());
	_batch = new SpriteBatch();
	_sceneManager = new SceneManager(_stage,this);
	_sceneManager.init();
	
	Gdx.input.setInputProcessor(_stage);
    }

    @Override
    public void render() {
	// main rendering
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	_stage.act(Gdx.graphics.getDeltaTime());
	_stage.draw();
	_sceneManager.render(_batch); // rendering current active scene.
    }

    @Override
    public void dispose() {
	// When closing application
	_sceneManager.dispose();
    }
}
