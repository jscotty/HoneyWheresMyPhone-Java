package com.exam.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.font.FontLoader;
import com.exam.handlers.Assets;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class AssetsLoaderScene extends Scene {
	
	private Assets _assets;
	private FontLoader _fontLoader;
	private BitmapFont _font;
	private TextureRegion _background;
	private String _loadingText = "loading assets please wait";
	private String _loadingDrawText = "loading assets please wait";
	private float _dotTimer = 0;

	/**
	 * Constructor for initialization
	 * @param manager
	 */
	public AssetsLoaderScene(SceneManager manager) {
		super(manager);
		_assets = Main.assets;
		_fontLoader = new FontLoader();
		_font = new BitmapFont(Gdx.files.internal("font/supercell-magic.fnt"),Gdx.files.internal("font/supercell-magic.png"), false);
		_font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		_font.setScale(0.8f);
		_background = new TextureRegion(new Texture(Gdx.files.internal("background/characterScreen.jpg")));
	}

	@Override
	public void handleInput() {

	}

	@Override
	public void update(float deltaTime) {
		_assets.load();
		if(Assets.isFinishedLoading){
			_fontLoader.loadFonts();
		}
		
		if(_fontLoader.isFontsLoaded())
			pSceneManager.setScene(SceneManager.MENU);

		_loadingDrawText = _loadingText;
		for (int i = 0; i < (int)_dotTimer; i++) {
			_loadingDrawText += ".";
		}
		if(_dotTimer > 3) _dotTimer = 0;
		_dotTimer+= 0.1f;
	}

	@Override
	public void render() {
		pSpriteBatch.setProjectionMatrix(pCamera.combined);
		pSpriteBatch.begin();
		pSpriteBatch.draw(_background, 0, 0);
		_font.draw(pSpriteBatch, _loadingDrawText, 20, (Main.HEIGHT/2)+50);
		_font.draw(pSpriteBatch, ""+_assets.getPercentage()+"%", 300, Main.HEIGHT/2);
		pSpriteBatch.end();
	}

	@Override
	public void dispose() {

	}

}
