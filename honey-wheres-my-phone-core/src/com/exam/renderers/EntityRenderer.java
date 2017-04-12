package com.exam.renderers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.entity.Entity;

public class EntityRenderer implements Renderer {

	private List<Entity> entities; // using list for easy removing entities in the middle.
	
	@Override
	public void Init() {
		entities = new ArrayList<Entity>();
	}
	
	public void processEntities(Entity[] entities){
		for (int i = 0; i < entities.length; i++) {
			processEntity(entities[i]);
		}
	}
	
	public void processEntities(List<Entity> entities){
		for (Entity entity : entities) {
			processEntity(entity);
		}
	}
	
	public void processEntity(Entity entity){
		entities.add(entity);
		entities.sort(entity);
	}

	@Override
	public void render(SpriteBatch batch) {
		for (Entity entity : entities) {
			entity.update();
			entity.draw(batch);
		}
		
	}

	@Override
	public void dispose() {
		
	}
	
	
	/**
	 * @return list of entities (Entity)
	 */
	public List<Entity> getEntities(){
		return entities;
	}
	
}
