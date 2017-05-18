package com.exam.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.handlers.MyInput;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Hook extends Entity {
	
	private float _speedDevider = 5f; // for flowing movement
	private float _mouseX;

	/**
	 * constructor and initalization. Casting parameters to base class
	 * @param world for box2D physics and events.
	 * @param position
	 * @param bodyType for box2D physics and events.
	 * @param spriteType for receiving desired texture.
	 * @param manager to process/register this instance.
	 */
	public Hook(World world, Vector2 position, BodyType bodyType, SpriteType spriteType, EntityManager manager) {
		super(world, position, bodyType, spriteType, manager);
		_mouseX = position.x;
	}

	/**
	 * handles input from MyInput class.
	 */
	private void handleInput(){
		if(MyInput.isMouseDown(MyInput.MOUSE_BUTTON_LEFT)){
			_mouseX = MyInput.getMouseXCoordinate();
			// set mouse position always in screen
			if(_mouseX > Main.WIDTH) _mouseX = Main.WIDTH;
			if(_mouseX < 0) _mouseX = 0;
		}
		pBody.setTransform(getBodyPosition(), 0);
		// add distance calculation ( what is desired add factor to 'teleport' to desired position) devided by a speed factor.
		pPosition.x += (_mouseX - pPosition.x)/_speedDevider;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime); // update base.
		handleInput();
	}

}
