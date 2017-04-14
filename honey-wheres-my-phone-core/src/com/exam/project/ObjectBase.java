package com.exam.project;

import java.util.Comparator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.exam.toolbox.MathTools;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

public class ObjectBase extends Actor implements Comparator<Actor> {

	private Texture texture = null;
	private TextureRegion textureRegion = null; // always using texture region for rendering.
	private Sprite sprite;

	protected float x = 0;
	protected float y = 0;
	protected Vector2 position = new Vector2(0,0);
	protected float originX = 0.5f;
	protected float originY = 0.5f;
	protected float scaleX = 1;
	protected float scaleY = 1;
	protected float rotation = 0;
	protected Integer layer = 1; // to make it comparable for comparator implementation.
	
	
	/**
	 * Invisible entity.
	 * @param x position in percentage 0-100%
	 * @param y position in percentage 0-100%
	 */
	public ObjectBase(float x, float y){
		this.position.x = x;
		this.position.y = y;
		setZIndex(layer);
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

		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		this.textureRegion = new TextureRegion(texture);
		this.position.x = x;
		this.position.y = y;
		
		this.sprite = new Sprite(texture);
		setZIndex(layer);
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
		calculatePosition();
	}
	
	public ObjectBase setLayer(int layer){
		this.layer = layer;
		setZIndex(layer);
		return this;
	}
	
	public ObjectBase setObjectScale(float x, float y){
		this.scaleX = x;
		this.scaleY = y;
		return this;
	}
	
	public ObjectBase setObjectRotation(float rotation){
		this.rotation = rotation;
		return this;
	}
	
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
	
	public void translatePosition(float x, float y){
		this.x += x;
		this.y += y;
		calculatePosition();
	}
	
	public void moveToTarget(ObjectBase target, float speed){
		Vector2 targetPos = target.getPosition();
		Vector2 myPos = new Vector2(position.x, position.y);
		
		//normalize
		
		Vector2 result = new Vector2(0,0);

	    result.x = targetPos.x - myPos.x;
	    result.y = targetPos.y - myPos.y;

	    double magnitude = MathTools.magnitude(result);
	    result.nor();
	    
	    if(magnitude < 1) return;
	    position.x += result.x*speed;
	    position.y += result.y*speed;
		
		calculatePosition();
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
		return ((position.x/100)*Main.WIDTH) - (getWidth()*originX);
	}
	/**
	 * @return y position
	 */
	public float getY() {
		return ((position.y/100)*Main.HEIGHT) - (getHeight()*originY);
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
		return textureRegion.getRegionWidth()*scaleX;
	}
	
	public float getHeight(){
		return textureRegion.getRegionHeight()*scaleY;
	}
	public Sprite getSprite(){
		return sprite;
	}
}
