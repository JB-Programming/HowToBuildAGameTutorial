package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import other.DoubleStorage;

public abstract class GameEnemy {
	
	public DoubleStorage<Integer, Integer> tile = new DoubleStorage<Integer, Integer>();
	public Color c;
	
	public GameEnemy(int x, int y, Color c) {
		tile.first = x;
		tile.second = y;
		this.c = c;
	}
	
	public GameEnemy translate(EnemyType type) {
		switch(type) {
		case Slow:
			return new SlowEnemy(tile.first, tile.second, c);
		case Basic:
			return new SlowEnemy(tile.first, tile.second, c);
		}
		return null;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);

}
