package com.exam.items;

import com.exam.toolbox.SpriteType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public enum ItemType {
	LIGHTER(SpriteType.PROPS_LIGHTER, 1, 1),
	CONDOM(SpriteType.PROPS_CONDOM, 1, 3),
	ID_CARD(SpriteType.PROPS_ID_CARD, 1, 1),
	MAKE_UP_OPEN(SpriteType.PROPS_MAKE_UP_OPEN, 1, 1),
	PEPPERSPRAY(SpriteType.PROPS_PEPPERSPRAY, 1, 2),
	PILL(SpriteType.PROPS_PILL, 1, 1),
	CIGARETTES(SpriteType.PROPS_CIGARETTES, 1, 3),
	SNACK(SpriteType.PROPS_SNACK, 1, 2),
	TAMPON(SpriteType.PROPS_TAMPON, 1, 2),
	WATERMELON(SpriteType.PROPS_WATERMELON, 1, 5),
	BRUSH(SpriteType.PROPS_BRUSH, 2, 10),
	LIPSTICK(SpriteType.PROPS_LIPSTICK, 2, 8),
	LOLLY(SpriteType.PROPS_LOLLY, 2, 8),
	MAKE_UP_CLOSED(SpriteType.PROPS_MAKE_UP_OPEN, 2, 7),
	KNIFE(SpriteType.PROPS_KNIFE, 2, 12),
	WALLET(SpriteType.PROPS_WALLET, 2, 10),
	REDBULL(SpriteType.PROPS_REDBULL, 2, 9),
	NOTE(SpriteType.PROPS_NOTE, 2, 8),
	UNDERWEAR(SpriteType.PROPS_UNDERWEAR, 2, 12),
	TICKETS(SpriteType.PROPS_TICKETS, 2, 10),
	BLUSH(SpriteType.PROPS_BLUSH, 3, 15),
	FESTIVAL_COINS(SpriteType.PROPS_FESTIVAL_COINS, 3, 15),
	FOHN(SpriteType.PROPS_FOHN, 3, 18),
	TICKET(SpriteType.PROPS_TICKET, 3, 16),
	LABELLO(SpriteType.PROPS_LABELLO, 3, 15),
	CHARGER(SpriteType.PROPS_CHARGER, 3, 25),
	SHOE(SpriteType.PROPS_SHOE, 3, 15),
	KEYS(SpriteType.PROPS_KEYS, 3, 15),
	TISSUES(SpriteType.PROPS_TISSUES, 3, 15),
	FISH(SpriteType.PROPS_FISH, 3, 30);

	private SpriteType _sprite;
	private float _score;
	private int _level;

	/**
	 * Enumeration constructor
	 * @param sprite type
	 * @param score value
	 */
	private ItemType(SpriteType sprite, int level, float score) {
		this._sprite = sprite;
		this._score = score;
		this._level = level;
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
}
