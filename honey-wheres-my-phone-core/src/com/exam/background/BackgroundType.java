package com.exam.background;

import com.exam.assets.SpriteType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public enum BackgroundType {

	BACKGROUND_TOP(SpriteType.BACKGROUND_TOP, SpriteType.BACKGROUND_TOP_OVERLAY, 0),
	BACKGROUND_LEVEL_00(SpriteType.BACKGROUND_LEVEL_01, SpriteType.BACKGROUND_OVERLAY_LEVEL_00, 0),
	BACKGROUND_LEVEL_01(SpriteType.BACKGROUND_LEVEL_01, SpriteType.BACKGROUND_OVERLAY_LEVEL_01, 13),
	BACKGROUND_LEVEL_02(SpriteType.BACKGROUND_LEVEL_02, SpriteType.BACKGROUND_OVERLAY_LEVEL_02, 0),
	BACKGROUND_LEVEL_03(SpriteType.BACKGROUND_LEVEL_03, SpriteType.BACKGROUND_OVERLAY_LEVEL_03, 14),
	BACKGROUND_LEVEL_04(SpriteType.BACKGROUND_LEVEL_04, SpriteType.BACKGROUND_OVERLAY_LEVEL_04, 0),
	BACKGROUND_LEVEL_05(SpriteType.BACKGROUND_LEVEL_05, SpriteType.BACKGROUND_OVERLAY_LEVEL_05, 14),
	BACKGROUND_LEVEL_06(SpriteType.BACKGROUND_LEVEL_06, SpriteType.BACKGROUND_OVERLAY_LEVEL_06, 0);

	private SpriteType _backgroundSprite;
	private SpriteType _overlaySprite;
	private int _repeatCount;

	/**
	 * Initialization.
	 */
	private BackgroundType(SpriteType background, SpriteType overlay, int repeatCount) {
		this._backgroundSprite = background;
		this._overlaySprite = overlay;
		this._repeatCount = repeatCount;
	}

	/**
	 * @return background SpriteType
	 */
	public SpriteType getBackgroundSprite() {
		return _backgroundSprite;
	}

	/**
	 * @return overlay SpriteType
	 */
	public SpriteType getOverlaySprite() {
		return _overlaySprite;
	}

	/**
	 * @return amount of repeating this background type.
	 */
	public int getRepeatCount() {
		return _repeatCount;
	}
}
