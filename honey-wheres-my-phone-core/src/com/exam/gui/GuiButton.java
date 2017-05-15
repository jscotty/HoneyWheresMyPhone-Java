package com.exam.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.exam.handlers.GUIManager;
import com.exam.handlers.MyInput;
import com.exam.project.Main;
import com.exam.toolbox.SpriteType;

public class GuiButton extends Gui {

	private float _positionX;
	private float _positionY;
	private float _width;
	private float _height;
	
	private TextureRegion _renderTexture;
	private TextureRegion _textureDown;
	private TextureRegion _textureUp;
	
	private Vector3 _mousePosition; // vector3 to use camera.unproject for pressed behavior
	private OrthographicCamera _camera;
	
	private boolean _clicked;
	
	public GuiButton(float x, float y, SpriteType spriteTypeUp, SpriteType spriteTypeDown, OrthographicCamera cam, GUIManager manager) {
		super(x, y, manager);
		this._positionX = x;
		this._positionY = y;
		this._camera = cam;

		_textureUp = Main.assets.getTexture(spriteTypeUp);
		_textureDown = Main.assets.getTexture(spriteTypeDown);
		_width = _textureUp.getRegionWidth();
		_height = _textureUp.getRegionHeight();
		_mousePosition = new Vector3();
		
		_renderTexture = _textureUp;
		
	}
	
	public void update(float dt) {
		_mousePosition.set(MyInput.getMouseXCoordinate(), MyInput.getMouseYCoordinate(), 0);
		_camera.unproject(_mousePosition);

		// for some reason cam.unproject had set 0,0 vector in the middle of the screen instead of left bottom corner.
		// so I'd add a basic calculation to fix this issue.
		_mousePosition.x /= 2;
		_mousePosition.y += Main.HEIGHT;
		_mousePosition.y /= 2;
		
		if(MyInput.isMousePressed(0) && _mousePosition.x > _positionX - _width / 2 && _mousePosition.x < _positionX + _width / 2 && _mousePosition.y > _positionY - _height / 2 && _mousePosition.y < _positionY + _height / 2) {
			_clicked = true;
		} else if(MyInput.isMouseDown(0) && _mousePosition.x > _positionX - _width / 2 && _mousePosition.x < _positionX + _width / 2 && _mousePosition.y > _positionY - _height / 2 && _mousePosition.y < _positionY + _height / 2) {
			if(_renderTexture != _textureDown)
				_renderTexture = _textureDown;
		} else {
			if(_renderTexture != _textureUp)
				_renderTexture = _textureUp;
			_clicked = false;
		}
		
	}
	
	@Override
	public void render(SpriteBatch spriteBatch) {
		spriteBatch.setColor(1, 1, 1, pAlpha);
		spriteBatch.draw(_renderTexture, _positionX - (_width*pScaleX) / 2, _positionY - (_height*pScaleY) / 2, pOriginX, pOriginY, _width, _height,pScaleX, pScaleY, pAngle);
		spriteBatch.setColor(1, 1, 1, 1);
		
	}
	
	public boolean isClicked() {
		return _clicked; 
	}

}
