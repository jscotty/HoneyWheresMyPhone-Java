package com.exam.tween;

import com.exam.entity.Entity;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class EntityAccessor  implements TweenAccessor<Entity> {

	//preparing tweening
	@Override
	public int getValues(Entity entity, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case AccessorReferences.SCALE:
			returnValues[0] = entity.getScaleX();
			returnValues[1] = entity.getScaleY();
			return 2;
		case AccessorReferences.POSITION:
			returnValues[0] = entity.getPosition().x;
			returnValues[1] = entity.getPosition().y;
		    return 2;
		case AccessorReferences.YPOSITION:
			returnValues[0] = entity.getPosition().y;
		    return 1;
		default:
		    assert false; // stop execution!
		    return -1;
		}
	}

	//Applies tween data
	@Override
	public void setValues(Entity entity, int tweenType, float[] newValues) {
		switch (tweenType) {
		case AccessorReferences.SCALE:
			entity.setScale(newValues[0], newValues[1]);
		    break;
		case AccessorReferences.POSITION:
			entity.setPosition(newValues[0], newValues[1]);
			break;
		case AccessorReferences.YPOSITION:
			entity.setPosition(entity.getPosition().x, newValues[0]);
			break;
		default:
		    assert false; // stop execution!
		    break;
		}
	}
}
