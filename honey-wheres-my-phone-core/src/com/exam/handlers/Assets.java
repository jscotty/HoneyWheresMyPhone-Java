package com.exam.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.project.Main;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

public class Assets {
	
	public static boolean isFinishedLoading = false;
	
	private HashMap<SpriteType, TextureRegion> _textures; // HashMap is like a Dictionary in C# and is powerful by it searching by Object element.

	private int _assetsLoadedCount = 0;
	
	private int _percentage = 0;
	public Assets() {
		_textures = new HashMap<SpriteType, TextureRegion>();
		
	}
	
	public void load(){
		loadAsset();
		
		_percentage = (int)(((float)_assetsLoadedCount / (float)SpriteType.values().length)*100); 
	}
	
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
	
	public int getPercentage() {
		return _percentage;
	}

}
