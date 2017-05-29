package com.exam.items;

import com.badlogic.gdx.math.Vector2;
import com.exam.entity.Animation;
import com.exam.entity.EntityManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Phone extends Item {

	private Animation _phoneAnimation;

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
		_phoneAnimation = new Animation(phoneType.getAnimationType(), true, 1f, position, manager); // progressing/registering animation to entityManager.
	}
}
