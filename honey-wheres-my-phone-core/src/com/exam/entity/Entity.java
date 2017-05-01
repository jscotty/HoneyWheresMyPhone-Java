package com.exam.entity;

import com.badlogic.gdx.math.Vector2;
import com.exam.project.ObjectBase;
import com.exam.toolbox.MathTools;
import com.exam.toolbox.SpriteType;

public class Entity extends ObjectBase {

    public Entity(float x, float y) {
	super(x, y);
    }

    public Entity(String spritePath, float x, float y) {
	super(spritePath, x, y);
    }

    public Entity(SpriteType type, float x, float y) {
	super(type, x, y);
    }

    public void translatePosition(float x, float y) {
	this.pPosition.x += x;
	this.pPosition.y += y;
	calculatePosition();
    }

    public void moveToTarget(ObjectBase target, float speed) {
	Vector2 tTargetPos = target.getPosition();
	Vector2 tMyPos = new Vector2(pPosition.x, pPosition.y);

	// normalize

	Vector2 tResult = new Vector2(0, 0);

	tResult.x = tTargetPos.x - tMyPos.x;
	tResult.y = tTargetPos.y - tMyPos.y;

	double tMagnitude = MathTools.magnitude(tResult);
	tResult.nor();

	if (tMagnitude < 1)
	    return;
	pPosition.x += tResult.x * speed;
	pPosition.y += tResult.y * speed;

	calculatePosition();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
