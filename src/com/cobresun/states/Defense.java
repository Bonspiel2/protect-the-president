package com.cobresun.states;

import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.cobresun.enemies.Citizen;
import com.cobresun.enemies.Innocent;
import com.cobresun.enemies.Attacker;
import com.cobresun.main.Game;
import com.cobresun.main.Screen;
import com.cobresun.main.SoundEffect;
import com.cobresun.map.Building;
import com.cobresun.map.Map;
import com.cobresun.map.Park;
import com.cobresun.map.Road;
import com.cobresun.map.Terrain;
import com.cobresun.map.WhiteHouse;
import com.cobresun.menus.SideMenu;
import com.cobresun.projectiles.Bullet;
import com.cobresun.towers.RiotPolice;
import com.cobresun.towers.Sniper;
import com.cobresun.towers.Tower;

public class Defense extends GameState{
	private Map m;
	private SideMenu menu;
	public boolean inRound;
	private int round;
	private int[] rounds;
	private boolean[] curRoundComp;
	int personOut;
	boolean allOut;

	int wait;

	Random r;

	ArrayList<Citizen> citizens;
	ArrayList<Attacker> attackers;
	ArrayList<Bullet> bullets;

	WhiteHouse w;

	int currency;
	int attacks;
	
	Clip shot;

	public Defense(Game g) {
		super(g);
		m = new Map(12);
		menu = new SideMenu(this, m);
		round = -1;
		rounds = loadRounds("rsrc/easy");

		citizens = new ArrayList<Citizen>();
		attackers = new ArrayList<Attacker>();
		bullets = new ArrayList<Bullet>();

		w = new WhiteHouse(100);

		wait = 0;

		r = new Random(System.nanoTime());

		try {
			m.init("rsrc/default");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		inRound = false;

		currency = 200;
	}

	public void draw(Screen s) {
		s.draw(m);
		s.draw(menu);
		for (int i = 0; i < citizens.size(); i++) {
			s.draw(citizens.get(i));
		}
		for (int i = 0; i < attackers.size(); i++) {
			s.draw(attackers.get(i));
		}
		for (int i = 0; i < bullets.size(); i++) {
			s.draw(bullets.get(i));
		}
		s.draw(w);
		s.draw(currency);
	}

	public void addMouse(Screen s) {
		s.addMouseListener(menu);
		s.addMouseMotionListener(menu);
	}

	public void startRound() {
		round++;

		int people = rounds[round] * 5;

		boolean[] roundComp = new boolean[people];

		for (int i = 0; i < rounds[round]; i++) {
			int pos = r.nextInt(people);
			roundComp[pos] = true;
		}
		curRoundComp = roundComp;
		personOut = 0;
		inRound = true;
		allOut = false;
	}

	public int[] loadRounds(String path) {
		int totalRounds = 10;
		Scanner in = null;
		try {
			in = new Scanner(new File(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int[] r = new int[10];

		for (int i = 0; i < 10; i++) {
			r[i] = in.nextInt();
		}

		return r;
	}

	public void update() {
		if (inRound) {
			wait--;
			if (!allOut && wait <= 0) {
				if (curRoundComp[personOut]) {
					attackers.add(new Attacker(m, 11 * 60 + 15, 4 * 60 + 15, r.nextDouble()*1.5 + 0.4));
				} else {
					citizens.add(new Innocent(m, 11 * 60 + 15, 4 * 60 + 15, r.nextDouble()*1.5 + 0.4));
				}
				wait = r.nextInt(100);
				personOut++;
				allOut = personOut >= curRoundComp.length;
			} else if (allOut && attackers.isEmpty() && citizens.isEmpty()) {
				inRound = false;
				citizens.clear();
			}
			m.update(this);

			for (int i = 0; i < citizens.size(); i++) {
				if (citizens.get(i).move()) {
					citizens.remove(i);
					i--;
				}
			}

			for (int i = 0; i < attackers.size(); i++) {
				if (attackers.get(i).move()) {
					w.attack(attackers.remove(i).getAttack(), this);
					SoundEffect.MSHOT.play();
					i--;
				}
			}
			
			for (int i = 0; i < bullets.size(); i++) {
				Bullet b = bullets.get(i);
				b.update();
				if (b.atTarget()) {
					attackers.remove(b.getTarget());
					giveMoney(10);
					bullets.remove(i);
					i--;
				}
			}
		} else if (!inRound && round == rounds.length - 1){
			endGame(true);
		}
	}

	public void placeTower(int x, int y) {
		int i = x/60;
		int j = y/60;

		Terrain t = m.getTerrain(x, y);
		Tower pt = m.getPTower();

		if (pt.getCost() <= currency) {

			if (pt instanceof RiotPolice && t instanceof Road) {
				m.placeTower(x, y, pt);
				currency-=pt.getCost();
			} else if (pt instanceof Sniper && t instanceof Building) {
				m.placeTower(x, y, pt);
				currency-=pt.getCost();
			} else {
				m.setPTower(null);
			}
		} else {
			m.setPTower(null);
		}
	}

	public ArrayList<Citizen> getCitizens() {
		return citizens;
	}

	public ArrayList<Attacker> getAttackers() {
		return attackers;
	}

	public void giveMoney(int amount) {
		currency+=amount;
	}

	public void endGame(boolean won) {
		Game g = getGame();
		g.endGame(won);
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet); 
	}
	
	public boolean inRound() {
		return inRound;
	}

	public void kill(int x, int y) {
		if (m.getTerrain(x, y) instanceof Road) {
			boolean found = false;
			for (int i = 0; i < attackers.size() && !found; i++) {
				Attacker a = attackers.get(i);
				if (a.containsPoint(x,y)) {
					SoundEffect.SHOT.play();
					found = true;
					attackers.remove(i);
					giveMoney(10);
				}
			}
			
			for (int i = 0; i < citizens.size() && !found; i++) {
				Citizen c = citizens.get(i);
				if (c.containsPoint(x,y)) {
					SoundEffect.SHOT.play();
					found = true;
					citizens.remove(i);
					w.attack(5, this);
				}
			}
		}
		
	}

	public void removeTower(int x, int y) {
		m.removeTower(x, y);	
	}
	
	public MouseListener getMouse() {
		return menu;
	}
}
