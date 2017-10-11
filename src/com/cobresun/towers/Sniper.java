package com.cobresun.towers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.cobresun.enemies.Attacker;
import com.cobresun.enemies.Citizen;
import com.cobresun.main.SoundEffect;
import com.cobresun.projectiles.Bullet;
import com.cobresun.states.Defense;

public class Sniper extends Tower{
	Clip shot;
	BufferedImage pic;
	int range;
	double spotP;
	Random r;

	int cooldown;

	public Sniper(int i, int j, int size) {
		super(i, j, size, 100);

		range = 120;
		spotP = 0.005;

		r = new Random(System.nanoTime());
		cooldown = 0;

		try {
			pic = ImageIO.read(new File("rsrc/sniper.png"));
		} catch (IOException e) {

		}
	}

	public Sniper(int x, int y) {
		super(x/60, y/60, 60, 100);

		try {
			pic = ImageIO.read(new File("rsrc/sniper.png"));
		} catch (IOException e) {

		}
	}

	@Override
	public void draw(Graphics2D g) {
		if (getPlacing()) {
			g.setColor(new Color(0, 0, 0, 50));
			g.fillOval(getX()-100, getY() - 100, 240, 240);
		}

		g.drawImage(pic, getX(), getY(), 40, 40, null);
	}

	@Override
	public void update(Defense d) {
		cooldown--;

		if (cooldown <= 0) {
			ArrayList<Attacker> a = d.getAttackers();

			for (int i = 0; i < a.size(); i++) {
				Attacker at = a.get(i);

				double ax = at.getX();
				double ay = at.getY();

				double dis = Math.sqrt(Math.pow(getX()+ 20 - ax, 2) + Math.pow(getY() + 20 - ay, 2));


				if (dis <= range) {
					double g = r.nextDouble();
					if (g <= spotP) {
						SoundEffect.SHOT.play();
						d.addBullet(new Bullet(getX() + 20, getY() + 20, 5, a.get(i)));
						cooldown = 200;
					}
				}
			}
		}

	}



}
