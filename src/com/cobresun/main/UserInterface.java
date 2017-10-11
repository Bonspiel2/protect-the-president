package com.cobresun.main;

import javax.swing.JFrame;

public class UserInterface {

	public static void main(String[] args) {
		
		JFrame window = new JFrame("Protect the President");
		
		Game g = new Game();
		
		g.init();
		
		
		window.setContentPane(g.getPane());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack(); 
		window.setVisible(true);
		
		g.run();

	}

}
