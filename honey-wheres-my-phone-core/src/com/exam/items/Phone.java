package com.exam.items;

import com.badlogic.gdx.math.Vector2;
import com.exam.entity.Animation;
import com.exam.entity.EntityManager;

public class Phone extends Item {

	private Animation phoneAnimation;
	
	public Phone(PhoneType phoneType, Vector2 position, EntityManager manager, float speed, ItemManager itemManager) {
		super(position, manager, speed, itemManager);
		phoneAnimation = new Animation(phoneType.getAnimationType(), true, 1f, position, manager);
	}
}
