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
import com.exam.managers.GameManager;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class ItemManager extends GameEventHandler implements ContactListener{

	public static final String ITEM_DATA = "Item";
	public static final String PHONE_DATA = "Item";

	private final int[][] ITEMROWS_TILL100 = new int[][]{
		{1,0,0,0,0},
		{0,1,0,0,0},
		{0,0,1,0,0},
		{0,0,0,1,0},
		{0,0,0,0,1},
		{0,0,1,0,1},
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
	private float _speed = 2f;
	private float _backgroundSpeed;
	private float _delay = 5f;
	private float _meters;
	private boolean _start = false;
	private boolean _reverse = false;
	private float yPosition = -100;
	private int backgroundLevel;
	private int phoneLevel = 0;
	private int currentPhoneLevel = 0;
	
	private List<Item> _itemsDestroyed = new ArrayList<Item>();
	private List<Item> _itemsInField = new ArrayList<Item>();

	private List<List<ItemType>> _itemLevels = new ArrayList<List<ItemType>>();
	
	/**
	 * Constructor for initialization
	 * @param world for Item Box2D physics
	 * @param manager to process items
	 */
	public ItemManager(World world, EntityManager manager) {
		this._world = world;
		this._entityManager = manager;
		_random = new Random();
		initItemLists();
	}
	
	private void initItemLists() {
		for (int i = 0; i < 3; i++) { // create 3 lists
			_itemLevels.add(new ArrayList<ItemType>());
		}
		for (ItemType item : ItemType.values()) {
			System.out.println(item);
			_itemLevels.get(item.getLevel()-1).add(item); // add item to preferred level
		}
	}
	
	/**
	 * Update item manager to manage speed and spawning.
	 * @param deltaTime
	 */
	public void update(float deltaTime){
		if(!_start) return;
		
		calculateSpeed();
		
		if(phoneLevel > currentPhoneLevel){
			spawnPhone();
			currentPhoneLevel++;
		}
		
		if(_meters > _delay){
			spawn();
			_delay += 5;
		}
		

		for (Item item : _itemsInField) {
			item.setSpeed(_speed);
		}
	}
	
	private void calculateSpeed(){
		_speed = _backgroundSpeed *1.5f;
	}
	
	private void spawnPhone(){
		Phone item = (Phone) new Phone(PhoneType.values()[currentPhoneLevel], new Vector2(Main.WIDTH/2, yPosition), _entityManager, _speed, this).addBodyBox(_world, BodyType.DynamicBody, 40,40, Main.WIDTH/2, yPosition, ITEM_DATA).setIndex(3);
		
		_delay+=10;
		_itemsInField.add(item);
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
				int randomItem = _random.nextInt(_itemLevels.get(backgroundLevel).size());
				ItemType itemType;
				Item item = null;
				System.out.println(_speed);
				if(_reverse){ 
					if(_itemsDestroyed.size() == 0) return;
					itemType = _itemsDestroyed.get(_itemsDestroyed.size()-1).getItemType();
					item = (Item) new Item(itemType, position, _entityManager, _speed, this).addBodyBox(_world, BodyType.DynamicBody, 40,40, position.x ,position.y, ITEM_DATA);
				} else {
					 itemType = _itemLevels.get(backgroundLevel).get(randomItem);
					 item = (Item) new Item(itemType, position, _entityManager, _speed, this).addBodyBox(_world, BodyType.DynamicBody, 40,40, position.x ,position.y, ITEM_DATA);
				}
				
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
		if(contact.getFixtureA().getUserData() == ITEM_DATA && contact.getFixtureB().getUserData() == ITEM_DATA) return;
		GameManager.isHit = true;
		gameReverse();
		for (Item item : _itemsInField) {
			item.hit();
		}
	}

	@Override
	public void endContact(Contact contact) { }

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) { }

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) { }
//endregion

//region GameEventHandler methods
	@Override
	public void castMethod() {
		_start = true;
	}
//endregion
	
	public void setBackgroundData(float backgroundSpeed, int backgroundLevel, int phoneLevel, float meters){
		this.backgroundLevel = backgroundLevel;
		this._backgroundSpeed = backgroundSpeed;
		this._meters = meters;
		this.phoneLevel = phoneLevel;
	}
}
