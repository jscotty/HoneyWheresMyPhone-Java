package com.exam.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Renderer {

	/**
	 * Initialize renderer system.
	 */
	public void Init();
	/**
	 * Render rederer system.
	 * @param sprite batch
	 */
	public void render(SpriteBatch batch);
	/**
	 * Dispose renderer system.
	 */
	public void dispose();
}
