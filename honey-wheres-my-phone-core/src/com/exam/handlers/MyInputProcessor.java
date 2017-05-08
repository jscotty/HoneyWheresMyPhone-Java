package com.exam.handlers;

import com.badlogic.gdx.InputAdapter;
import com.exam.project.Main;

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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println(screenX);
		MyInput.setMouseState(button, true);
		return true;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		MyInput.setMouseState(button, false);
		return true;
	}
}