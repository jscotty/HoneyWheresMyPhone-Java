package com.exam.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.font.FontLoader;
import com.exam.font.FontType;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class GuiText extends Gui{
	
	private BitmapFont _font;
	
	private String _message = ""; //message to draw
	private float _size = 1; // size factor of font.
	private float _shadowXOffset;
	private float _shadowYOffset;
	private boolean _hasShadow = false;
	
	private Color _fontColor = new Color(1,1,1,1);
	private Color _shadowColor;
	
	/**
	 * Constructor for initialization
	 * @param x position
	 * @param y position
	 * @param manager
	 * @param fontType
	 */
	public GuiText(float x, float y, GuiManager manager, FontType fontType) {
		super(x, y, manager);
		_font = FontLoader.getFont(fontType);
	}

	public GuiText(float x, float y, GuiManager manager, FontType fontType, String text) {
		super(x, y, manager);
		_font = FontLoader.getFont(fontType);
		_message = text;
	}
	
	/**
	 * Add shadow to this gui text with a stable offset.
	 * @param offset
	 * @param color
	 * @return
	 */
	public GuiText addShadow(float offset, Color color){
		this._shadowXOffset = offset;
		this._shadowYOffset = offset;
		_hasShadow = true;
		_shadowColor = color;
		return this;
	}
	
	/**
	 * Add shadow to this gui text with x and y offset
	 * @param xOffset
	 * @param yOffset
	 * @param color
	 * @return
	 */
	public GuiText addShadow(float xOffset, float yOffset, Color color){
		this._shadowXOffset = xOffset;
		this._shadowYOffset = yOffset;
		_hasShadow = true;
		_shadowColor = color;
		return this;
	}
	
	/**
	 * Set text message to be drawn.
	 * @param message to be drawn
	 */
	public void setText(String message){
		this._message = message;
	}
	
	/**
	 * Set size of this text.
	 * @param size
	 */
	public void setFontSize(float size){
		this._size = size;
	}
	
	/**
	 * Set text color.
	 * @param color
	 */
	public void setColor(Color color){
		_fontColor = color;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		if(_size == 0) return; // bitmapfont does not support a font size of 0
		_font.setScale(_size);
		
		if(_hasShadow){
			_font.setColor(_shadowColor);
			_font.draw(spriteBatch, _message, pPosition.x + _shadowXOffset, pPosition.y + _shadowYOffset);
		}
		
		_font.setColor(_fontColor);
		_font.draw(spriteBatch, _message, pPosition.x, pPosition.y);
		
		//reset font for other classes who might use this font as well!
		_font.setColor(new Color(1,1,1,1));
		_font.setScale(1); 
	}

	/**
	 * @return font size
	 */
	public float getSize() {
		return _size;
	}

}
