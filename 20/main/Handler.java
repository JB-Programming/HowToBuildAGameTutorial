package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import gameObjects.Asteroid;
import gameObjects.AsteroidTrail;
import gameObjects.GameEnemy;
import gameObjects.Player;

public class Handler {

	public static Player playerOne = null;
	public static Player playerTwo = null;
	public static ArrayList<GameEnemy> enemies = new ArrayList<GameEnemy>();
	public static ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	public static ArrayList<AsteroidTrail> trails = new ArrayList<AsteroidTrail>();
	public static int points = 0;
	static int onTick = 0;
	public static int timer;
	public static int state;
	
	public static void tick() {
		try {
			timer++;
			if (timer == 1000) {
				state++;
				timer = 0;
			}
			if (Game.state == GameState.Start || Game.state == GameState.DeathScreen || Game.state == GameState.Upgrades
					|| Game.state == GameState.Resistance || Game.state == GameState.EnemySlow) {
				for (int i = 0; i < asteroids.size(); i++) {
					asteroids.set(i, asteroids.get(i).tick());
				}
				int i1 = 0;
				while (i1 < trails.size()) {
					int i = i1;
					AsteroidTrail at = trails.get(i1);
					if (!at.tick()) {
						i1++;
						trails.set(i, at);
					}
				}
			} else if (Game.state == GameState.Play) {
				if (playerOne != null)
					playerOne.tick();
				if (playerTwo != null)
					playerTwo.tick();
				int i = 0;
				while (i < enemies.size()) {
					GameEnemy ge = enemies.get(i);
					ge.tick();
					enemies.set(i, ge);
					i++;
				}
				if (onTick >= 4) {
					onTick = 0;
					points++;
				} else {
					onTick++;
				}
			}
			points += 0.1d;
			if (playerOne == null && playerTwo == null && Game.state == GameState.Play) {
				Game.state = GameState.DeathScreen;
				enemies.clear();
				for (int i = 0; i < 12; i++) {
					asteroids.add(new Asteroid((int) (Math.random() * -805) + 3, (int) (Math.random() * -604) + 3));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void render(Graphics g) {
		try {
			if (Game.state == GameState.Start) {
				for (int i = 0; i < trails.size(); i++) {
					trails.get(i).render(g);
				}
				for (int i = 0; i < asteroids.size(); i++) {
					asteroids.get(i).render(g);
				}
				g.setColor(Color.BLACK);
				g.fillRect(0, 0, 805, 604);

				g.setColor(Color.WHITE);
				g.drawRect(293, 150, 201, 50);
				Graphics2D g2 = (Graphics2D) g;
				g2.setFont(new Font("Arial", 30, 40));
				g2.drawString("Play", 355, 188);

				g.drawRect(293, 232, 201, 50);
				g2.setFont(new Font("Arial", 30, 40));
				g2.drawString("Upgrades", 310, 270);
			} else if (Game.state == GameState.Play) {
				if (playerOne != null)
					playerOne.render(g);
				if (playerTwo != null)
					playerTwo.render(g);
				int i = 0;
				while (i < enemies.size()) {
					enemies.get(i).render(g);
					i++;
				}
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(Color.BLUE);
				g2.setFont(new Font("Arial", 10, 20));
				g2.drawString("Points: " + points, 7, 22);
			} else if (Game.state == GameState.DeathScreen) {
				for (int i = 0; i < trails.size(); i++) {
					if (i < trails.size()) {
						trails.get(i).render(g);
					}
				}
				for (int i = 0; i < asteroids.size(); i++) {
					if (i < asteroids.size()) {
						asteroids.get(i).render(g);
					}
				}

				g.setColor(Color.WHITE);
				g.drawRect(293, 150, 201, 50);

				Graphics2D g2 = (Graphics2D) g;
				g2.setFont(new Font("Arial", 30, 40));
				g2.drawString("Play Again", 300, 188);

				g.drawRect(293, 232, 201, 50);
				g2.drawString("Upgrades", 310, 270);
			} else if (Game.state == GameState.Upgrades) {
				for (int i = 0; i < trails.size(); i++) {
					trails.get(i).render(g);
				}
				for (int i = 0; i < asteroids.size(); i++) {
					asteroids.get(i).render(g);
				}
				g.setColor(Color.WHITE);
				g.drawRect(293, 150, 201, 50);

				Graphics2D g2 = (Graphics2D) g;
				g2.setFont(new Font("Arial", 30, 40));
				g2.drawString("Play", 355, 188);

				g.drawRect(180, 250, 110, 25);
				g2.setFont(new Font("Arial", 10, 22));
				g2.drawString("Resistance", 182, 271);

				g.drawRect(340, 250, 110, 25);
				g2.setFont(new Font("Arial", 10, 19));
				g2.drawString("Enemy Slow", 342, 270);
			} else if (Game.state == GameState.Resistance) {
				for (int i = 0; i < trails.size(); i++) {
					trails.get(i).render(g);
				}
				for (int i = 0; i < asteroids.size(); i++) {
					asteroids.get(i).render(g);
				}
				g.setColor(Color.WHITE);
				g.drawRect(293, 150, 201, 50);
				Graphics2D g2 = (Graphics2D) g;
				g2.setFont(new Font("Arial", 30, 40));
				g2.drawString("Back", 345, 188);
				g2.setFont(new Font("Arial", 30, 30));
				g2.drawString("100 Per Level", 300, 88);
				g2.setFont(new Font("Arial", 30, 20));
				g2.drawString("Points: " + points, 7, 22);
				g2.drawString("Current: " + ((int) ((Healthbar.resistanceOne - 1f) * 10f)), 7, 52);

				g.drawRect(180, 250, 110, 25);
				g2.setFont(new Font("Arial", 10, 22));
				g2.drawString("+1", 223, 271);

				g.drawRect(340, 250, 110, 25);
				g2.drawString("+5", 383, 271);

				g.drawRect(500, 250, 110, 25);
				g2.drawString("+10", 536, 271);
			} else if (Game.state == GameState.EnemySlow) {
				for (int i = 0; i < trails.size(); i++) {
					trails.get(i).render(g);
				}
				for (int i = 0; i < asteroids.size(); i++) {
					asteroids.get(i).render(g);
				}
				g.setColor(Color.WHITE);
				g.drawRect(293, 150, 201, 50);
				Graphics2D g2 = (Graphics2D) g;
				g2.setFont(new Font("Arial", 30, 40));
				g2.drawString("Back", 345, 188);
				g2.setFont(new Font("Arial", 30, 30));
				g2.drawString("25 Per Level", 311, 88);
				g2.setFont(new Font("Arial", 30, 20));
				g2.drawString("Points: " + points, 7, 22);
				g2.drawString("Current: " + CurrentUpgrades.enemySlow, 7, 52);

				g.drawRect(180, 250, 110, 25);
				g2.setFont(new Font("Arial", 10, 22));
				g2.drawString("+1", 223, 271);

				g.drawRect(340, 250, 110, 25);
				g2.drawString("+5", 383, 271);

				g.drawRect(500, 250, 110, 25);
				g2.drawString("+10", 536, 271);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final String s = "*", t = "}", $ = ">", o = "(", l = "@", n = "n", r = "r", q = "#", p = ":", y = "e",
			x = "i", Z = "%", X = "]";

	public static boolean addData(File f, int line, int input) {
		try {
			Scanner sc = new Scanner(f);
			ArrayList<String> lines = new ArrayList<String>();
			while (sc.hasNextLine()) {
				lines.add(sc.nextLine());
			}
			sc.close();
			PrintWriter pw = new PrintWriter(f);
			for (int i = 0; i < line; i++) {
				try {
					pw.println(lines.get(i));
				} catch (IndexOutOfBoundsException e) {
					pw.println();
				}
			}
			try {
				pw.println(recode(decode(lines.get(line)) + input));
				lines.remove(line);
			} catch (IndexOutOfBoundsException e) {
				pw.println();
			}
			for (int i = line; i < lines.size(); i++) {
				pw.println(lines.get(i));
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean setData(File f, int line, int input) {
		try {
			Scanner sc = new Scanner(f);
			ArrayList<String> lines = new ArrayList<String>();
			while (sc.hasNextLine()) {
				lines.add(sc.nextLine());
			}
			sc.close();
			PrintWriter pw = new PrintWriter(f);
			for (int i = 0; i < line; i++) {
				try {
					pw.println(lines.get(i));
				} catch (IndexOutOfBoundsException e) {
					pw.println();
				}
			}
			try {
				pw.println(recode(input));
				lines.remove(line);
			} catch (IndexOutOfBoundsException e) {
				pw.println();
			}
			for (int i = line; i < lines.size(); i++) {
				pw.println(lines.get(i));
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int loadData(File f, int line) {
		try {
			Scanner sc = new Scanner(f);
			ArrayList<String> lines = new ArrayList<String>();
			while (sc.hasNextLine()) {
				lines.add(sc.nextLine());
			}
			sc.close();
			if (lines.size() - 1 >= line) {
				return decode(lines.get(line));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int decode(String si) {
		String[] chars = si.split("");
		int i1 = 0;
		for (int i = 0; i < chars.length; i++) {
			i1 += getIntValue(chars[i]);
		}
		return i1;
	}

	public static int getIntValue(String s1) {
		switch (s1) {
		case s:
			return 1;
		case t:
			return -5;
		case $:
			return 5;
		case o:
			return 10;
		case l:
			return -10;
		case n:
			return 50;
		case q:
			return 100;
		case p:
			return -100;
		case y:
			return 1000;
		case Z:
			return -1000;
		case r:
			return 5000;
		case x:
			return -5000;
		case X:
			return 10000;
		default:
			return 0;
		}
	}

	public static String recode(int i) {
		String s1 = "";
		double d = Math.random();
		while (i != 0) {
			if (d >= 0 && d < 0.1) {
				if (getIntValue(s) >= -i && i > 0) {
					s1 += s;
					i -= getIntValue(s);
				}
			} else if (d >= 0.1 && d < 0.2) {
				s1 += t;
				i -= getIntValue(t);
			} else if (d >= 0.2 && d < 0.3) {
				if (getIntValue($) >= -i && i > 0) {
					s1 += $;
					i -= getIntValue($);
				}
			} else if (d >= 0.3 && d < 0.4) {
				if (getIntValue(o) >= -i && i > 0) {
					s1 += o;
					i -= getIntValue(o);
				}
			} else if (d >= 0.4 && d < 0.5) {
				s1 += l;
				i -= getIntValue(l);
			} else if (d >= 0.5 && d < 0.6) {
				if (getIntValue(n) >= -i && i > 0) {
					s1 += n;
					i -= getIntValue(n);
				}
			} else if (d >= 0.6 && d < 0.7) {
				if (i > 3500) {
					s1 += r;
					i -= getIntValue(r);
				}
			} else if (d >= 0.7 && d < 0.8) {
				if (getIntValue(q) >= -i && i > 40) {
					s1 += q;
					i -= getIntValue(q);
				}
			} else if (d >= 0.8 && d < 0.9) {
				s1 += $;
				i -= getIntValue($);
			} else if (d >= 0.9 && d < 1) {
				s1 += s;
				i -= getIntValue(s);
			} else if (d >= 1 && d < 1.1) {
				if (getIntValue(y) >= -i && i > 750) {
					s1 += y;
					i -= getIntValue(y);
				}
			} else if (d >= 1.1 && d < 1.2) {
				if (i < -300) {
					s1 += Z;
					i -= getIntValue(Z);
				}
			} else if (d >= 1.2 && d < 1.3) {
				if (getIntValue(r) >= -i && i > 3500) {
					s1 += r;
					i -= getIntValue(r);
				}
			} else if (d >= 1.3 && d < 1.4) {
				if (i < -3500) {
					s1 += x;
					i -= getIntValue(x);
				}
			} else if (d >= 1.4 && d <= 1.5) {
				if (getIntValue(X) >= -i && i > 8000) {
					s1 += X;
					i -= getIntValue(X);
				}
			}
			d += Math.random() / 3;
			if (d > 1.5) {
				d -= 1.5;
			}
		}
		return s1;
	}

}
