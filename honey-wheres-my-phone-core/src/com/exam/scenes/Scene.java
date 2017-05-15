package com.exam.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public abstract class Scene {

	protected SceneManager pSceneManager;
	protected Main pMain;

	protected SpriteBatch pSpriteBatch;
	protected OrthographicCamera pCamera;
	protected OrthographicCamera pHudCamera;

	/**
	 * Constructor to initialize all variables
	 * @param pSceneManager
	 */
	protected Scene(SceneManager manager) {
		this.pSceneManager = manager;
		pMain = manager.getMain();
		pSpriteBatch = pMain.getBatch();
		pCamera = pMain.getCamera();
		pHudCamera = pMain.getHudCamera();
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
	 * Renders every frame
	 */
	public abstract void render();

	/**
	 * The dispose method get called when the application quits.
	 */
	public abstract void dispose();

}
