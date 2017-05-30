package com.exam.assets;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public enum AudioType {
	COLLECT_ITEM("audio/Audio_Collect.mp3", false),
	MAIN_MENU("audio/Audio_Menu.mp3", true),
	GAME_AUDIO("audio/Audio_Main_Theme.mp3", true),
	UPGRADE("audio/Audio_Upgrade.mp3", false),
	BUTTON_CLICK("audio/Audio_button_click.mp3", false);
	
	private String _audioPath;
	private boolean _isLooping;
	
	private AudioType(String path, boolean loop){
		this._audioPath = path;
		this._isLooping = loop;
	}
	
	public String getAudioPath() {
		return _audioPath;
	}
	
	public boolean isLooping() {
		return _isLooping;
	}
}
