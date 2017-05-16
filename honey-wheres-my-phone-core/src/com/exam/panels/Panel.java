package com.exam.panels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aurelienribon.tweenengine.TweenManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public abstract class Panel {
	
	protected TweenManager tweenManager;
	
	public Panel() {
		tweenManager = new TweenManager();
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

}
