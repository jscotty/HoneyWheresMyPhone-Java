package com.exam.handlers;

import com.badlogic.gdx.math.Vector2;

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
		mouseDown = new boolean[NUM_MOUSE_BUTTONS];
		mouseDownPreiousState = new boolean[NUM_MOUSE_BUTTONS];
	}
	
	public static void update(){
		for (int i = 0; i < NUM_MOUSE_BUTTONS; i++) {
			mouseDownPreiousState[i] = mouseDown[i];
		}
	}
	
	public static void setMouseState(int i, boolean state){
		if(i >= NUM_MOUSE_BUTTONS) return;
		mouseDown[i] = state;
	}
	
	public static void setMouseCoordinates(float xCoord, float yCoord){
		mouseXCoordinate = xCoord;
		mouseYCoordinate = yCoord;
	}
	
	public static boolean isMouseDown(int i){
		return mouseDown[i];
	}
	
	public static boolean isMousePressed(int i){
		return !mouseDown[i] && mouseDownPreiousState[i];
	}
	
	public static Vector2 getMouseScreenCoordinates(int i){
		return new Vector2(mouseXCoordinate, mouseYCoordinate);
	}
	
	public static float getMouseXCoordinate(){
		return mouseXCoordinate*2;
	}
	
	public static float getMouseYCoordinate(){
		return mouseYCoordinate*2;
	}
	
}
