package com.exam.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.exam.assets.AnimationType;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Animation extends Entity {

	private TextureRegion[] _textures;
	private int _frameIndex = 0; // current animation frame
	private boolean _loop = false; // if the animation has to repeat
	private float _keyTimeDuration; // duration of every key frame to be rendered
	private float _keyTime; // current key time
	private float _time; // current time.
	private boolean _isFinished = false; // if loop is false check if the last frame is being rendered.

	public Animation(AnimationType animationType, boolean loop, float duration, Vector2 position,
			EntityManager manager) {
		super(position, manager);
		this._loop = loop;
		_textures = Main.assets.getAnimation(animationType);
		_keyTimeDuration = duration / _textures.length; // time delay to render every key frame.
		_keyTime = _keyTimeDuration;
		setTexture(_textures[_frameIndex]);
	}

	@Override
	public void update(float deltaTime) {
		if (_isFinished)
			return; // if animation has been finished stop updating
		_time += deltaTime;
		if (_time >= _keyTime) { // next frame
			_keyTime += _keyTimeDuration;

			if (_frameIndex >= _textures.length - 1) {
				if (!_loop)
					_isFinished = true; // if we are not looping. stop.
				else
					_frameIndex = 0; // reset animation frame index
			} else
				_frameIndex++; // next frame
			setTexture(_textures[_frameIndex]); // set animation frame texture to be rendered.
		}
	}

	/**
	 * Set current frame. 
	 * This can be used to set an animation on a particular frame.
	 * @param frameIndex
	 */
	public void setFrame(int frameIndex) {
		this._frameIndex = frameIndex;
	}

	/**
	 * @return check if animation has finished.
	 */
	public boolean isFinished() {
		return _isFinished;
	}
}
