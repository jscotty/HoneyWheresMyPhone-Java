package com.exam.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.gui.Gui;

public class GUIManager {

	private List<Gui> guis = new ArrayList<Gui>();
	
	public GUIManager() {
		
	}
	
	public void processGui(Gui gui){
		guis.add(gui);
	}
	
	public void sortGuis(){
		Collections.sort(guis);
	}
	
	public void update(float deltaTime){
		for (Gui gui : guis) {
			gui.update(deltaTime);
		}
	}
	
	public void render(SpriteBatch spriteBatch){
		for (Gui gui : guis) {
			gui.render(spriteBatch);
		}
	}

}
