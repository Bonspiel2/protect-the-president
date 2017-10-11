package com.cobresun.map;

import java.awt.Color;
import java.awt.Graphics2D;

public class Road extends Terrain{

	public Road(int i, int j, int size) {
		super(i, j, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		super.draw(g);
	}

}
