package com.exam.items;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.handlers.EntityManager;
import com.exam.project.Main;

public class ItemManager {

	private final int[][] itemRows = new int[][]{
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
	private float time = 0;
	private float speed = 2f;
	private float delay = 2f;
	
	public ItemManager(World world, EntityManager manager) {
		this.world = world;
		this.entityManager = manager;
		random = new Random();
		
		spawn();
	}
	
	public void update(float deltaTime){
		time+= deltaTime;
		speed += deltaTime *0.1f;
		delay -= deltaTime *0.1f;
		if(time > delay){
			spawn();
			time = 0;
		}
	}
	
	private void spawn(){
		int randomRow = random.nextInt(itemRows.length);
		spawnItems(randomRow);
	}
	
	private void spawnItems(int index){
		int[] row = itemRows[index];
		for (int i = 0; i < row.length; i++) {
			float xPosition = ((screenWidth/row.length) * (i)) + border;
			System.out.println(xPosition);
			Item item;
			if(row[i] == 1){
				int randomItem = random.nextInt(ItemType.values().length);
				item = new Item(ItemType.values()[randomItem], i, world, new Vector2(xPosition,-100), BodyType.DynamicBody, entityManager, speed);
			}
			
		}
	}

}
