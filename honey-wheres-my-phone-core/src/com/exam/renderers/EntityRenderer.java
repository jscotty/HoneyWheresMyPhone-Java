package com.exam.renderers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.exam.entity.Entity;

public class EntityRenderer implements IRenderer {

    private Stage _stage;
    private List<Entity> _entities;

    public EntityRenderer(Stage stage) {
	this._stage = stage;
    }

    @Override
    public void init() {
	_entities = new ArrayList<Entity>();
    }

    public void processEntities(Entity[] entities) {
	for (int i = 0; i < entities.length; i++) {
	    processEntity(entities[i]);
	}
    }

    public void processEntities(List<Entity> entities) {
	for (Entity entity : entities) {
	    processEntity(entity);
	}
    }

    public void processEntity(Entity entity) {
	_entities.add(entity);
	_stage.addActor(entity);
    }

    @Override
    public void render(SpriteBatch batch) {

	update();
    }

    private void update() {
	for (Entity entity : _entities) {
	    entity.update();
	}
    }

    @Override
    public void dispose() {
	_stage.dispose();
    }

    /**
     * @return list of entities (Entity)
     */
    public List<Entity> getEntities() {
	return _entities;
    }

}
