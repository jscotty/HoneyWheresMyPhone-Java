package com.exam.font;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontLoader {
	
	private static HashMap<FontType, BitmapFont> fonts;
	private boolean fontsLoaded = false;
	
	public FontLoader() {
		fonts = new HashMap<FontType, BitmapFont>();
	}
	
	public void loadFonts(){
		for (FontType font : FontType.values()) {
			fonts.put(font, new BitmapFont(Gdx.files.internal(font.getFontName()+".fnt") , Gdx.files.internal(font.getFontName()+".png"), false));
		}
		fontsLoaded = true;
	}
	
	public static BitmapFont getFont(FontType fontType){
		return fonts.get(fontType);
	}
	
	public boolean isFontsLoaded() {
		return fontsLoaded;
	}
	
}
