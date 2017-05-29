package com.exam.items;

import com.exam.assets.AnimationType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public enum PhoneType {
	LEVEL_01(AnimationType.PHONE_LEVEL_1),
	LEVEL_02(AnimationType.PHONE_LEVEL_2),
	LEVEL_03(AnimationType.PHONE_LEVEL_3),
	LEVEL_04(AnimationType.PHONE_LEVEL_4),
	LEVEL_05(AnimationType.PHONE_LEVEL_5);

	private AnimationType _animationType;

	private PhoneType(AnimationType animationType) {
		this._animationType = animationType;
	}

	/**
	 * @return animation type to generate animation from.
	 */
	public AnimationType getAnimationType() {
		return _animationType;
	}
}
