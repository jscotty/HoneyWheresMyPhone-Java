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
	
	private float speedDevider = 5f;
	private float mouseX;

	public Hook(World world, Vector2 position, BodyType bodyType, SpriteType spriteType, EntityManager manager) {
		super(world, position, bodyType, spriteType, manager);
		mouseX = position.x;
	}

	private void handleInput(){
		if(MyInput.isMouseDown(MyInput.MOUSE_BUTTON_LEFT)){
			mouseX = MyInput.getMouseXCoordinate();
			// set mouse position always in screen
			if(mouseX > Main.WIDTH) mouseX = Main.WIDTH;
			if(mouseX < 0) mouseX = 0;
		}
		body.setTransform(getBodyPosition(), 0);
		// add distance calculation ( what is desired add factor to 'teleport' to desired position) devided by a speed factor.
		position.x += (mouseX - position.x)/speedDevider;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime); // update base.
		handleInput();
	}

}
