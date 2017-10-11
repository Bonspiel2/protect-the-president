package com.cobresun.states;

import java.awt.TextArea;
import java.awt.event.MouseListener;

import com.cobresun.main.Game;
import com.cobresun.main.Screen;
import com.cobresun.menus.InitialMenu;
import com.cobresun.menus.Menu;
import com.cobresun.menus.Tutorial;

public class MainMenu extends GameState{
	Menu m;
	
	public MainMenu(Game g) {
		super(g);
		m = new InitialMenu(this);
	}
	
	public void draw(Screen s) {
		s.draw(m);
	}
	
	public void addMouse(Screen s) {
		s.addMouseListener(m);
	}
	
	public void startGame() {
		Game g = getGame();
		
		g.startDefense();
	}
	
	public void startTutorial() {
		Game g = getGame();
		g.removeMouse(m);
		
		m = new Tutorial(this);
		
		g.addMouse(m);
	}
	
	public MouseListener getMouse() {
		return m;
	}
	
	public void add(TextArea t) {
		Game g = getGame();
		g.add(t);
	}

}
