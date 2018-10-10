package gameObjects;

import java.awt.Color;
import java.awt.Graphics;

import main.CurrentUpgrades;
import main.Game;
import main.Handler;
import other.DoubleStorage;

public class BasicEnemy extends GameEnemy {

	public long tickTime;
	public int timeInbetween;
	public int addedTime;

	public BasicEnemy(int x, int y, Color c) {
		super(x, y, c);
		tickTime = System.currentTimeMillis();
		addedTime = CurrentUpgrades.enemySlow * 5;
		timeInbetween = (int) (600 + addedTime + (Math.random() * 800));
	}

	@Override
	public void tick() {
		if (System.currentTimeMillis() >= tickTime + timeInbetween) {
			aiMove();
			tickTime = System.currentTimeMillis();
			timeInbetween = (int) (600 + addedTime + (Math.random() * 800));
		}
		tile.second = Game.clamp(tile.second, 0, 13);
		tile.first = Game.clamp(tile.first, 0, 18);
	}

	private void aiMove() {
		Player p1 = Handler.playerOne;
		Player p2 = Handler.playerTwo;
		if (p1 != null && p2 != null) {
			int p1xdiff = Math.abs(p1.tile.first - tile.first);
			int p1ydiff = Math.abs(p1.tile.second - tile.second);
			double p1dis = Math.sqrt(Math.pow(p1xdiff, 2) + Math.pow(p1ydiff, 2));
			int p2xdiff = Math.abs(p2.tile.first - tile.first);
			int p2ydiff = Math.abs(p2.tile.second - tile.second);
			double p2dis = Math.sqrt(Math.pow(p2xdiff, 2) + Math.pow(p2ydiff, 2));
			DoubleStorage<Integer, Integer> targetTile = new DoubleStorage<Integer, Integer>();
			if (p1dis > p2dis) {
				targetTile = p2.tile;
			} else if (p2dis > p1dis) {
				targetTile = p1.tile;
			} else {
				targetTile = tile;
			}
			if (targetTile.first == tile.first && targetTile.second == tile.second) {

			} else if (Math.abs(targetTile.first - tile.first) <= Math.abs(targetTile.second - tile.second)) {
				if (targetTile.second < tile.second) {
					tile.second -= 3;
				} else {
					tile.second += 3;
				}
			} else if (Math.abs(targetTile.first - tile.first) > Math.abs(targetTile.second - tile.second)) {
				if (targetTile.first < tile.first) {
					tile.first -= 3;
				} else {
					tile.first += 3;
				}
			}
		} else if ((p1 == null) && (p2 != null)) {
			DoubleStorage<Integer, Integer> targetTile = new DoubleStorage<Integer, Integer>();
			targetTile = p2.tile;
			if (targetTile.first == tile.first && targetTile.second == tile.second) {

			} else if (Math.abs(targetTile.first - tile.first) <= Math.abs(targetTile.second - tile.second)) {
				if (targetTile.second < tile.second) {
					tile.second -= 3;
				} else {
					tile.second += 3;
				}
			} else if (Math.abs(targetTile.first - tile.first) > Math.abs(targetTile.second - tile.second)) {
				if (targetTile.first < tile.first) {
					tile.first -= 3;
				} else {
					tile.first += 3;
				}
			}
		} else if ((p2 == null) && (p1 != null)) {
			DoubleStorage<Integer, Integer> targetTile = new DoubleStorage<Integer, Integer>();
			targetTile = p1.tile;
			if (targetTile.first == tile.first && targetTile.second == tile.second) {

			} else if (Math.abs(targetTile.first - tile.first) <= Math.abs(targetTile.second - tile.second)) {
				if (targetTile.second < tile.second) {
					tile.second -= 3;
				} else {
					tile.second += 3;
				}
			} else if (Math.abs(targetTile.first - tile.first) > Math.abs(targetTile.second - tile.second)) {
				if (targetTile.first < tile.first) {
					tile.first -= 3;
				} else {
					tile.first += 3;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(c);
		g.fillRect(1 + (tile.first * 42), 1 + (tile.second * 41), 42, 41);
	}

}
