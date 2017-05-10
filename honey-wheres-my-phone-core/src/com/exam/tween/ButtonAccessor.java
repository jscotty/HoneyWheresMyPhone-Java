package com.exam.tween;

import com.exam.gui.Button;

import aurelienribon.tweenengine.TweenAccessor;

public class ButtonAccessor implements TweenAccessor<Button> {

	@Override
	public int getValues(Button button, int tweenType, float[] returnValues) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValues(Button button, int tweenType, float[] newValues) {
		switch (tweenType) {
		case AccessorReferences.ALPHA:
		    
		    break;
		case AccessorReferences.SCALE:
			
		    break;
		default:
		    assert false; // stop execution!
		    break;
		}
	}

}
