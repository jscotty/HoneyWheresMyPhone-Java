package com.exam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.exam.handlers.Assets;
import com.exam.project.Main;

public class AssetsLoaderScene extends Scene {
	
	private Assets assets;
	private BitmapFont font;
	private String loadingText = "loading assets please wait";
	private String loadingDrawText = "loading assets please wait";
	private float dotTimer = 0;

	public AssetsLoaderScene(SceneManager manager) {
		super(manager);
		assets = Main.assets;
		font = new BitmapFont(Gdx.files.internal("font/supercell-magic.fnt"),Gdx.files.internal("font/supercell-magic.png"), false);
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		font.setScale(0.8f);
	}

	@Override
	public void handleInput() {

	}

	@Override
	public void update(float deltaTime) {
		assets.load();
		if(Assets.isFinishedLoading)sceneManager.pushScene(SceneManager.MENU);

		loadingDrawText = loadingText;
		for (int i = 0; i < (int)dotTimer; i++) {
			loadingDrawText += ".";
		}
		if(dotTimer > 3) dotTimer = 0;
		dotTimer+= 0.1f;
	}

	@Override
	public void render() {
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		font.draw(spriteBatch, loadingDrawText, 20, (Main.HEIGHT/2)+50);
		font.draw(spriteBatch, ""+assets.getPercentage()+"%", 300, Main.HEIGHT/2);
		spriteBatch.end();
	}

	@Override
	public void dispose() {

	}

}
