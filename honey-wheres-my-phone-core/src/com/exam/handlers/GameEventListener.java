package com.exam.handlers;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public interface GameEventListener {

	public void gameStart(GameEvent event);
	public void gameReverse(GameEvent event);
	public void gameEnd(GameEvent event);

}
