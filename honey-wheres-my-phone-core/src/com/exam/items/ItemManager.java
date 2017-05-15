package com.exam.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.handlers.EntityManager;
import com.exam.project.Main;

public class ItemManager implements ContactListener{
	
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
	
	private List<Item> _itemsDestroyed = new ArrayList<Item>();
	private List<Item> _itemsInField = new CopyOnWriteArrayList<Item>();
	
	public ItemManager(World world, EntityManager manager) {
		this._world = world;
		this._entityManager = manager;
		_random = new Random();
	}
	
	public void update(float deltaTime){
		_meters += deltaTime + _speedMutliplier;
		System.out.println((_speed - 2)/10);
		_speed += deltaTime * 0.2f;
		_speedMutliplier += 0.000025f;
		if(_meters > _delay){
			spawn();
			_delay += 5;
			System.out.println(_delay);
		}
	}
	
	private void spawn(){
		int randomRow = _random.nextInt(ITEMROWS_TILL100.length);
		spawnItems(randomRow);
	}
	
	private void spawnItems(int index){
		int[] row = ITEMROWS_TILL100[index];
		for (int i = 0; i < row.length; i++) {
			float xPosition = ((_screenWidth/row.length) * (i)) + BORDER;
			if(row[i] == 1){
				Vector2 position = new Vector2(xPosition, -100);
				int randomItem = _random.nextInt(ItemType.values().length);
				if(_reverse){
					
				} else { 
					Item item = (Item) new Item(ItemType.values()[randomItem], _world, position, BodyType.DynamicBody, _entityManager, _speed, this).addBodyBox(50,50, position.x ,position.y, USER_DATA);
					_itemsInField.add(item);
				}
			}
			
		}
	}
	
	public void removeItem(Item item){
		item.disableBody();
		_itemsDestroyed.add(item);
		_entityManager.removeEntity(item);
		_itemsInField.remove(item);
	}

	@Override
	public void beginContact(Contact contact) {
		for (Item item : _itemsInField) {
			//item.reverse();
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

}
