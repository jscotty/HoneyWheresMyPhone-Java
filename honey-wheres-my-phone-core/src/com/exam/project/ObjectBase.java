package com.exam.project;

import java.util.Comparator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

public class ObjectBase extends Actor implements Comparator<Actor> {

    private Texture _texture = null;
    private TextureRegion _textureRegion = null; // always using texture region for rendering.
    protected Sprite _sprite;

    protected Vector2 pPosition = new Vector2(0, 0);
    protected float pOriginX = 0.5f;
    protected float pOriginY = 0.5f;
    protected float pScaleX = 1;
    protected float pScaleY = 1;
    protected float pRotation = 0;
    protected Integer pLayer = 1; // to make it comparable for comparator implementation.

    private float pScaleXFactor = 0;
    private float pScaleYFactor = 0;

    /**
     * Invisible object.
     * @param x position in percentage 0-100%
     * @param y position in percentage 0-100%
     */
    public ObjectBase(float x, float y) {
	this.pPosition.x = x;
	this.pPosition.y = y;
	setZIndex(pLayer);
	calculateScale();
	calculatePosition();
    }

    /**
     * Constructor with given texture.
     * @param texture
     * @param x position in percentage 0-100%
     * @param y position in percentage 0-100%
     */
    public ObjectBase(Texture texture, float x, float y) {
	this._texture = texture;
	this._textureRegion = new TextureRegion(texture);
	this.pPosition.x = x;
	this.pPosition.y = y;

	this._sprite = new Sprite(_textureRegion);
	setZIndex(pLayer);
	calculateScale();
	calculatePosition();
    }

    /**
     * Constructor with given texture path for generating texture.
     * @param texturePath
     * @param x position in percentage 0-100%
     * @param y position in percentage 0-100%
     */
    public ObjectBase(String texturePath, float x, float y) {
	this._texture = new Texture(texturePath);

	this.pPosition.x = x;
	this.pPosition.y = y;

	this._sprite = new Sprite(_texture);
	setZIndex(pLayer);
	calculateScale();
	calculatePosition();
    }

    /**
     * Constructor with given type for region generation.
     * @param spriteType
     * @param x position in percentage 0-100%
     * @param y position in percentage 0-100%
     */
    public ObjectBase(SpriteType type, float x, float y) {
	this._textureRegion = SpriteSheetReaderShoebox.getTextureFromAtlas(type);
	this.pPosition.x = x;
	this.pPosition.y = y;

	this._sprite = new Sprite(_textureRegion);
	setZIndex(pLayer);
	calculateScale();
	calculatePosition();
    }

    /**
     * set layer for rendering ordering.
     * @param layer
     * @return
     */
    public ObjectBase setLayer(int layer) {
	this.pLayer = layer;
	setZIndex(layer);
	return this;
    }

    /**
     * set object scale Automatically calculates new position
     * @param x scale
     * @param y scale
     * @return this
     */
    public ObjectBase setObjectScale(float x, float y) {
	this.pScaleX = x;
	this.pScaleY = y;
	calculateScale();
	calculatePosition();
	return this;
    }

    /**
     * set object rotation Automatically calculates new position
     * @param rotation
     * @return this
     */
    public ObjectBase setObjectRotation(float rotation) {
	this.pRotation = rotation;
	calculatePosition();
	return this;
    }

    /**
     * set object origin position to center or change the positon calculations
     * Automatically calculates new position
     * @param x origin position
     * @param y origin position
     * @return this
     */
    public ObjectBase setObjectOrigin(float x, float y) {
	this.pOriginX = x;
	this.pOriginY = y;
	calculatePosition();
	return this;
    }

    /**
     * Update entity.
     */
    public void update() {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
	//batch.draw(sprite, getX(),getY());
	_sprite.draw(batch);
    }

    protected void calculatePosition() {
	if (_sprite == null)
	    return;
	_sprite.setPosition(getX(), getY());
	_sprite.setRotation(pRotation);
	_sprite.setScale(pScaleX, pScaleY);
	_sprite.setOrigin(pOriginX, pOriginY);
    }

    private void calculateScale() {
	pScaleXFactor = (float) Gdx.graphics.getWidth() / (float) Main.WIDTH;
	pScaleYFactor = (float) Gdx.graphics.getHeight() / (float) Main.HEIGHT;

	pScaleX = pScaleX * pScaleXFactor;
	pScaleY = pScaleY * pScaleYFactor;
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
	return _texture;
    }

    /**
     * @return atlas/spritesheet texture region
     */
    public TextureRegion getTextureRegion() {
	return _textureRegion;
    }

    /**
     * @return x position
     */
    public float getX() {
	return ((pPosition.x / 100) * Gdx.graphics.getWidth()) - (getWidth() * pOriginX);
    }

    /**
     * @return y position
     */
    public float getY() {
	return ((pPosition.y / 100) * Gdx.graphics.getHeight()) - (getHeight() * pOriginY);
    }

    public Vector2 getPosition() {
	return pPosition;
    }

    public float getScaleX() {
	return pScaleX;
    }

    public float getScaleY() {
	return pScaleY;
    }

    public float getRotation() {
	return pRotation;
    }

    public Integer getLayer() {
	return pLayer;
    }

    public float getOriginX() {
	return pOriginX;
    }

    public float getOriginY() {
	return pOriginY;
    }

    public float getWidth() {
	return _sprite.getWidth() * pScaleX;
    }

    public float getHeight() {
	return _sprite.getHeight() * pScaleY;
    }

    public Sprite getSprite() {
	return _sprite;
    }
}
