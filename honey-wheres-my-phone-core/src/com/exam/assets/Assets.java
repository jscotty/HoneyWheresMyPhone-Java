package com.exam.assets;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.exam.toolbox.SpriteSheetReaderShoebox;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Assets {

	public static boolean isFinishedLoadingAssets = false;
	
	private HashMap<SpriteType, TextureRegion> _textures; // HashMap is like a Dictionary in C# and is powerful by it searching by Object element.
	private HashMap<AnimationType, TextureRegion[]> _animations; // HashMap is like a Dictionary in C# and is powerful by it searching by Object element.
	private HashMap<AudioType, Sound> _soundFiles; // HashMap is like a Dictionary in C# and is powerful by it searching by Object element.
	private HashMap<AudioType, Music> _musicFiles; // HashMap is like a Dictionary in C# and is powerful by it searching by Object element.

	//index
	private int _assetsLoadedCount = 0;
	private int _animationLoadedCount = 0;
	private int _audioLoadedCount = 0;
	
	private int _percentage = 0; // 0-100%
	private boolean _loadAnimations = false;
	private boolean _loadAudio = false;
	private int _totalAmountOfAssets = 0;
	
	/**
	 * Constructor for initialization
	 */
	public Assets() {
		_textures = new HashMap<SpriteType, TextureRegion>();
		_animations = new HashMap<AnimationType, TextureRegion[]>();
		_soundFiles = new HashMap<AudioType, Sound>();
		_musicFiles = new HashMap<AudioType, Music>();
		_totalAmountOfAssets = AudioType.values().length + SpriteType.values().length + AnimationType.values().length;
	}
	
	/**
	 * load asset and animations.
	 */
	public void load(){
		if(_loadAnimations){
			loadAnimation();
		} else if(_loadAudio) {
			loadAudio();
		} else{
			loadAsset();
		}
		_percentage = (int)(((float)(_animationLoadedCount + _audioLoadedCount + _assetsLoadedCount) / (float)_totalAmountOfAssets)*100); //total percentage
		
	}
	
	/**
	 * load next asset. 
	 * Asset count: _assetsLoadedCount and maximum count of SpriteType.values().length
	 */
	private void loadAsset(){
		if(_assetsLoadedCount > SpriteType.values().length-1){
			_loadAnimations = true;
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
			_loadAudio = true;
			_loadAnimations = false;
			return;
		}
		AnimationType type = AnimationType.values()[_animationLoadedCount];
		_animations.put(type, SpriteSheetReaderShoebox.getTexturesFromAtlas(type));

		_animationLoadedCount++;
		
	}
	
	/**
	 * Load next audio file.
	 * Check if audio is loopable, if audio type is loopable we'll list it in a music list
	 * otherwise we list it in a sound list.
	 */
	private void loadAudio(){
		if(_audioLoadedCount > AudioType.values().length-1){
			isFinishedLoadingAssets = true;
			_loadAudio = false;
			return;
		}
		AudioType type = AudioType.values()[_audioLoadedCount];
		if(type.isLooping())
			_musicFiles.put(type, Gdx.audio.newMusic(Gdx.files.internal(type.getAudioPath())));
		else
			_soundFiles.put(type, Gdx.audio.newSound(Gdx.files.internal(type.getAudioPath())));

		_audioLoadedCount++;
	}
	
	/**
	 * Loads all assets in one frame
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

	/**
	 * @param type
	 * @return Array of textureRegions to render an animation from.
	 */
	public TextureRegion[] getAnimation(AnimationType type){
		return _animations.get(type);
	}
	
	/**
	 * @param audio type to search for.
	 * @return audio file used for effects
	 */
	public Sound getAudio(AudioType type){
		if(type.isLooping())
			return null;
		return _soundFiles.get(type);
	}
	
	/**
	 * @param audio type to search for.
	 * @return loopable audio file
	 */
	public Music getMusic(AudioType type){
		if(!type.isLooping())
			return null;
		return _musicFiles.get(type);
	}
	
	/**
	 * @return percentage of loaded assets
	 */
	public int getPercentage() {
		return _percentage;
	}
	
	/**
	 * Used for loading screen text message.
	 * @return if is loading animations
	 */
	public boolean isLoadingAnimations() {
		return _loadAnimations;
	}
	
	/**
	 * Used for loading screen text message
	 * @return If process is loading audio
	 */
	public boolean isLoadingAudio() {
		return _loadAudio;
	}

}
