package com.exam.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.entity.Entity;

public class EntityManager {

	private List<Entity> _entities = new CopyOnWriteArrayList<Entity>();
	
	public EntityManager() {}
	
	public void processEntity(Entity entity){
		_entities.add(entity);
	}
	
	public void sortEntities(){
		Collections.sort(_entities);
	}
	
	public void removeEntity(Entity entity){
		
		_entities.remove(entity); // no need to update this entity anymore!
	}
	
	public void update(float deltaTime){
		for (Entity entity : _entities) {
			entity.update(deltaTime);
		}
	}
	
	public void render(SpriteBatch spriteBatch){
		for (Entity entity : _entities) {
			entity.render(spriteBatch);
		}
	}

}
