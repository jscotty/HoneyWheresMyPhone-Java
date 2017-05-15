package com.exam.toolbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class SpriteSheetReaderShoebox {

    /**
     * Reads XML file with given information from sriteType, and generates
     * TextureRegion from atlas/Spritesheet.
     * @param spriteType
     * @return textureRegion from atlas in given spriteType
     */
    public static TextureRegion getTextureFromAtlas(SpriteType type) {
	TextureRegion tDesiredTexture = null; // desired texture
	XmlReader tXml = new XmlReader();
	try {
	    // Element is the root element of your document, i.e. <levels>
	    XmlReader.Element tElement = tXml.parse(Gdx.files.internal(type.getAtlas() + ".xml")); // parse xml file to element
	    Texture tAtlasTexture = new Texture(type.getAtlas() + ".png"); // get atlas texture

	    Array<Element> tSub = tElement.getChildrenByName("SubTexture");
	    for (Element child : tSub) {
		// check if xml contains given sprite name
		if (child.getAttribute("name").equals(type.getSpriteName())) {
		    int x = 0, y = 0, width = 0, height = 0;
		    //sprite name found, generate textureRegion
		    x = Integer.parseInt(child.getAttribute("x"));
		    y = Integer.parseInt(child.getAttribute("y"));
		    width = Integer.parseInt(child.getAttribute("width"));
		    height = Integer.parseInt(child.getAttribute("height"));

		    tDesiredTexture = new TextureRegion(tAtlasTexture, x, y, width, height);
		}
	    }
	} catch (Exception e) {
	    //atlas/spritesheet texture nor xml file not found!!!
	    System.out.println(e.getStackTrace());
	}
	if (tDesiredTexture == null)
	    System.out.println("Failed to read xml file or find given name.");
	return tDesiredTexture;
    }

    /**
     * Reads XML file and generates TextureRegion from atlas/Spritesheet.
     * @param atlas path (.xml and .png are predefined)
     * @param spriteName
     * @return texture region of given sprite name in atlas.
     */
    public static TextureRegion getTextureFromAtlas(String atlas, String spriteName) {
	TextureRegion tDesiredTexture = null; // desired texture
	XmlReader tXml = new XmlReader();
	try {
	    // Element is the root element of your document, i.e. <levels>
	    XmlReader.Element tElement = tXml.parse(Gdx.files.internal(atlas + ".xml")); // parse xml file to element
	    Texture tAtlasTexture = new Texture(atlas + ".png"); // get atlas texture

	    Array<Element> tSub = tElement.getChildrenByName("SubTexture");
	    for (Element child : tSub) {
		    int x = 0, y = 0, width = 0, height = 0;
		// check if xml contains given sprite name
		if (child.getAttribute("name").equals(spriteName)) {
		    //sprite name found, generate textureRegion
		    x = Integer.parseInt(child.getAttribute("x"));
		    y = Integer.parseInt(child.getAttribute("y"));
		    width = Integer.parseInt(child.getAttribute("width"));
		    height = Integer.parseInt(child.getAttribute("height"));

		    tDesiredTexture = new TextureRegion(tAtlasTexture, x, y, width, height);
		}
	    }
	} catch (Exception e) {
	    System.out.println(e.getStackTrace());
	}
	return tDesiredTexture;
    }

}
