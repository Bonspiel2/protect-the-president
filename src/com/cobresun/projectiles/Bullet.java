package com.cobresun.projectiles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.cobresun.enemies.Citizen;
import com.cobresun.interfaces.Drawable;

public class Bullet implements Drawable{
	double x;
	double y;
	double gx;
	double gy;
	int v;
	double dis;
	
	Citizen target;

	public Bullet(int x, int y, int v, Citizen target) {
		this.x = x;
		this.y = y;
		this.target = target;
		this.v = v;
		
		this.gx = target.getX();
		this.gy = target.getY();

		dis = Math.sqrt(Math.pow(x-gx, 2) + Math.pow(y-gy, 2));
	}

	public void update() {
		gx = target.getX();
		gy = target.getY();
		double dx = gx - x;
		double dy = gy - y;

		double mx = (v * dx)/dis;
		double my = (v * dy)/dis;

		x += mx;
		y += my;

		dis = Math.sqrt(Math.pow(x-gx, 2) + Math.pow(y-gy, 2));
	}
	
	public boolean atTarget() {
		boolean atTarget = false;
		if (dis < v) {
			atTarget = true;
		}
		return atTarget;
	}

	public void draw(Graphics2D g) {
		
		g.setColor(Color.ORANGE);
		g.fillOval((int)x, (int) y, 10, 10);
		
		
	}

	public Citizen getTarget() {
		return target;
	}

}
