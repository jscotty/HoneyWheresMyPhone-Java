package com.exam.items;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.exam.entity.Animation;
import com.exam.entity.EntityManager;
import com.exam.managers.GameManager;

import aurelienribon.tweenengine.TweenManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Phone extends Item {

	private int _phoneIndex = 0;

	/**
	 * Constructor for initialization.
	 * @param phoneType to create animation.
	 * @param position to draw animation at.
	 * @param manager to register animation.
	 * @param speed for movement.
	 * @param itemManager to manage this item.
	 */
	public Phone(PhoneType phoneType, Vector2 position, EntityManager manager, float speed, ItemManager itemManager) {
		super(position, manager, speed, itemManager);
		Animation phoneAnimation = new Animation(phoneType.getAnimationType(), true, 1f, position, manager); // progressing/registering animation to entityManager.
		
		boolean phoneIndexFound = false;
		for (PhoneType phone : PhoneType.values()) {
			if(phoneType == phone)
				phoneIndexFound = true;
			if(!phoneIndexFound)
				_phoneIndex++;
		}
	}
	
	public Phone addBodyBox(World world, BodyType bodyType, float width, float height, float positionX, float positionY) {
		return (Phone) super.addBodyBox(world, bodyType, height, width, positionX, positionY, this);
	}
	
	@Override
	public void animateAway(TweenManager tweenManager) {
		super.animateAway(tweenManager);
		GameManager.collectedPhone(_phoneIndex);
	}
	
	/**
	 * @return index of this phone
	 */
	public int getIndex(){
		return _phoneIndex;
	}
}
