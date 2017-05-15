package com.exam.handlers;

import com.badlogic.gdx.InputAdapter;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class MyInputProcessor extends InputAdapter{

	@Override
	public boolean keyDown(int keycode) {
		return super.keyDown(keycode);
	}
	
	@Override
	public boolean keyUp(int keycode) {
		return super.keyUp(keycode);
	}
	
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		MyInput.setMouseCoordinates(screenX, screenY);
		return super.touchDragged(screenX, screenY, pointer);
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		MyInput.setMouseCoordinates(screenX, screenY);
		return super.mouseMoved(screenX, screenY);
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		MyInput.setMouseState(button, true);
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		MyInput.setMouseState(button, false);
		return true;
	}
}
