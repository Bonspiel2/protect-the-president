package com.cobresun.menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.cobresun.main.Screen;
import com.cobresun.states.GameOver;

public class Endgame extends Menu {
	Button again;
	GameOver g;
	
	int mes;
	
	String[] message = {"Oooooo you got the president killed. Luckily, he was on\r\n" + 
			"a golfing trip and just had a dummy in the oval office \r\n" + 
			"with a random word generator to tweet for him. He's still here \r\n" + 
			"for 3 more years, yay!\r\n",
			"Good job man! The president got away with his life! ...\r\n" + 
			"because he was actually out on a golfing trip. Maybe check\r\n" + 
			"if he's in the office first before you go spending our \r\n" + 
			"defense budget, okay?"};
	
	
	public Endgame(GameOver g, boolean won) {
		if (won) {
			mes = 1;
		} else {
			mes = 0;
		}
		
		this.g = g;
		
		again = new Button(Screen.WIDTH/2 - 75, Screen.HEIGHT - 100, 150, 50, "PLAY AGAIIN");
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Screen.WIDTH, Screen.HEIGHT);
		g.setColor(Color.GREEN);
		again.draw(g);
		g.setColor(Color.WHITE);
		g.drawString("GAME OVER", Screen.WIDTH/2 - 75, Screen.HEIGHT/2 - 300);
		drawString(g, message[mes], Screen.WIDTH/2 - 200, Screen.HEIGHT/2 - 200);
	}

	@Override
	public void mouseClicked(MouseEvent a) {
		int x = a.getX();
		int y = a.getY();
		
		if (again.buttonPressed(x, y)) {
			g.playAgain();
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
	
	private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

}
