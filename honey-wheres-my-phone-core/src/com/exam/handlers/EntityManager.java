package com.exam.handlers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.entity.Entity;

public class EntityManager {

	private World world;
	private List<Entity> entities = new ArrayList<Entity>();
	
	public EntityManager(World world) {
		
	}
	
	public void processEntity(Entity entity){
		entities.add(entity);
	}
	
	public void update(float deltaTime){
		for (Entity entity : entities) {
			entity.update(deltaTime);
		}
	}
	
	public void render(SpriteBatch batch){
		for (Entity entity : entities) {
			batch.draw(entity.getTexture(), entity.getX(), entity.getY(), entity.getOriginX(), entity.getOriginY(),
					entity.getWidth(), entity.getHeight(),entity.getScaleX(), entity.getScaleY(), entity.getAngle());
		}
	}

}
