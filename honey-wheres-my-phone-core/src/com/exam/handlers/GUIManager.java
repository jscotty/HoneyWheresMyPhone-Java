package com.exam.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.font.FontLoader;
import com.exam.font.FontType;
import com.exam.gui.Gui;

public class GUIManager {

	private List<Gui> _guis = new ArrayList<Gui>();
	
	public GUIManager() {
		
	}
	
	public void processGui(Gui gui){
		_guis.add(gui);
	}
	
	public void sortGuis(){
		Collections.sort(_guis);
	}
	
	public void update(float deltaTime){
		for (Gui gui : _guis) {
			gui.update(deltaTime);
		}
	}
	
	public void render(SpriteBatch spriteBatch){
		for (Gui gui : _guis) {
			gui.render(spriteBatch);
		}
	}

}
