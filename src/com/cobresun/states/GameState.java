package com.cobresun.states;

import java.awt.event.MouseListener;

import com.cobresun.main.Game;
import com.cobresun.main.Screen;

public abstract class GameState {
	
	private Game g;
	
	public GameState(Game g) {
		this.g = g;
	}
	
	public void draw(Screen s) {
	}
	
	public void update() {
		
	}
	
	public void addMouse(Screen s) {
		
	}
	
	public Game getGame() {
		return g;
	}

	public MouseListener getMouse() {
		return null;
	}

}
