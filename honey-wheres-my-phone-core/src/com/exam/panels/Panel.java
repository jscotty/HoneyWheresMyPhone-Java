package com.exam.panels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.gui.GuiManager;

import aurelienribon.tweenengine.TweenManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public abstract class Panel {

	protected GuiManager pGuiManager; // to register and process guis
	protected TweenManager pTweenManager;
	protected boolean pIsActive = false;

	public Panel() {
		pTweenManager = new TweenManager();
		pGuiManager = new GuiManager();
	}

	/**
	 * Start animation to show panel
	 */
	public abstract void startAnimation();

	/**
	 * End animation to remove panel
	 */
	public abstract void endAnimation();

	/**
	 * Update panel.
	 * Updates every frame.
	 * @param deltaTime
	 */
	public abstract void update(float deltaTime);

	/**
	 * Render panel.
	 * Renders every frame.
	 * @param deltaTime
	 */
	public abstract void render(SpriteBatch spriteBatch);

	/**
	 * Dispose panel.
	 * Disposes when panel closes/application quits.
	 * @param deltaTime
	 */
	public abstract void dispose();

	/**
	 * @return if this panel is active on screen.
	 */
	public boolean isActive() {
		return pIsActive;
	}

}
