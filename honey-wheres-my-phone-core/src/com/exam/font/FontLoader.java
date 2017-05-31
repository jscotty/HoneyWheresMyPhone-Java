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

	private static HashMap<FontType, BitmapFont> _fonts; // store fonts at
	private boolean _fontsLoaded = false; // check if all fonts are loaded

	/**
	 * Constructor for initialization
	 */
	public FontLoader() {
		_fonts = new HashMap<FontType, BitmapFont>();
	}

	/**
	 * Loads all fonts in a for loop.
	 */
	public void loadFonts() {
		for (FontType font : FontType.values()) {
			_fonts.put(font, new BitmapFont(Gdx.files.internal(font.getFontPathName() + ".fnt"),
					Gdx.files.internal(font.getFontPathName() + ".png"), false));
		}
		_fontsLoaded = true;
	}

	/**
	 * Get loaded font from hashMap
	 * @param fontType to get from hashMap
	 * @return desired BitmapFont.
	 */
	public static BitmapFont getFont(FontType fontType) {
		return _fonts.get(fontType);
	}

	/**
	 * @return if all fonts are loaded
	 */
	public boolean isFontsLoaded() {
		return _fontsLoaded;
	}

}
