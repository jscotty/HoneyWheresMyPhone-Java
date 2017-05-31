package com.exam.assets;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public enum AudioType {
	// path, loopable audio file
	COLLECT_ITEM("audio/Audio_Collect.mp3", false),
	MAIN_MENU("audio/Audio_Menu.mp3", true),
	GAME_AUDIO("audio/Audio_Main_Theme.mp3", true),
	UPGRADE("audio/Audio_Upgrade.mp3", false),
	BUTTON_CLICK("audio/Audio_button_click.mp3", false);
	
	private String _audioPath;
	private boolean _isLooping;
	
	/**
	 * Enumeration constructor
	 * @param path to audio file
	 * @param loopable item
	 */
	private AudioType(String path, boolean loop){
		this._audioPath = path;
		this._isLooping = loop;
	}
	
	/**
	 * @return path to audio file.
	 */
	public String getAudioPath() {
		return _audioPath;
	}
	
	/**
	 * @return if audio type has to loop 
	 */
	public boolean isLooping() {
		return _isLooping;
	}
}
