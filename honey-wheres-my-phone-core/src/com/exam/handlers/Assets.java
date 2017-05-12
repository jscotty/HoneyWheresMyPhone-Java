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
	private Main main;
	
	private HashMap<SpriteType, TextureRegion> textures; // HashMap is like a Dictionary in C# and is powerful by it searching by Object element.

	private int assetsLoadedCount = 0;
	
	private int percentage = 0;
	public Assets() {
		textures = new HashMap<SpriteType, TextureRegion>();
		
	}
	
	public void load(){
		loadAsset();
		
		percentage = (int)(((float)assetsLoadedCount / (float)SpriteType.values().length)*100); 
	}
	
	private void loadAsset(){
		if(assetsLoadedCount > SpriteType.values().length-1){
			isFinishedLoading = true;
			return;
		}
		SpriteType type = SpriteType.values()[assetsLoadedCount];
		if(type.getAtlas() == ""){
			// can not load texture region because there is no atlas.
			Texture texture = new Texture(Gdx.files.internal(type.getSpriteName()));
			textures.put(type, new TextureRegion(texture));

			assetsLoadedCount++;
		} else {
			textures.put(type, SpriteSheetReaderShoebox.getTextureFromAtlas(type));

			assetsLoadedCount++;
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
				textures.put(type, new TextureRegion(texture));

				assetsLoadedCount++;
			} else {
				textures.put(type, SpriteSheetReaderShoebox.getTextureFromAtlas(type));

				assetsLoadedCount++;
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
		return textures.get(type);
	}
	
	public int getPercentage() {
		return percentage;
	}

}
