package com.cobresun.menus;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.cobresun.enemies.Attacker;
import com.cobresun.enemies.Citizen;
import com.cobresun.enemies.Innocent;
import com.cobresun.interfaces.Drawable;
import com.cobresun.main.Screen;
import com.cobresun.map.WhiteHouse;
import com.cobresun.states.MainMenu;
import com.cobresun.towers.RiotPolice;
import com.cobresun.towers.Sniper;

public class Tutorial extends Menu implements Drawable, MouseListener{
	Button next;
	MainMenu m;

	String[] pages = {"It was a beautiful day in Washington D.C. The sun was shining," + System.lineSeparator() + 
			"the tourists were taking pictures, and the Trump was tweeting.\r\n" + 
			"All of a sudden, the White House recieved reports of an impeding\r\n" + 
			"terrorist attack so Trump picks the closest moron to take care\r\n" + 
			"of security, luckily it's you!",
			"All the terrorists are dressed up as tourists so it'll be difficult\r\n" + 
					"to tell whether or not they are up to no good.",
					"You can set up snipers on buildings that will spot and kill \r\n" + 
							"terrorists in the crowd.",
							"You can also put police officers on the streets to stop\r\n" + 
									"everyone passing by. The will discover and stab any terrorists,\r\n" + 
									"though they get tired after checking 20 people.",
									"Finally, if all else fails you can click on them to kill them \r\n" + 
											"yourself! Sometimes the killers will equip their guns so keep\r\n" + 
											"an eye out. But be warned: if you kill a tourist, it will\r\n" + 
											"cause the White House to take damage.\r\n",
											"Every time a killer gets into the house, the president will\r\n" + 
													"take damage. Just keep him alive, no need to worry about a \r\n" + 
													"few scuffs on the skin.",
	"Good Luck! I wouldn't want to be in your shoes."};

	Drawable[][] entities = {{},
			{new Innocent(Screen.WIDTH/2 - 15, 300)},
			{new Sniper(Screen.WIDTH/2 - 30, 300)},
			{new RiotPolice(Screen.WIDTH/2 - 30, 300)},
			{new Attacker(Screen.WIDTH/2 - 15, 300)},
			{},
			{}};

	int currentPage;

	public Tutorial(MainMenu m) {
		currentPage = 0;
		next = new Button(Screen.WIDTH/2 - 75, Screen.HEIGHT - 100, 150, 50, "NEXT");
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent a) {
		int x = a.getX();
		int y = a.getY();

		if (next.buttonPressed(x, y)) {

			if (currentPage+1 >= pages.length) {
				m.startGame();
			}else {
				currentPage++;
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);

		g.setColor(Color.WHITE);

		drawString(g, pages[currentPage], Screen.WIDTH/2 - 200, Screen.HEIGHT/2 - 200);

		for (int i = 0; i < entities[currentPage].length; i++) {
			entities[currentPage][i].draw(g);
		}

		g.setColor(Color.GREEN);
		next.draw(g);

	}

	private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
	}


}
