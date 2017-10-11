package com.cobresun.main;

import java.awt.TextArea;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import com.cobresun.states.*;

public class Game {
	
	private Thread t;
	int FPS;
	long targetTime;
	public Screen s;
	boolean running;
	
	
	private GameState state;
	
	public void init() {
		FPS = 30;
		targetTime = 1000/FPS;
		s = new Screen();
		
		running = true;
		
		state = new MainMenu(this);
		state.addMouse(s);
	}
	
	public void run() {
		long start, elapsed, wait;
		
		while (running) {
			start = System.nanoTime();
			
			state.update();
			
			state.draw(s);
			
			s.drawToScreen();
			
			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed/100000;

			if (wait < 0){
				wait = 0;
			}

			try {
				Thread.sleep(wait);
			}
			catch(Exception e){
			}
		}
	}
	
	public void close() {
		
	}
	
	public JPanel getPane() {
		return s;
	}

	public void startDefense() {
		s.removeMouseListener(state.getMouse());
		state = new Defense(this);
		
		state.addMouse(s);
	}
	
	public void removeMouse(MouseListener m) {
		s.removeMouseListener(m);
	}
	
	public void addMouse(MouseListener m) {
		s.addMouseListener(m);
	}
	
	public void add(TextArea t) {
		s.add(t);
	}

	public void endGame(boolean won) {
		s.removeMouseListener(state.getMouse());
		state = new GameOver(this, won);
		
		state.addMouse(s);
	}

}
