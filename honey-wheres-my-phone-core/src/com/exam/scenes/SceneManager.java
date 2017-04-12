package com.exam.scenes;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SceneManager {
	
	private static Stack<Scene> scenes; // stack current scene
	
	/**
	 * Constructor for initialization
	 */
	public SceneManager(){
		scenes = new Stack<Scene>();
		scenes.push(new MenuScene(this));
	}
	
	/**
	 * Load new scene
	 * @param scene
	 */
	public static void loadScene(Scene scene){
		scenes.peek().dispose();
		scenes.push(scene);
		scenes.peek().init();
	}
	
	/**
	 * Initialize scene
	 */
	public void init(){
		scenes.peek().init();
	}
	
	/**
	 * Render scene
	 * @param sprite batch
	 */
	public void render(SpriteBatch batch){
		scenes.peek().render(batch);
	}
	
	/**
	 * Dispose scene
	 */
	public void dispose(){
		scenes.peek().dispose();
	}
}
