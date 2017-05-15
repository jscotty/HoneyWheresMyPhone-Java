package com.exam.font;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public enum FontType {
	SUPERCELL_MAGIC("font/supercell-magic");
	
	private String _fontName;
	
	private FontType(String fontName){
		this._fontName = fontName;
	}
	
	/**
	 * Get font path name, can be used for .fnt and .png
	 * @return font path name.
	 */
	public String getFontPathName() {
		return _fontName;
	}
}
