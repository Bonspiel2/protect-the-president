package com.cobresun.enemies;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cobresun.map.Map;

public class Innocent extends Citizen{

	public Innocent(Map m, int x, int y, double d) {
		super(m, x, y, d);
	}
	
	public Innocent(int x, int y) {
		super(x,y);
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.YELLOW);
		super.draw(g);
	}

}
