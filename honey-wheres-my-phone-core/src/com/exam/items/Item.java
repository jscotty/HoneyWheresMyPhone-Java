package com.exam.items;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.exam.entity.Entity;
import com.exam.handlers.EntityManager;
import com.exam.project.Main;

public class Item extends Entity {
	
	private ItemType _itemType;
	private float _speed = 2f;
	private ItemManager _itemManager;
	private float _adjustifier = 0.1f;
	
	public Item(ItemType itemType, World world, Vector2 position, BodyType bodyType, EntityManager manager, float speed, ItemManager itemManager) {
		super(world, position, bodyType, itemType.getSpriteType(), manager);
		this._itemType = itemType;
		this._speed = speed;
		this._itemManager = itemManager;
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);
		
		_speed += deltaTime *_adjustifier;
		pPosition.y += _speed;
		
		pBody.setTransform(pPosition, 0);
		
		if(pPosition.y > Main.HEIGHT+100)
			_itemManager.removeItem(this);
	}
	
	public void reverse(){
		_speed = -2;
		_adjustifier = -_adjustifier;
	}
	
	public float getScore(){
		return _itemType.getScore();
	}
}
