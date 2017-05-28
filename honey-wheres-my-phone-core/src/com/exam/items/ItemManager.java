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
import com.exam.entity.Entity;
import com.exam.entity.EntityManager;
import com.exam.handlers.GameEventHandler;
import com.exam.managers.GameManager;
import com.exam.project.Main;
import com.exam.tween.EntityAccessor;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class ItemManager extends GameEventHandler implements ContactListener{

	public static final String ITEM_DATA = "Item";
	public static final String PHONE_DATA = "Item";
	private final int DELAY = 5;

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
	private float yPosition = -100;
	private int backgroundLevel;
	private int phoneLevel = 0;
	private int currentPhoneLevel = 0;
	
	private List<ItemType> _itemsDestroyed = new ArrayList<ItemType>();
	private List<Item> _itemsInField = new ArrayList<Item>();

	private List<List<ItemType>> _itemLevels = new ArrayList<List<ItemType>>();
	private TweenManager _tweenManager;
	
	/**
	 * Constructor for initialization
	 * @param world for Item Box2D physics
	 * @param manager to process items
	 */
	public ItemManager(World world, EntityManager manager) {
		Tween.registerAccessor(Entity.class, new EntityAccessor());
		this._world = world;
		this._entityManager = manager;
		this._tweenManager = new TweenManager();
		_random = new Random();
		initItemLists();
	}
	
	private void initItemLists() {
		for (int i = 0; i < 3; i++) { // create 3 lists
			_itemLevels.add(new ArrayList<ItemType>());
		}
		for (ItemType item : ItemType.values()) {
			_itemLevels.get(item.getLevel()-1).add(item); // add item to preferred level
		}
	}
	
	/**
	 * Update item manager to manage speed and spawning.
	 * @param deltaTime
	 */
	public void update(float deltaTime){
		if(!_start) return;
		_tweenManager.update(deltaTime);
		calculateSpeed();
		
		if(phoneLevel > currentPhoneLevel){
			spawnPhone();
			currentPhoneLevel++;
		}
		
		if(GameManager.isHit){
			if(_meters <= _delay){
				spawn();
				_delay -= DELAY;
			}
		} else {
			if(_meters > _delay){
				spawn();
				_delay += DELAY;
			}
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
		
		_delay+=DELAY*2;
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
				Vector2 position;
				int randomItem = _random.nextInt(_itemLevels.get(backgroundLevel).size());
				ItemType itemType;
				Item item = null;
				if(GameManager.isHit){ 
					position = new Vector2(xPosition, Main.HEIGHT+ 100);
					 itemType = _itemLevels.get(backgroundLevel).get(randomItem);
					 item = (Item) new Item(itemType, position, _entityManager, _speed, this).addBodyBox(_world, BodyType.DynamicBody, 40,40, position.x ,position.y);
				} else {
					position = new Vector2(xPosition, yPosition);
					 itemType = _itemLevels.get(backgroundLevel).get(randomItem);
					 item = (Item) new Item(itemType, position, _entityManager, _speed, this).addBodyBox(_world, BodyType.DynamicBody, 40,40, position.x ,position.y);
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
		if(!GameManager.isHit) // add to destroyed list if it had to be spawned back in reverse mode
			_itemsDestroyed.add(item.getItemType()); // still adding to list for catching up later
		_entityManager.removeEntity(item); // remove from manager.
		_itemsInField.remove(item); // remove from items in field list.
	}

// region contactlistener methods
	@Override
	public void beginContact(Contact contact) {
		if(contact.getFixtureA().getUserData().getClass() == Item.class && contact.getFixtureB().getUserData().getClass() == Item.class){
			Item fixtureA = (Item) contact.getFixtureA().getUserData();
			Item fixtureB = (Item) contact.getFixtureB().getUserData();
			fixtureA.bounce();
			fixtureB.bounce();
			return;
		}
		
		GameManager.isHit = true;
		yPosition = Main.HEIGHT + -(yPosition);
		if(contact.getFixtureA().getUserData().getClass() == Item.class){
			Item item = (Item) contact.getFixtureA().getUserData();
			item.animateAway(_tweenManager);
			GameManager.addMoney((int)item.getScore());
		} else if(contact.getFixtureB().getUserData().getClass() == Item.class){
			Item item = (Item) contact.getFixtureB().getUserData();
			item.animateAway(_tweenManager);
			GameManager.addMoney((int)item.getScore());
		}
		gameReverse();
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
