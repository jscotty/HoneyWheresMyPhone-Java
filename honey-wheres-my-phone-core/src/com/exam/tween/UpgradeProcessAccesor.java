package com.exam.tween;

import com.exam.gui.UpgradeProcess;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class UpgradeProcessAccesor implements TweenAccessor<UpgradeProcess> {

	//preparing tweening
	@Override
	public int getValues(UpgradeProcess process, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case AccessorReferences.SCALE:
			returnValues[0] = process.getScaleX();
			returnValues[1] = process.getScaleY();
			return 2;
		default:
		    assert false; // stop execution!
		    return -1;
		}
	}

	//Applies tween data
	@Override
	public void setValues(UpgradeProcess process, int tweenType, float[] newValues) {
		switch (tweenType) {
		case AccessorReferences.SCALE:
			process.scaleProcessIcons(newValues[0], newValues[1]);
		    break;
		default:
		    assert false; // stop execution!
		    break;
		}
	}

}
