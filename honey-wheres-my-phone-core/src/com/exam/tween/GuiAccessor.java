package com.exam.tween;

import com.exam.gui.Gui;

import aurelienribon.tweenengine.TweenAccessor;

public class GuiAccessor implements TweenAccessor<Gui> {

	@Override
	public int getValues(Gui button, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case AccessorReferences.ALPHA:
			returnValues[0] = button.getAlpha();
		    return 1;
		case AccessorReferences.SCALE:
			returnValues[0] = button.getScaleX();
			returnValues[1] = button.getScaleY();
			return 2;
		default:
		    assert false; // stop execution!
		    return -1;
		}
	}

	@Override
	public void setValues(Gui button, int tweenType, float[] newValues) {
		switch (tweenType) {
		case AccessorReferences.ALPHA:
		    button.setAlpha(newValues[0]);
		    break;
		case AccessorReferences.SCALE:
			button.setScale(newValues[0], newValues[1]);
		    break;
		default:
		    assert false; // stop execution!
		    break;
		}
	}
}
