package com.exam.tween;

import com.exam.gui.GUITexture;

import aurelienribon.tweenengine.TweenAccessor;

public class GUITextureAccessor implements TweenAccessor<GUITexture> {

    @Override
    public int getValues(GUITexture texture, int tweenType, float[] returnValues) {
	switch (tweenType) {
	case AccessorReferences.ALPHA:
	    returnValues[0] = texture.getSprite().getColor().a;
	    return 1;
	case AccessorReferences.SCALE:
	    returnValues[0] = texture.getScaleX();
	    return 1;
	case AccessorReferences.ROTATE:
	    returnValues[0] = texture.getRotation();
	    return 1;
	default:
	    assert false; // stop execution!
	    return -1; // error
	}
    }

    @Override
    public void setValues(GUITexture texture, int tweenType, float[] newValues) {
	switch (tweenType) {
	case AccessorReferences.ALPHA:
	    texture.getSprite().setColor(texture.getColor().r, texture.getColor().g, texture.getColor().b, newValues[0]);
	    break;
	case AccessorReferences.SCALE:
	    texture.setObjectScale(newValues[0], newValues[0]);
	    break;
	case AccessorReferences.ROTATE:
	    texture.setObjectRotation(newValues[0]);
	    break;
	default:
	    assert false; // stop execution!
	    break;
	}
    }
}