package com.exam.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.exam.assets.SpriteType;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Gui implements Comparable<Gui> {

	protected Vector2 pPosition = new Vector2(0, 0);
	protected float pAlpha = 1f;
	protected float pWidth;
	protected float pHeight;
	protected float pOriginX = 0.5f;
	protected float pOriginY = 0.5f;
	protected float pScaleX = 1f; // scale X factor
	protected float pScaleY = 1f; // scale Y factor
	protected float pAngle = 0f;

	protected int pZIndex = 0; // for sorting.

	private TextureRegion _texture;

	//region constructors
	/**
	 * 'ghost' Gui nor empty constructor for child classes.
	 * @param x
	 * @param y
	 */
	public Gui(float x, float y, GuiManager manager) {
		this.pPosition.x = x;
		this.pPosition.y = y;

		manager.processGui(this);
	}

	/**
	 * Initialization for the 
	 * @param x
	 * @param y
	 * @param spriteType
	 */
	public Gui(float x, float y, SpriteType spriteType, GuiManager manager) {
		this.pPosition.x = x;
		this.pPosition.y = y;

		_texture = Main.assets.getTexture(spriteType);
		pWidth = _texture.getRegionWidth();
		pHeight = _texture.getRegionHeight();

		manager.processGui(this);
	}
	//endregion

	public Gui setIndex(int index) {
		pZIndex = index;
		return this;
	}

	/**
	 * updates every frame
	 * @param pSpriteBatch
	 */
	public void update(float deltaTime) {
	}

	/**
	 * Rendering texture.
	 * called every frame
	 * @param spriteBatch
	 */
	public void render(SpriteBatch spriteBatch) {
		spriteBatch.setColor(1, 1, 1, pAlpha);
		spriteBatch.draw(_texture, pPosition.x - (pWidth * pScaleX) / 2, pPosition.y - (pHeight * pScaleY) / 2,
				pOriginX, pOriginY, pWidth, pHeight, pScaleX, pScaleY, pAngle);
		spriteBatch.setColor(1, 1, 1, 1);
	}

	@Override
	public int compareTo(Gui o) {
		if (this.pZIndex < o.pZIndex) {
			return -1;
		} else {
			return 1;
		}
	}

	//region properties
	public TextureRegion getTexture() {
		return _texture;
	}

	/**
	 * Set texture to new texture region to change visualization.
	 * @param texture
	 */
	public void setTexture(SpriteType spriteType) {
		this._texture = Main.assets.getTexture(spriteType);
	}

	/**
	 * @return scale X factor
	 */
	public float getScaleX() {
		return pScaleX;
	}

	/**
	 * @return scale Y factor
	 */
	public float getScaleY() {
		return pScaleY;
	}

	/**
	 * Set scale X factor.
	 * @param scaleX
	 */
	public void setScaleX(float scaleX) {
		this.pScaleX = scaleX;
	}

	/**
	 * Set scale Y factor.
	 * @param scaleX
	 */
	public void setScaleY(float scaleY) {
		this.pScaleY = scaleY;
	}

	public void setScale(float scaleX, float scaleY) {
		this.pScaleX = scaleX;
		this.pScaleY = scaleY;
	}

	/**
	 * @return gui alpha value
	 */
	public float getAlpha() {
		return pAlpha;
	}

	/**
	 * set alpha to given value
	 * @param alpha value
	 */
	public void setAlpha(float alpha) {
		this.pAlpha = alpha;
	}

	/**
	 * set x position of this instance
	 * @param x position
	 */
	public void setXPosition(float x) {
		pPosition.x = x;
	}

	/**
	 * @return render position of this instance
	 */
	public Vector2 getPosition() {
		return pPosition;
	}

	/**
	 * @return width of current render texture
	 */
	public float getWidth() {
		return _texture.getRegionWidth();
	}

	/**
	 * @return height of current render texture
	 */
	public float getHeight() {
		return _texture.getRegionHeight();
	}
	//endregion
}
