package com.exam.handlers;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public abstract class GameEventHandler {

	private Set<GameEventListener> _listeners; // set managing automatically if adding listener is already in the list.

	/**
	 * Constructor for initialization
	 */
	public GameEventHandler() {
		_listeners = new HashSet<GameEventListener>();
	}

	/**
	 * Cast method to handlers
	 */
	public abstract void castMethod();

	/**
	 * Add listener to the list.
	 * @param listener
	 */
	public synchronized void addListener(GameEventListener listener) {
		_listeners.add(listener);
	}

	/**
	 * Remove listener from the list.
	 * @param listener
	 */
	public synchronized void removeListener(GameEventListener listener) {
		_listeners.remove(listener);
	}

	/**
	 * Start the game event casting to all listeners.
	 */
	protected synchronized void gameStart() {
		GameEvent event = new GameEvent(this);
		for (GameEventListener listener : _listeners) {
			listener.gameStart(event);
		}
	}

	/**
	 * Reverse the game event casting to all listeners.
	 */
	protected synchronized void gameReverse() {
		GameEvent event = new GameEvent(this);
		for (GameEventListener listener : _listeners) {
			listener.gameReverse(event);
		}
	}

	/**
	 * End the game event casting to all listeners.
	 */
	protected synchronized void gameEnd() {
		GameEvent event = new GameEvent(this);
		for (GameEventListener listener : _listeners) {
			listener.gameEnd(event);
		}
	}
}
