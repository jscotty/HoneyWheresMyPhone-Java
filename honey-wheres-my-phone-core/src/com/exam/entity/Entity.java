package com.exam.entity;

import com.exam.project.ObjectBase;
import com.exam.toolbox.SpriteType;

public class Entity extends ObjectBase {

	public Entity(float x, float y){
		super(x,y);
	}
	
	public Entity(String spritePath, float x, float y){
		super(spritePath, x, y);
	}
	
	public Entity(SpriteType type, float x, float y) {
		super(type, x, y);
	}

	@Override
	public void update() {
		super.update();
	}
}
