package com.exam.background;

import com.badlogic.gdx.math.Vector2;
import com.exam.entity.Entity;
import com.exam.entity.EntityManager;
import com.exam.handlers.MyInput;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;
import com.exam.tween.AccessorReferences;
import com.exam.tween.EntityAccessor;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

public class BackgroundManager {
	
	private Entity backgroundTop;
	private Entity borderTop;
	private Background backgroundLevel1;
	private TweenManager tweenManager;

	private float speed;
	private int level;
	
	private float startAnimationDuration = 1f;
	
	public BackgroundManager(EntityManager manager) {
		tweenManager = new TweenManager();
		Tween.registerAccessor(Entity.class, new EntityAccessor());
		backgroundTop = new Entity(new Vector2(Main.WIDTH/2,Main.HEIGHT/2), SpriteType.BACKGROUND_TOP, manager).setIndex(-4);
		borderTop = new Entity(new Vector2(Main.WIDTH/2,Main.HEIGHT/2), SpriteType.BACKGROUND_TOP_OVERLAY, manager);
		backgroundLevel1 = new Background(new Vector2(Main.WIDTH/2, -Main.HEIGHT/2+5), BackgroundType.BACKGROUND_LEVEL_00,manager);
	}
	
	public void startScrolling(){
		Timeline.createSequence()
		.pushPause(0.1f)
		.push(Tween.set(borderTop, AccessorReferences.POSITION).target(Main.WIDTH/2,Main.HEIGHT/2))
		.pushPause(0.1f)
		.beginParallel()
		.push(Tween.to(borderTop, AccessorReferences.POSITION, startAnimationDuration).target(Main.WIDTH/2,Main.HEIGHT*1.5f))
		.push(Tween.to(backgroundLevel1, AccessorReferences.POSITION, startAnimationDuration).target(Main.WIDTH/2,Main.HEIGHT/2))
		.end()
		.start(tweenManager);
	}
	
	private void handleInput(){
		if(MyInput.isMouseDown(0)){
			startScrolling();
		}
	}
	
	public void update(float deltaTime){
		handleInput();
		tweenManager.update(deltaTime);
	}

}
