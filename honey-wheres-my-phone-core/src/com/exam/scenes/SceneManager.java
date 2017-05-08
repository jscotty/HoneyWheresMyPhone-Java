package com.exam.scenes;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.exam.project.Main;

public class SceneManager {

    private Main main;
    
    private Stack<Scene> scenes;
    
    public static final int PLAY = 9;
    
    public SceneManager(Main main){
    	this.main = main;
    	scenes = new Stack<Scene>();
    	pushScene(PLAY);
    }
    
    public void update(float deltaTime){
    	scenes.peek().update(deltaTime);
    }
    
    public void render(){
    	scenes.peek().render();
    }
    
    private Scene getScene(int state){
    	if(state == PLAY) return new MainScene(this);
    	return null;
    }
    
    public void setScene(int state){
    	popScene();
    	pushScene(state);
    }
    
    public void pushScene(int state){
    	scenes.push(getScene(state));
    }
    
    public void popScene(){
    	Scene scene = scenes.pop();
    	scene.dispose();
    }
    
    public Main getMain(){
    	return main;
    }
    
}
