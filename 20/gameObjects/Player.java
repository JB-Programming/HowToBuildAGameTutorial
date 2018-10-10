package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import main.Handler;
import main.Healthbar;
import other.DoubleStorage;

public class Player {

	public DoubleStorage<Integer, Integer> tile = new DoubleStorage<Integer, Integer>();
	public Color c;
	boolean p1;

	public Player(Color c, int x, int y, boolean p1) {
		tile.first = x;
		tile.second = y;
		this.c = c;
		this.p1 = p1;
	}

	public void tick() {
		tile.second = Game.clamp(tile.second, 0, 13);
		tile.first = Game.clamp(tile.first, 0, 18);
		int i = 0;
		while (i < Handler.enemies.size()) {
			GameEnemy ge = Handler.enemies.get(i);
			if (ge instanceof BasicEnemy) {
				if (ge.tile.first == tile.first && ge.tile.second == tile.second) {
					Healthbar.takeHealthDown(0.2f, p1);
				}
			}
			i++;
		}
	}

	public void render(Graphics g) {
		g.setColor(c);
		g.fillRect((tile.first * 42) + 1, (tile.second * 41) + 1, 42, 41);
	}

}
