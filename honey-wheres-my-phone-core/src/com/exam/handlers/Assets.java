package com.exam.handlers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.toolbox.AnimationType;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Assets {

	public static boolean isFinishedLoadingAssets = false;
	
	private HashMap<SpriteType, TextureRegion> _textures; // HashMap is like a Dictionary in C# and is powerful by it searching by Object element.
	private HashMap<AnimationType, TextureRegion[]> _animations; // HashMap is like a Dictionary in C# and is powerful by it searching by Object element.

	private int _assetsLoadedCount = 0;
	private int _animationLoadedCount = 0;
	
	private int _percentage = 0;
	private boolean loadAnimations = false;
	
	/**
	 * Constructor for initialization
	 */
	public Assets() {
		_textures = new HashMap<SpriteType, TextureRegion>();
		_animations = new HashMap<AnimationType, TextureRegion[]>();
	}
	
	/**
	 * load asset.
	 */
	public void load(){
		if(loadAnimations){
			loadAnimation();
			_percentage = (int)(((float)_animationLoadedCount / (float)AnimationType.values().length)*100); 
		} else {
			loadAsset();
			_percentage = (int)(((float)_assetsLoadedCount / (float)SpriteType.values().length)*100); 
		}
		
	}
	
	/**
	 * load next asset. 
	 * Asset count: _assetsLoadedCount and maximum count of SpriteType.values().length
	 */
	private void loadAsset(){
		if(_assetsLoadedCount > SpriteType.values().length-1){
			loadAnimations = true;
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
	 * load next animation. 
	 * Asset count: _assetsLoadedCount and maximum count of SpriteType.values().length
	 */
	private void loadAnimation(){
		if(_animationLoadedCount > AnimationType.values().length-1){
			isFinishedLoadingAssets = true;
			return;
		}
		AnimationType type = AnimationType.values()[_animationLoadedCount];
		_animations.put(type, SpriteSheetReaderShoebox.getTexturesFromAtlas(type));

		_animationLoadedCount++;
		
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
		isFinishedLoadingAssets = true;
	}
	
	/** 
	 * Receive 'pre'loaded TextureRegion.
	 * @param SpriteType for searching in HashMap
	 * @return loaded TextureRegion
	 */
	public TextureRegion getTexture(SpriteType type){
		return _textures.get(type);
	}

	public TextureRegion[] getAnimation(AnimationType type){
		return _animations.get(type);
	}
	
	/**
	 * @return percentage of loaded assets
	 */
	public int getPercentage() {
		return _percentage;
	}
	
	public boolean isLoadAnimations() {
		return loadAnimations;
	}

}
