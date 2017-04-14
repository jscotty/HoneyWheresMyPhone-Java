package com.exam.gui;

import com.badlogic.gdx.graphics.Texture;
import com.exam.project.ObjectBase;
import com.exam.toolbox.SpriteType;

public class GUITexture extends ObjectBase {
	
	public GUITexture(float x, float y) {
		super(x, y);
	}
	/**
	 * Constructor with given texture.
	 * @param texture
	 * @param x position
	 * @param y position
	 */
	public GUITexture(Texture texture, float x, float y){
		super(texture, x, y);
	}
	
	/**
	 * Constructor with given texture path for generating texture.
	 * @param texturePath
	 * @param x position
	 * @param y position
	 */
	public GUITexture(String texturePath, float x, float y){
		super(texturePath, x, y);
	}
	
	/**
	 * Constructor with given type for region generation.
	 * @param spriteType
	 * @param x position
	 * @param y position
	 */
	public GUITexture(SpriteType type, float x, float y){
		super(type, x, y);
	}

	@Override
	public void update() {
		
	}
}
