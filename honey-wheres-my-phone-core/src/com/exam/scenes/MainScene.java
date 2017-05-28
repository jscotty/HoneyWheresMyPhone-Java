package com.exam.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.background.Background;
import com.exam.background.BackgroundManager;
import com.exam.entity.EntityManager;
import com.exam.entity.Hook;
import com.exam.font.FontType;
import com.exam.gui.GUIManager;
import com.exam.gui.GuiButton;
import com.exam.gui.GuiText;
import com.exam.handlers.GameEvent;
import com.exam.handlers.GameEventListener;
import com.exam.handlers.MyInput;
import com.exam.items.ItemManager;
import com.exam.managers.GameManager;
import com.exam.panels.EndPanel;
import com.exam.panels.PausePanel;
import com.exam.panels.UpgradePanel;
import com.exam.project.Main;
import com.exam.toolbox.AnimationType;
import com.exam.toolbox.SpriteType;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class MainScene extends Scene implements GameEventListener{
	
	private World _world;
	private Box2DDebugRenderer _debugRenderer;
	private EntityManager _entityManager;
	private GUIManager _guiManager;

	private Hook _hook;
	private GuiText _metersText;
	private GuiText _cashText;
	private ItemManager _itemManager;
	private GuiButton _pauseButton;
	private PausePanel _pausePanel;
	private EndPanel _endPanel;
	
	private BackgroundManager backgroundManager;

	/**
	 * Constructor for initialization.
	 * @param manager
	 */
	protected MainScene(SceneManager manager) {
		super(manager);
		_world = new World(new Vector2(0f,-10f), true);
		_debugRenderer = new Box2DDebugRenderer();
		_entityManager = new EntityManager();
		_guiManager = new GUIManager();
		_itemManager = new ItemManager(_world, _entityManager);
		_world.setContactListener(_itemManager);
		
		backgroundManager = new BackgroundManager(_entityManager);
		
		_hook = (Hook) new Hook(new Vector2(200, 2000), AnimationType.ARM_WIGGLE, AnimationType.HAND_REACHING, _entityManager).addBodyCircle(_world, BodyType.KinematicBody, 45, 195, 1475, "");
		_hook.addHandler(backgroundManager);
		_hook.addHandler(_itemManager);
		backgroundManager.addListener(_hook);
		backgroundManager.addListener(this);
		_itemManager.addListener(_hook);
		_pauseButton = new GuiButton(600, 1200, SpriteType.BUTTON_PAUSE_IDLE, SpriteType.BUTTON_PAUSE_PRESSED, pHudCamera, _guiManager);
		_pausePanel = new PausePanel(pHudCamera, pSceneManager);
		_endPanel = new EndPanel(pHudCamera, pSceneManager);

		_metersText = new GuiText(50, 50, _guiManager, FontType.SUPERCELL_MAGIC).addShadow(-5, new Color(0,0,0,1));
		_cashText = new GuiText(50, Main.HEIGHT-50, _guiManager, FontType.SUPERCELL_MAGIC).addShadow(-5, new Color(0,0,0,1));
		
		_entityManager.sortEntities();
	}

	@Override
	public void handleInput() {
		if(_endPanel.isActive())return;
		if(_pauseButton.isClicked()){
			_pausePanel.startAnimation();
		}
	}

	@Override
	public void update(float deltaTime) {
		if(GameManager.isPaused) {
			_pausePanel.update(deltaTime); // only need to update the pause screen 
			return;
		}
		handleInput();
		_world.step(Main.STEP, 1, 1);
		_entityManager.update(deltaTime);
		_guiManager.update(deltaTime);
		_itemManager.update(deltaTime);
		backgroundManager.update(deltaTime);
		
		_itemManager.setBackgroundData(backgroundManager.getSpeed(), backgroundManager.getBackgroundLevel(), backgroundManager.getPhoneLevel(), backgroundManager.getMeters());
		_metersText.setText(backgroundManager.getMeters() + "/" + GameManager.getMaximumDepth());
		_cashText.setText("$ " + GameManager.getMoney());
		
		_endPanel.update(deltaTime);
		
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

		_pausePanel.render(pSpriteBatch);
		_endPanel.render(pSpriteBatch);
		//_debugRenderer.render(_world, pCamera.combined);
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void gameStart(GameEvent event) {}

	@Override
	public void gameEnd(GameEvent event) {
		_endPanel.startAnimation();
	}

	@Override
	public void gameReverse(GameEvent event) {}

}
