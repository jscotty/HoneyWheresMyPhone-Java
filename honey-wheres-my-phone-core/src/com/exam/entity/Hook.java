package com.exam.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.handlers.Assets;
import com.exam.handlers.MyInput;
import com.exam.toolbox.SpriteType;

public class Hook extends Entity {
	

	public Hook(World world, Vector2 position, BodyType bodyType, SpriteType spriteType, Assets assets) {
		super(world, position, bodyType, spriteType, assets);
	}

	private void handleInput(){
		if(MyInput.isMouseDown(MyInput.MOUSE_BUTTON_LEFT)){
			position.x = MyInput.getMouseXCoordinate();
			body.setTransform(getBodyPosition(), 0);
		}
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		handleInput();
	}

}
