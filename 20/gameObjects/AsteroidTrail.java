package gameObjects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.Handler;

public class AsteroidTrail {

	public int x, y;
	public float length, maxLength;

	public AsteroidTrail(int length, double x, double y) {
		this.length = length;
		this.x = (int) x;
		this.y = (int) y;
		maxLength = length;
	}

	public boolean tick() {
		boolean b = false;
		if (length <= 0) {
			Handler.trails.remove(this);
			b = true;
		}
		length--;
		return b;
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(length/maxLength));
		g.setColor(new Color((int) length, (int) ((maxLength - length)/2), 0));
		g.fillRect(x, y, 6, 6);
		g2d.setComposite(makeTransparent(1));
	}
	
	public AlphaComposite makeTransparent(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, alpha);
	}

}
