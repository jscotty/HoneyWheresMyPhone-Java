package com.exam.gui;

import com.badlogic.gdx.graphics.Color;
import com.exam.assets.SpriteType;
import com.exam.font.FontType;
import com.exam.managers.GameManager;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class UpgradeProcess {

	private final float OFFSET = 10;
	private final float FONT_SIZE = 0.6f;
	
	private SpriteType _emptyProcess = SpriteType.UPGRADE_PROCESS_EMPTY;
	private SpriteType _fullProcess = SpriteType.UPGRADE_PROCESS_FULL;
	private SpriteType _selectProcess = SpriteType.UPGRADE_PROCESS_SELECT;
	private Gui[] _processIcons;
	private int _upgradeCount = 0;
	private int _maximumUpgradeCount = 4;
	private GuiText _upgradeTypeText;
	private int _index;
	private int _cost = 100;

	//for accessor.
	private float _scaleX = 1;
	private float _scaleY = 1;

	public UpgradeProcess(GuiManager manager, float xPos, float yPos, String upgradeText, int index) {
		this._index = index;
		_processIcons = new Gui[]{
				new Gui(xPos, yPos, _selectProcess, manager),
				new Gui(xPos, yPos, _emptyProcess, manager),
				new Gui(xPos, yPos, _emptyProcess, manager),
				new Gui(xPos, yPos, _emptyProcess, manager),
				new Gui(xPos, yPos, _emptyProcess, manager)
		};
		for (int i = 0; i < _processIcons.length; i++) {
			_processIcons[i].setXPosition(_processIcons[i].getPosition().x + (i*(_processIcons[i].getWidth() + OFFSET)));
		}
		
		_upgradeTypeText = new GuiText(xPos-OFFSET, yPos+ (_processIcons[0].getHeight()), manager, FontType.SUPERCELL_MAGIC).addShadow(-5, new Color(0,0,0,1));
		_upgradeTypeText.setText(upgradeText);
		_upgradeTypeText.setFontSize(FONT_SIZE);
		
		initUpgradeProcessVisualisation();
	}
	
	/**
	 * Initialize visualization of saved data.
	 */
	private void initUpgradeProcessVisualisation(){
		_upgradeCount =  GameManager.getUpgradeLevel(_index);
		for (int i = 0; i < _upgradeCount; i++) {
			_processIcons[i].setTexture(_fullProcess);
			if(i+1 <= _maximumUpgradeCount)
				_processIcons[i+1].setTexture(_selectProcess);
			_cost *= 2;
		}
	}
	
	/**
	 * Upgrade process.
	 * Visualizes upgrade level
	 */
	public void upgrade(){
		if(GameManager.getMoney() < _cost) return; // not enough money
		if(_upgradeCount > _maximumUpgradeCount) return; // Last upgrade already purchased 
		GameManager.addUpgrade(_index); // save upgrade
		GameManager.removeMoney(_cost); // remove and save amount of money
		_processIcons[_upgradeCount].setTexture(_fullProcess); // set icon to purchased
		if(_upgradeCount+1 <= _maximumUpgradeCount)
			_processIcons[_upgradeCount+1].setTexture(_selectProcess); // if there is another upgrade possible set the next icon to next 'upgrade level icon'.
		_cost *= 2;
		_upgradeCount++;
	}
	
	/**
	 * Scale all icon sizes.
	 * Using for accessor.
	 * @param scaleX
	 * @param scaleY
	 */
	public void scaleProcessIcons(float scaleX, float scaleY){
		this._scaleX = scaleX;
		this._scaleY = scaleY;
		for (int i = 0; i < _processIcons.length; i++) {
			_processIcons[i].setScale(scaleX, scaleY);
		}
		
		_upgradeTypeText.setFontSize(FONT_SIZE*scaleX);
	}
	
	/**
	 * @return Scale X factor
	 */
	public float getScaleX() {
		return _scaleX;
	}
	
	/**
	 * @return Scale Y factor
	 */
	public float getScaleY() {
		return _scaleY;
	}
	
	/**
	 * @return Get the cost value of this process
	 */
	public int getCost() {
		return _cost;
	}
}
