package com.exam.handlers;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class GameEvent { //basic event

	private GameEventHandler _source; // to send source of handler to the listener

	public GameEvent(GameEventHandler source) {
		this._source = source;
	}

	/**
	 * @return source of callback.
	 */
	public GameEventHandler getSource() {
		return _source;
	}
}
