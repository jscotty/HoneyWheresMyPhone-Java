package com.exam.gui;

import java.util.Comparator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.handlers.GUIManager;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;

public class Gui implements Comparator<Gui> {
	private float positionX;
	private float positionY;
	private float width;
	private float height;
	
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
		spriteBatch.draw(texture, positionX - width / 2, positionY - height / 2);
	}

	@Override
	public int compare(Gui o1, Gui o2) {
		if (o1.zIndex < o2.zIndex){
			return -1;
		} else {
			return 1;
		}
	}
}
