package com.exam.gui;

import com.badlogic.gdx.graphics.Color;
import com.exam.font.FontType;
import com.exam.toolbox.SpriteType;

public class UpgradeProcess {

	private final float OFFSET = 10;
	private final float FONT_SIZE = 0.6f;
	
	private SpriteType emptyProcess = SpriteType.UPGRADE_PROCESS_EMPTY;
	private SpriteType fullProcess = SpriteType.UPGRADE_PROCESS_FULL;
	private SpriteType selectProcess = SpriteType.UPGRADE_PROCESS_SELECT;
	private Gui[] processIcons;
	private int upgradeCount = 0;
	private int maximumUpgradeCount = 4;
	private GuiText upgradeTypeText;

	//for accessor.
	private float scaleX = 1;
	private float scaleY = 1;

	public UpgradeProcess(GUIManager manager, float xPos, float yPos, String upgradeText) {
		processIcons = new Gui[]{
				new Gui(xPos, yPos, selectProcess, manager),
				new Gui(xPos, yPos, emptyProcess, manager),
				new Gui(xPos, yPos, emptyProcess, manager),
				new Gui(xPos, yPos, emptyProcess, manager),
				new Gui(xPos, yPos, emptyProcess, manager)
		};
		for (int i = 0; i < processIcons.length; i++) {
			processIcons[i].setXPosition(processIcons[i].getPosition().x + (i*(processIcons[i].getWidth() + OFFSET)));
		}
		
		upgradeTypeText = new GuiText(xPos-OFFSET, yPos+ (processIcons[0].getHeight()), manager, FontType.SUPERCELL_MAGIC).addShadow(-5, new Color(0,0,0,1));
		upgradeTypeText.setText(upgradeText);
		upgradeTypeText.setFontSize(FONT_SIZE);
	}
	
	public void upgrade(){
		if(upgradeCount > maximumUpgradeCount) return;
		processIcons[upgradeCount].setTexture(fullProcess);
		if(upgradeCount+1 <= maximumUpgradeCount)
			processIcons[upgradeCount+1].setTexture(selectProcess);
		upgradeCount++;
	}
	
	public void scaleProcessIcons(float scaleX, float scaleY){
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		for (int i = 0; i < processIcons.length; i++) {
			processIcons[i].setScale(scaleX, scaleY);
		}
		
		upgradeTypeText.setFontSize(FONT_SIZE*scaleX);
	}
	
	public float getScaleX() {
		return scaleX;
	}
	
	public float getScaleY() {
		return scaleY;
	}
}
