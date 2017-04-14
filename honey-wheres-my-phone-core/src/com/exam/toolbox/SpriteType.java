package com.exam.toolbox;

public enum SpriteType {

	//props
	PROPS_ARM("props/Sheet_Props", "Player_Arm.png"),
	PROPS_BOOK("props/Sheet_Props", "Prop_Book.png"),
	PROPS_CHARGER("props/Sheet_Props", "Prop_Charger.png"),
	PROPS_EYE_SHADOW("props/Sheet_Props", "Prop_EyeShadow.png"),
	PROPS_LIPSTICK("props/Sheet_Props", "Prop_Lipstick.png"),
	PROPS_MIRROR("props/Sheet_Props", "Prop_Mirror.png"),
	PROPS_PENTY("props/Sheet_Props", "Prop_Penty.png"),
	PROPS_TAMPON("props/Sheet_Props", "Prop_Tampon.png"),
	PROPS_WALLER("props/Sheet_Props", "Prop_Wallet.png"),
	PROPS_BLUSH("props/Sheet_Props", "Prop_blush.png"),
	
	//backgrounds
	BACKGOUND_PLYAY_01("", "background/Background_Play_01.png"),
	
	// buttons
	BUTTON_ACCEPT_PRESSED("buttons/Sheet_Buttons", "Button_Accept_Clicked.png"),
	BUTTON_ACCEPT_IDLE("buttons/Sheet_Buttons", "Button_Accept_Idle.png"),

	BUTTON_AUDIO_OFF_PRESSED("buttons/Sheet_Buttons", "Button_AudioOff_Clicked.png"),
	BUTTON_AUDIO_OFF_IDLE("buttons/Sheet_Buttons", "Button_AudioOff_Idle.png"),

	BUTTON_AUDIO_ON_PRESSED("buttons/Sheet_Buttons", "Button_AudioOn_Clicked.png"),
	BUTTON_AUDIO_ON_IDLE("buttons/Sheet_Buttons", "Button_AudioOn_Idle.png"),

	BUTTON_HOME_PRESSED("buttons/Sheet_Buttons", "Button_Home_Clicked.png"),
	BUTTON_HOME_IDLE("buttons/Sheet_Buttons", "Button_Home_Idle.png"),

	BUTTON_LEFT_PRESSED("buttons/Sheet_Buttons", "Button_Left_Clicked.png"),
	BUTTON_LEFT_IDLE("buttons/Sheet_Buttons", "Button_Left_Idle.png"),

	BUTTON_PAUSE_PRESSED("buttons/Sheet_Buttons", "Button_Pauze_Clicked.png"),
	BUTTON_PAUSE_IDLE("buttons/Sheet_Buttons", "Button_Pauze_Idle.png"),

	BUTTON_PLAY_SMALL_PRESSED("buttons/Sheet_Buttons", "Button_PlaySmall_Clicked.png"),
	BUTTON_PLAY_SMALL_IDLE("buttons/Sheet_Buttons", "Button_PlaySmall_Idle.png"),

	BUTTON_PLUS_PRESSED("buttons/Sheet_Buttons", "Button_Plus_Clicked.png"),
	BUTTON_PLUS_IDLE("buttons/Sheet_Buttons", "Button_Plus_Idle.png"),

	BUTTON_QUIT_PRESSED("buttons/Sheet_Buttons", "Button_Quit_Clicked.png"),
	BUTTON_QUIT_IDLE("buttons/Sheet_Buttons", "Button_Quit_Idle.png"),

	BUTTON_REPLAY_PRESSED("buttons/Sheet_Buttons", "Button_Replay_Clicked.png"),
	BUTTON_REPLAY_IDLE("buttons/Sheet_Buttons", "Button_Replay_Idle.png"),

	BUTTON_RIGHT_PRESSED("buttons/Sheet_Buttons", "Button_Right_Clicked.png"),
	BUTTON_RIGHT_IDLE("buttons/Sheet_Buttons", "Button_Right_Idle.png"),

	BUTTON_MENU_PRESSED("buttons/Sheet_Buttons", "Menu_Button_Clicked.png"),
	BUTTON_MENU_IDLE("buttons/Sheet_Buttons", "Menu_Button_Idle.png"),

	BUTTON_PLAY_PRESSED("buttons/Sheet_Buttons", "Play_Button_Clicked.png"),
	BUTTON_PLAY_IDLE("buttons/Sheet_Buttons", "Play_Button_Idle.png"),

	BUTTON_RESUME_PRESSED("buttons/Sheet_Buttons", "Resume_Button_Clicked.png"),
	BUTTON_RESUME_IDLE("buttons/Sheet_Buttons", "Resume_Button_Idle.png"),

	BUTTON_TROPHIES_PRESSED("buttons/Sheet_Buttons", "Trophies_Button_Clicked.png"),
	BUTTON_TROPHIES_IDLE("buttons/Sheet_Buttons", "Trophies_Button_Idle.png"),

	BUTTON_UPGRADES_PRESSED("buttons/Sheet_Buttons", "Upgrades_Button_Clicked.png"),
	BUTTON_UPGRADES_IDLE("buttons/Sheet_Buttons", "Upgrades_Button_Idle.png");
	
	private String atlas;
	private String spriteName;
	
	/**
	 * Enum constructor
	 * @param atlas path
	 * @param sprite name
	 */
	private SpriteType(String atlas, String spriteName){
		this.atlas = atlas;
		this.spriteName = spriteName;
	}

	/**
	 * @return texture atlas path (add .png for image/ add .xml for data)
	 */
	public String getAtlas() {
		return atlas;
	}

	/**
	 * @return desired texture name (visible in <atlas>.xml located in ./assets/ folder)
	 */
	public String getSpriteName() {
		return spriteName;
	}
}
