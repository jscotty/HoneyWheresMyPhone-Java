package com.exam.background;

import com.badlogic.gdx.math.Vector2;
import com.exam.assets.SpriteType;
import com.exam.entity.Entity;
import com.exam.entity.EntityManager;
import com.exam.handlers.GameEventHandler;
import com.exam.handlers.MyInput;
import com.exam.managers.GameManager;
import com.exam.project.Main;
import com.exam.tween.AccessorReferences;
import com.exam.tween.EntityAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class BackgroundManager extends GameEventHandler {

	private final float BACKWARDS_SPEED = -25f; // if the user hits a target the game will go backwards. This is the desired speed for backward state. 
	private final int MAXIMUM_METERS = 1000; // maximum meters to travel.
	private final int PHONE_LEVELS = 5; // there are 5 phones in game for 5 distance upgrades
	private final int BACKGROUND_LEVELS = 3; // there are 3 background types (bag background, rocks background and lava background)

	private final float[] LEVEL_SPEED = new float[] { 5f, 8f, 10f, 12f, 14f, 15f }; // clamp speed on each level.

	private float _levelsSize = 0; // size in pixels of all backgrounds calculated.
	private float _currentPixelsMoved = 0; // amount of pixels moved.
	private int _meters = 0; // total meters calculated from moved pixels and level size
	private int _phoneLevelDistance = 0; // distance of each phone level
	private int _phoneLevel = 0; // current phone level
	private int _backgroundLevelDistance = 0; // distance of each background level
	private int _backgroundLevel = 0; // current background level.

	private EntityManager _entityManager; // for managing backgrounds
	private TweenManager _tweenManager;

	private Entity _backgroundTop; // introduction asset.
	private Entity _borderTop; // introduction asset.
	private Background[] _activeBackgrounds; // active scrolling backgrounds
	private int _backgroundCount = 0; // current background count for repeating.

	private float _speed = 2f; // current speed
	private boolean _introEnded = false; // has the introduction ended true or false?
	private boolean _backwardEnded = false; // has backward state ended true or false?

	private float _startAnimationDuration = 1f; // duration of introduction
	private boolean _start = false; // game start.
	private int _activeBackgroundIndex = 2; // active background index. (using for indexing the next background in BackgroundType.class)
	private int _backgroundRepeatCount = 0; // counting the repeat index to check if the visualization has to change.

	/**
	 * Constructor for initialization.
	 * @param entityManager for processing backgrounds.
	 */
	public BackgroundManager(EntityManager manager) {
		this._entityManager = manager;
		_tweenManager = new TweenManager();
		Tween.registerAccessor(Entity.class, new EntityAccessor());
		_backgroundTop = new Entity(new Vector2(Main.WIDTH / 2, Main.HEIGHT / 2), SpriteType.BACKGROUND_TOP, manager)
				.setIndex(-4);
		_borderTop = new Entity(new Vector2(Main.WIDTH / 2, Main.HEIGHT / 2), SpriteType.BACKGROUND_TOP_OVERLAY,
				manager);
		_activeBackgrounds = new Background[] {
				new Background(new Vector2(Main.WIDTH / 2, -(Main.HEIGHT / 2 - 20)), BackgroundType.BACKGROUND_LEVEL_00,
						manager),
				new Background(new Vector2(Main.WIDTH / 2, (-Main.HEIGHT / 2 + 5) * 2),
						BackgroundType.BACKGROUND_LEVEL_01, manager) };
		_backgroundCount = _activeBackgrounds[1].getRepeatCount();

		calculateLevelsSize();
	}

	/**
	 * Starts introduction animation.
	 */
	private void startAnimation() {
		Timeline.createSequence().pushPause(0.1f).beginParallel()
				.push(Tween.to(_borderTop, AccessorReferences.POSITION, _startAnimationDuration).target(Main.WIDTH / 2,
						Main.HEIGHT * 1.5f))
				.push(Tween.to(_activeBackgrounds[0], AccessorReferences.POSITION, _startAnimationDuration)
						.target(Main.WIDTH / 2, Main.HEIGHT / 2 + 300))
				.push(Tween.to(_activeBackgrounds[1], AccessorReferences.POSITION, _startAnimationDuration)
						.target(Main.WIDTH / 2, -(Main.HEIGHT / 2) + 300).setCallback(new TweenCallback() {

							@Override
							public void onEvent(int arg0, BaseTween<?> arg1) {
								gameStart();

							}
						}))
				.end().start(_tweenManager);
	}

	/**
	 * Handling input.
	 */
	private void handleInput() {
		if (_start)
			return; // stop when animation is already started!
		if (MyInput.isMouseDown(0)) {
			_start = true;
			startAnimation();
		}
	}

	/**
	 * scrolling backgrounds by calculated speed.
	 * Checking if background is out of range.
	 */
	private void scroll() {
		for (int i = 0; i < _activeBackgrounds.length; i++) {
			_activeBackgrounds[i].scroll(_speed);
			int nextIndex = i+1;
			if(nextIndex > _activeBackgrounds.length-1)
				nextIndex = 0; // reset next index if it is bigger than the size of the background array
			
			float yOffset = (_activeBackgrounds[i].getPosition().y - _activeBackgrounds[nextIndex].getPosition().y)- Main.HEIGHT; // calculating y offset
			if(GameManager.isHit){
				if(yOffset > 0) // if there is a gap found.
					_activeBackgrounds[i].scroll(yOffset); // adding/subtracting offset to background to always tile properly
			} else {
				if(yOffset > 0) // if there is a gap found.
					_activeBackgrounds[i].scroll(-yOffset); // adding/subtracting offset to background to always tile properly
			}
			
			if (GameManager.isHit) {
				if (_activeBackgrounds[i].getPosition().y <= -(Main.HEIGHT * 0.49f)) {
					previousBackground(_activeBackgrounds[i]);
				}
			} else {
				if (_activeBackgrounds[i].getPosition().y >= Main.HEIGHT * 1.49f) {
					nextBackground(_activeBackgrounds[i]);
				}
			}
		}
	}

	/**
	 * Update gets called every frame.
	 * @param deltaTime
	 */
	public void update(float deltaTime) {
		handleInput();
		_tweenManager.update(deltaTime);

		if (!_introEnded || _backwardEnded)
			return; // no need to update meters and speed calculations if the introduction is not ended.
		_currentPixelsMoved += _speed; // add speed to moved pixels.
		_meters = (int) ((float) (_currentPixelsMoved / _levelsSize) * MAXIMUM_METERS);

		if (_meters > _phoneLevelDistance * (_phoneLevel + 1) - 15) { // 15 is a dirty fixed number which caused the phone gets spawned when the user hits the phonelevel distance.
			_phoneLevel++;
		}
		if (_meters > _backgroundLevelDistance * (_backgroundLevel + 1)) {
			_backgroundLevel++;
		}
		// speed adjustifiers. 
		if (GameManager.isHit) {
			if (_speed <= BACKWARDS_SPEED) {
				_speed = BACKWARDS_SPEED; // clamping speed
			} else
				_speed -= 0.2f;
		} else {

			if (_speed >= LEVEL_SPEED[_phoneLevel]) {
				_speed = LEVEL_SPEED[_phoneLevel]; // clamping speed by phone level
			} else
				_speed += 0.01f;
		}
		if (_meters >= GameManager.getMaximumDepth()) // end reached.
			gameReverse();
		scroll();
	}

	/**
	 * Reset background position and check if next visualization has to be rendered.
	 * @param background (to reset)
	 */
	private void nextBackground(Background background) {
		if (_activeBackgroundIndex >= BackgroundType.values().length - 1) {
			gameReverse();
			return; //null pointer return this method.
		}

		background.setPosition(Main.WIDTH / 2, -(Main.HEIGHT / 2) + (_speed * 2) + 20); // reset position

		if (_backgroundRepeatCount >= _backgroundCount) {
			_backgroundRepeatCount = 0;
			_activeBackgroundIndex++;
			BackgroundType backgroundType = BackgroundType.values()[_activeBackgroundIndex];
			_backgroundCount = backgroundType.getRepeatCount();
		} else
			_backgroundRepeatCount++;
		background.changeVisualization(BackgroundType.values()[_activeBackgroundIndex]); // change visualization
	}

	/**
	 * Reset background position and check if previous visualization has to be rendered.
	 * @param background (to reset)
	 */
	private void previousBackground(Background background) {
		if (_activeBackgroundIndex <= 0) {
			_backwardEnded = true;
			gameEnd();
			return; //null pointer return this method.
		}
		background.setPosition(Main.WIDTH / 2, (Main.HEIGHT * 1.5f) + (_speed * 2) - 20); // reset position

		if (_backgroundRepeatCount <= 0) {
			_backgroundCount = 0;
			_activeBackgroundIndex--;
			BackgroundType backgroundType = BackgroundType.values()[_activeBackgroundIndex];
			background.changeVisualization(backgroundType);
			_backgroundRepeatCount = backgroundType.getRepeatCount();
		} else
			_backgroundRepeatCount--;
		background.changeVisualization(BackgroundType.values()[_activeBackgroundIndex]);
	}

	/**
	 * Calculate level size of all backgroundTypes
	 */
	private void calculateLevelsSize() {
		for (BackgroundType background : BackgroundType.values()) { // loop trough all backgroundType values.
			_levelsSize += (background.getRepeatCount() + 1) * Main.HEIGHT;
		}

		_levelsSize -= Main.HEIGHT * 2; // Subtracting first two background sizes of intro backgrounds.

		_phoneLevelDistance = (int) (MAXIMUM_METERS / PHONE_LEVELS); // 1000 / 5 = 200
		_backgroundLevelDistance = (int) (MAXIMUM_METERS / BACKGROUND_LEVELS); // 1000 / 3 = 333
	}

	public int getMeters() {
		if (_meters > MAXIMUM_METERS)
			_meters = MAXIMUM_METERS; // clamp meters
		if (_meters < 0)
			_meters = 0; // clamp meters
		return _meters;
	}

	public float getSpeed() {
		return _speed;
	}

	public int getBackgroundLevel() {
		return _backgroundLevel;
	}

	public int getPhoneLevel() {
		return _phoneLevel;
	}

	@Override
	public void castMethod() {
		_introEnded = true;
		_entityManager.removeEntity(_backgroundTop);
	}

	@Override
	protected synchronized void gameReverse() {
		super.gameReverse();
		GameManager.isHit = true;
	}

	@Override
	protected synchronized void gameEnd() {
		super.gameEnd();
		for (Background background : _activeBackgrounds) {
			background.setPosition(Main.WIDTH / 2, Main.HEIGHT / 2);
			background.changeVisualization(BackgroundType.values()[0]);
		}
	}

}
