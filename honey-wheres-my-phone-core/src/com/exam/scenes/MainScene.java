package com.exam.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.assets.AnimationType;
import com.exam.assets.AudioType;
import com.exam.assets.SpriteType;
import com.exam.background.BackgroundManager;
import com.exam.entity.EntityManager;
import com.exam.entity.Hook;
import com.exam.font.FontType;
import com.exam.gui.GuiManager;
import com.exam.gui.GuiButton;
import com.exam.gui.GuiText;
import com.exam.handlers.GameEvent;
import com.exam.handlers.GameEventListener;
import com.exam.items.ItemManager;
import com.exam.managers.GameManager;
import com.exam.managers.SoundManager;
import com.exam.panels.EndPanel;
import com.exam.panels.PausePanel;
import com.exam.project.Main;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class MainScene extends Scene implements GameEventListener{
	
	private World _world;
	private EntityManager _entityManager;
	private GuiManager _guiManager;

	private Hook _hook;
	private GuiText _metersText;
	private GuiText _cashText;
	private ItemManager _itemManager;
	private GuiButton _pauseButton;
	private PausePanel _pausePanel;
	private EndPanel _endPanel;
	
	private BackgroundManager _backgroundManager;

	/**
	 * Constructor for initialization.
	 * @param manager
	 */
	protected MainScene(SceneManager manager) {
		super(manager);
		_world = new World(new Vector2(0f,-10f), true);
		_entityManager = new EntityManager();
		_guiManager = new GuiManager();
		_itemManager = new ItemManager(_world, _entityManager);
		_world.setContactListener(_itemManager);
		
		_backgroundManager = new BackgroundManager(_entityManager);
		
		_hook = (Hook) new Hook(new Vector2(200, 2000), AnimationType.ARM_WIGGLE, AnimationType.HAND_REACHING, _entityManager).addBodyCircle(_world, BodyType.KinematicBody, 45, 195, 1475, "");
		_hook.addHandler(_backgroundManager);
		_hook.addHandler(_itemManager);
		_backgroundManager.addListener(_hook);
		_backgroundManager.addListener(this);
		_itemManager.addListener(_hook);
		_pauseButton = new GuiButton(600, 1200, SpriteType.BUTTON_PAUSE_IDLE, SpriteType.BUTTON_PAUSE_PRESSED, pHudCamera, _guiManager);
		_pausePanel = new PausePanel(pHudCamera, pSceneManager);
		_endPanel = new EndPanel(pHudCamera, pSceneManager);

		_metersText = new GuiText(50, 50, _guiManager, FontType.SUPERCELL_MAGIC).addShadow(-5, new Color(0,0,0,1));
		_cashText = new GuiText(50, Main.HEIGHT-50, _guiManager, FontType.SUPERCELL_MAGIC).addShadow(-5, new Color(0,0,0,1));
		
		SoundManager.playAudio(AudioType.GAME_AUDIO);
		
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
		_backgroundManager.update(deltaTime);
		
		_itemManager.setBackgroundData(_backgroundManager.getSpeed(), _backgroundManager.getBackgroundLevel(), _backgroundManager.getPhoneLevel(), _backgroundManager.getMeters());
		_metersText.setText(_backgroundManager.getMeters() + "/" + GameManager.getMaximumDepth());
		_cashText.setText("$ " + GameManager.getMoney());
		
		_endPanel.update(deltaTime);
		
	}

	@Override
	public void render() {
		pSpriteBatch.setProjectionMatrix(pCamera.combined);
		pSpriteBatch.begin();
		_entityManager.render(pSpriteBatch); // let the entity manager render all entities.
		pSpriteBatch.end();
		
		pSpriteBatch.setProjectionMatrix(pHudCamera.combined);
		pSpriteBatch.begin();
		_guiManager.render(pSpriteBatch); // let the gui manager render all guis.
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
