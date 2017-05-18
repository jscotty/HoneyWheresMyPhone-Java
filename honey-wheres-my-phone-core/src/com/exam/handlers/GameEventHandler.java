package com.exam.handlers;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class GameEventHandler {

	private Set<GameEventListener> listeners; // set managing automatically if adding listener is already in the list.
	
	public GameEventHandler() {
		listeners = new HashSet<GameEventListener>();
	}
	
	public synchronized void addListener(GameEventListener listener){
		listeners.add(listener);
	}
	
	public synchronized void removeListener(GameEventListener listener){
		listeners.remove(listener);
	}
	
	protected synchronized void gameStart(){
		GameEvent event = new GameEvent(this);
		for (GameEventListener listener : listeners) {
			listener.gameStart(event);
		}
	}
	
	protected synchronized void gameEnd(){
		GameEvent event = new GameEvent(this);
		for (GameEventListener listener : listeners) {
			listener.gameEnd(event);
		}
	}
}
