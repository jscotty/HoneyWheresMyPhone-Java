package com.exam.font;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class FontLoader {
	
	private static HashMap<FontType, BitmapFont> fonts;
	private boolean _fontsLoaded = false;
	
	/**
	 * Constructor for initialization
	 */
	public FontLoader() {
		fonts = new HashMap<FontType, BitmapFont>();
	}
	
	/**
	 * Loads all fonts in a for loop.
	 */
	public void loadFonts(){
		for (FontType font : FontType.values()) {
			fonts.put(font, new BitmapFont(Gdx.files.internal(font.getFontPathName()+".fnt") , Gdx.files.internal(font.getFontPathName()+".png"), false));
		}
		_fontsLoaded = true;
	}
	
	/**
	 * Get loaded font from hashMap
	 * @param fontType to get from hashMap
	 * @return desired BitmapFont.
	 */
	public static BitmapFont getFont(FontType fontType){
		return fonts.get(fontType);
	}
	
	/**
	 * @return if all fonts are loaded
	 */
	public boolean isFontsLoaded() {
		return _fontsLoaded;
	}
	
}
