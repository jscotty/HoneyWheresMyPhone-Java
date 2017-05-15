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
import com.exam.gui.GuiButton;
import com.exam.gui.GuiText;
import com.exam.handlers.EntityManager;
import com.exam.handlers.GUIManager;
import com.exam.handlers.MyInput;
import com.exam.items.ItemManager;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class MainScene extends Scene{
	
	private World _world;
	private Box2DDebugRenderer _debugRenderer;
	private EntityManager _entityManager;
	private GUIManager _guiManager;
	
	private Background _background;
	private Hook _hook;
	private GuiText _metersText;
	private ItemManager _itemManager;

	protected MainScene(SceneManager manager) {
		super(manager);
		_world = new World(new Vector2(0f,-10f), true);
		_debugRenderer = new Box2DDebugRenderer();
		_entityManager = new EntityManager();
		_guiManager = new GUIManager();
		_itemManager = new ItemManager(_world, _entityManager);
		_world.setContactListener(_itemManager);
		
		
		_background = new Background(_world, new Vector2(Main.WIDTH/2,Main.HEIGHT/2), BodyType.StaticBody, SpriteType.BACKGOUND_PLYAY_01, _entityManager);
		_background.addOverlay(SpriteType.BACKGOUND_PLYAY_01_OVERLAY);
		_hook = (Hook) new Hook(_world, new Vector2(200, 1200), BodyType.KinematicBody, SpriteType.PROPS_ARM, _entityManager).addBodyCircle(40, 195, 950, "");
		
		_metersText = new GuiText(50, 50, _guiManager, FontType.SUPERCELL_MAGIC).addShadow(-5, new Color(0,0,0,1));
		
		_entityManager.sortEntities();
	}

	@Override
	public void handleInput() {
		
	}

	@Override
	public void update(float deltaTime) {
		handleInput();
		_world.step(Main.STEP, 1, 1);
		_entityManager.update(deltaTime);
		_itemManager.update(deltaTime);
		
		_metersText.setMessage(_itemManager.getMeters() + "/1000");
	}

	@Override
	public void render() {
		pSpriteBatch.setProjectionMatrix(pCamera.combined);
		pSpriteBatch.begin();
		_entityManager.render(pSpriteBatch);
		pSpriteBatch.end();
		
		pSpriteBatch.setProjectionMatrix(pHudCamera.combined);
		pSpriteBatch.begin();
		_guiManager.render(pSpriteBatch);
		pSpriteBatch.end();
		
		_debugRenderer.render(_world, pCamera.combined);
	}

	@Override
	public void dispose() {
		
	}

}
