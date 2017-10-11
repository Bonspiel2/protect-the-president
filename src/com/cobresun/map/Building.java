package com.cobresun.map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Building extends Terrain{
	BufferedImage pic;

	public Building(int i, int j, int size) {
		super(i, j, size);

		try {
			pic = ImageIO.read(new File("rsrc/build.png"));
		} catch (IOException e) {
			
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(pic, getX(), getY(), 60, 60, null);
	}

}
