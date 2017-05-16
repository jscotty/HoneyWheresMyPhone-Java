package com.exam.tween;

import com.exam.gui.Gui;
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
	public int getValues(UpgradeProcess button, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case AccessorReferences.SCALE:
			returnValues[0] = button.getScaleX();
			returnValues[1] = button.getScaleY();
			return 2;
		default:
		    assert false; // stop execution!
		    return -1;
		}
	}

	//Applies tween data
	@Override
	public void setValues(UpgradeProcess button, int tweenType, float[] newValues) {
		switch (tweenType) {
		case AccessorReferences.SCALE:
			button.scaleProcessIcons(newValues[0], newValues[1]);
		    break;
		default:
		    assert false; // stop execution!
		    break;
		}
	}

}
