package com.exam.handlers;

import com.badlogic.gdx.InputAdapter;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class MyInputProcessor extends InputAdapter {

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
		MyInput.setMouseCoordinates(screenX, screenY); // set mouse coordinates
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		MyInput.setMouseCoordinates(screenX, screenY); // set mouse coordinates
		return super.mouseMoved(screenX, screenY);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		MyInput.setMouseState(button, true); // set state to down
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		MyInput.setMouseState(button, false); // set state to up
		return true;
	}
}
