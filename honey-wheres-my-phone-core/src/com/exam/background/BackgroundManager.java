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
	private final float START_ANIMATION_ADD_POSITION = 300;
	private final int MAXIMUM_METERS = 1000;
	private final int PHONE_LEVELS = 5; // there are 5 phones in game for 5 distance upgrades
	private final int BACKGROUND_LEVELS = 3; // there are 3 background types (bag background, rocks background and lava background)
	
	private float levelsSize = -START_ANIMATION_ADD_POSITION;
	private float currentPixelsMoved = 0;
	private int meters = 0;
	private int phoneLevelDistance = 0;
	private int phoneLevel = 0;
	private int backgroundLevelDistance = 0;
	private int backgroundLevel = 0;
	
	private EntityManager entityManager;
	private Entity backgroundTop;
	private Entity borderTop;
	private Background[] introBackgrounds;
	private List<Background> activeBackgrounds = new ArrayList<Background>();
	private TweenManager tweenManager;
	private int backgroundCount = 0;

	private float speed = 10f;
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
		
		calculateLevelsSize();
	}
	
	private void startScrolling(){
		Timeline.createSequence()
		.pushPause(0.1f)
		.beginParallel()
		.push(Tween.to(borderTop, AccessorReferences.POSITION, startAnimationDuration).target(Main.WIDTH/2,Main.HEIGHT*1.5f))
		.push(Tween.to(introBackgrounds[0], AccessorReferences.POSITION, startAnimationDuration).target(Main.WIDTH/2,Main.HEIGHT/2 + START_ANIMATION_ADD_POSITION))
		.push(Tween.to(introBackgrounds[1], AccessorReferences.POSITION, startAnimationDuration).target(Main.WIDTH/2, -(Main.HEIGHT/2)+START_ANIMATION_ADD_POSITION).setCallback(new TweenCallback() {
			
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

			if(background.getPosition().y >= Main.HEIGHT*1.49f){
				nextBackground(background);
			}
		}
		
	}
	
	public void update(float deltaTime){
		handleInput();
		tweenManager.update(deltaTime);
		
		if(!introEnded)return;
		if(speed >= MAXIMUM_SPEED)
			speed = MAXIMUM_SPEED;
		else
			speed += 0.001f;
		currentPixelsMoved += speed;
		meters = (int)((float)(currentPixelsMoved / levelsSize)*MAXIMUM_METERS);
		if(meters > phoneLevelDistance*(phoneLevel+1)){
			phoneLevel++;
		}
		if(meters > backgroundLevelDistance*(backgroundLevel+1)){
			backgroundLevel++;
		}
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
	
	private void calculateLevelsSize(){
		for (BackgroundType background : BackgroundType.values()) {
			levelsSize += (background.getRepeatCount()+1)*Main.HEIGHT;
		}
		
		levelsSize -= Main.HEIGHT*2; // substracting first two background sizes of intro backgrounds.

		phoneLevelDistance = (int) (MAXIMUM_METERS / PHONE_LEVELS);
		backgroundLevelDistance = (int) (MAXIMUM_METERS / BACKGROUND_LEVELS);
	}
	
	public int getMeters() {
		if(meters > MAXIMUM_METERS) meters = MAXIMUM_METERS;
		return meters;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public int getBackgroundLevel() {
		return backgroundLevel;
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
