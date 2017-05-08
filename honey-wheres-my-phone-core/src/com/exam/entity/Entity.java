package com.exam.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;
import com.badlogic.gdx.physics.box2d.World;

public class Entity {

	protected Vector2 position;
	protected Vector2 bodyPosition = new Vector2(0,0);
	protected TextureRegion texture;
	protected Body body;
	protected World world;
	protected Entity parent;
	
	protected float originX = 0.5f;
	protected float originY = 0.5f;
	protected float scaleX = 1f;
	protected float scaleY = 1f;
	protected float angle = 0f;
	
	private float width = 0;
	private float height = 0;

	private float cachedBodyPosX;
	private float cachedBodyPosY;
	
	private BodyType bodyType;
	

	/**
	 * Constructor for circle body 'ghost' body initialization
	 * @param world
	 * @param bodyType
	 * @param radius
	 */
	public Entity(World world, Vector2 position, BodyType bodyType, float width, float height) {
		this.world = world;
		this.position = position;
		this.bodyType = bodyType;
		this.width = width;
		this.height = height;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(position);
		bodyDef.type = bodyType;
		
		body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
	}

	/**
	 * Constructor for square body 'ghost' body initialization
	 * @param world
	 * @param bodyType
	 * @param radius
	 */
	public Entity(World world, Vector2 position, BodyType bodyType, float radius) {
		this.world = world;
		this.position = position;
		this.bodyType = bodyType;
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(position);
		bodyDef.type = bodyType;
		
		body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(texture.getRegionWidth(), texture.getRegionHeight());
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
	}

	/**
	 * constructor for box body initialization
	 * @param world
	 * @param bodyType
	 * @param spriteType
	 */
	public Entity(World world, Vector2 position, BodyType bodyType, SpriteType spriteType) {
		this.world = world;
		this.position = position;
		this.bodyType = bodyType;
		texture = SpriteSheetReaderShoebox.getTextureFromAtlas(spriteType);
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(position);
		bodyDef.type = bodyType;
		
		body = world.createBody(bodyDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(texture.getRegionWidth(), texture.getRegionHeight());
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
	}

	/**
	 * Constructor for circle body initialization
	 * @param world
	 * @param bodyType
	 * @param spriteType
	 * @param radius
	 */
	public Entity(World world, Vector2 position, BodyType bodyType, SpriteType spriteType, float radius) {
		this.world = world;
		this.position = position;
		this.bodyType = bodyType;
		
		texture = SpriteSheetReaderShoebox.getTextureFromAtlas(spriteType);
		
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(position);
		bodyDef.type = bodyType;
		
		body = world.createBody(bodyDef);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
	}
	
	public void resetPosition(){
		position.x = 0;
		position.y = 0;
	}
	
	
	public Entity setAngle(float angle){
		this.angle = angle;
		return this;
	}
	
	public Entity setBodyBox(float width, float height, float positionX, float positionY){
		body.destroyFixture(body.getFixtureList().first());
		this.bodyPosition.x = position.x - positionX ;
		this.bodyPosition.y = position.y - positionY;
		cachedBodyPosX = positionX;
		cachedBodyPosY = positionY;
		System.out.println(bodyPosition);

		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(positionX, positionY);
		bodyDef.type = bodyType;
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width, height);
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
		return this;
	}
	
	public Entity setBodyCircle(float radius, float positionX, float positionY){
		body.destroyFixture(body.getFixtureList().first());
		this.bodyPosition.x = position.x - positionX ;
		this.bodyPosition.y = position.y - positionY;

		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(positionX, positionY);
		bodyDef.type = bodyType;
		
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		body.createFixture(fdef);
		return this;
	}
	
	
	public void update(float deltaTime){
		this.bodyPosition.x = cachedBodyPosX + position.x;
		this.bodyPosition.y = cachedBodyPosY + position.y;
		if(parent != null){
			body.setTransform(new Vector2(	
				parent.getPosition().x + position.x,
				parent.getPosition().y + position.y
			), parent.getAngle() + angle);
		}
	}
	
	protected Vector2 getBodyPosition(){
		System.out.println(bodyPosition);
		return new Vector2(position.x + bodyPosition.x, position.y + bodyPosition.y);
	}
	
	public float getX(){
		if(parent != null)
			return (parent.getPosition().x + position.x) - getOriginX();
		else
			return position.x- getOriginX();
	}
	
	public float getY(){
		if(parent != null)
			return (parent.getPosition().y + position.y) - getOriginY();
		else
			return position.y- getOriginY();
	}
	
	public float getWidth(){
		if(texture != null)
			return texture.getRegionWidth();
		return width;
	}
	
	public float getHeight(){
		if(texture != null)
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
