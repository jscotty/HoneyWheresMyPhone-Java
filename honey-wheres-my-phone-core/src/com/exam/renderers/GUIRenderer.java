package com.exam.renderers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.exam.gui.GUIButton;
import com.exam.gui.GUITexture;

public class GUIRenderer implements Renderer {

	private Stage stage;
	private List<GUITexture> guis = new ArrayList<GUITexture>();
	
	public GUIRenderer(Stage stage) {
		this.stage = stage;
	}
	
	@Override
	public void Init() {
		
	}
	
	public void processGUIButton(GUIButton guiButton){
		stage.addActor(guiButton.getButton());
	}
	
	public void processGUITexture(GUITexture guiTexture){
		stage.addActor(guiTexture);
		
	}

	@Override
	public void render(SpriteBatch batch) {
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		renderGUITextures(batch);
	}
	
	private void renderGUITextures(SpriteBatch batch){
		if(guis.size()==0)return;
		for (GUITexture guiTexture : guis) {
			guiTexture.update();
			guiTexture.getSprite().draw(batch);
		}
	}

	@Override
	public void dispose() {
		guis.clear();
	}

}
