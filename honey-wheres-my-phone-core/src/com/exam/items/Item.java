package com.exam.items;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.exam.entity.Entity;
import com.exam.handlers.EntityManager;

public class Item extends Entity {
	
	private ItemType itemType;
	private float speed = 2f;
	
	public Item(ItemType itemType, int row, World world, Vector2 position, BodyType bodyType, EntityManager manager, float speed) {
		super(world, position, bodyType, itemType.getSpriteType(), manager);
		this.itemType = itemType;
		this.speed = speed;
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);
		
		speed += deltaTime *0.1f;
		position.y += speed;
	}
	
	public float getScore(){
		return itemType.getScore();
	}
}
