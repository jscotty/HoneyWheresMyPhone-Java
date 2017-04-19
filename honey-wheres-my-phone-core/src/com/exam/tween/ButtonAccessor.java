package com.exam.tween;

import com.exam.gui.GUIButton;

import aurelienribon.tweenengine.TweenAccessor;

public class ButtonAccessor implements TweenAccessor<GUIButton>{
	
	@Override
	public int getValues(GUIButton button, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case AccessorReferences.ALPHA:
			returnValues[0] = button.getColor().a;
			return 1;
		case AccessorReferences.SCALE:
			returnValues[0] = button.getScaleX();
			return 1;
		default:
			assert false; // stop execution!
			return -1; // error
		}
	}

	@Override
	public void setValues(GUIButton button, int tweenType, float[] newValues) {
		switch (tweenType) {
		case AccessorReferences.ALPHA:
			button.getButton().setColor(button.getButton().getColor().r,button.getButton().getColor().g,button.getButton().getColor().b, newValues[0]);
			break;
		case AccessorReferences.SCALE:
			button.setObjectScale(newValues[0], newValues[0]);
			break;
		default:
			assert false; // stop execution!
			break;
		}
	}
}
