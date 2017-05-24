package com.exam.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class GameManager {
	
	private static final String PREFERENCE_LOCATION = "gameData";
	private static final String[] UPGRADE_LOCATIONS = new String[]{
			"Depth", "Start Depth", "Item Value"
	};

	public static boolean isPaused = false;
	public static boolean isHit = false;
	
	private static Preferences preferences;
	
	public static void init(){
		preferences = Gdx.app.getPreferences(PREFERENCE_LOCATION);
	}
	
	public static void addUpgrade(int index){
		int currentUpgrade = preferences.getInteger(UPGRADE_LOCATIONS[index]);
		
		currentUpgrade++;
		preferences.putInteger(UPGRADE_LOCATIONS[index], currentUpgrade);
		preferences.flush();
	}
	
	public static int getUpgradeLevel(int index){
		return preferences.getInteger(UPGRADE_LOCATIONS[index]);
	}
	
	public static void reset(){
		isPaused = false;
		isHit = false;
	}
	
}
