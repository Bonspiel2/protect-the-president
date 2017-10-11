package com.cobresun.map;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.cobresun.interfaces.Drawable;
import com.cobresun.main.Screen;
import com.cobresun.states.Defense;
import com.cobresun.towers.Tower;

public class Map implements Drawable{
	
	private Terrain[][] terrainLayer;
	private Tower[][] towerLayer;
	
	int size;
	
	Tower pTower;
	
	public Map(int x) {
		terrainLayer = new Terrain[x][x];
		towerLayer = new Tower[x][x];
		size = x;
	}
	
	public void init(String path) throws FileNotFoundException {
		Scanner in = new Scanner(new File(path));
		
		for (int i = 0; i < terrainLayer.length; i++) {
			for (int j = 0; j < terrainLayer[0].length; j++) {
				String block = in.next();
				if (block.equals("P")) {
					terrainLayer[i][j] = new Park(j,i,Screen.HEIGHT/size);
				} else if (block.equals("B")) {
					terrainLayer[i][j] = new Building(j,i,Screen.HEIGHT/size);
				} else if (block.equals("R")) {
					terrainLayer[i][j] = new Road(j,i,Screen.HEIGHT/size);
				} else if (block.equals("H")) {
					terrainLayer[i][j] = new House(j,i,Screen.HEIGHT/size);
				}
					
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i = 0; i < terrainLayer.length; i++) {
			for (int j = 0; j < terrainLayer[0].length; j++) {
				terrainLayer[i][j].draw(g);
			}
		}
		for (Tower[] t : towerLayer) {
			for (Tower b : t) {
				if (b!=null) {
					b.draw(g);
				}
			}
		}
		
		if (pTower != null) {
			pTower.draw(g);
		}
		
	}

	public void update(Defense d) {
		for (Tower[] tl : towerLayer) {
			for (Tower t : tl) {
				if (t!= null) {
					t.update(d);
				}
			}
		}
		
	}
	
	public void placeTower(int x, int y, Tower t) {
		int i = x/60;
		int j = y/60;
		
		t.setI(i);
		t.setJ(j);
		t.setPlacing(false);
		
		towerLayer[i][j] = t;
		
		pTower = null;
	}
	
	public void setPTower(Tower t) {
		pTower = t;
	}
	
	public void setPTowerPos(int x, int y) {
		pTower.setX((int)(x/60));
		pTower.setY((int)(y/60));
	}
	
	public Terrain getTerrain(int x, int y) {
		int i = x/60;
		int j = y/60;
		return terrainLayer[j][i];
	}
	
	public Tower getPTower() {
		return pTower;
	}

	public void removeTower(int x, int y) {
		int i = x/60;
		int j = y/60;
		
		towerLayer[i][j] = null;
		
	}

}
