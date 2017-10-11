package com.cobresun.enemies;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cobresun.interfaces.Drawable;
import com.cobresun.map.Map;

public abstract class Citizen implements Drawable{
	BufferedImage pic;
	Map m;

	double v;
	double x;
	double y;

	int curPoint;

	boolean checked;

	private final int[][] goalPoints = {{550, 250},
			{550, 70},
			{130, 70},
			{130, 190},
			{370, 190},
			{370, 430},
			{550, 430},
			{550, 610},
			{190, 610},
			{190, 370},
			{120, 370}};

	public Citizen(Map m, int x, int y, double d) {
		this.m = m;
		this.x = x;
		this.y = y;
		this.v = d;

		curPoint = 0;

		checked = false;
		
		pic = null;
		
		try {
			pic = ImageIO.read(new File("rsrc/man.png"));
		} catch (IOException e) {
			
		}
	}
	
	public Citizen(int x, int y) {
		this.x = x;
		this.y = y;
		
		try {
			pic = ImageIO.read(new File("rsrc/man.png"));
		} catch (IOException e) {
			
		}
	}

	public void draw(Graphics2D g) {
		//g.fillRect((int)x, (int)y, 30, 40);
		g.drawImage(pic, (int) x, (int) y, 30, 40, null);
	}

	public boolean move() {

		boolean done = false;

		int gx = goalPoints[curPoint][0];
		int gy = goalPoints[curPoint][1];
		if (atPoint(x, y, gx, gy, v)) {
			curPoint++;
			if (curPoint == goalPoints.length) {
				done = true;
			} else {

				gx = goalPoints[curPoint][0];
				gy = goalPoints[curPoint][1];
			}
		}

		if (x < gx) {
			x += v;
		} else {
			x -= v;
		}

		if (y < gy) {
			y += v;
		} else {
			y -= v;
		}

		return done;


	}

	private boolean atPoint(double x, double y, int gx, int gy, double v2) {
		return (Math.abs(x - gx) < Math.max(1, v2)) && (Math.abs(y - gy) < Math.max(1, v2));
	}

	public boolean collided(int i, int j) {
		boolean hasCollided = false;

		int ei = (int) (x/60);
		int ej = (int) (y/60);

		if (ei == i && ej == j) {
			hasCollided = true;
			checked = true;
		}

		return hasCollided;
	}


	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean containsPoint(int x, int y) {
		return ((this.x-10) <= x) && ((this.x + 40) >= x) && ((this.y-10) <= y) && ((this.y + 50) >= y);
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}



}
