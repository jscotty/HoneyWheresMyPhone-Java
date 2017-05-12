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

	private final int[][] itemRows_till100 = new int[][]{
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

	private World world;
	private EntityManager entityManager;
	private Random random;
	
	private final float border = 80.0f;
	private float screenWidth = Main.WIDTH - border;
	private float speedMutliplier;
	private float speed = 2f;
	private float delay = 5f;
	private float meters;
	private boolean reverse = false;
	
	private List<Item> itemsDestroyed = new ArrayList<Item>();
	private List<Item> itemsInField = new CopyOnWriteArrayList<Item>();
	
	public ItemManager(World world, EntityManager manager) {
		this.world = world;
		this.entityManager = manager;
		random = new Random();
	}
	
	public void update(float deltaTime){
		meters += deltaTime + speedMutliplier;
		System.out.println((speed - 2)/10);
		speed += deltaTime * 0.2f;
		speedMutliplier += 0.000025f;
		if(meters > delay){
			spawn();
			delay += 5;
			System.out.println(delay);
		}
	}
	
	private void spawn(){
		int randomRow = random.nextInt(itemRows_till100.length);
		spawnItems(randomRow);
	}
	
	private void spawnItems(int index){
		int[] row = itemRows_till100[index];
		for (int i = 0; i < row.length; i++) {
			float xPosition = ((screenWidth/row.length) * (i)) + border;
			if(row[i] == 1){
				Vector2 position = new Vector2(xPosition, -100);
				int randomItem = random.nextInt(ItemType.values().length);
				if(reverse){
					
				} else { 
					Item item = (Item) new Item(ItemType.values()[randomItem], world, position, BodyType.DynamicBody, entityManager, speed, this).addBodyBox(50,50, position.x ,position.y, USER_DATA);
					itemsInField.add(item);
				}
			}
			
		}
	}
	
	public void removeItem(Item item){
		item.disableBody();
		itemsDestroyed.add(item);
		entityManager.removeEntity(item);
		itemsInField.remove(item);
	}

	@Override
	public void beginContact(Contact contact) {
		for (Item item : itemsInField) {
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
		return (int)meters;
	}

}
