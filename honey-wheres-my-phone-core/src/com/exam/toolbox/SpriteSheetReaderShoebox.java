package com.exam.toolbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class SpriteSheetReaderShoebox {
	
	/**
	 * Reads XML file with given information from sriteType, and generates TextureRegion from atlas/Spritesheet.
	 * @param spriteType
	 * @return textureRegion from atlas in given spriteType
	 */
	public static TextureRegion getTextureFromAtlas(SpriteType type){
		TextureRegion desiredTexture = null; // desired texture
		XmlReader xml = new XmlReader();
		try {
	        // Element is the root element of your document, i.e. <levels>
	        XmlReader.Element element = xml.parse(Gdx.files.internal(type.getAtlas() + ".xml")); // parse xml file to element
	        Texture atlasTexture = new Texture(type.getAtlas() + ".png"); // get atlas texture
	        
	        int x=0, y=0, width=0, height=0;
	        Array<Element>sub = element.getChildrenByName("SubTexture");
	        for (Element child : sub){
	        	// check if xml contains given sprite name
	            if(child.getAttribute("name").equals(type.getSpriteName())){
	            	//sprite name found, generate textureRegion
	            	x = Integer.parseInt(child.getAttribute("x"));
	            	y = Integer.parseInt(child.getAttribute("y"));
	            	width = Integer.parseInt(child.getAttribute("width"));
	            	height = Integer.parseInt(child.getAttribute("height"));
	            	
	            	desiredTexture = new TextureRegion(atlasTexture, x, y, width, height);
	            }
	        }
	    } catch (Exception e) {
	    	//atlas/spritesheet texture nor xml file not found!!!
	    	System.out.println(e.getStackTrace());
	    }
		return desiredTexture;
	}
	
	/**
	 * Reads XML file and generates TextureRegion from atlas/Spritesheet.
	 * @param atlas path (.xml and .png are predefined)
	 * @param spriteName
	 * @return
	 */
	public static TextureRegion getTextureFromAtlas(String atlas, String spriteName){
		TextureRegion desiredTexture = null; // desired texture
		XmlReader xml = new XmlReader();
		try {
	        // Element is the root element of your document, i.e. <levels>
	        XmlReader.Element element = xml.parse(Gdx.files.internal(atlas + ".xml")); // parse xml file to element
	        Texture atlasTexture = new Texture(atlas + ".png"); // get atlas texture
	        
	        int x=0, y=0, width=0, height=0;
	        Array<Element>sub = element.getChildrenByName("SubTexture");
	        for (Element child : sub){
	        	// check if xml contains given sprite name
	            if(child.getAttribute("name").equals(spriteName)){
	            	//sprite name found, generate textureRegion
	            	x = Integer.parseInt(child.getAttribute("x"));
	            	y = Integer.parseInt(child.getAttribute("y"));
	            	width = Integer.parseInt(child.getAttribute("width"));
	            	height = Integer.parseInt(child.getAttribute("height"));
	            	
	            	desiredTexture = new TextureRegion(atlasTexture, x, y, width, height);
	            }
	        }
	    } catch (Exception e) {
	    	System.out.println(e.getStackTrace());
	    }
		return desiredTexture;
	}
}
