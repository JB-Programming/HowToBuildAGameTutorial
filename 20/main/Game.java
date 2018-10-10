package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import gameObjects.Asteroid;
import gameObjects.Player;
import gameObjects.SlowEnemy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -7600755214008009002L;

	public static GameState state;
	private Thread thread;
	private boolean running = false;

	public Game() {
		new Window(805, 604, "Game", this);
		state = GameState.Start;
		addKeyListener(new Keys());
		addMouseListener(new Mouse());
		for (int i = 0; i < 11; i++) {
			Handler.asteroids.add(new Asteroid((int) (Math.random() * -805) + 3, (int) (Math.random() * -604) + 3));
		}
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		requestFocus();
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
		}
		if (rende()) {
			CurrentUpgrades.loadUpgrades();
		}
		while (running) {
			try {
				Thread.sleep(8);
				tick();
				render();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		stop();
	}

	private void stop() {
		try {
			running = false;
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean rende() {
		BufferStrategy bs = getBufferStrategy();

		Graphics g = bs.getDrawGraphics();

		g.setFont(new Font("Times New Roman", 100, 100));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 805, 604);
		g.setColor(Color.YELLOW);
		g.drawString("Loading...", 202, 293);
		g.dispose();
		bs.show();
		return true;
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		if (state == GameState.Play) {
			g.setColor(new Color(200, 100, 0));
			g.fillRect(0, 0, 805, 604);
			Handler.render(g);
			drawLines(g);
			Healthbar.render(g);
		} else if (state == GameState.Start) {
			Handler.render(g);
		} else if (state == GameState.Upgrades || state == GameState.Resistance || state == GameState.DeathScreen
				|| state == GameState.EnemySlow) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 805, 604);
			Handler.render(g);
		}

		g.dispose();
		bs.show();
	}

	private void drawLines(Graphics g) {
		g.setColor(Color.BLACK);
		int x = 0;
		int y = 0;
		int xmax = 800;
		int ymax = 600;
		int i = 0;
		while (i < 21) {
			g.drawLine(x + (i * 42), y, i * 42, ymax);
			i++;
		}
		i = 0;
		while (i < 16) {
			g.drawLine(x, y + (i * 41), xmax, i * 41);
			i++;
		}
	}

	private void tick() {
		if (state == GameState.Play) {
			Healthbar.tick();
		}
		Handler.tick();
	}

	public static float clamp(float var, float min, float max) {
		if (var > max) {
			return max;
		} else if (var < min) {
			return min;
		} else {
			return var;
		}
	}

	public static int clamp(int var, int min, int max) {
		if (var > max) {
			return max;
		} else if (var < min) {
			return min;
		} else {
			return var;
		}
	}

	public static void reset() {
		Handler.playerOne = new Player(Color.BLUE, 18, 6, true);
		Handler.playerTwo = new Player(Color.GREEN, 0, 6, false);
		Healthbar.healthOne = 100.0f;
		Healthbar.healthTwo = 100.0f;
		Handler.enemies.clear();
		Handler.timer = 0;
		Handler.state = 0;
		Handler.enemies.add(new SlowEnemy(((int) (Math.random() * 17)) + 1, (int) (Math.random() * 14), Color.YELLOW));
		Handler.asteroids.clear();
		Handler.trails.clear();
		state = GameState.Play;
	}

}
