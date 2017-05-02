package com.exam.renderers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IRenderer {

    /**
     * Initialize renderer system.
     */
    public void init();

    /**
     * Updating render systems
     */
    public void render();

    /**
     * Dispose renderer system.
     */
    public void dispose();
}
