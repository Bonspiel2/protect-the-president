package com.cobresun.map;

import java.awt.Color;
import java.awt.Graphics2D;

public class House extends Terrain{

	public House(int i, int j, int size) {
		super(i, j, size);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		super.draw(g);
	}
}
