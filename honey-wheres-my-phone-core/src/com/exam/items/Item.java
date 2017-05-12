package com.exam.items;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.exam.entity.Entity;
import com.exam.handlers.EntityManager;
import com.exam.project.Main;

public class Item extends Entity {
	
	private ItemType itemType;
	private float speed = 2f;
	private ItemManager itemManager;
	private float adjustifier = 0.1f;
	
	public Item(ItemType itemType, World world, Vector2 position, BodyType bodyType, EntityManager manager, float speed, ItemManager itemManager) {
		super(world, position, bodyType, itemType.getSpriteType(), manager);
		this.itemType = itemType;
		this.speed = speed;
		this.itemManager = itemManager;
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);
		
		speed += deltaTime *adjustifier;
		position.y += speed;
		
		body.setTransform(position, 0);
		
		if(position.y > Main.HEIGHT+100)
			itemManager.removeItem(this);
	}
	
	public void reverse(){
		speed = -2;
		adjustifier = -adjustifier;
	}
	
	public float getScore(){
		return itemType.getScore();
	}
}
