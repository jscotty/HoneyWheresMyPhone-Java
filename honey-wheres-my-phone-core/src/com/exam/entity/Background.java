package com.exam.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.exam.toolbox.SpriteType;

public class Background extends Entity {
	
	private final float MAX_SCROLL_SPEED = 0.02F, MIN_SCROLL_SPEED = 0.004F;
	
	private float scrollSpeed = 0;

	public Background(String spritePath, float x, float y) {
		super(spritePath, x, y);
		sprite.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
	}
	
	public Background(SpriteType type, float x, float y) {
		super(type, x, y);
		sprite.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
	}
	
	/**
	 * scroll background sprite
	 * @param speed multiplier
	 */
	public void scroll(float speed){
		scrollSpeed += (0.0001f * Gdx.graphics.getDeltaTime())* speed; // using 0.0001f because 1.0 will be scrolling extremely fast
		
		scrollSpeed = Math.max(MIN_SCROLL_SPEED, Math.min(MAX_SCROLL_SPEED, scrollSpeed)); // clamping speeds
		sprite.scroll(0, scrollSpeed);
		
		if(Gdx.input.isButtonPressed(1)) stopScroll();
	}
	
	/**
	 *  stop scrolling background.
	 */
	public void stopScroll(){
		scrollSpeed = 0;
		sprite.scroll(0, 0);
		sprite.setColor(1,1,1,1);
	}
	
	public float getScrollSpeed(){
		return scrollSpeed;
	}

}
