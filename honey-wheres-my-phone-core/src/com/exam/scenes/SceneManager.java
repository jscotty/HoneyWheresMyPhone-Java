package com.exam.scenes;

import java.util.Stack;
import com.exam.project.Main;

/**
 * @author Justin Scott Bieshaar
 *	   Mediacollege Amsterdam.
 * 	   Portfolio: Justinbieshaar.com
 */
public class SceneManager {

    private Main _main;
    
    private Stack<Scene> _scenes;

    public static final int LOADER = -1;
    public static final int MENU = 0;
    public static final int PLAY = 1;
    
    /**
     * Constructor for initialization
     * @param main
     */
    public SceneManager(Main main){
    	this._main = main;
    	_scenes = new Stack<Scene>();
    	pushScene(LOADER);
    }
    
    /**
     * Update current scene
     * Updates every frame
     * @param deltaTime
     */
    public void update(float deltaTime){
    	_scenes.peek().update(deltaTime);
    }
    
    /**
     * Render current scene
     * Renders every frame
     */
    public void render(){
    	_scenes.peek().render();
    }
    
    /**
     * Get scene class
     * @param state
     * @return
     */
    private Scene getScene(int state){
    	if(state == LOADER) return new AssetsLoaderScene(this);
    	if(state == MENU) return new MainMenuScene(this);
    	if(state == PLAY) return new MainScene(this);
    	return null;
    }
    
    /**
     * Set new scene
     * @param state
     */
    public void setScene(int state){
    	popScene();
    	pushScene(state);
    }
    
    /**
     * push new scene
     * @param state
     */
    private void pushScene(int state){
    	_scenes.push(getScene(state));
    }
    
    /**
     * remove and dispose current scene
     */
    public void popScene(){
    	Scene scene = _scenes.pop();
    	scene.dispose();
    }
    
    /**
     * @return Main class
     */
    public Main getMain(){
    	return _main;
    }
    
}
