package com.exam.entity;

import java.util.Comparator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

public class Entity  implements Comparator<Entity> {

	private Texture texture = null;
	private TextureRegion textureRegion = null; // always using texture region for rendering.
	private Sprite sprite;

	protected float x = 0;
	protected float y = 0;
	protected float originX = 0.5f;
	protected float originY = 0.5f;
	protected float scaleX = 1;
	protected float scaleY = 1;
	protected float rotation = 0;
	protected Integer layer = 1; // to make it comparable for comparator implementation.
	
	/**
	 * Invisible entity.
	 * @param x position
	 * @param y position
	 */
	public Entity(float x, float y){
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * Constructor with given texture.
	 * @param texture
	 * @param x position
	 * @param y position
	 */
	public Entity(Texture texture, float x, float y){
		this.texture = texture;
		this.textureRegion = new TextureRegion(texture);
		this.x = x;
		this.y = y;
		
		this.sprite = new Sprite(textureRegion);
	}
	
	/**
	 * Constructor with given texture path for generating texture.
	 * @param texturePath
	 * @param x position
	 * @param y position
	 */
	public Entity(String texturePath, float x, float y){
		this.texture = new Texture(texturePath);
		this.textureRegion = new TextureRegion(texture);
		this.x = x;
		this.y = y;
		
		this.sprite = new Sprite(textureRegion);
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
		
		this.sprite = new Sprite(textureRegion);
	}
	
	public Entity setLayer(int layer){
		this.layer = layer;
		return this;
	}
	
	public Entity setScale(float x, float y){
		this.scaleX = x;
		this.scaleY = y;
		return this;
	}
	
	public Entity setRotation(float rotation){
		this.rotation = rotation;
		return this;
	}
	
	/**
	 * Update entity.
	 */
	public void update(){ 
		calculatePosition();
	}
	
	public void draw(SpriteBatch batch){
		sprite.draw(batch);
	}
	
	protected void calculatePosition(){
		sprite.setPosition(getX(), getY());
		sprite.setRotation(rotation);
		sprite.setScale(scaleX, scaleY);
		sprite.setOrigin(originX, originY);
	}
	
	@Override
	public int compare(Entity o1, Entity o2) {
		return o1.layer.compareTo(o2.layer);
	}

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
		return this.x - (getWidth()*originX);
	}
	/**
	 * @return y position
	 */
	public float getY() {
		return this.y - (getHeight()*originY);
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public float getRotation() {
		return rotation;
	}

	public Integer getLayer() {
		return layer;
	}

	public float getOriginX() {
		return originX;
	}

	public float getOriginY() {
		return originY;
	}
	
	public float getWidth(){
		return textureRegion.getRegionWidth()*scaleX;
	}
	
	public float getHeight(){
		return textureRegion.getRegionHeight()*scaleY;
	}
	public Sprite getSprite(){
		return sprite;
	}
}
