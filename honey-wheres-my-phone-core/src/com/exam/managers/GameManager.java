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

	// locations
	private static final String PREFERENCE_LOCATION = "gameData"; // preference location name.
	private static final String MONEY_LOCATION = "Money";
	private static final String[] PHONES_COLLECTED_LOCATION = new String[] { "Phone1", "Phone2", "Phone3", "Phone4",
			"Phone5" };
	private static final String[] UPGRADE_LOCATIONS = new String[] { "Depth", "Start Depth", "Item Value" };

	public static boolean isPaused = false;
	public static boolean isHit = false;

	private static int _money;

	private static Preferences _preferences; // all data is stored in this class.

	/**
	 * Initialize save data
	 */
	public static void init() {
		_preferences = Gdx.app.getPreferences(PREFERENCE_LOCATION);
		_money = _preferences.getInteger(MONEY_LOCATION);
		_preferences.flush();
	}

	/**
	 * Save and set upgrade data
	 * @param index (0 = depth, 1 = start depth, 2 = item value)
	 */
	public static void addUpgrade(int index) {
		int currentUpgrade = _preferences.getInteger(UPGRADE_LOCATIONS[index]);

		currentUpgrade++;
		_preferences.putInteger(UPGRADE_LOCATIONS[index], currentUpgrade);
		_preferences.flush();
	}

	/**
	 * Save and set if phone is collected.
	 * @param index
	 */
	public static void collectedPhone(int index) {
		_preferences.putBoolean(PHONES_COLLECTED_LOCATION[index], true);
		_preferences.flush();
	}

	/**
	 * Save and set added money
	 * @param money
	 */
	public static void addMoney(int money) {
		_money += money + (money * getUpgradeLevel(ITEM_VALUE_LEVEL));
		_preferences.putInteger(MONEY_LOCATION, _money);
		_preferences.flush();
	}

	/**
	 * Save and removes amount of given money
	 * @param money
	 */
	public static void removeMoney(int money) {
		System.out.println(money);
		_money -= money;
		_preferences.putInteger(MONEY_LOCATION, _money);
		_preferences.flush();
	}

	/**
	 * @param index of upgrade
	 * @return saved upgrade level
	 */
	public static int getUpgradeLevel(int index) {
		return _preferences.getInteger(UPGRADE_LOCATIONS[index]);
	}

	/**
	 * @return maximum depth upgrade level.
	 */
	public static int getMaximumDepth() {
		return 200 * (_preferences.getInteger(UPGRADE_LOCATIONS[DEPTH_LEVEL]) + 1); // dirty fix..
	}

	/**
	 * @return current amount of money.
	 */
	public static int getMoney() {
		return _money;
	}

	/**
	 * @param index
	 * @return Saved data if phone is already collected
	 */
	public static boolean isPoneCollected(int index) {
		return _preferences.getBoolean(PHONES_COLLECTED_LOCATION[index]);
	}

	/**
	 * Reset game state.
	 */
	public static void reset() {
		isPaused = false;
		isHit = false;
	}

}
