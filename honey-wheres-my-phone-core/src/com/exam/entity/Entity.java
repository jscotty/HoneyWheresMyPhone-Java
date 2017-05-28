package com.exam.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.exam.handlers.GameEvent;
import com.exam.handlers.GameEventListener;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Entity implements Comparable<Entity>{

	// render data
	protected Vector2 pPosition = new Vector2(0, 0); // position to be rendered at
	private Vector2 _bodyPositionDistance = new Vector2(0, 0); // Calculating distance to adjust the body position
	protected Vector2 pBodyPosition = new Vector2(0, 0); // body position for box2D utility

	protected float pOriginX = 0.5f; // entity origin X
	protected float pOriginY = 0.5f; // entity origin Y
	protected float pScaleX = 1f; // scale X factor
	protected float pScaleY = 1f; // scale Y factor
	protected float pAngle = 0f;
	private float _width = 0;
	private float _height = 0;

	// box2D and render visualization data
	protected Body pBody;
	protected TextureRegion pTexture;
	private World _world;

	protected int pZIndex = 0; // for sorting.

	//region Constructors

	/**
	 * Constructor for 'ghost' initialization
	 * @param world
	 * @param bodyType
	 */
	public Entity(Vector2 position, EntityManager manager) {
		this.pPosition = position;

		manager.processEntity(this);
	}

	/**
	 * constructor for box body initialization
	 * @param world
	 * @param bodyType
	 * @param spriteType
	 */
	public Entity(Vector2 position, SpriteType spriteType, EntityManager manager) {
		this.pPosition = position;
		pTexture = Main.assets.getTexture(spriteType);

		manager.processEntity(this);
	}

	//endregion
	
	public Entity setIndex(int index){
		pZIndex = index;
		return this;
	}

	//region body initialization.
	/**
	 * Add squared body to this entity on entity position and size of entity (texture)
	 * @return this
	 */
	public Entity addBodyBox(World world, BodyType bodyType, Object userData) {
		this._world = world;
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(pTexture.getRegionWidth() / Main.DEVIDER, pTexture.getRegionHeight() / Main.DEVIDER);

		setupBody(shape, bodyType, pPosition.x, pPosition.y, userData);
		return this;
	}

	/**
	 * Add polygon body to this entity on entity position with a custom shape
	 * @return this
	 */
	public Entity addBodyBox(World world, BodyType bodyType, float[] vertices, Object userData) {
		this._world = world;
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);

		setupBody(shape, bodyType, pPosition.x, pPosition.y, userData);
		return this;
	}

	/**
	 * Add squared body to this entity on given position with given size.
	 * @param width size
	 * @param height size
	 * @param x position
	 * @param y position
	 * @return this
	 */
	public Entity addBodyBox(World world, BodyType bodyType, float width, float height, float positionX, float positionY, Object userData) {
		this._world = world;
		//Manhattan distance calculation
		this._bodyPositionDistance.x = pPosition.x - positionX;
		this._bodyPositionDistance.y = pPosition.y - positionY;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);
		

		setupBody(shape, bodyType, positionX, positionY, userData);
		return this;
	}

	/**
	 * Add squared body to this entity on given position and size of entity (texture)
	 * @param x position
	 * @param y position
	 * @return this
	 */
	public Entity addBodyBox(World world, BodyType bodyType, float positionX, float positionY, Object userData) {
		this._world = world;
		//Manhattan distance calculation
		this._bodyPositionDistance.x = pPosition.x - positionX;
		this._bodyPositionDistance.y = pPosition.y - positionY;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(pTexture.getRegionWidth() / Main.DEVIDER, pTexture.getRegionHeight() / Main.DEVIDER);

		setupBody(shape, bodyType, positionX, positionY, userData);
		return this;
	}

	/**
	 * Add circle body to this entity with given radius on given position
	 * @param radius
	 * @param positionX
	 * @param positionY
	 * @return
	 */
	public Entity addBodyCircle(World world, BodyType bodyType, float radius, float positionX, float positionY, Object userData) {
		this._world = world;
		//Manhattan distance calculation
		this._bodyPositionDistance.x = pPosition.x - positionX;
		this._bodyPositionDistance.y = pPosition.y - positionY;

		CircleShape shape = new CircleShape();
		shape.setRadius(radius);

		setupBody(shape, bodyType, positionX, positionY,userData);
		return this;
	}

	/**
	 * setting up a Box2D body.
	 * @param shape (CircleShape nor PolygonShape)
	 * @param x position
	 * @param y position
	 */
	private void setupBody(Shape shape, BodyType bodyType, float positionX, float positionY, Object userData) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(positionX, positionY);
		bodyDef.type = bodyType;
		pBody = _world.createBody(bodyDef);

		bodyDef.type = bodyType;
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		pBody.createFixture(fdef).setUserData(userData);
	}
	
	public void disableBody(){
		_world.destroyBody(pBody);
	}
	//endregion

	/**
	 * reset position to 0
	 */
	public void resetPosition() {
		pPosition.x = 0;
		pPosition.y = 0;
	}

	/**
	 * set angle to given parameter
	 * @param angle
	 * @return this
	 */
	public Entity setAngle(float angle) {
		this.pAngle = angle;
		return this;
	}

	/**
	 * updates every frame
	 * @param deltaTime, time between current and last frame.
	 */
	public void update(float deltaTime) {
		pBodyPosition.x = (pPosition.x - _bodyPositionDistance.x);
		pBodyPosition.y = (pPosition.y - _bodyPositionDistance.y);
	}

	/**
	 * rendering texture
	 * called every frame
	 * @param spriteBatch
	 */
	public void render(SpriteBatch spriteBatch) {
		if(pTexture == null) return;
		spriteBatch.draw(pTexture, getX(), getY(), pOriginX, pOriginY, getWidth(), getHeight(), pScaleX, pScaleY, pAngle);
	}

	@Override
	public int compareTo(Entity o) {
		if (this.pZIndex < o.pZIndex) {
			return -1;
		} else {
			return 1;
		}
	}

