package com.exam.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class EntityManager {

	private List<Entity> _entities; // list of entities to draw and update
	private List<Entity> _entitiesToRemove; // list of entities that have to be removed after all entities are updated to prevent null pointers in a for loop.

	public EntityManager() {
		_entities = new ArrayList<Entity>();
		_entitiesToRemove = new ArrayList<Entity>();
	}

	/**
	 * process entity to be rendered and update.
	 * @param entity to process
	 */
	public void processEntity(Entity entity) {
		_entities.add(entity);
		sortEntities();
	}

	/**
	 * Sorting entities in order of entity.zIndex.
	 * Sorts for render ordering.
	 */
	public void sortEntities() {
		Collections.sort(_entities);
	}

	/**
	 * Remove parameter entity from list.
	 * @param entity to remove
	 */
	public void removeEntity(Entity entity) {
		_entitiesToRemove.add(entity);
	}

	/**
	 * Update all processed entities.
	 * Updates every frame.
	 * @param deltaTime time between every frame
	 */
	public void update(float deltaTime) {
		for (Entity entity : _entities) {
			entity.update(deltaTime);
		}

		if (_entitiesToRemove.size() == 0) 
			return; // no items to remove.

		for (Entity entity : _entitiesToRemove) {
			_entities.remove(entity);
		}
		_entitiesToRemove.clear();
	}

	/**
	 * Render all processed entities
	 * Renders every frame.
	 * @param spriteBatch to call draw function
	 */
	public void render(SpriteBatch spriteBatch) {
		for (Entity entity : _entities) {
			entity.render(spriteBatch);
		}
	}

}
