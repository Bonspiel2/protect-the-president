package com.cobresun.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MenuListener;

import com.cobresun.enemies.Citizen;
import com.cobresun.interfaces.Drawable;

public class Screen extends JPanel{
	
	public static final int WIDTH = 1020;
	public static final int HEIGHT = 720;
	
	public Graphics2D g;
	public BufferedImage image;
	
	
	public Screen() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
		
		setBounds(0, 0, WIDTH, HEIGHT);
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
	}
	
	public void draw(Drawable d) {
		d.draw(g);
	}
	
	public void draw(int cur) {
		g.setColor(Color.BLACK);
		g.drawString("Money: " + cur, 760, HEIGHT - 240);
	}
	
	public void drawToScreen(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
}
