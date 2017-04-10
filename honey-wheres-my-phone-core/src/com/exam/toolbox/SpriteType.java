package com.exam.toolbox;

public enum SpriteType {
	BUTTON_START_IDLE("sprites", "Button_Play_Idle.png"),
	BUTTON_START_PRESSED("sprites", "Button_Play_Pressed.png");
	
	private String atlas;
	private String spriteName;
	
	/**
	 * Enum constructor
	 * @param atlas path
	 * @param sprite name
	 */
	private SpriteType(String atlas, String spriteName){
		this.atlas = atlas;
		this.spriteName = spriteName;
	}

	/**
	 * @return texture atlas path (add .png for image/ add .xml for data)
	 */
	public String getAtlas() {
		return atlas;
	}

	/**
	 * @return desired texture name (visible in <atlas>.xml located in ./assets/ folder)
	 */
	public String getSpriteName() {
		return spriteName;
	}
}
