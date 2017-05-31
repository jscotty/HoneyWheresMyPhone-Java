package com.exam.tween;

import com.exam.gui.GuiText;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class GuiTextAccessor  implements TweenAccessor<GuiText> {

	//preparing tweening
	@Override
	public int getValues(GuiText text, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case AccessorReferences.ALPHA:
			returnValues[0] = text.getAlpha();
		    return 1;
		case AccessorReferences.SCALE:
			returnValues[0] = text.getSize();
			return 2;
		default:
		    assert false; // stop execution!
		    return -1;
		}
	}

	//Applies tween data
	@Override
	public void setValues(GuiText button, int tweenType, float[] newValues) {
		switch (tweenType) {
		case AccessorReferences.ALPHA:
		    button.setAlpha(newValues[0]);
		    break;
		case AccessorReferences.SCALE:
			button.setFontSize(newValues[0]);
		    break;
		default:
		    assert false; // stop execution!
		    break;
		}
	}
}