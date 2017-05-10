package com.exam.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.entity.Entity;

public class EntityManager {

	private List<Entity> entities = new ArrayList<Entity>();
	
	public EntityManager() {}
	
	public void processEntity(Entity entity){
		entities.add(entity);
	}
	
	public void sortEntities(){

		Collections.sort(entities);
	}
	
	public void update(float deltaTime){
		for (Entity entity : entities) {
			entity.update(deltaTime);
		}
	}
	
	public void render(SpriteBatch spriteBatch){
		for (Entity entity : entities) {
			entity.render(spriteBatch);
		}
	}

}