//region properties
	/**
	 * Set texture by SpriteType to change visualization.
	 * @param spriteType
	 */
	public void setTexture(SpriteType spriteType){
		this.pTexture = Main.assets.getTexture(spriteType);
	}
	
	/**
	 * Set texture to new texture region to change visualization.
	 * @param texture
	 */
	public void setTexture(TextureRegion texture){
		this.pTexture = texture;
	}
	
	/**
	 * Set render position.
	 * @param xPosition
	 * @param yPosition
	 */
	public void setPosition(float xPosition, float yPosition){
		this.pPosition.x = xPosition;
		this.pPosition.y = yPosition;
	}
	
	/**
	 * Set scale factor.
	 * @param scaleX
	 * @param scaleY
	 */
	public void setScale(float scaleX, float scaleY){
		this.pScaleX = scaleX;
		this.pScaleY = scaleY;
	}
	
	protected Vector2 getBodyPosition() {
		return pBodyPosition;
	}

	protected float getX() {
		return pPosition.x - getOriginX();
	}

	protected float getY() {
		return pPosition.y - getOriginY();
	}

	public float getWidth() {
		if (pTexture != null)
			return pTexture.getRegionWidth();
		return _width; // return cached width if there is no texture
	}

	public float getHeight() {
		if (pTexture != null)
			return pTexture.getRegionHeight();
		return _height; // return cached height if there is no texture.
	}

	public TextureRegion getTexture() {
		return pTexture;
	}

	public Vector2 getPosition() {
		return pPosition;
	}
	
	public float getOriginX() {
		return pOriginX * getWidth();
	}

	public float getOriginY() {
		return pOriginY * getHeight();
	}

	public float getScaleX() {
		return pScaleX;
	}

	public float getScaleY() {
		return pScaleY;
	}

	public float getAngle() {
		return pAngle;
	}
//endregion
}
