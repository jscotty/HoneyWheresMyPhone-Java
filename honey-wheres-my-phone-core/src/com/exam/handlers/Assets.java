package com.exam.handlers;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

public class Assets {
	
	public static boolean isFinishedLoading = false;
	
	private HashMap<SpriteType, TextureRegion> textures; // HashMap is like a Dictionary in C# and is powerful by it searching by Object element.

	public Assets() {
		textures = new HashMap<SpriteType, TextureRegion>();
		
		loadAllAssets();
	}
	
	/**
	 * Loads all assets
	 */
	private void loadAllAssets(){
		// looping through all textureRegionElements.
		for (SpriteType type : SpriteType.values()) {
			if(type.getAtlas() == "") return; // can not load textures.
			textures.put(type, SpriteSheetReaderShoebox.getTextureFromAtlas(type));
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

}
