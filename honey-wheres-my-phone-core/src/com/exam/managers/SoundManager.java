package com.exam.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.exam.assets.AudioType;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class SoundManager {
	
	private static float _volume = 1f;
	public static boolean isMuted = false;

	/**
	 * play particular audio file.
	 * @param audioType to search to
	 */
	public static void playAudio(AudioType audioType){
		if(audioType.isLooping()) {
			Music music = Main.assets.getMusic(audioType);
			music.play();
			music.setLooping(true);
		} else {
			Sound sound = Main.assets.getAudio(audioType);
			sound.play(_volume);
		}
	}
	
	/**
	 * Stop particular audio file.
	 * @param audioType to search to
	 */
	public static void stopAudio(AudioType audioType){
		if(!audioType.isLooping()) return;
		Music music = Main.assets.getMusic(audioType);
		music.stop();
	}
	
	/**
	 * Pause particular audio file
	 */
	public static void pauseAudio(AudioType audioType){
		if(!audioType.isLooping()) return;
		Music music = Main.assets.getMusic(audioType);
		music.pause();
	}
	
	/**
	 * Resumes particular audio file
	 */
	public static void resumeAudio(AudioType audioType){
		if(!audioType.isLooping()) return;
		Music music = Main.assets.getMusic(audioType);
		music.play();
	}
	
	/**
	 * Toggle to turn audio on or off.
	 */
	public static void muteToggle(){
		// toggle volume for sound effects
		if(_volume > 0)
			_volume = 0f;
		else
			_volume = 1f;
		
		isMuted = !isMuted; // toggle mute boolean
		
		// set new volume to all music files
		for (AudioType audioType : AudioType.values()) {
			if(audioType.isLooping()){
				Music music = Main.assets.getMusic(audioType);
				music.setVolume(_volume);
			}
		}
	}
	
	/**
	 * Stops all looping sounds
	 */
	public static void resetAudio(){
		for (AudioType audioType : AudioType.values()) {
			if(audioType.isLooping()){
				Music music = Main.assets.getMusic(audioType);
				music.stop();
			}
		}
	}

}
