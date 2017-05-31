package com.exam.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.assets.Assets;
import com.exam.handlers.MyInput;
import com.exam.handlers.MyInputProcessor;
import com.exam.managers.GameManager;
import com.exam.scenes.SceneManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Main extends ApplicationAdapter {

	// screen width and height
	public static final int WIDTH = 720, HEIGHT = 1280;
	public static final float DEVIDER = 2f;
	public static final float STEP = 1/60f;
	private final String TITLE = "Honey? where's my phone?";

	public static Assets assets;

	private SpriteBatch _batch;
	private OrthographicCamera _camera;
	private OrthographicCamera _hudCamera;
	
	private SceneManager _sceneManager;
	
	@Override
	public void create() {
		// initialize
		Gdx.graphics.setDisplayMode((int)(Main.WIDTH / DEVIDER), (int)(Main.HEIGHT / DEVIDER), false);
		Gdx.graphics.setTitle(TITLE);
		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		GameManager.init();

		assets = new Assets();
		_batch = new SpriteBatch();
		_camera = new OrthographicCamera();
		_camera.setToOrtho(false, WIDTH, HEIGHT);
		_hudCamera = new OrthographicCamera();
		_hudCamera.setToOrtho(false, WIDTH, HEIGHT);
		
		
		_sceneManager = new SceneManager(this);
	}

	@Override
	public void render() {
		// main rendering
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		_sceneManager.update(Gdx.graphics.getDeltaTime());
		_sceneManager.render();
		MyInput.update();
	}

	@Override
	public void dispose() {
		// When closing application
	}

	/**
	 * @return sprite batch for rendering
	 */
	public SpriteBatch getBatch() {
		return _batch;
	}

	/**
	 * @return main camera for entities
	 */
	public OrthographicCamera getCamera() {
		return _camera;
	}

	/**
	 * @return hud camera for guis.
	 */
	public OrthographicCamera getHudCamera() {
		return _hudCamera;
	}
}
