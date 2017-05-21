package com.exam.background;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.exam.entity.Entity;
import com.exam.entity.EntityManager;
import com.exam.handlers.GameEventHandler;
import com.exam.handlers.MyInput;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;
import com.exam.tween.AccessorReferences;
import com.exam.tween.EntityAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class BackgroundManager extends GameEventHandler {
	
	private final float MAXIMUM_SPEED = 25F;
	
	private EntityManager entityManager;
	private Entity backgroundTop;
	private Entity borderTop;
	private Background[] introBackgrounds;
	private List<Background> activeBackgrounds = new ArrayList<Background>();
	private TweenManager tweenManager;
	private int backgroundCount = 0;

	private float speed = 2f;
	private boolean introEnded = false;
	
	private float startAnimationDuration = 1f;
	private boolean start = false;
	private int activeBackgroundIndex = 1;
	private Vector2 cachedNextBackgroundPosition = new Vector2(Main.WIDTH/2, -(Main.HEIGHT/2)+20);
	private int backgroundRepeatCount = 0;
	
	public BackgroundManager(EntityManager manager) {
		this.entityManager = manager;
		tweenManager = new TweenManager();
		Tween.registerAccessor(Entity.class, new EntityAccessor());
		backgroundTop = new Entity(new Vector2(Main.WIDTH/2,Main.HEIGHT/2), SpriteType.BACKGROUND_TOP, manager).setIndex(-4);
		borderTop = new Entity(new Vector2(Main.WIDTH/2,Main.HEIGHT/2), SpriteType.BACKGROUND_TOP_OVERLAY, manager);
		introBackgrounds = new Background[]{
			new Background(new Vector2(Main.WIDTH/2, -(Main.HEIGHT/2-20)), BackgroundType.BACKGROUND_LEVEL_00,manager),
			new Background(new Vector2(Main.WIDTH/2, (-Main.HEIGHT/2+5)*2), BackgroundType.BACKGROUND_LEVEL_01,manager)
		};
		activeBackgrounds.add(introBackgrounds[0]);
		activeBackgrounds.add(introBackgrounds[1]);
		backgroundCount = introBackgrounds[1].getRepeatCount();
	}
	
	private void startScrolling(){
		Timeline.createSequence()
		.pushPause(0.1f)
		.beginParallel()
		.push(Tween.to(borderTop, AccessorReferences.POSITION, startAnimationDuration).target(Main.WIDTH/2,Main.HEIGHT*1.5f))
		.push(Tween.to(introBackgrounds[0], AccessorReferences.POSITION, startAnimationDuration).target(Main.WIDTH/2,Main.HEIGHT/2 + 300))
		.push(Tween.to(introBackgrounds[1], AccessorReferences.POSITION, startAnimationDuration).target(Main.WIDTH/2, -(Main.HEIGHT/2)+300).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				gameStart();
				
			}
		}))
		.end()
		.start(tweenManager);
	}
	
	private void handleInput(){
		if(start) return; // stop when animation is already started!
		if(MyInput.isMouseDown(0)){
			start = true;
			startScrolling();
		}
	}
	
	private void scroll(){
		for (Background background : activeBackgrounds) {
			background.scroll(speed);

			if(background.getPosition().y > Main.HEIGHT*1.5f){
				nextBackground(background);
			}
		}
		
	}
	
	public void update(float deltaTime){
		handleInput();
		tweenManager.update(deltaTime);
		
		if(!introEnded)return;
		speed += 0.001f;
		scroll();
	}
	
	private void nextBackground(Background background){
		if(activeBackgroundIndex >= BackgroundType.values().length-1) {introEnded = false; return;}
		
		background.setPosition(Main.WIDTH/2, cachedNextBackgroundPosition.y+(speed*2));
		
		if(backgroundRepeatCount >= backgroundCount){
			backgroundRepeatCount = 0;
			activeBackgroundIndex++;
			BackgroundType backgroundType = BackgroundType.values()[activeBackgroundIndex];
			background.changeVisualization(backgroundType);
			backgroundCount = backgroundType.getRepeatCount();
			System.out.println(backgroundCount);
		} else 
			backgroundRepeatCount++;
		background.changeVisualization(BackgroundType.values()[activeBackgroundIndex]);
	}
	
	@Override
	protected synchronized void gameStart() {
		super.gameStart();
	}

	@Override
	public void castMethod() {
		introEnded = true;
		entityManager.removeEntity(backgroundTop);
	}

}
