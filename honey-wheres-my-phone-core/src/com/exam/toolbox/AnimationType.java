package com.exam.toolbox;

public enum AnimationType {
	
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
	
	private String animationPath;
	private int startFrame;
	private int endFrame;
	
	private AnimationType(String animationPath, int startFrame, int endFrame){
		this.animationPath = animationPath;
		this.startFrame = startFrame;
		this.endFrame = endFrame;
	}
	
	public String getAnimationPath() {
		return animationPath;
	}
	
	public int getStartFrame() {
		return startFrame;
	}
	
	public int getEndFrame() {
		return endFrame;
	}
}
