package com.exam.handlers;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class MyInput {
	
	private static boolean[] mouseDown;
	private static boolean[] mouseDownPreiousState;

	private static float mouseXCoordinate;
	private static float mouseYCoordinate;

	public static final int NUM_MOUSE_BUTTONS = 3;
	public static final int MOUSE_BUTTON_LEFT = 0;
	public static final int MOUSE_BUTTON_RIGHT = 1;
	public static final int MOUSE_BUTTON_SCROLL = 2;
	
	static{
		// initializing all static final arrays.
		mouseDown = new boolean[NUM_MOUSE_BUTTONS];
		mouseDownPreiousState = new boolean[NUM_MOUSE_BUTTONS];
	}
	
	/**
	 * Update input
	 */
	public static void update(){
		for (int i = 0; i < NUM_MOUSE_BUTTONS; i++) {
			mouseDownPreiousState[i] = mouseDown[i];
		}
	}
	
	/**
	 * Set mouse state to mouseDown (true) or mouseUp (false)
	 * @param i index
	 * @param state true or false
	 */
	public static void setMouseState(int i, boolean state){
		if(i >= NUM_MOUSE_BUTTONS) return;
		mouseDown[i] = state;
	}
	
	/**
	 * Set mouseX and mouseYCoordinate
	 * @param xCoordinate
	 * @param yCoordinate
	 */
	public static void setMouseCoordinates(float xCoord, float yCoord){
		mouseXCoordinate = xCoord;
		mouseYCoordinate = yCoord;
	}
	
	/**
	 * @param index
	 * @return if mouse is down
	 */
	public static boolean isMouseDown(int i){
		return mouseDown[i];
	}
	
	/**
	 * @param index
	 * @return if previous state is pressed and current state is up. (if mouse is clicked)
	 */
	public static boolean isMousePressed(int i){
		return !mouseDown[i] && mouseDownPreiousState[i];
	}
	
	/**
	 * @param index
	 * @return mouse coordinates on screen.
	 */
	public static Vector2 getMouseScreenCoordinates(int i){
		return new Vector2(mouseXCoordinate, mouseYCoordinate);
	}
	
	/**
	 * @return mouse x coordinate on screen
	 */
	public static float getMouseXCoordinate(){
		return mouseXCoordinate*2;
	}
	
	/**
	 * @return mouse y coordinate on screen
	 */
	public static float getMouseYCoordinate(){
		return mouseYCoordinate*2;
	}
	
}
