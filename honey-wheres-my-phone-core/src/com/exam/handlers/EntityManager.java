package com.exam.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.entity.Entity;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class EntityManager {

	private List<Entity> _entities;
	private List<Entity> _entitiesToRemove;
	
	public EntityManager(){
		_entities = new ArrayList<Entity>();
		_entitiesToRemove = new ArrayList<Entity>();
	}
	
	/**
	 * process entity to be rendered and update.
	 * @param entity
	 */
	public void processEntity(Entity entity){
		_entities.add(entity);
	}
	
	/**
	 * Sorting entities in order of entity.zIndex.
	 * Sorts for render ordering.
	 */
	public void sortEntities(){
		Collections.sort(_entities);
	}
	
	/**
	 * Remove parameter entity from list.
	 * @param entity
	 */
	public void removeEntity(Entity entity){
		_entitiesToRemove.add(entity);
	}
	
	/**
	 * Update all processed entities.
	 * Updates every frame.
	 * @param deltaTime
	 */
	public void update(float deltaTime){
		for (Entity entity : _entities) {
			entity.update(deltaTime);
		}
		
		if(_entitiesToRemove.size() == 0) return;
		
		for (Entity entity : _entitiesToRemove) {
			_entities.remove(entity);
		}
		_entitiesToRemove.clear();
	}
	
	/**
	 * Render all processed entities
	 * Renders every frame.
	 * @param spriteBatch
	 */
	public void render(SpriteBatch spriteBatch){
		for (Entity entity : _entities) {
			entity.render(spriteBatch);
		}
	}

}
