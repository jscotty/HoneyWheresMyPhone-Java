package com.exam.entity;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.handlers.GameEvent;
import com.exam.handlers.GameEventHandler;
import com.exam.handlers.GameEventListener;
import com.exam.handlers.MyInput;
import com.exam.project.Main;
import com.exam.toolbox.AnimationType;
import com.exam.toolbox.SpriteType;
import com.exam.tween.AccessorReferences;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Hook extends Entity implements GameEventListener{
	
	private TweenManager tweenManager;
	private float _speedDevider = 5f; // for flowing movement
	private float _mouseX;
	private Set<GameEventHandler> handlers = new HashSet<GameEventHandler>();
	
	private float startTweenAnimationDuration = 1f;
	private boolean start = false;
	private Animation armAnimation;
	private Animation handAnimation;

	/**
	 * constructor and initalization. Casting parameters to base class
	 * @param world for box2D physics and events.
	 * @param position
	 * @param bodyType for box2D physics and events.
	 * @param spriteType for receiving desired texture.
	 * @param manager to process/register this instance.
	 */
	public Hook(Vector2 position, AnimationType armAnimationType, AnimationType handAnimationType, EntityManager manager) {
		super(position, manager);
		tweenManager = new TweenManager();
		_mouseX = position.x;


		armAnimation = (Animation) new Animation(armAnimationType, true, 0.8f, position , manager).setIndex(3);
		handAnimation = (Animation) new Animation(handAnimationType, true, 0.8f, new Vector2(0,0) , manager).setIndex(2);
	}

	/**
	 * handles input from MyInput class.
	 */
	private void handleInput(){
		if(MyInput.isMouseDown(MyInput.MOUSE_BUTTON_LEFT)){
			_mouseX = MyInput.getMouseXCoordinate();
			// set mouse position always in screen
			if(_mouseX > Main.WIDTH) _mouseX = Main.WIDTH;
			if(_mouseX < 0) _mouseX = 0;
		}
		pBody.setTransform(getBodyPosition(), 0);
		// add distance calculation ( what is desired add factor to 'teleport' to desired position) devided by a speed factor.
		pPosition.x += (_mouseX - pPosition.x)/_speedDevider;
	}
	
	private void tweenToStartPosition(){
		Tween.to(this, AccessorReferences.POSITION, startTweenAnimationDuration).target(Main.WIDTH/2, 1450).setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int arg0, BaseTween<?> arg1) {
				castAnimationEnd();
			}
		}).start(tweenManager);
	}
	
	private void tweenToReversePosition(){
		Tween.to(this, AccessorReferences.YPOSITION, startTweenAnimationDuration).target(800).start(tweenManager);
	}
	
	private void tweenToEndPosition(){
		Tween.to(this, AccessorReferences.YPOSITION, startTweenAnimationDuration).target(2000).start(tweenManager);
	}
	
	public synchronized void addHandler(GameEventHandler handler){
		handlers.add(handler);
	}
	
	public synchronized void removeHandler(GameEventHandler handler){
		handlers.remove(handler);
	}
	
	private void castAnimationEnd(){
		for (GameEventHandler handler : handlers) {
			handler.castMethod();
		}
	}
	
	@Override
	public void update(float deltaTime) {
		super.update(deltaTime); // update base.
		handleInput();
		tweenManager.update(deltaTime);

		armAnimation.setPosition(pPosition.x, pPosition.y);
		handAnimation.setPosition(pPosition.x+10, pPosition.y - 530); // +10 because the sprites do not match correctly.
	}
	
	@Override
	public void gameStart(GameEvent event) {
		if(start)return; // only call once!
		start = true;
		
		tweenToStartPosition();
	}
	
	@Override
	public void gameEnd(GameEvent event) {
		tweenToEndPosition();
	}

	@Override
	public void gameReverse(GameEvent event) {
		tweenToReversePosition();
	}

}
