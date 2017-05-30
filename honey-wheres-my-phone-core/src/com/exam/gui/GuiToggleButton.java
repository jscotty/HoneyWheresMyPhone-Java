package com.exam.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.exam.assets.SpriteType;

public class GuiToggleButton extends GuiButton {

	private boolean toggle = false;
	private SpriteType[] spriteTypes = new SpriteType[4];
	
	public GuiToggleButton(float x, float y, SpriteType spriteTypeUp, SpriteType spriteTypeDown, SpriteType spriteTypeToggledUp, SpriteType spriteTypeToggledDown, OrthographicCamera cam, GuiManager manager) {
		super(x, y, spriteTypeUp, spriteTypeDown, cam, manager);
		spriteTypes[0] = spriteTypeUp;
		spriteTypes[1] = spriteTypeDown;
		spriteTypes[2] = spriteTypeToggledUp;
		spriteTypes[3] = spriteTypeToggledDown;
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		
		if(isClicked()){
			toggle = !toggle;
			if(toggle)
				setTextureTypes(spriteTypes[2], spriteTypes[3]);
			else 
				setTextureTypes(spriteTypes[0], spriteTypes[1]);
		}
	}
}
