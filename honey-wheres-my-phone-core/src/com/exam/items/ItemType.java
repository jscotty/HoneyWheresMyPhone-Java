package com.exam.items;

import com.exam.toolbox.SpriteType;

public enum ItemType {
	//type										score
	BLUSH(SpriteType.PROPS_BLUSH, 					1),
	CHARGER(SpriteType.PROPS_CHARGER, 				1),
	EYE_SHADOW(SpriteType.PROPS_EYE_SHADOW,			1),
	LIPSTICK(SpriteType.PROPS_LIPSTICK,				1),
	MIRROR(SpriteType.PROPS_MIRROR,					1),
	PENTY(SpriteType.PROPS_PENTY,					1),
	TAMPON(SpriteType.PROPS_TAMPON,					2),
	WALLET(SpriteType.PROPS_WALLET,					1),
	BOOK(SpriteType.PROPS_BOOK,						1);
	
	private SpriteType sprite;
	private float score;

	/**
	 * Enumeration constructor
	 * @param sprite type
	 * @param score value
	 */
	private ItemType(SpriteType sprite, float score){
		this.sprite = sprite;
		this.score = score;
	}

	/**
	 * @return spriteType
	 */
	public SpriteType getSprite() {
		return sprite;
	}
	
	/**
	 * @return score value of this item type
	 */
	public float getScore() {
		return score;
	}
}
