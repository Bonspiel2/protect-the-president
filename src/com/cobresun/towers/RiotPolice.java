package com.cobresun.towers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.cobresun.enemies.Attacker;
import com.cobresun.enemies.Citizen;
import com.cobresun.main.SoundEffect;
import com.cobresun.states.Defense;

public class RiotPolice extends Tower{
	BufferedImage pic;
	
	double energy;
	int attack;
	
	Clip ruffle;
	Clip stab;

	public RiotPolice(int i, int j, int size) {
		super(i, j, size, 50);
		energy = 20; 
		attack = 5;
		
		try {
			pic = ImageIO.read(new File("rsrc/police.png"));
		} catch (IOException e) {
			
		}
	}
	
	public RiotPolice(int x, int y) {
		super(x/60, y/60, 60, 50);
		
		try {
			pic = ImageIO.read(new File("rsrc/police.png"));
		} catch (IOException e) {
			
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(pic, getX(), getY(), 40, 40, null);
		
		g.setColor(Color.BLUE);
		
		g.fillRect(getX() - 5, getY() + 45, (int) (50 * (double)(energy/20)), 5);
	}

	@Override
	public void update(Defense d) {
		ArrayList<Citizen> eList = d.getCitizens();
		ArrayList<Attacker> aList = d.getAttackers();
		
		for (int i = 0; i < aList.size(); i++) {
			Attacker a = aList.get(i);
				if (a.collided(getI(), getJ())) {
					SoundEffect.STAB.play();
					energy--;
					aList.remove(i);
					d.giveMoney(10);
					i--;
					
					if (!enoughEnergy()) {
						d.removeTower(getX(), getY());
						return;
					}
				}
		}

		for (Citizen e : eList) {
			if (!e.isChecked()) {
				if (e.collided(getI(), getJ())) {
					SoundEffect.RUFFLE.play();
					energy--;
					e.setChecked(true);
					
					if (!enoughEnergy()) {
						d.removeTower(getX(), getY());
						return;
					}
				}
			}
		}

	}
	
	public boolean enoughEnergy() {
		return energy > 0;
	}

}
