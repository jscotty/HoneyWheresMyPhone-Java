package com.exam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.exam.entity.Background;
import com.exam.entity.Entity;
import com.exam.entity.Hook;
import com.exam.renderers.EntityRenderer;
import com.exam.renderers.GUIRenderer;
import com.exam.toolbox.SpriteType;

public class MainScene extends Scene {

    private Stage _stage;
    private Hook _hook;
    private EntityRenderer _entityRenderer;
    private GUIRenderer _guiRenderer;
    private Background _background;
    private Entity _backgroundOverlay;

    /**
     * Initialization
     * @param sceneManager
     */
    public MainScene(SceneManager sceneManager) {
	super(sceneManager);

    }

    @Override
    public void init() {
	_stage = new Stage(new ScreenViewport());
	_hook = new Hook(SpriteType.PROPS_ARM, 50, 80);

	_entityRenderer = new EntityRenderer(_stage);
	_guiRenderer = new GUIRenderer(_stage);
	_background = (Background) new Background(SpriteType.BACKGOUND_PLYAY_01, 0, 0).setObjectOrigin(0, 0);
	_backgroundOverlay = (Entity) new Entity(SpriteType.BACKGOUND_PLYAY_01_OVERLAY, 0, 0).setObjectOrigin(0, 0);
	_entityRenderer.init();
	_guiRenderer.init();

	_entityRenderer.processEntity(_background);
	_entityRenderer.processEntity(_backgroundOverlay);
	_entityRenderer.processEntity(_hook);

	addButtonListeners();

	Gdx.input.setInputProcessor(_stage);

    }

    /**
     * add events to buttons
     */
    private void addButtonListeners() {

    }

    @Override
    public void render(SpriteBatch batch) {
	_entityRenderer.render(batch);
	_guiRenderer.render(batch);
	_background.scroll(1);
    }

    @Override
    public void dispose() {
	_entityRenderer.dispose();
	_stage.clear();
    }

}
