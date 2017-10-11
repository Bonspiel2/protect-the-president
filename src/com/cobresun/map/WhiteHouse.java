package com.cobresun.map;

import java.awt.Color;
import java.awt.Graphics2D;

import com.cobresun.interfaces.Drawable;
import com.cobresun.states.Defense;

public class WhiteHouse implements Drawable {
	double health;
	double totalHealth;
	
	public WhiteHouse(double health) {
		this.health = health;
		this.totalHealth = health;
	}
	
	public void attack(double health, Defense d) {
		this.health-=health;
		
		if (this.health <= 0) {
			d.endGame(false);
		}
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.RED);
		g.fillRect(10, 450, 100, 20);
		
		g.setColor(Color.GREEN);
		g.fillRect(10, 450, (int) (100 * (health/totalHealth)), 20);
		
	}
	
}
