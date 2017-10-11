package com.cobresun.menus;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cobresun.interfaces.Drawable;

public class Button implements Drawable{
	
	int x;
	int y;
	int w;
	int h;
	
	String label;
	
	public Button(int x, int y, int w, int h, String label) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.label = label;
	}
	
	@Override
	public void draw(Graphics2D g) {
		int stringWidth = g.getFontMetrics().stringWidth(label);
		int stringHeight = g.getFontMetrics().getHeight();
		
		g.fillRect(x, y, w, h);
		
		g.setColor(Color.BLACK);
		g.drawString(label, (w/2) - (stringWidth/2) + x , y + (h/2) + (stringHeight/2));
	}
	
	public boolean buttonPressed(int x, int y) {
		return (x >= this.x && x <= (this.x + this.w)) && (y >= this.y && y <= (this.y + this.h));
	}
	
	

}
