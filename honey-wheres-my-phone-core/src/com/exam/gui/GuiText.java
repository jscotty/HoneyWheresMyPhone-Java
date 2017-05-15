package com.exam.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.font.FontLoader;
import com.exam.font.FontType;
import com.exam.handlers.GUIManager;

public class GuiText extends Gui{
	
	private BitmapFont _font;
	
	private String _message = "";
	private float _size = 1;
	private float _shadowXOffset;
	private float _shadowYOffset;
	private boolean _hasShadow = false;
	
	private Color _fontColor = new Color(1,1,1,1);
	private Color _shadowColor;
	
	public GuiText(float x, float y, GUIManager manager, FontType fontType) {
		super(x, y, manager);
		_font = FontLoader.getFont(fontType);
	}
	
	public GuiText addShadow(float offset, Color color){
		this._shadowXOffset = offset;
		this._shadowYOffset = offset;
		_hasShadow = true;
		_shadowColor = color;
		return this;
	}
	
	public GuiText addShadow(float xOffset, float yOffset, Color color){
		this._shadowXOffset = xOffset;
		this._shadowYOffset = yOffset;
		_hasShadow = true;
		_shadowColor = color;
		return this;
	}
	
	public void setMessage(String message){
		this._message = message;
	}
	
	public void setFontSize(float size){
		this._size = size;
	}
	
	public void setColor(Color color){
		_fontColor = color;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
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

	

}
