package com.cobresun.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.cobresun.interfaces.Drawable;
import com.cobresun.main.Screen;
import com.cobresun.states.MainMenu;

public class InitialMenu extends Menu implements Drawable, MouseListener{
	MainMenu m;
	Button start;
	String title;
	
	public InitialMenu(MainMenu m) {
		start = new Button(Screen.WIDTH/2 - 120, Screen.HEIGHT/2, 240, 100, "START");
		title = "PROTECT THE PRESIDENT";
		this.m = m;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
		
		g.setColor(Color.WHITE);
		g.drawString(title, Screen.WIDTH/2 - 75, Screen.HEIGHT/2 - 100);
		
		g.setColor(Color.GREEN);
		start.draw(g);
		
	}

	@Override
	public void mouseClicked(MouseEvent a) {
		int x = a.getX();
		int y = a.getY();
		
		if (start.buttonPressed(x, y)) {
			m.startTutorial();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
