package com.exam.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.exam.assets.AudioType;
import com.exam.project.Main;

public class SoundManager {
	
	private static float volume = 1f;

	public static void playAudio(AudioType audioType){
		if(audioType.isLooping()) {
			Music music = Main.assets.getMusic(audioType);
			music.play();
			music.setLooping(true);
		} else {
			Sound sound = Main.assets.getAudio(audioType);
			sound.play(volume);
		}
	}
	
	public static void stopAudio(AudioType audioType){
		if(!audioType.isLooping()) return;
		Music music = Main.assets.getMusic(audioType);
		music.stop();
	}
	
	public static void pauseAudio(){
		for (AudioType audioType : AudioType.values()) {
			if(audioType.isLooping()){
				Music music = Main.assets.getMusic(audioType);
				music.pause();
			}
		}
	}
	
	public static void resumeAudio(){
		for (AudioType audioType : AudioType.values()) {
			if(audioType.isLooping()){
				Music music = Main.assets.getMusic(audioType);
				music.play();
			}
		}
	}
	
	public static void muteToggle(){
		if(volume > 0)
			volume = 0f;
		else
			volume = 1f;
		
		for (AudioType audioType : AudioType.values()) {
			
			if(audioType.isLooping()){
				Music music = Main.assets.getMusic(audioType);
				music.setVolume(volume);
			}
		}
	}
	
	public static void resetAudio(){
		
		for (AudioType audioType : AudioType.values()) {
			
			if(audioType.isLooping()){
				Music music = Main.assets.getMusic(audioType);
				music.stop();
			}
		}
	}

}
