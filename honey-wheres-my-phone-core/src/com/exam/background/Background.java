package com.exam.background;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.assets.SpriteType;
import com.exam.entity.Entity;
import com.exam.entity.EntityManager;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Background extends Entity {

	private Entity _overlay;
	private BackgroundType _backgroundType;

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
	 * Move this backround in y direction by given speed.
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