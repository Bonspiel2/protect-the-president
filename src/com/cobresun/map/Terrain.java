package com.cobresun.map;

import java.awt.Graphics2D;

import com.cobresun.interfaces.Drawable;

public abstract class Terrain implements Drawable{
	private int x;
	private int y;
	private int i;
	private int j;
	
	private int size;
	
	public Terrain(int i, int j, int size) {
		this.i = i;
		this.j = j;
		
		this.size = size;
		
		x = i * size;
		y = j * size;
	}
	
	public void draw(Graphics2D g) {
		g.fillRect(x, y, size, size);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
