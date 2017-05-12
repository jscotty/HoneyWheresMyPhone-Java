package com.exam.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.exam.font.FontLoader;
import com.exam.font.FontType;
import com.exam.handlers.GUIManager;

public class GuiText extends Gui{
	
	private BitmapFont font;
	
	private FontType fontType;
	
	private String message = "";
	private float size = 1;
	private float shadowXOffset;
	private float shadowYOffset;
	private boolean hasShadow = false;
	
	private Color fontColor = new Color(1,1,1,1);
	private Color shadowColor;
	
	public GuiText(float x, float y, GUIManager manager, FontType fontType) {
		super(x, y, manager);
		this.fontType = fontType;
		font = FontLoader.getFont(fontType);
	}
	
	public GuiText addShadow(float offset, Color color){
		this.shadowXOffset = offset;
		this.shadowYOffset = offset;
		hasShadow = true;
		shadowColor = color;
		return this;
	}
	
	public GuiText addShadow(float xOffset, float yOffset, Color color){
		this.shadowXOffset = xOffset;
		this.shadowYOffset = yOffset;
		hasShadow = true;
		shadowColor = color;
		return this;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
	public void setFontSize(float size){
		this.size = size;
	}
	
	public void setColor(Color color){
		fontColor = color;
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		font.setScale(size);
		
		if(hasShadow){
			font.setColor(shadowColor);
			font.draw(spriteBatch, message, position.x + shadowXOffset, position.y + shadowYOffset);
		}
		
		font.setColor(fontColor);
		font.draw(spriteBatch, message, position.x, position.y);
		
		//reset font for other classes who might use this font as well!
		font.setColor(new Color(1,1,1,1));
		font.setScale(1); 
	}

	

}
