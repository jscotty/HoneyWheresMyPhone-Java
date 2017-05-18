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
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class Entity implements Comparable<Entity> {

	// render data
	protected Vector2 pPosition = new Vector2(0, 0); 
	private Vector2 _bodyPositionDistance = new Vector2(0, 0); // Calculating distance to adjust the body position
	protected Vector2 pBodyPosition = new Vector2(0, 0);

	protected float pOriginX = 0.5f;
	protected float pOriginY = 0.5f;
	protected float pScaleX = 1f;
	protected float pScaleY = 1f;
	protected float pAngle = 0f;
	private Entity _parent;
	private float _width = 0;
	private float _height = 0;

	// box2D and render visualization data
	protected Body pBody;
	protected TextureRegion pTexture;
	private World _world;
	private BodyType _bodyType;

	protected int pZIndex = 0; // for sorting.

	//region Constructors

	/**
	 * Constructor for 'ghost' initialization
	 * @param world
	 * @param bodyType
	 */
	public Entity(World world, Vector2 position, BodyType bodyType, EntityManager manager) {
		this._world = world;
		this.pPosition = position;
		this._bodyType = bodyType;

		manager.processEntity(this);
	}

	/**
	 * constructor for box body initialization
	 * @param world
	 * @param bodyType
	 * @param spriteType
	 */
	public Entity(World world, Vector2 position, BodyType bodyType, SpriteType spriteType, EntityManager manager) {
		this._world = world;
		this.pPosition = position;
		this._bodyType = bodyType;
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
	public Entity addBodyBox(String userData) {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(pTexture.getRegionWidth() / Main.DEVIDER, pTexture.getRegionHeight() / Main.DEVIDER);

		setupBody(shape, pPosition.x, pPosition.y, userData);
		return this;
	}

	/**
	 * Add polygon body to this entity on entity position with a custom shape
	 * @return this
	 */
	public Entity addBodyBox(float[] vertices, String userData) {
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);

		setupBody(shape, pPosition.x, pPosition.y, userData);
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
	public Entity addBodyBox(float width, float height, float x, float y, String userData) {
		//Manhattan distance calculation
		this._bodyPositionDistance.x = pPosition.x - x;
		this._bodyPositionDistance.y = pPosition.y - y;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);
		

		setupBody(shape, x, y, userData);
		return this;
	}

	/**
	 * Add squared body to this entity on given position and size of entity (texture)
	 * @param x position
	 * @param y position
	 * @return this
	 */
	public Entity addBodyBox(float x, float y, String userData) {
		//Manhattan distance calculation
		this._bodyPositionDistance.x = pPosition.x - x;
		this._bodyPositionDistance.y = pPosition.y - y;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(pTexture.getRegionWidth() / Main.DEVIDER, pTexture.getRegionHeight() / Main.DEVIDER);

		setupBody(shape, x, y,userData);
		return this;
	}

	/**
	 * Add circle body to this entity with given radius on given position
	 * @param radius
	 * @param positionX
	 * @param positionY
	 * @return
	 */
	public Entity addBodyCircle(float radius, float positionX, float positionY, String userData) {
		this._bodyPositionDistance.x = pPosition.x - positionX;
		this._bodyPositionDistance.y = pPosition.y - positionY;

		CircleShape shape = new CircleShape();
		shape.setRadius(radius);

		setupBody(shape, positionX, positionY,userData);
		return this;
	}

	/**
	 * setting up a Box2D body.
	 * @param shape (CircleShape nor PolygonShape)
	 * @param x position
	 * @param y position
	 */
	private void setupBody(Shape shape, float positionX, float positionY, String userData) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(positionX, positionY);
		bodyDef.type = _bodyType;
		pBody = _world.createBody(bodyDef);

		bodyDef.type = _bodyType;
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		pBody.createFixture(fdef).setUserData(userData);;
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
	 * updates everu frame
	 * @param deltaTime, time between current and last frame.
	 */
	public void update(float deltaTime) {
		pBodyPosition.x = (pPosition.x - _bodyPositionDistance.x);
		pBodyPosition.y = (pPosition.y - _bodyPositionDistance.y);
		if (_parent != null) {
			pBody.setTransform(new Vector2(_parent.getPosition().x + pPosition.x, _parent.getPosition().y + pPosition.y),
					_parent.getAngle() + pAngle);
		}
	}

	/**
	 * rendering texture
	 * called every frame
	 * @param spriteBatch
	 */
	public void render(SpriteBatch spriteBatch) {
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
	protected Vector2 getBodyPosition() {
		return pBodyPosition;
	}

	public float getX() {
		if (_parent != null)
			return (_parent.getPosition().x + pPosition.x) - getOriginX();
		else
			return pPosition.x - getOriginX();
	}

	public float getY() {
		if (_parent != null)
			return (_parent.getPosition().y + pPosition.y) - getOriginY();
		else
			return pPosition.y - getOriginY();
	}

	public float getWidth() {
		if (pTexture != null)
			return pTexture.getRegionWidth();
		return _width;
	}

	public float getHeight() {
		if (pTexture != null)
			return pTexture.getRegionHeight();
		return _height;
	}

	public TextureRegion getTexture() {
		return pTexture;
	}

	public Vector2 getPosition() {
		return pPosition;
	}

	public Entity getParent() {
		return _parent;
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
