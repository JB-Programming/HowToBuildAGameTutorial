package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import main.Handler;

public class Asteroid {
		
	public double xd, yd, x, y;
	
	public long tickTime;

	public Asteroid(int x, int y) {
		this.x = x;
		this.y = y;
		xd = ((Math.random()/3) + 0.333f)*1.53f;
		yd = 1.9f - xd + (Math.random()/5.0f);
		xd /= 1.3f;
		yd /= 1.3f;
		tickTime = System.currentTimeMillis();
	}
	
	public Asteroid tick() {
		x += xd;
		y += yd;
		if (x > 805) {
			x = 0;
		}
		if (y > 604) {
			y = 0;
		}
		if (tickTime + 60 < System.currentTimeMillis()) {
			Handler.trails.add(new AsteroidTrail(70, x, y));
			tickTime = System.currentTimeMillis();
		}
		return this;
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(255, 0, 0));
		g.fillRect((int) x, (int) y, 6, 6);
	}

}
