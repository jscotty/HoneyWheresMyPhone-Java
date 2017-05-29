package com.exam.handlers;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class GameEvent { //basic event

	private GameEventHandler _source;

	public GameEvent(GameEventHandler source) {
		this._source = source;
	}

	public GameEventHandler getSource() {
		return _source;
	}
}
