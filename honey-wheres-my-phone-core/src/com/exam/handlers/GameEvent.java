package com.exam.handlers;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class GameEvent {

	private GameEventHandler source;
	
	public GameEvent(GameEventHandler source) {
		this.source = source;
	}
	
	public GameEventHandler getSource() {
		return source;
	}
}
