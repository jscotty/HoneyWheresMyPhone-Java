package com.exam.tween;

import com.badlogic.gdx.graphics.g2d.Sprite;

import aurelienribon.tweenengine.TweenAccessor;

public class SpriteAccessor implements TweenAccessor<Sprite> {
	
	@Override
	public int getValues(Sprite sprite, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case AccessorReferences.ALPHA:
			returnValues[0] = sprite.getColor().a;
			return 1;
		default:
			assert false; // stop execution!
			return -1; // error
		}
	}

	@Override
	public void setValues(Sprite sprite, int tweenType, float[] newValues) {
		switch (tweenType) {
		case AccessorReferences.ALPHA:
			sprite.setColor(sprite.getColor().r,sprite.getColor().g,sprite.getColor().b, newValues[0]);
			break;
		default:
			assert false; // stop execution!
			break;
		}
	}

}
