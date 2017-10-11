package com.cobresun.enemies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import com.cobresun.map.Map;

public class Attacker extends Citizen{
	BufferedImage gun;

	int attack;
	boolean weapon;
	Random r;
	int weaponTimer;
	double wP;

	public Attacker(Map m, int x, int y, double d) {
		super(m, x, y, d);
		attack = 5;
		weapon = false;
		r = new Random(System.currentTimeMillis());
		wP = 0.001;
		
		try {
			gun = ImageIO.read(new File("rsrc/gun.png"));
		} catch (IOException e) {
			
		}
	}
	
	public Attacker(int x, int y) {
		super(x, y);
		weapon = true;
		
		try {
			gun = ImageIO.read(new File("rsrc/gun.png"));
		} catch (IOException e) {
			
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.YELLOW);
		super.draw(g);
		
		if (weapon) {
			g.drawImage(gun,(int) getX()+15,(int) getY()+20, 30, 20, null);
		}
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public boolean move() {
		if (!weapon) {
			double c = r.nextDouble();
			if (c <= wP) {
				weapon = true;
				weaponTimer = 60;
			}
		} else if (weaponTimer <= 0){
			weapon = false;
		} else {
			weaponTimer--;
		}
		return super.move();

	}

	

}
