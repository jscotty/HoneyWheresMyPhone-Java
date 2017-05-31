package com.exam.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.exam.assets.SpriteType;

public class GuiToggleButton extends GuiButton {

	private boolean _toggle = false;
	private SpriteType[] _spriteTypes = new SpriteType[4];
	
	/**
	 * Constructor for initialization.
	 * @param x position to render at.
	 * @param y position to render at.
	 * @param spriteTypeUp active sprite button up state.
	 * @param spriteTypeDown active sprite button down state.
	 * @param spriteTypeToggledUp toggled sprite button up state.
	 * @param spriteTypeToggledDown toggled sprite button down state.
	 * @param camera for handling input.
	 * @param manager to register and process this instance.
	 */
	public GuiToggleButton(float x, float y, SpriteType spriteTypeUp, SpriteType spriteTypeDown, SpriteType spriteTypeToggledUp, SpriteType spriteTypeToggledDown, OrthographicCamera cam, GuiManager manager) {
		super(x, y, spriteTypeUp, spriteTypeDown, cam, manager);
		_spriteTypes[0] = spriteTypeUp;
		_spriteTypes[1] = spriteTypeDown;
		_spriteTypes[2] = spriteTypeToggledUp;
		_spriteTypes[3] = spriteTypeToggledDown;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		if(isClicked()){
			toggle();
		}
	}
	
	/**
	 * Toggle this instance
	 * This will change the visualization
	 */
	public void toggle(){
		_toggle = !_toggle;
		// toggle textures
		if(_toggle)
			setTextureTypes(_spriteTypes[2], _spriteTypes[3]);
		else 
			setTextureTypes(_spriteTypes[0], _spriteTypes[1]);
	}
	
	/**
	 * @return If toggled.
	 */
	public boolean isToggled() {
		return _toggle;
	}
}
