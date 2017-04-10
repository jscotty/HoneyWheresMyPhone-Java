package com.exam.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

public abstract class Entity {

	private Texture texture = null;
	private TextureRegion textureRegion = null;

	private float x;
	private float y;
	
	/**
	 * Constructor with given texture.
	 * @param texture
	 * @param x position
	 * @param y position
	 */
	public Entity(Texture texture, float x, float y){
		this.texture = texture;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor with given texture path for generating texture.
	 * @param texturePath
	 * @param x position
	 * @param y position
	 */
	public Entity(String texturePath, float x, float y){
		this.texture = new Texture(texturePath);
		this.x = x;
		this.y = y;
	}

	
	/**
	 * Constructor with given type for region generation.
	 * @param spriteType
	 * @param x position
	 * @param y position
	 */
	public Entity(SpriteType type, float x, float y){
		this.textureRegion = SpriteSheetReaderShoebox.getTextureFromAtlas(type);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Update entity.
	 */
	public abstract void update();

	/**
	 * @return texture (In case not using an atlas/spritesheet)
	 */
	public Texture getTexture() {
		return texture;
	}
	
	/**
	 * @return atlas/spritesheet texture region
	 */
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

	/**
	 * @return x position
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return y position
	 */
	public float getY() {
		return y;
	}
}
