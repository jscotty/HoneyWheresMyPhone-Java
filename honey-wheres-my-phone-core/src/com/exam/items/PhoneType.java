package com.exam.items;

import com.exam.toolbox.AnimationType;

public enum PhoneType {
	LEVEL_01(AnimationType.PHONE_LEVEL_1),
	LEVEL_02(AnimationType.PHONE_LEVEL_2),
	LEVEL_03(AnimationType.PHONE_LEVEL_3),
	LEVEL_04(AnimationType.PHONE_LEVEL_4),
	LEVEL_05(AnimationType.PHONE_LEVEL_5);
	
	private AnimationType animationType;
	
	private PhoneType(AnimationType animationType){
		this.animationType = animationType;
	}
	
	public AnimationType getAnimationType() {
		return animationType;
	}
}
