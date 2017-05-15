package com.exam.scenes;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.exam.project.Main;

public class SceneManager {

    private Main _main;
    
    private Stack<Scene> _scenes;

    public static final int LOADER = -1;
    public static final int MENU = 0;
    public static final int PLAY = 1;
    
    public SceneManager(){
    	_scenes = new Stack<Scene>();
    	pushScene(LOADER);
    }
    
    public SceneManager(Main main){
    	this._main = main;
    	_scenes = new Stack<Scene>();
    	pushScene(LOADER);
    }
    
    public void update(float deltaTime){
    	_scenes.peek().update(deltaTime);
    }
    
    public void render(){
    	_scenes.peek().render();
    }
    
    private Scene getScene(int state){
    	if(state == LOADER) return new AssetsLoaderScene(this);
    	if(state == MENU) return new MainMenuScene(this);
    	if(state == PLAY) return new MainScene(this);
    	return null;
    }
    
    public void setScene(int state){
    	popScene();
    	pushScene(state);
    }
    
    public void pushScene(int state){
    	_scenes.push(getScene(state));
    }
    
    public void popScene(){
    	Scene scene = _scenes.pop();
    	scene.dispose();
    }
    
    public Main getMain(){
    	return _main;
    }
    
}
