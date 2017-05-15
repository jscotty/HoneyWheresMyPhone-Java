package com.exam.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Assets {
	
	public static boolean isFinishedLoading = false;
	
	private HashMap<SpriteType, TextureRegion> _textures; // HashMap is like a Dictionary in C# and is powerful by it searching by Object element.

	private int _assetsLoadedCount = 0;
	
	private int _percentage = 0;
	
	/**
	 * Constructor for initialization
	 */
	public Assets() {
		_textures = new HashMap<SpriteType, TextureRegion>();
	}
	
	/**
	 * load asset.
	 */
	public void load(){
		loadAsset();
		
		_percentage = (int)(((float)_assetsLoadedCount / (float)SpriteType.values().length)*100); 
	}
	
	/**
	 * load next asset. 
	 * Asset count: _assetsLoadedCount and maximum count of SpriteType.values().length
	 */
	private void loadAsset(){
		if(_assetsLoadedCount > SpriteType.values().length-1){
			isFinishedLoading = true;
			return;
		}
		SpriteType type = SpriteType.values()[_assetsLoadedCount];
		if(type.getAtlas() == ""){
			// can not load texture region because there is no atlas.
			Texture texture = new Texture(Gdx.files.internal(type.getSpriteName()));
			_textures.put(type, new TextureRegion(texture));

			_assetsLoadedCount++;
		} else {
			_textures.put(type, SpriteSheetReaderShoebox.getTextureFromAtlas(type));

			_assetsLoadedCount++;
		}
	}
	
	/**
	 * Loads all assets
	 */
	@SuppressWarnings("unused")
	private void loadAllAssets(){
		// looping through all textureRegionElements.
		for (SpriteType type : SpriteType.values()) {
			if(type.getAtlas() == ""){
				// can not load texture region because there is no atlas.
				Texture texture = new Texture(Gdx.files.internal(type.getSpriteName()));
				_textures.put(type, new TextureRegion(texture));

				_assetsLoadedCount++;
			} else {
				_textures.put(type, SpriteSheetReaderShoebox.getTextureFromAtlas(type));

				_assetsLoadedCount++;
			}
		}
		isFinishedLoading = true;
	}
	
	/** 
	 * Receive 'pre'loaded TextureRegion.
	 * @param SpriteType for searching in HashMap
	 * @return loaded TextureRegion
	 */
	public TextureRegion getTexture(SpriteType type){
		return _textures.get(type);
	}
	
	/**
	 * @return percentage of loaded assets
	 */
	public int getPercentage() {
		return _percentage;
	}

}
