package com.exam.entity;

import com.exam.toolbox.SpriteType;

public class Background extends Entity {
	
	private float counter = 0;

	public Background(String spritePath, float x, float y) {
		super(spritePath, x, y);
	}
	
	public Background(SpriteType type, float x, float y) {
		super(type, x, y);
	}
	
	public void scroll(float speed){
		counter += 0.00001f;
		sprite.scroll(0, counter);
	}

}
