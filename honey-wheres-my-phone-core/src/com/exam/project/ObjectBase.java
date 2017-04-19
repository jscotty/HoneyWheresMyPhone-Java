package com.exam.project;

import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

public class ObjectBase extends Actor implements Comparator<Actor> {

	private Texture texture = null;
	private TextureRegion textureRegion = null; // always using texture region for rendering.
	protected Sprite sprite;
	
	protected Vector2 position = new Vector2(0,0);
	protected float originX = 0.5f;
	protected float originY = 0.5f;
	protected float scaleX = 1;
	protected float scaleY = 1;
	protected float rotation = 0;
	protected Integer layer = 1; // to make it comparable for comparator implementation.

	private float scaleXFactor = 0;
	private float scaleYFactor = 0;
	
	
	/**
	 * Invisible object.
	 * @param x position in percentage 0-100%
	 * @param y position in percentage 0-100%
	 */
	public ObjectBase(float x, float y){
		this.position.x = x;
		this.position.y = y;
		setZIndex(layer);
		calculateScale();
		calculatePosition();
	}
	
	/**
	 * Constructor with given texture.
	 * @param texture
	 * @param x position in percentage 0-100%
	 * @param y position in percentage 0-100%
	 */
	public ObjectBase(Texture texture, float x, float y){
		this.texture = texture;
		this.textureRegion = new TextureRegion(texture);
		this.position.x = x;
		this.position.y = y;
		
		this.sprite = new Sprite(textureRegion);
		setZIndex(layer);
		calculateScale();
		calculatePosition();
	}
	
	/**
	 * Constructor with given texture path for generating texture.
	 * @param texturePath
	 * @param x position in percentage 0-100%
	 * @param y position in percentage 0-100%
	 */
	public ObjectBase(String texturePath, float x, float y){
		this.texture = new Texture(texturePath);
		
		this.position.x = x;
		this.position.y = y;
		
		this.sprite = new Sprite(texture);
		setZIndex(layer);
		calculateScale();
		calculatePosition();
	}

	
	/**
	 * Constructor with given type for region generation.
	 * @param spriteType
	 * @param x position in percentage 0-100%
	 * @param y position in percentage 0-100%
	 */
	public ObjectBase(SpriteType type, float x, float y){
		this.textureRegion = SpriteSheetReaderShoebox.getTextureFromAtlas(type);
		this.position.x = x;
		this.position.y = y;
		
		this.sprite = new Sprite(textureRegion);
		setZIndex(layer);
		calculateScale();
		calculatePosition();
	}
	
	/** set layer for rendering ordering.
	 * @param layer
	 * @return
	 */
	public ObjectBase setLayer(int layer){
		this.layer = layer;
		setZIndex(layer);
		return this;
	}
	
	/** set object scale
	 * Automatically calculates new position
	 * @param x scale
	 * @param y scale
	 * @return this
	 */
	public ObjectBase setObjectScale(float x, float y){
		this.scaleX = x;
		this.scaleY = y;
		calculateScale();
		calculatePosition();
		return this;
	}
	
	/** set object rotation
	 * Automatically calculates new position
	 * @param rotation
	 * @return this
	 */
	public ObjectBase setObjectRotation(float rotation){
		this.rotation = rotation;
		calculatePosition();
		return this;
	}
	
	/** set object origin position to center or change the positon calculations
	 * Automatically calculates new position
	 * @param x origin position
	 * @param y origin position
	 * @return this
	 */
	public ObjectBase setObjectOrigin(float x, float y){
		this.originX = x;
		this.originY = y;
		calculatePosition();
		return this;
	}
	
	/**
	 * Update entity.
	 */
	public void update(){ 
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		//batch.draw(sprite, getX(),getY());
		sprite.draw(batch);
	}
	
	protected void calculatePosition(){
		if(sprite==null)return;
		sprite.setPosition(getX(), getY());
		sprite.setRotation(rotation);
		sprite.setScale(scaleX, scaleY);
		sprite.setOrigin(originX, originY);
	}
	
	private void calculateScale(){
		scaleXFactor =  (float)Gdx.graphics.getWidth()/  (float)Main.WIDTH;
		scaleYFactor =   (float)Gdx.graphics.getHeight()/  (float)Main.HEIGHT;

		scaleX = scaleX * scaleXFactor;
		scaleY = scaleY * scaleYFactor;
	}

	@Override
	public int compare(Actor arg0, Actor arg1) {
		if (arg0.getZIndex() < arg1.getZIndex()) {
            return -1;
        } else if (arg0.getZIndex() == arg1.getZIndex()) {
            return 0;
        } else {
            return 1;
        }
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
		return ((position.x/100)*Gdx.graphics.getWidth()) - (getWidth()*originX);
	}
	/**
	 * @return y position
	 */
	public float getY() {
		return ((position.y/100)*Gdx.graphics.getHeight()) - (getHeight()*originY);
	}
	
	public Vector2 getPosition(){
		return position;
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
		return sprite.getWidth()*scaleX;
	}
	
	public float getHeight(){
		return sprite.getHeight()*scaleY;
	}
	public Sprite getSprite(){
		return sprite;
	}
}
