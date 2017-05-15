package com.exam.font;

public enum FontType {
	SUPERCELL_MAGIC("font/supercell-magic");
	
	private String _fontName;
	
	private FontType(String fontName){
		this._fontName = fontName;
	}
	
	public String getFontPathName() {
		return _fontName;
	}
}
