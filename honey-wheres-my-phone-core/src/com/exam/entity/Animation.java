package com.exam.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.exam.toolbox.SpriteSheetReaderShoebox;

public class Animation extends Entity{

	private TextureRegion[] textures;
	
	public Animation(String atlas, Vector2 position, EntityManager manager) {
		super(position, manager);
		textures = SpriteSheetReaderShoebox.getTexturesFromAtlas(atlas);
		
	}
	
	@Override
	public void update(float deltaTime) {
		
	}
	
	@Override
	public void render(SpriteBatch spriteBatch) {
		
	}
}
