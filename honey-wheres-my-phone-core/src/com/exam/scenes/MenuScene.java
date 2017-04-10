package com.exam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.project.Main;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

public class MenuScene extends Scene{
	
	/**
	 * Initialization
	 * @param sceneManager
	 */
	public MenuScene(SceneManager sceneManager) {
		super(sceneManager);
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void render(SpriteBatch batch) {
		TextureRegion text = SpriteSheetReaderShoebox.getTextureFromAtlas(SpriteType.BUTTON_START_IDLE);
		if(Gdx.input.isButtonPressed(0)){
			text = SpriteSheetReaderShoebox.getTextureFromAtlas(SpriteType.BUTTON_START_PRESSED);
		}
		batch.draw(text,Main.WIDTH/2-text.getRegionWidth()/2,Main.HEIGHT/2-text.getRegionHeight()/2);
	}

	@Override
	public void dispose() {
		
	}

}
