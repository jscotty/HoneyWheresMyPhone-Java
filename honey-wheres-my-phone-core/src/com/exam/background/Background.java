package com.exam.background;

import com.badlogic.gdx.math.Vector2;
import com.exam.entity.Entity;
import com.exam.entity.EntityManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Background extends Entity {

	private Entity _overlay; // every background has an overlay to draw
	private BackgroundType _backgroundType; // for returning additional information

	/**
	 * Constructor for initializing.
	 * @param world for body 
	 * @param position for rendering position
	 * @param bodyType for Box2D physics
	 * @param spriteType for receiving sprite to render
	 * @param manager to process/register this class for rendering.
	 */
	public Background(Vector2 position, BackgroundType backgroundType, EntityManager manager) {
		super(position, backgroundType.getBackgroundSprite(), manager); // casting parameters to base class
		this._backgroundType = backgroundType;
		_overlay = new Entity(position, backgroundType.getOverlaySprite(), manager).setIndex(10);
		pZIndex = -2;
	}

	/**
	 * Move this background in y direction by given speed.
	 * @param movement speed
	 */
	public void scroll(float speed) {
		pPosition.y += speed;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		_overlay.setPosition(pPosition.x, pPosition.y);
	}

	/**
	 * @return repeat count of current background type.
	 */
	public int getRepeatCount() {
		return _backgroundType.getRepeatCount();
	}

	/**
	 * Change the visualization of this background by given backgroundType.
	 * @param backgroundType
	 */
	public void changeVisualization(BackgroundType backgroundType) {
		this.setTexture(backgroundType.getBackgroundSprite());
		_overlay.setTexture(backgroundType.getOverlaySprite());
		_backgroundType = backgroundType;
	}
}