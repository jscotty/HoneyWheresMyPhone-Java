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

			Array<Element> sub = tElement.getChildrenByName("SubTexture");
			for (Element child : sub) {
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
			System.out.println("Failed to read xml file or find given name." + type.getSpriteName());
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

			Array<Element> sub = tElement.getChildrenByName("SubTexture");
			for (Element child : sub) {
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
	
	public static TextureRegion[] getTexturesFromAtlas(String atlas) {
		TextureRegion[] textures = null;
		XmlReader tXml = new XmlReader();
		try {
			// Element is the root element of your document, i.e. <levels>
			XmlReader.Element tElement = tXml.parse(Gdx.files.internal(atlas + ".xml")); // parse xml file to element
			Texture tAtlasTexture = new Texture(atlas + ".png"); // get atlas texture

			Array<Element> sub = tElement.getChildrenByName("SubTexture");
			textures = new TextureRegion[sub.size]; // desired texture
			int index = 0;
			for (Element child : sub) {
				int x = 0, y = 0, width = 0, height = 0;
				//sprite name found, generate textureRegion
				x = Integer.parseInt(child.getAttribute("x"));
				y = Integer.parseInt(child.getAttribute("y"));
				width = Integer.parseInt(child.getAttribute("width"));
				height = Integer.parseInt(child.getAttribute("height"));

				textures[index] = new TextureRegion(tAtlasTexture, x, y, width, height);
				index++;
			}
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return textures;
	}
	
	public static TextureRegion[] getTexturesFromAtlas(AnimationType type) {
		TextureRegion[] textures = null;
		XmlReader tXml = new XmlReader();
		try {
			// Element is the root element of your document, i.e. <levels>
			XmlReader.Element tElement = tXml.parse(Gdx.files.internal(type.getAnimationPath() + ".xml")); // parse xml file to element
			Texture tAtlasTexture = new Texture(type.getAnimationPath() + ".png"); // get atlas texture

			Array<Element> sub = tElement.getChildrenByName("SubTexture");
			int arrayIndex = 0;
			int spriteIndex = 0;
			if(type.getStartFrame()==0&&type.getEndFrame()==0){
				textures = new TextureRegion[sub.size];
			} else {
				textures = new TextureRegion[(type.getEndFrame() - type.getStartFrame())+1];
			}
			for (Element child : sub) {
				int x = 0, y = 0, width = 0, height = 0;
				//sprite name found, generate textureRegion
				x = Integer.parseInt(child.getAttribute("x"));
				y = Integer.parseInt(child.getAttribute("y"));
				width = Integer.parseInt(child.getAttribute("width"));
				height = Integer.parseInt(child.getAttribute("height"));

				if(type.getStartFrame()==0&&type.getEndFrame()==0){
					textures[arrayIndex] = new TextureRegion(tAtlasTexture, x, y, width, height);
					arrayIndex++;
				}else if(spriteIndex >= type.getStartFrame() && spriteIndex <= type.getEndFrame()){
					textures[arrayIndex] = new TextureRegion(tAtlasTexture, x, y, width, height);
					arrayIndex++;
				}
				spriteIndex++;
			}
		} catch (Exception e) {
			System.out.println("Could not load animation from: " + type.getAnimationPath());
			System.out.println(e.getStackTrace());
		}
		return textures;
	}

}
