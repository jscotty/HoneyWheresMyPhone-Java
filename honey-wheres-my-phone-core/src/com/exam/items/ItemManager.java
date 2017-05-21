package com.exam.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.entity.EntityManager;
import com.exam.handlers.GameEventHandler;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class ItemManager extends GameEventHandler implements ContactListener{
	
	public static final String USER_DATA = "Item";

	private final int[][] ITEMROWS_TILL100 = new int[][]{
		{1,0,0,0,0},
		{0,1,0,0,0},
		{0,0,1,0,0},
		{0,0,0,1,0},
		{0,0,0,0,1},
		{0,0,1,0,1},
		{0,0,1,1,0},
		{1,1,0,0,1},
		{0,0,1,1,1},
		{1,0,0,1,0},
		{0,0,1,1,0}
	};

	private World _world;
	private EntityManager _entityManager;
	private Random _random;
	
	private final float BORDER = 80.0f;
	private float _screenWidth = Main.WIDTH - BORDER;
	private float _speedMutliplier;
	private float _speed = 2f;
	private float _delay = 5f;
	private float _meters;
	private boolean _reverse = false;
	private boolean _start = false;
	private float yPosition = -100;
	
	private List<Item> _itemsDestroyed = new ArrayList<Item>();
	private List<Item> _itemsInField = new ArrayList<Item>();
	
	/**
	 * Constructor for initialization
	 * @param world for Item Box2D physics
	 * @param manager to process items
	 */
	public ItemManager(World world, EntityManager manager) {
		this._world = world;
		this._entityManager = manager;
		_random = new Random();
	}
	
	/**
	 * Update item manager to manage speed and spawning.
	 * @param deltaTime
	 */
	public void update(float deltaTime){
		if(!_start) return;
		if(_reverse){
			_speed -= deltaTime * 0.6f;
			_speedMutliplier -= 0.000025f;
			yPosition = Main.HEIGHT + 100;
		} else {
			_speed += deltaTime * 0.2f;
			_speedMutliplier += 0.000025f;
			yPosition = -100;
		}
		_meters += deltaTime + _speedMutliplier;
		if(_meters > _delay){
			spawn();
			_delay += 5;
		}
	}
	
	/**
	 * Spawn items in order of random row
	 */
	private void spawn(){
		int randomRow = _random.nextInt(ITEMROWS_TILL100.length);
		spawnItems(randomRow);
	}
	
	/**
	 * Spawn items in order of 1 integers from ITEMROWS_TILL100.
	 * @param index or row array
	 */
	private void spawnItems(int index){
		int[] row = ITEMROWS_TILL100[index];
		for (int i = 0; i < row.length; i++) {
			float xPosition = ((_screenWidth/row.length) * (i)) + BORDER;
			if(row[i] == 1){
				Vector2 position = new Vector2(xPosition, yPosition);
				int randomItem = _random.nextInt(ItemType.values().length);
				Item item = (Item) new Item(ItemType.values()[randomItem], position, _entityManager, _speed, this).addBodyBox(_world, BodyType.DynamicBody, 50,50, position.x ,position.y, USER_DATA);
				_itemsInField.add(item);
				
			}
			
		}
	}
	
	/**
	 * remove item from screen.
	 * @param item
	 */
	public void removeItem(Item item){
		item.disableBody(); // no Box2D needed anymore
		_itemsDestroyed.add(item); // still adding to list for catching up later
		_entityManager.removeEntity(item); // remove from manager.
		_itemsInField.remove(item); // remove from items in field list.
	}

// region contactlistener methods
	@Override
	public void beginContact(Contact contact) {
		_reverse = true;
		_speed = -_speed;
		for (Item item : _itemsInField) {
			item.reverse();
		}
	}

	@Override
	public void endContact(Contact contact) { }

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) { }

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) { }
	
	public int getMeters() {
		return (int)_meters;
	}
//endregion

//region GameEventHandler methods
	@Override
	public void castMethod() {
		_start = true;
	}
//endregion
}
