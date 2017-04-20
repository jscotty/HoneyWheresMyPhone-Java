package com.exam.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IRenderer {

    /**
     * Initialize renderer system.
     */
    public void init();

    /**
     * Render system.
     * @param sprite batch
     */
    public void render(SpriteBatch batch);

    /**
     * Dispose renderer system.
     */
    public void dispose();
}
