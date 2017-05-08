package com.exam.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.handlers.Assets;
import com.exam.handlers.EntityManager;
import com.exam.handlers.MyInput;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;

public class Hook extends Entity {
	

	public Hook(World world, Vector2 position, BodyType bodyType, SpriteType spriteType, EntityManager manager) {
		super(world, position, bodyType, spriteType, manager);
	}

	private void handleInput(){
		if(MyInput.isMouseDown(MyInput.MOUSE_BUTTON_LEFT)){
			float mouseX = MyInput.getMouseXCoordinate();
			body.setTransform(getBodyPosition(), 0);
			if(mouseX > Main.WIDTH || mouseX < 0) return;
			position.x = mouseX;
		}
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		handleInput();
	}

}
