package com.cobresun.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Park extends Terrain{
	
	BufferedImage pic;

	public Park(int i, int j, int size) {
		super(i, j, size);
		try {
			pic = ImageIO.read(new File("rsrc/grass.png"));
		} catch (IOException e) {
			
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.drawImage(pic, getX(), getY(), 60, 60, null);
	}

}
