package com.exam.entity;

import com.badlogic.gdx.Gdx;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;

public class Hook extends Entity {
	
	public Hook(String texturePath, float x, float y) {
		super(texturePath, x, y);
	}
	
	public Hook(SpriteType type, float x, float y) {
		super(type, x, y);
	}

	@Override
	public void update() {
		super.update();
		moveWithInput();
	}
	
	private void moveWithInput(){
		if(!Gdx.input.isButtonPressed(0)) return;
		position.x = (float)(Gdx.input.getX()/(float)Main.WIDTH)*100;
		calculatePosition();
	}

}
