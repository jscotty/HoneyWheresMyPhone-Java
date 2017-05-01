package com.exam.renderers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.exam.gui.GUIButton;
import com.exam.gui.GUITexture;

public class GUIRenderer implements IRenderer {

    private Stage _stage;
    private List<GUITexture> _guis = new ArrayList<GUITexture>();

    public GUIRenderer(Stage stage) {
	this._stage = stage;
    }

    @Override
    public void init() {

    }

    public void processGUIButton(GUIButton guiButton) {
	_stage.addActor(guiButton.getButton());
    }

    public void processGUITexture(GUITexture guiTexture) {
	_stage.addActor(guiTexture);

    }

    @Override
    public void render(SpriteBatch batch) {
	renderGUITextures(batch);
    }

    private void renderGUITextures(SpriteBatch batch) {
	if (_guis.size() == 0)
	    return;
	for (GUITexture guiTexture : _guis) {
	    guiTexture.update();
	    guiTexture.getSprite().draw(batch);
	}
    }

    @Override
    public void dispose() {
	_guis.clear();
    }

}
