package com.exam.entity;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.exam.handlers.EntityManager;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Background extends Entity {
	
	private float _speed = 0f;
	private float _maxSpeed = 0.01f;
	private List<TextureRegion> _overlays = new ArrayList<TextureRegion>(); // just in case there will be more overlays needed.

	/**
	 * Constructor for initializing.
	 * @param world for body 
	 * @param position for rendering position
	 * @param bodyType for Box2D physics
	 * @param spriteType for receiving sprite to render
	 * @param manager to process/register this class for rendering.
	 */
	public Background(World world, Vector2 position, BodyType bodyType, SpriteType spriteType, EntityManager manager) {
		super(world, position, bodyType, spriteType, manager); // casting parameters to base class
		pTexture.getTexture().setWrap(TextureWrap.Repeat, TextureWrap.Repeat); // set texture to repeat mode for scrolling.
		pZIndex = -2;
	}
	
	/**
	 * Adds a sprite to draw as overlay.
	 * @param spriteType for receiving sprite to render.
	 */
	public void addOverlay(SpriteType spriteType){
		_overlays.add(Main.assets.getTexture(spriteType));
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		_speed += 0.0005*deltaTime;
		if(_speed > _maxSpeed) _speed = _maxSpeed;
		pTexture.scroll(0,_speed);
	}
	
	@Override
	public void render(SpriteBatch spriteBatch) {
		super.render(spriteBatch);
		if(_overlays.size() == 0) return; // no need to draw overlays who do not exist!
		for (TextureRegion overlay : _overlays) {
			spriteBatch.draw(overlay, getX(), getY(), pOriginX, pOriginY,overlay.getRegionWidth(), overlay.getRegionHeight(), pScaleX, pScaleY, pAngle);
		}
	}

}
