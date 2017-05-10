package com.exam.gui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.handlers.GUIManager;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;

public class Gui implements Comparable<Gui> {
	private float positionX;
	private float positionY;
	private float width;
	private float height;
	
	protected float alpha = 1f;
	
	protected float originX = 0.5f;
	protected float originY = 0.5f;
	protected float scaleX = 1f;
	protected float scaleY = 1f;
	protected float angle = 0f;
	
	protected int zIndex = 0; // for sorting.
	
	private TextureRegion texture;
	
	/**
	 * 'ghost' Gui nor empty constructor for child classes.
	 * @param x
	 * @param y
	 */
	public Gui(float x, float y, GUIManager manager){
		this.positionX = x;
		this.positionY = y;
		
		manager.processGui(this);
	}
	
	/**
	 * Initialization for the 
	 * @param x
	 * @param y
	 * @param spriteType
	 */
	public Gui(float x, float y, SpriteType spriteType, GUIManager manager) {
		this.positionX = x;
		this.positionY = y;

		texture = Main.assets.getTexture(spriteType);
		width = texture.getRegionWidth();
		height = texture.getRegionHeight();
		
		manager.processGui(this);
	}
	
	public Gui setIndex(int index){
		zIndex = index;
		return this;
	}

	/**
	 * Update gui for behaviour.
	 * @param spriteBatch
	 */
	public void update(float deltaTime) {
		
	}
	
	/**
	 * Render sprites. This method is called every frame.
	 * @param spriteBatch
	 */
	public void render(SpriteBatch spriteBatch) {
		spriteBatch.setColor(1, 1, 1, alpha);
		spriteBatch.draw(texture, positionX - (width*scaleX) / 2, positionY - (height*scaleY) / 2, originX, originY, width, height,scaleX, scaleY, angle);
		spriteBatch.setColor(1, 1, 1, 1);
	}

	@Override
	public int compareTo(Gui o) {
		if (this.zIndex < o.zIndex){
			return -1;
		} else {
			return 1;
		}
	}
	
	public TextureRegion getTexture() {
		return texture;
	}
	public float getScaleX() {
		return scaleX;
	}
	public float getScaleY() {
		return scaleY;
	}
	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}
	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}
	public void setScale(float scaleX, float scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}
	public float getAlpha() {
		return alpha;
	}
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
}
