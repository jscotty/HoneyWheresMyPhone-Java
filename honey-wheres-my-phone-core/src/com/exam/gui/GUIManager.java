package com.exam.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class GUIManager {

	private List<Gui> _guis = new ArrayList<Gui>();
	
	/**
	 * Process gui for updating and rendering
	 * @param gui
	 */
	public void processGui(Gui gui){
		_guis.add(gui);
	}
	
	/**
	 * Sorting guis in order of gui.zIndex.
	 * Sorts for render ordering.
	 */
	public void sortGuis(){
		Collections.sort(_guis);
	}
	
	/**
	 * Update all processed guis
	 * Updates every frame
	 * @param deltaTime
	 */
	public void update(float deltaTime){
		for (Gui gui : _guis) {
			gui.update(deltaTime);
		}
	}
	
	/**
	 * Render all processed guis
	 * Renders every frame.
	 * @param spriteBatch
	 */
	public void render(SpriteBatch spriteBatch){
		for (Gui gui : _guis) {
			gui.render(spriteBatch);
		}
	}

}
