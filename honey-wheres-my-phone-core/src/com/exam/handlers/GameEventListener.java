package com.exam.handlers;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public interface GameEventListener {

	/**
	 * Game starts if the introduction has ended
	 * @param event
	 */
	public void gameStart(GameEvent event);

	/**
	 * Game is in reversed state
	 * @param event
	 */
	public void gameReverse(GameEvent event);

	/**
	 * User is in end screen.
	 * @param event
	 */
	public void gameEnd(GameEvent event);

}
