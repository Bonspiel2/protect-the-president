package com.cobresun.states;

import java.awt.event.MouseListener;

import com.cobresun.main.Game;
import com.cobresun.main.Screen;
import com.cobresun.menus.Endgame;
import com.cobresun.menus.Menu;

public class GameOver extends GameState {
	Menu m;
	
	public GameOver(Game g, boolean won) {
		super(g);
		m = new Endgame(this, won);
	}
	
	public void draw(Screen s) {
		s.draw(m);
	}
	
	public void addMouse(Screen s) {
		s.addMouseListener(m);
	}
	
	public MouseListener getMouse() {
		return m;
	}

	public void playAgain() {
		Game g = getGame();
		
		g.startDefense();
		
	}

}
