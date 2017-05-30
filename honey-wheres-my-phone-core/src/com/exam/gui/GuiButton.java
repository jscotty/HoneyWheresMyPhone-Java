package com.exam.gui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.exam.assets.AudioType;
import com.exam.assets.SpriteType;
import com.exam.handlers.MyInput;
import com.exam.managers.SoundManager;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class GuiButton extends Gui {
	private float _width;
	private float _height;

	private TextureRegion _renderTexture;
	private TextureRegion _textureDown;
	private TextureRegion _textureUp;

	private Vector3 _mousePosition; // vector3 to use camera.unproject for pressed behavior
	private OrthographicCamera _camera;

	protected boolean pIsClicked;
	private boolean _isAlreadyPressed = false;

	/**
	 * Constructor for initialization
	 * @param x
	 * @param y
	 * @param spriteTypeUp
	 * @param spriteTypeDown
	 * @param cam
	 * @param manager
	 */
	public GuiButton(float x, float y, SpriteType spriteTypeUp, SpriteType spriteTypeDown, OrthographicCamera cam,
			GuiManager manager) {
		super(x, y, manager);
		this._camera = cam;

		_textureUp = Main.assets.getTexture(spriteTypeUp);
		_textureDown = Main.assets.getTexture(spriteTypeDown);
		_width = _textureUp.getRegionWidth();
		_height = _textureUp.getRegionHeight();
		_mousePosition = new Vector3();

		_renderTexture = _textureUp;

	}

	@Override
	public void update(float deltaTime) {
		_mousePosition.set(MyInput.getMouseXCoordinate(), MyInput.getMouseYCoordinate(), 0);
		_camera.unproject(_mousePosition);

		if(!_isAlreadyPressed){
			// for some reason cam.unproject had set 0,0 vector in the middle of the screen instead of left bottom corner.
			// so I'd add a basic calculation to fix this issue.
			_mousePosition.x /= 2;
			_mousePosition.y += Main.HEIGHT;
			_mousePosition.y /= 2;
			if (MyInput.isMousePressed(0) && onHover()) {
				pIsClicked = true;
				SoundManager.playAudio(AudioType.BUTTON_CLICK);
			} else if (MyInput.isMouseDown(0) && onHover()) {
				if (_renderTexture != _textureDown)
					_renderTexture = _textureDown;
			} else {
				if (_renderTexture != _textureUp)
					_renderTexture = _textureUp;
				pIsClicked = false;
			}
		}

		if(MyInput.isMouseDown(0) && !onHover())
			_isAlreadyPressed = true;
		else if(!MyInput.isMouseDown(0))
			_isAlreadyPressed = false;
	}
	
	/**
	 * @return if mouse or finger is hovering over this button.
	 */
	private boolean onHover(){
		return(_mousePosition.x > pPosition.x - _width / 2
				&& _mousePosition.x < pPosition.x + _width / 2 && _mousePosition.y > pPosition.y - _height / 2
				&& _mousePosition.y < pPosition.y + _height / 2);
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		spriteBatch.setColor(1, 1, 1, pAlpha);
		spriteBatch.draw(_renderTexture, pPosition.x - (_width * pScaleX) / 2, pPosition.y - (_height * pScaleY) / 2,
				pOriginX, pOriginY, _width, _height, pScaleX, pScaleY, pAngle);
		spriteBatch.setColor(1, 1, 1, 1);

	}
	
	/**
	 * @param textureUp
	 * @param textureDown
	 */
	protected void setTextureTypes(SpriteType textureUp, SpriteType textureDown){
		this._textureUp = Main.assets.getTexture(textureUp);
		this._textureDown = Main.assets.getTexture(textureDown);
	}

	/**
	 * @return if button is been clicked.
	 */
	public boolean isClicked() {
		return pIsClicked;
	}

}
