package com.exam.font;

public enum FontType {
	SUPERCELL_MAGIC("font/supercell-magic");
	
	private String fontName;
	
	private FontType(String fontName){
		this.fontName = fontName;
	}
	
	public String getFontName() {
		return fontName;
	}
}
