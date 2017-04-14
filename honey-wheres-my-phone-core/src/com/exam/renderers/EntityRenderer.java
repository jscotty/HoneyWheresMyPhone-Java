package com.exam.renderers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.exam.entity.Entity;

public class EntityRenderer implements Renderer {

	private Stage stage;
	private List<Entity> entities; // using list for easy removing entities in the middle.
	
	public EntityRenderer(Stage stage) {
		this.stage = stage;
	}
	
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
		stage.addActor(entity);
	}

	@Override
	public void render(SpriteBatch batch) {
		stage.act();
		stage.draw();
		
		update();
	}
	
	private void update(){
		for (Entity entity : entities) {
			entity.update();
		}
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
	
	
	/**
	 * @return list of entities (Entity)
	 */
	public List<Entity> getEntities(){
		return entities;
	}
	
}
