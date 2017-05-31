package com.exam.assets;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public enum AnimationType {
	
	// path to animation sprite sheet, begin sprite of animation , end sprite of animation
	// NOTE: start and end frame are used to crop out an animation from a sprite sheet with multiple animations.
	// use 0 start and 0 end frame to let the program know it has to use the full sprite sheet animation.
	
	//phones
	PHONE_LEVEL_1("phones/Prop_PhoneAnimatons1",0,13),
	PHONE_LEVEL_2("phones/Prop_PhoneAnimatons1",14,22),
	PHONE_LEVEL_3("phones/Prop_PhoneAnimations2",0,10),
	PHONE_LEVEL_4("phones/Prop_PhoneAnimations2",11,19),
	PHONE_LEVEL_5("phones/Prop_PhoneAnimations2",20,28),
	
	//arm/hand animations
	ARM_WIGGLE("arm/Arm_Animation",0,0),
	HAND_REACHING("arm/Hand_Animation_Reaching",0,0),
	HAND_TRANSITION("arm/Hand_Animation_Transition",0,0),
	HAND_GRABBING("arm/Hand_Animation_Grabbing",0,0);
	
	private String _animationPath;
	private int _startFrame;
	private int _endFrame;
	
	/**
	 * Enumeration constructor
	 * use 0 start and 0 end frame to let the program know it has to use the full sprite sheet animation.
	 * @param path to animation sheet and image
	 * @param startFrame index of animation
	 * @param endFrame index of animation
	 */
	private AnimationType(String animationPath, int startFrame, int endFrame){
		this._animationPath = animationPath;
		this._startFrame = startFrame;
		this._endFrame = endFrame;
	}
	
	/**
	 * @return Path to animation
	 */
	public String getAnimationPath() {
		return _animationPath;
	}
	
	/**
	 * @return Start frame of animation
	 */
	public int getStartFrame() {
		return _startFrame;
	}
	
	/**
	 * @return end frame of animation
	 */
	public int getEndFrame() {
		return _endFrame;
	}
}
