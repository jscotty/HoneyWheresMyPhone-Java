package com.exam.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.exam.project.Main;
import com.exam.toolbox.AnimationType;
import com.exam.toolbox.SpriteSheetReaderShoebox;

public class Animation extends Entity{

	private TextureRegion[] textures;
	private int frameIndex = 0;
	private boolean loop = false;
	private float keyTimeDuration;
	private float keyTime;
	private float time;
	private boolean isFinished = false;
	
	public Animation(AnimationType animationType, boolean loop, float duration, Vector2 position, EntityManager manager) {
		super(position, manager);
		this.loop = loop;
		textures = Main.assets.getAnimation(animationType);
		keyTimeDuration = duration/textures.length; // time delay to render every key frame.
		keyTime = keyTimeDuration;
		setTexture(textures[frameIndex]);
	}
	
	@Override
	public void update(float deltaTime) {
		if(isFinished) return;
		time += deltaTime;
		if(time >= keyTime){
			keyTime += keyTimeDuration;
			
			if(frameIndex >= textures.length-1){
				if(!loop) isFinished = true;
				else frameIndex = 0;
			} else
				frameIndex++;
			setTexture(textures[frameIndex]);
		}
	}
	
	public void setFrame(int frameIndex){
		this.frameIndex = frameIndex;
	}
	
	public boolean isFinished(){
		return isFinished;
	}
}
