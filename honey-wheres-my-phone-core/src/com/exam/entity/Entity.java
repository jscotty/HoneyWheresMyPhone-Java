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
import com.exam.handlers.EntityManager;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;
import com.badlogic.gdx.physics.box2d.World;

public class Entity implements Comparable<Entity> {

	protected Vector2 position;
	protected Vector2 bodyPositionDistance = new Vector2(0, 0);
	protected Vector2 bodyPosition = new Vector2(0, 0);
	protected TextureRegion texture;
	protected Body body;
	protected World world;
	protected Entity parent;

	protected float originX = 0.5f;
	protected float originY = 0.5f;
	protected float scaleX = 1f;
	protected float scaleY = 1f;
	protected float angle = 0f;

	protected int zIndex = 0; // for sorting.

	private float width = 0;
	private float height = 0;

	private BodyType bodyType;

	//region Constructors

	/**
	 * Constructor for 'ghost' initialization
	 * @param world
	 * @param bodyType
	 */
	public Entity(World world, Vector2 position, BodyType bodyType, EntityManager manager) {
		this.world = world;
		this.position = position;
		this.bodyType = bodyType;

		manager.processEntity(this);
	}

	/**
	 * constructor for box body initialization
	 * @param world
	 * @param bodyType
	 * @param spriteType
	 */
	public Entity(World world, Vector2 position, BodyType bodyType, SpriteType spriteType, EntityManager manager) {
		this.world = world;
		this.position = position;
		this.bodyType = bodyType;
		texture = Main.assets.getTexture(spriteType);

		manager.processEntity(this);
	}

	//endregion
	
	public Entity setIndex(int index){
		zIndex = index;
		return this;
	}

	//region body initialization.
	/**
	 * Add squared body to this entity on entity position and size of entity (texture)
	 * @return this
	 */
	public Entity addBodyBox() {
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(texture.getRegionWidth() / Main.DEVIDER, texture.getRegionHeight() / Main.DEVIDER);

		setupBody(shape, position.x, position.y);
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
	public Entity addBodyBox(float width, float height, float x, float y) {
		//Manhattan distance calculation
		this.bodyPositionDistance.x = position.x - x;
		this.bodyPositionDistance.y = position.y - y;

		System.out.println("currPos " + position + " || bodyPos " + x + " , " + y + " | " + bodyPositionDistance);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);

		setupBody(shape, x, y);
		return this;
	}

	/**
	 * Add squared body to this entity on given position and size of entity (texture)
	 * @param x position
	 * @param y position
	 * @return this
	 */
	public Entity addBodyBox(float x, float y) {
		//Manhattan distance calculation
		this.bodyPositionDistance.x = position.x - x;
		this.bodyPositionDistance.y = position.y - y;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(texture.getRegionWidth() / Main.DEVIDER, texture.getRegionHeight() / Main.DEVIDER);

		setupBody(shape, x, y);
		return this;
	}

	/**
	 * Add circle body to this entity with given radius on given position
	 * @param radius
	 * @param positionX
	 * @param positionY
	 * @return
	 */
	public Entity addBodyCircle(float radius, float positionX, float positionY) {
		this.bodyPositionDistance.x = position.x - positionX;
		this.bodyPositionDistance.y = position.y - positionY;

		CircleShape shape = new CircleShape();
		shape.setRadius(radius);

		setupBody(shape, positionX, positionY);
		return this;
	}

	/**
	 * setting up a Box2D body.
	 * @param shape (CircleShape nor PolygonShape)
	 * @param x position
	 * @param y position
	 */
	private void setupBody(Shape shape, float positionX, float positionY) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(positionX, positionY);
		bodyDef.type = bodyType;
		body = world.createBody(bodyDef);

		bodyDef.type = bodyType;
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
	}
	//endregion

	public void resetPosition() {
		position.x = 0;
		position.y = 0;
	}

	public Entity setAngle(float angle) {
		this.angle = angle;
		return this;
	}

	public void update(float deltaTime) {
		bodyPosition.x = (position.x - bodyPositionDistance.x);
		bodyPosition.y = (position.y - bodyPositionDistance.y);
		if (parent != null) {
			body.setTransform(new Vector2(parent.getPosition().x + position.x, parent.getPosition().y + position.y),
					parent.getAngle() + angle);
		}
	}

	public void render(SpriteBatch spriteBatch) {
		spriteBatch.draw(texture, getX(), getY(), originX, originY, getWidth(), getHeight(), scaleX, scaleY, angle);
	}

	@Override
	public int compareTo(Entity o) {
		if (this.zIndex < o.zIndex) {
			return -1;
		} else {
			return 1;
		}
	}

	protected Vector2 getBodyPosition() {
		return bodyPosition;
	}

	public float getX() {
		if (parent != null)
			return (parent.getPosition().x + position.x) - getOriginX();
		else
			return position.x - getOriginX();
	}

	public float getY() {
		if (parent != null)
			return (parent.getPosition().y + position.y) - getOriginY();
		else
			return position.y - getOriginY();
	}

	public float getWidth() {
		if (texture != null)
			return texture.getRegionWidth();
		return width;
	}

	public float getHeight() {
		if (texture != null)
			return texture.getRegionHeight();
		return height;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public Vector2 getPosition() {
		return position;
	}

	public Entity getParent() {
		return parent;
	}

	public float getOriginX() {
		return originX * getWidth();
	}

	public float getOriginY() {
		return originY * getHeight();
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public float getAngle() {
		return angle;
	}
}
