package com.exam.toolbox;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public enum SpriteType {

    //props
    PROPS_ARM("", "props/Arm.png"),
    PROPS_BOOK("props/Sheet_Props", "Prop_Book.png"),
    PROPS_CHARGER("props/Sheet_Props", "Prop_Charger.png"),
    PROPS_EYE_SHADOW("props/Sheet_Props", "Prop_EyeShadow.png"),
    PROPS_LIPSTICK("props/Sheet_Props", "Prop_Lipstick.png"),
    PROPS_MIRROR("props/Sheet_Props", "Prop_Mirror.png"),
    PROPS_PENTY("props/Sheet_Props", "Prop_Penty.png"),
    PROPS_TAMPON("props/Sheet_Props", "Prop_Tampon.png"),
    PROPS_WALLET("props/Sheet_Props", "Prop_Wallet.png"),
    PROPS_BLUSH("props/Sheet_Props", "Prop_blush.png"),

    //backgrounds
    BACKGROUND("","background/characterScreen.jpg"),
    BACKGROUND_TOP("","background/BackgroundTop.png"),
    BACKGROUND_TOP_OVERLAY("","background/BorderTop.png"),

    BACKGROUND_LEVEL_00("","background/Background_lvl1.png"),
    BACKGROUND_OVERLAY_LEVEL_00("","background/Border_lvl0.png"),

    BACKGROUND_LEVEL_01("","background/Background_lvl1.png"),
    BACKGROUND_OVERLAY_LEVEL_01("","background/Border_lvl1.png"),

    BACKGROUND_LEVEL_02("","background/Background_lvl2.png"),
    BACKGROUND_OVERLAY_LEVEL_02("","background/Border_lvl2.png"),

    BACKGROUND_LEVEL_03("","background/Background_lvl3.png"),
    BACKGROUND_OVERLAY_LEVEL_03("","background/Border_lvl3.png"),

    BACKGROUND_LEVEL_04("","background/Background_lvl4.png"),
    BACKGROUND_OVERLAY_LEVEL_04("","background/Border_lvl4.png"),

    BACKGROUND_LEVEL_05("","background/Background_lvl5.png"),
    BACKGROUND_OVERLAY_LEVEL_05("","background/Border_lvl5.png"),

    BACKGROUND_LEVEL_06("","background/Background_lvl6.png"),
    BACKGROUND_OVERLAY_LEVEL_06("","background/Border_lvl6.png"),
    
    BACKGOUND_PLYAY_01("", "Background_Play_01.png"),
    BACKGOUND_PLYAY_01_OVERLAY("", "Background_Overlay.png"),

    BACKGROUND_FADE_01("buttons/Sheet_UserInterface", "Background_Fade1.png"),
    BACKGROUND_FADE_02("buttons/Sheet_UserInterface", "Background_Fade2.png"),
    BACKGROUND_FADE_03("buttons/Sheet_UserInterface", "Background_Fade3.png"),

    //logo's
    LOGO_01("buttons/Sheet_UserInterface", "Logo1.png"),

    //upgrade tab
    UPGRADE_ARM_LENGTH("buttons/Sheet_UserInterface", "Upgrade_ArmLength.png"),
    UPGRADE_STARTDEPTH_INCREASER("buttons/Sheet_UserInterface", "Upgrade_StartDeeper.png"),
    UPGRADE_ITEMVALUE_INCREASER("buttons/Sheet_UserInterface", "Upgrade_ItemValue.png"),
    UPGRADE_PROCESS_EMPTY("buttons/Sheet_UserInterface", "Upgrade_ProcessEmpty.png"),
    UPGRADE_PROCESS_FULL("buttons/Sheet_UserInterface", "Upgrade_ProcessFull.png"),
    UPGRADE_PROCESS_SELECT("buttons/Sheet_UserInterface", "Upgrade_ProcessSelect.png"),
    
    //Text
    GAME_PAUSED_TEXT("buttons/Sheet_UserInterface", "Text_GamePaused.png"),
    
    //buttons
    BUTTON_AUDIO_ON_PRESSED("buttons/Sheet_UserInterface", "Button_AudioOn_Clicked.png"),
    BUTTON_AUDIO_ON_IDLE("buttons/Sheet_UserInterface", "Button_AudioOn_Idle.png"),
    
    BUTTON_AUDIO_OFF_PRESSED("buttons/Sheet_UserInterface", "Button_AudioOff_Clicked.png"),
    BUTTON_AUDIO_OFF_IDLE("buttons/Sheet_UserInterface", "Button_AudioOff_Idle.png"),

    BUTTON_BUY_PRESSED("buttons/Sheet_UserInterface", "Button_Buy_Pressed.png"),
    BUTTON_BUY_IDLE("buttons/Sheet_UserInterface", "Button_Buy_Idle.png"),

    BUTTON_HOME_PRESSED("buttons/Sheet_UserInterface", "Button_Home_Clicked.png"),
    BUTTON_HOME_IDLE("buttons/Sheet_UserInterface", "Button_Home_Idle.png"),

    BUTTON_LEFT_PRESSED("buttons/Sheet_UserInterface", "Button_Left_Clicked.png"),
    BUTTON_LEFT_IDLE("buttons/Sheet_UserInterface", "Button_Left_Idle.png"),

    BUTTON_PAUSE_PRESSED("buttons/Sheet_UserInterface", "Button_Pauze_Clicked.png"),
    BUTTON_PAUSE_IDLE("buttons/Sheet_UserInterface", "Button_Pauze_Idle.png"),

    BUTTON_PLAY_SMALL_PRESSED("buttons/Sheet_UserInterface", "Button_PlaySmall_Pressed.png"),
    BUTTON_PLAY_SMALL_IDLE("buttons/Sheet_UserInterface", "Button_PlaySmall_Idle.png"),

    BUTTON_QUIT_PRESSED("buttons/Sheet_UserInterface", "Button_Quit_Pressed.png"),
    BUTTON_QUIT_IDLE("buttons/Sheet_UserInterface", "Button_Quit_Idle.png"),

    BUTTON_RIGHT_PRESSED("buttons/Sheet_UserInterface", "Button_Right_Clicked.png"),
    BUTTON_RIGHT_IDLE("buttons/Sheet_UserInterface", "Button_Right_Idle.png"),

    BUTTON_MENU_PRESSED("buttons/Sheet_UserInterface", "Menu_Button_Clicked.png"),
    BUTTON_MENU_IDLE("buttons/Sheet_UserInterface", "Menu_Button_Idle.png"),

    BUTTON_PLAY_PRESSED("buttons/Sheet_UserInterface", "Play_Button_Clicked.png"),
    BUTTON_PLAY_IDLE("buttons/Sheet_UserInterface", "Play_Button_Idle.png"),

    BUTTON_RESUME_PRESSED("buttons/Sheet_UserInterface", "Resume_Button_Clicked.png"),
    BUTTON_RESUME_IDLE("buttons/Sheet_UserInterface", "Resume_Button_Idle.png"),

    BUTTON_TROPHIES_PRESSED("buttons/Sheet_UserInterface", "Trophies_Button_Clicked.png"),
    BUTTON_TROPHIES_IDLE("buttons/Sheet_UserInterface", "Trophies_Button_Idle.png"),

    BUTTON_UPGRADES_PRESSED("buttons/Sheet_UserInterface", "Upgrades_Button_Clicked.png"),
    BUTTON_UPGRADES_IDLE("buttons/Sheet_UserInterface", "Upgrades_Button_Idle.png");

    private String _atlas;
    private String _spriteName;

    /**
     * Enum constructor
     * @param atlas path
     * @param sprite name
     */
    private SpriteType(String atlas, String spriteName) {
	this._atlas = atlas;
	this._spriteName = spriteName;
    }

    /**
     * @return texture atlas path (add .png for image/ add .xml for data)
     */
    public String getAtlas() {
	return _atlas;
    }

    /**
     * @return desired texture name (visible in <atlas>.xml located in ./assets/ folder)
     */
    public String getSpriteName() {
	return _spriteName;
    }
}
