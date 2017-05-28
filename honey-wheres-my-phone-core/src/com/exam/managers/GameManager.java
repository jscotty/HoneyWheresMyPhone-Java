package com.exam.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.exam.background.BackgroundManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class GameManager {

	//Managing save data in this class.
	
	public static final int DEPTH_LEVEL = 0;
	public static final int START_DEPTH_LEVEL = 1;
	public static final int ITEM_VALUE_LEVEL = 2;
	
	private static final String PREFERENCE_LOCATION = "gameData";
	private static final String MONEY_LOCATION = "Money";
	private static final String[] PHONES_COLLECTED_LOCATION = new String[]{
			"Phone1", "Phone2", "Phone3", "Phone4", "Phone5"
	};
	private static final String[] UPGRADE_LOCATIONS = new String[]{
			"Depth", "Start Depth", "Item Value"
	};

	public static boolean isPaused = false;
	public static boolean isHit = false;
	
	private static int _money;
	
	private static Preferences preferences;
	
	public static void init(){
		preferences = Gdx.app.getPreferences(PREFERENCE_LOCATION);
		_money = preferences.getInteger(MONEY_LOCATION);
		preferences.flush();
	}
	
	public static void addUpgrade(int index){
		int currentUpgrade = preferences.getInteger(UPGRADE_LOCATIONS[index]);
		
		currentUpgrade++;
		preferences.putInteger(UPGRADE_LOCATIONS[index], currentUpgrade);
		preferences.flush();
	}
	
	public static void collectedPhone(int index){
		preferences.putBoolean(PHONES_COLLECTED_LOCATION[index], true);
		preferences.flush();
	}
	
	public static void addMoney(int money){
		_money += money + (money*getUpgradeLevel(ITEM_VALUE_LEVEL));
		preferences.putInteger(MONEY_LOCATION, money);
		preferences.flush();
	}
	
	public static void removeMoney(int money){
		System.out.println(money);
		_money -= money;
		preferences.putInteger(MONEY_LOCATION, _money);
		preferences.flush();
	}
	
	public static int getUpgradeLevel(int index){
		return preferences.getInteger(UPGRADE_LOCATIONS[index]);
	}
	
	public static int getMaximumDepth(){
		return 200 * (preferences.getInteger(UPGRADE_LOCATIONS[DEPTH_LEVEL])+1); // dirty fix..
	}
	
	public static int getMoney(){
		return _money;
	}
	
	public static boolean isPoneCollected(int index){
		return preferences.getBoolean(PHONES_COLLECTED_LOCATION[index]);
	}
	
	public static void reset(){
		isPaused = false;
		isHit = false;
	}
	
}
