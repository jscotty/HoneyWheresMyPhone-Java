package com.exam.scenes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.entity.Hook;
import com.exam.handlers.EntityManager;
import com.exam.handlers.MyInput;
import com.exam.handlers.ObjectContactListener;
import com.exam.toolbox.SpriteType;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class MainScene extends Scene{
	
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private Hook hook;
	private EntityManager entityManager;

	protected MainScene(SceneManager manager) {
		super(manager);
		world = new World(new Vector2(0f,-10f), true);
		world.setContactListener(new ObjectContactListener());
		debugRenderer = new Box2DDebugRenderer();
		entityManager = new EntityManager(world);
		
		hook = (Hook) new Hook(world, new Vector2(200, 1200), BodyType.KinematicBody, SpriteType.PROPS_ARM, main.getAssets()).addBodyCircle(60, 195, 950);
		entityManager.processEntity(hook);
	}

	@Override
	public void handleInput() {
	}

	@Override
	public void update(float deltaTime) {
		world.step(deltaTime, 6, 2);
		entityManager.update(deltaTime);
	}

	@Override
	public void render() {
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		entityManager.render(spriteBatch);
		spriteBatch.end();
		
		debugRenderer.render(world, camera.combined);
	}

	@Override
	public void dispose() {
		
	}

}
