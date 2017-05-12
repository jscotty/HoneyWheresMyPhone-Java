package com.exam.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.entity.Background;
import com.exam.entity.Hook;
import com.exam.font.FontType;
import com.exam.gui.Button;
import com.exam.gui.GuiText;
import com.exam.handlers.EntityManager;
import com.exam.handlers.GUIManager;
import com.exam.handlers.MyInput;
import com.exam.handlers.ObjectContactListener;
import com.exam.items.ItemManager;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class MainScene extends Scene{
	
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private EntityManager entityManager;
	private GUIManager guiManager;
	
	private Background background;
	private Hook hook;
	private GuiText metersText;
	private ItemManager itemManager;

	protected MainScene(SceneManager manager) {
		super(manager);
		world = new World(new Vector2(0f,-10f), true);
		world.setContactListener(new ObjectContactListener());
		debugRenderer = new Box2DDebugRenderer();
		entityManager = new EntityManager();
		guiManager = new GUIManager();
		itemManager = new ItemManager(world, entityManager);
		world.setContactListener(itemManager);
		
		
		background = new Background(world, new Vector2(Main.WIDTH/2,Main.HEIGHT/2), BodyType.StaticBody, SpriteType.BACKGOUND_PLYAY_01, entityManager);
		background.addOverlay(SpriteType.BACKGOUND_PLYAY_01_OVERLAY);
		hook = (Hook) new Hook(world, new Vector2(200, 1200), BodyType.KinematicBody, SpriteType.PROPS_ARM, entityManager).addBodyCircle(40, 195, 950, "");
		
		metersText = new GuiText(50, 50, guiManager, FontType.SUPERCELL_MAGIC).addShadow(-5, new Color(0,0,0,1));
		
		entityManager.sortEntities();
	}

	@Override
	public void handleInput() {
		
	}

	@Override
	public void update(float deltaTime) {
		handleInput();
		world.step(Main.STEP, 1, 1);
		entityManager.update(deltaTime);
		itemManager.update(deltaTime);
		
		metersText.setMessage(itemManager.getMeters() + "/1000");
	}

	@Override
	public void render() {
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		entityManager.render(spriteBatch);
		spriteBatch.end();
		
		spriteBatch.setProjectionMatrix(hudCamera.combined);
		spriteBatch.begin();
		guiManager.render(spriteBatch);
		spriteBatch.end();
		
		debugRenderer.render(world, camera.combined);
	}

	@Override
	public void dispose() {
		
	}

}
