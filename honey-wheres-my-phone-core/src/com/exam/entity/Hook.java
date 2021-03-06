package com.exam.entity;

import java.util.HashSet;
import java.util.Set;

import com.badlogic.gdx.math.Vector2;
import com.exam.assets.AnimationType;
import com.exam.handlers.GameEvent;
import com.exam.handlers.GameEventHandler;
import com.exam.handlers.GameEventListener;
import com.exam.handlers.MyInput;
import com.exam.project.Main;
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
public class Hook extends Entity implements GameEventListener {

	private TweenManager _tweenManager; // for animation update and calculations
	private float _speedDevider = 5f; // to flow the movement
	private float _mouseX; // mouse position
	private Set<GameEventHandler> _handlers = new HashSet<GameEventHandler>(); // handlers to send callbacks to

	private float _tweenDuration = 1f;
	private boolean _start = false;
	private Animation _armAnimation;
	private Animation _handAnimation;

	/**
	 * constructor and initialization. Casting parameters to base class
	 * @param world for box2D physics and events.
	 * @param position
	 * @param bodyType for box2D physics and events.
	 * @param spriteType for receiving desired texture.
	 * @param manager to process/register this instance.
	 */
	public Hook(Vector2 position, AnimationType armAnimationType, AnimationType handAnimationType,
			EntityManager manager) {
		super(position, manager);
		_tweenManager = new TweenManager();
		_mouseX = position.x;

		_armAnimation = (Animation) new Animation(armAnimationType, true, 0.8f, position, manager).setIndex(3);
		_handAnimation = (Animation) new Animation(handAnimationType, true, 0.8f, new Vector2(0, 0), manager)
				.setIndex(2);
	}

	/**
	 * handles input from MyInput class.
	 */
	private void handleInput() {
		if (MyInput.isMouseDown(MyInput.MOUSE_BUTTON_LEFT)) {
			_mouseX = MyInput.getMouseXCoordinate();
			// set mouse position always in screen
			if (_mouseX > Main.WIDTH)
				_mouseX = Main.WIDTH;
			if (_mouseX < 0)
				_mouseX = 0;
		}
		pBody.setTransform(getBodyPosition(), 0);
		// add distance calculation ( what is desired add factor to 'teleport' to desired position) divided by a speed factor.
		pPosition.x += (_mouseX - pPosition.x) / _speedDevider;
	}

	private void tweenToStartPosition() {
		Tween.to(this, AccessorReferences.POSITION, _tweenDuration).target(Main.WIDTH / 2, 1450)
				.setCallback(new TweenCallback() {

					@Override
					public void onEvent(int arg0, BaseTween<?> arg1) {
						castAnimationEnd();
					}
				}).start(_tweenManager);
	}

	private void tweenToReversePosition() {
		Tween.to(this, AccessorReferences.YPOSITION, _tweenDuration).target(800).start(_tweenManager);
	}

	private void tweenToEndPosition() {
		Tween.to(this, AccessorReferences.YPOSITION, _tweenDuration).target(2000).start(_tweenManager);
	}

	/**
	 * Apply a handler to cast events to.
	 * @param handler
	 */
	public synchronized void addHandler(GameEventHandler handler) {
		_handlers.add(handler);
	}

	/**
	 * Remove a handler for events casting.
	 * @param handler
	 */
	public synchronized void removeHandler(GameEventHandler handler) {
		_handlers.remove(handler);
	}

	/**
	 * Cast event of end animation to all handlers
	 */
	private void castAnimationEnd() {
		for (GameEventHandler handler : _handlers) {
			handler.castMethod();
		}
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime); // update base.
		handleInput();
		_tweenManager.update(deltaTime);

		_armAnimation.setPosition(pPosition.x, pPosition.y);
		_handAnimation.setPosition(pPosition.x + 10, pPosition.y - 530); // +10 because the sprites do not match correctly.
	}

	@Override
	public void gameStart(GameEvent event) {
		if (_start)
			return; // only call once!
		_start = true;

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
