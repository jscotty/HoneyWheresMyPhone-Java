package com.exam.items;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.exam.entity.Entity;
import com.exam.entity.EntityManager;
import com.exam.project.Main;
import com.exam.tween.AccessorReferences;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Item extends Entity {

	private final float TWEEN_DURATION = 0.8f;

	private ItemType _itemType;
	private float _speed = 2f;
	private ItemManager _itemManager;
	private float _xVelocity = 0f;

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

		if (!itemType.isMoving())
			return;
		Random random = new Random();
		int randomNumber = random.nextInt(10);
		if (randomNumber >= 5) // give this item a random direction to left or right
			_xVelocity = -1f;
		else
			_xVelocity = 1f;
	}

	public Item(Vector2 position, EntityManager manager, float speed, ItemManager itemManager) {
		super(position, manager);
		this._speed = speed;
		this._itemManager = itemManager;
	}

	/**
	 * adds a box2D body to this item
	 * @param world
	 * @param bodyType
	 * @param width
	 * @param height
	 * @param positionX
	 * @param positionY
	 * @return this
	 */
	public Item addBodyBox(World world, BodyType bodyType, float width, float height, float positionX,
			float positionY) {
		return (Item) super.addBodyBox(world, bodyType, width, height, positionX, positionY, this);
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		super.update(deltaTime);

		//movement 
		pPosition.y += _speed;

		pPosition.x += _xVelocity * (_speed / 5);

		if (pPosition.x <= 0 || pPosition.x >= Main.WIDTH)
			bounce();

		pBody.setTransform(pPosition, 0);

		if (pPosition.y > Main.HEIGHT + 200 || pPosition.y < -200)
			_itemManager.removeItem(this); // remove this instance if we are out of range.
	}

	/**
	 * Reverse velocity to 'bounce'
	 */
	public void bounce() {
		_xVelocity = -_xVelocity;
	}

	public void animateAway(TweenManager tweenManager) {
		Tween.to(this, AccessorReferences.POSITION, TWEEN_DURATION).target(10, Main.HEIGHT + 210).start(tweenManager);
		Tween.to(this, AccessorReferences.SCALE, TWEEN_DURATION).target(0, 0).start(tweenManager);
	}

	/**
	 * Set speed
	 * @param speed
	 */
	public void setSpeed(float speed) {
		this._speed = speed;
	}

	/**
	 * @return score of itemType
	 */
	public float getScore() {
		return _itemType.getScore();
	}

	/**
	 * @return item type.
	 */
	public ItemType getItemType() {
		return _itemType;
	}
}
