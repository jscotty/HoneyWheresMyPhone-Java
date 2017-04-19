package com.exam.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.exam.project.Main;
import com.exam.project.ObjectBase;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

public class GUIButton extends GUITexture {

	private TextureRegionDrawable textureIdle = null;
	private TextureRegionDrawable texturePressed = null;
	private ImageButton button;
	/**
	 * Constructor with given texture.
	 * @param texture
	 * @param x position
	 * @param y position
	 */
	public GUIButton(Texture texture, float x, float y){
		super(texture, x, y);
	}

	/**
	 * Constructor with given texture path for generating texture.
	 * @param texturePath
	 * @param x position
	 * @param y position
	 */
	public GUIButton(String texturePath, float x, float y){
		super(texturePath, x, y);
	}
	
	/**
	 * Constructor with given type for region generation.
	 * @param spriteType
	 * @param x position
	 * @param y position
	 */
	public GUIButton(SpriteType buttonIdle, SpriteType buttonPressed, float x, float y){
		super(x, y);
		this.textureIdle = new TextureRegionDrawable(SpriteSheetReaderShoebox.getTextureFromAtlas(buttonIdle));
		this.texturePressed= new TextureRegionDrawable(SpriteSheetReaderShoebox.getTextureFromAtlas(buttonPressed));
		button = new ImageButton(textureIdle);
		button.setTransform(true);
		button.setPosition(getX(), getY());
		button.setScale(scaleX, scaleY);
		button.getStyle().imageDown = texturePressed;
		button.getStyle().imageUp = textureIdle;
	}
	
	@Override
	public ObjectBase setObjectScale(float x, float y) {
		super.setObjectScale(x, y);
		button.setTransform(true);
		button.setScale(scaleX, scaleY);
		button.setPosition(getX(), getY());
		return this;
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	public ImageButton getButton() {
		return button;
	}
	
	@Override
	public float getX() {
		return ((position.x/100)*Gdx.graphics.getWidth()) - ((button.getWidth()*scaleX)*originX);
	}
	
	@Override
	public float getY() {
		return ((position.y/100)*Gdx.graphics.getHeight()) - ((button.getHeight()*scaleY)*originY);
	}
}
