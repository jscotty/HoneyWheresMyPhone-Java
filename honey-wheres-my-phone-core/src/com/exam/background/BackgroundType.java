package com.exam.background;

import com.exam.toolbox.SpriteType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public enum BackgroundType {

	BACKGROUND_LEVEL_00(SpriteType.BACKGROUND_LEVEL_01, SpriteType.BACKGROUND_OVERLAY_LEVEL_00, 1), 
	BACKGROUND_LEVEL_01(SpriteType.BACKGROUND_LEVEL_01, SpriteType.BACKGROUND_OVERLAY_LEVEL_01, 2), 
	BACKGROUND_LEVEL_02(SpriteType.BACKGROUND_LEVEL_02, SpriteType.BACKGROUND_OVERLAY_LEVEL_02, 1), 
	BACKGROUND_LEVEL_03(SpriteType.BACKGROUND_LEVEL_03, SpriteType.BACKGROUND_OVERLAY_LEVEL_03, 2), 
	BACKGROUND_LEVEL_04(SpriteType.BACKGROUND_LEVEL_04, SpriteType.BACKGROUND_OVERLAY_LEVEL_04, 1), 
	BACKGROUND_LEVEL_05(SpriteType.BACKGROUND_LEVEL_05, SpriteType.BACKGROUND_OVERLAY_LEVEL_05, 2), 
	BACKGROUND_LEVEL_06(SpriteType.BACKGROUND_LEVEL_06, SpriteType.BACKGROUND_OVERLAY_LEVEL_06, 1);
	
	private SpriteType backgroundSprite;
	private SpriteType overlaySprite;
	private int repeatCount;
	
	private BackgroundType(SpriteType background, SpriteType overlay, int repeatCount){
		this.backgroundSprite = background;
		this.overlaySprite = overlay;
		this.repeatCount = repeatCount;
	}
	
	public SpriteType getBackgroundSprite() {
		return backgroundSprite;
	}
	
	public SpriteType getOverlaySprite() {
		return overlaySprite;
	}
	
	public int getRepeatCount() {
		return repeatCount;
	}
}
