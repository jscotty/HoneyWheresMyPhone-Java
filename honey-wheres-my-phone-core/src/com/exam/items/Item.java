package com.exam.items;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.exam.entity.Entity;
import com.exam.entity.EntityManager;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Item extends Entity {
	
	private ItemType _itemType;
	private float _speed = 2f;
	private ItemManager _itemManager;
	private float _adjustifier = 0.1f;
	private float _xVelocity = 1f;
	
	/**
	 * Constructor for initialization.
	 * @param itemType for recieving score and sprite type
	 * @param world for box2D physics
	 * @param position to render at
	 * @param bodyType for box2D physics
	 * @param manager for processing
	 * @param speed current speed from manager
	 * @param itemManager for callback
	 */
	public Item(ItemType itemType, Vector2 position, EntityManager manager, float speed, ItemManager itemManager) {
		super(position, itemType.getSpriteType(), manager);
		this._itemType = itemType;
		this._speed = speed;
		this._itemManager = itemManager;
		
		Random random = new Random();
		int randomNumber = random.nextInt(10);
		if(randomNumber > 5)
			_xVelocity = -1f;
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);
		
		_speed += deltaTime *_adjustifier;
		pPosition.y += _speed + (Math.cos(pPosition.y)*10);
		
		pPosition.x += _xVelocity*_speed;
		
		if(pPosition.x <=0 || pPosition.x >= Main.WIDTH) bounce();
		
		pBody.setTransform(pPosition, 0);
		
		if(pPosition.y > Main.HEIGHT+200)
			_itemManager.removeItem(this);
	}
	
	private void bounce(){
		_xVelocity = -_xVelocity;
	}
	
	/**
	 * Reverse item direction
	 */
	public void reverse(){
		_speed = -2;
		_adjustifier = -_adjustifier;
	}
	
	/**
	 * @return score of itemType
	 */
	public float getScore(){
		return _itemType.getScore();
	}
	
	public ItemType getItemType() {
		return _itemType;
	}
}
