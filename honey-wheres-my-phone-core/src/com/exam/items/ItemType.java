package com.exam.items;

import com.exam.toolbox.SpriteType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public enum ItemType {
	LIGHTER(SpriteType.PROPS_LIGHTER, 1, 1, false),
	CONDOM(SpriteType.PROPS_CONDOM, 1, 3, false),
	ID_CARD(SpriteType.PROPS_ID_CARD, 1, 1, true),
	MAKE_UP_OPEN(SpriteType.PROPS_MAKE_UP_OPEN, 1, 1, false),
	PEPPERSPRAY(SpriteType.PROPS_PEPPERSPRAY, 1, 2, true),
	PILL(SpriteType.PROPS_PILL, 1, 1, false),
	CIGARETTES(SpriteType.PROPS_CIGARETTES, 1, 3, false),
	SNACK(SpriteType.PROPS_SNACK, 1, 2, false),
	TAMPON(SpriteType.PROPS_TAMPON, 1, 2, true),
	WATERMELON(SpriteType.PROPS_WATERMELON, 1, 5, true),
	BRUSH(SpriteType.PROPS_BRUSH, 2, 10, false),
	LIPSTICK(SpriteType.PROPS_LIPSTICK, 2, 8, false),
	LOLLY(SpriteType.PROPS_LOLLY, 2, 8, true),
	MAKE_UP_CLOSED(SpriteType.PROPS_MAKE_UP_OPEN, 2, 7, false),
	KNIFE(SpriteType.PROPS_KNIFE, 2, 12, false),
	WALLET(SpriteType.PROPS_WALLET, 2, 10, true),
	REDBULL(SpriteType.PROPS_REDBULL, 2, 9, true),
	NOTE(SpriteType.PROPS_NOTE, 2, 8, false),
	UNDERWEAR(SpriteType.PROPS_UNDERWEAR, 2, 12, true),
	TICKETS(SpriteType.PROPS_TICKETS, 2, 10, false),
	BLUSH(SpriteType.PROPS_BLUSH, 3, 15,false),
	FESTIVAL_COINS(SpriteType.PROPS_FESTIVAL_COINS, 3, 15, true),
	FOHN(SpriteType.PROPS_FOHN, 3, 18, false),
	TICKET(SpriteType.PROPS_TICKET, 3, 16, true),
	LABELLO(SpriteType.PROPS_LABELLO, 3, 15, false),
	CHARGER(SpriteType.PROPS_CHARGER, 3, 25, false),
	SHOE(SpriteType.PROPS_SHOE, 3, 15, false),
	KEYS(SpriteType.PROPS_KEYS, 3, 15, true),
	TISSUES(SpriteType.PROPS_TISSUES, 3, 15, false),
	FISH(SpriteType.PROPS_FISH, 3, 30, true);

	private SpriteType _sprite;
	private float _score;
	private int _level;
	private boolean _moving;

	/**
	 * Enumeration constructor
	 * @param sprite type
	 * @param score value
	 */
	private ItemType(SpriteType sprite, int level, float score, boolean moving) {
		this._sprite = sprite;
		this._score = score;
		this._level = level;
		this._moving = moving;
	}

	/**
	 * @return spriteType
	 */
	public SpriteType getSpriteType() {
		return _sprite;
	}

	/**
	 * @return score value of this item type
	 */
	public float getScore() {
		return _score;
	}
	
	public int getLevel() {
		return _level;
	}
	
	public boolean isMoving() {
		return _moving;
	}
}
