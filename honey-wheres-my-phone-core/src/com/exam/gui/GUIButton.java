package com.exam.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.exam.project.ObjectBase;
import com.exam.toolbox.SpriteSheetReaderShoebox;
import com.exam.toolbox.SpriteType;

public class GUIButton extends GUITexture {

    private TextureRegionDrawable _textureIdle = null;
    private TextureRegionDrawable _texturePressed = null;
    private ImageButton _button;


    /**
     * Constructor with given type for region generation.
     * @param spriteType
     * @param x position
     * @param y position
     */
    public GUIButton(SpriteType buttonIdle, SpriteType buttonPressed, float x, float y) {
	super(x, y);
	pOriginX = 0.5f;
	pOriginY = 0.5f;
	this._textureIdle = new TextureRegionDrawable(SpriteSheetReaderShoebox.getTextureFromAtlas(buttonIdle));
	this._texturePressed = new TextureRegionDrawable(SpriteSheetReaderShoebox.getTextureFromAtlas(buttonPressed));
	_button = new ImageButton(_textureIdle);
	_button.setTransform(true);
	_button.setPosition(getX(), getY());
	_button.setScale(pScaleX, pScaleY);
	_button.getStyle().imageDown = _texturePressed;
	_button.getStyle().imageUp = _textureIdle;
    }

    @Override
    public ObjectBase setObjectScale(float x, float y) {
	super.setObjectScale(x, y);
	_button.setTransform(true);
	_button.setScale(pScaleX, pScaleY);
	_button.setPosition(getX(), getY());
	return this;
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public ImageButton getButton() {
	return _button;
    }

    @Override
    public float getX() {
	return ((pPosition.x / 100) * Gdx.graphics.getWidth()) - (getWidth() * pOriginX);
    }

    @Override
    public float getY() {
	return ((pPosition.y / 100) * Gdx.graphics.getHeight()) - (getHeight()* pOriginY);
    }
    
    @Override
    public float getWidth() {
        return (_button.getWidth() * pScaleX);
    }
    
    @Override
    public float getHeight() {
        return (_button.getHeight() * pScaleY);
    }
}
