package main;

import java.awt.Color;
import java.awt.Graphics;

public class Healthbar {
	
	public static float healthOne = 100.0f, resistanceOne = 1.0f, healthTwo = 100.0f, resistanceTwo = 1.0f;
	
	public static void tick() {
		if (healthOne <= 0) {
			Handler.playerOne = null;
		}
		if (healthTwo <= 0) {
			Handler.playerTwo = null;
		}
	}
	
	public static void takeHealthDown(float amount, boolean p1) {
		if (p1) {
			healthOne -= amount/resistanceOne;
		}
		if (!p1) {
			healthTwo -= amount/resistanceTwo;
		}
		System.out.println(resistanceOne);
	}
	
	public static void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(335, 10, 129, 25);
		g.setColor(new Color(120, (int) (Game.clamp(healthOne * 2.55f, 0, 255)), 0));
		g.fillRect(335, 10, (int) (healthOne * 1.29f + 1), 25);
		g.setColor(Color.BLACK);
		g.drawRect(335, 10, 129, 25);
		
		g.setColor(Color.RED);
		g.fillRect(335, 45, 129, 25);
		g.setColor(new Color(120, (int) (Game.clamp(healthTwo * 2.55f, 0, 255)), 0));
		g.fillRect(335, 45, (int) (healthTwo * 1.29f + 1), 25);
		g.setColor(Color.BLACK);
		g.drawRect(335, 45, 129, 25);
	}

}
