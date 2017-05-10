package com.exam.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.handlers.Assets;
import com.exam.handlers.MyInput;
import com.exam.handlers.MyInputProcessor;
import com.exam.scenes.SceneManager;
import com.exam.toolbox.SpriteType;

public class Main extends ApplicationAdapter {

	// screen width and height
	public static final int WIDTH = 720, HEIGHT = 1280;
	public static final float DEVIDER = 2f;
	private final String TITLE = "Honey? where's my phone?";
	
	public static final float STEP = 1/60f;

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private OrthographicCamera hudCamera;
	
	private SceneManager sceneManager;
	public static Assets assets;

	@Override
	public void create() {
		// initialize
		Gdx.graphics.setDisplayMode((int)(Main.WIDTH / DEVIDER), (int)(Main.HEIGHT / DEVIDER), false);
		Gdx.graphics.setTitle(TITLE);
		Gdx.input.setInputProcessor(new MyInputProcessor());

		assets = new Assets();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		hudCamera = new OrthographicCamera();
		hudCamera.setToOrtho(false, WIDTH, HEIGHT);
		
		sceneManager = new SceneManager(this);
	}

	@Override
	public void render() {
		// main rendering
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		sceneManager.update(Gdx.graphics.getDeltaTime());
		sceneManager.render();
		MyInput.update();
	}

	@Override
	public void dispose() {
		// When closing application
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public OrthographicCamera getHudCamera() {
		return hudCamera;
	}
	
	public Assets getAssets() {
		return assets;
	}
}
