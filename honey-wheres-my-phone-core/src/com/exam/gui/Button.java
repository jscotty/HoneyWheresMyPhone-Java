package com.exam.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.exam.handlers.GUIManager;
import com.exam.handlers.MyInput;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;

public class Button extends Gui {

	private float positionX;
	private float positionY;
	private float width;
	private float height;
	
	private TextureRegion renderTexture;
	private TextureRegion textureDown;
	private TextureRegion textureUp;
	
	private Vector3 mousePosition; // vector3 to use camera.unproject for pressed behavior
	private OrthographicCamera camera;
	
	private boolean clicked;
	
	public Button(float x, float y, SpriteType spriteTypeUp, SpriteType spriteTypeDown, OrthographicCamera cam, GUIManager manager) {
		super(x, y, manager);
		this.positionX = x;
		this.positionY = y;
		this.camera = cam;

		textureUp = Main.assets.getTexture(spriteTypeUp);
		textureDown = Main.assets.getTexture(spriteTypeDown);
		width = textureUp.getRegionWidth();
		height = textureUp.getRegionHeight();
		mousePosition = new Vector3();
		
		renderTexture = textureUp;
		
	}
	
	public void update(float dt) {
		mousePosition.set(MyInput.getMouseXCoordinate(), MyInput.getMouseYCoordinate(), 0);
		camera.unproject(mousePosition);

		// for some reason cam.unproject had set 0,0 vector in the middle of the screen instead of left bottom corner.
		// so I'd add a basic calculation to fix this issue.
		mousePosition.x /= 2;
		mousePosition.y += Main.HEIGHT;
		mousePosition.y /= 2;
		
		if(MyInput.isMousePressed(0) && mousePosition.x > positionX - width / 2 && mousePosition.x < positionX + width / 2 && mousePosition.y > positionY - height / 2 && mousePosition.y < positionY + height / 2) {
			clicked = true;
		} else if(MyInput.isMouseDown(0) && mousePosition.x > positionX - width / 2 && mousePosition.x < positionX + width / 2 && mousePosition.y > positionY - height / 2 && mousePosition.y < positionY + height / 2) {
			if(renderTexture != textureDown)
				renderTexture = textureDown;
		} else {
			if(renderTexture != textureUp)
				renderTexture = textureUp;
			clicked = false;
		}
		
	}
	
	@Override
	public void render(SpriteBatch spriteBatch) {
		spriteBatch.setColor(1, 1, 1, alpha);
		spriteBatch.draw(renderTexture, positionX - (width*scaleX) / 2, positionY - (height*scaleY) / 2, originX, originY, width, height,scaleX, scaleY, angle);
		spriteBatch.setColor(1, 1, 1, 1);
		
	}
	
	public boolean isClicked() {
		return clicked; 
	}

}
