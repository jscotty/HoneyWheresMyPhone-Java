package com.exam.entity;

import com.badlogic.gdx.math.Vector2;
import com.exam.project.ObjectBase;
import com.exam.toolbox.MathTools;
import com.exam.toolbox.SpriteType;

public class Entity extends ObjectBase {

	public Entity(float x, float y){
		super(x,y);
	}
	
	public Entity(String spritePath, float x, float y){
		super(spritePath, x, y);
	}
	
	public Entity(SpriteType type, float x, float y) {
		super(type, x, y);
	}
	

	
	public void translatePosition(float x, float y){
		this.position.x += x;
		this.position.y += y;
		calculatePosition();
	}
	
	public void moveToTarget(ObjectBase target, float speed){
		Vector2 targetPos = target.getPosition();
		Vector2 myPos = new Vector2(position.x, position.y);
		
		//normalize
		
		Vector2 result = new Vector2(0,0);

	    result.x = targetPos.x - myPos.x;
	    result.y = targetPos.y - myPos.y;

	    double magnitude = MathTools.magnitude(result);
	    result.nor();
	    
	    if(magnitude < 1) return;
	    position.x += result.x*speed;
	    position.y += result.y*speed;
		
		calculatePosition();
	}

	@Override
	public void update() {
		super.update();
	}
}
