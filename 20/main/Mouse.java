package main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gameObjects.Asteroid;

public class Mouse implements MouseListener {

	static Rectangle play = new Rectangle(293, 150, 201, 50);
	static Rectangle upgrades = new Rectangle(293, 232, 201, 50);
	static Rectangle plusOne = new Rectangle(180, 250, 110, 25);
	static Rectangle plusFive = new Rectangle(340, 250, 110, 25);
	static Rectangle plusTen = new Rectangle(500, 250, 110, 25);

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	boolean finishedSaving = true;

	@Override
	public void mouseReleased(MouseEvent e) {
		switch (Game.state) {
		case Start:
			if (play.contains(e.getPoint())) {
				Game.state = GameState.Play;
				Game.reset();
			} else if (upgrades.contains(e.getPoint())) {
				Game.state = GameState.Upgrades;
			}
			break;
		case DeathScreen:
			if (play.contains(e.getPoint())) {
				Game.reset();
			}
			if (upgrades.contains(e.getPoint())) {
				Game.state = GameState.Upgrades;
				Handler.asteroids.clear();
				Handler.trails.clear();
				for (int i = 0; i < 7; i++) {
					Handler.asteroids
							.add(new Asteroid((int) (Math.random() * -805) + 3, (int) (Math.random() * -604) + 3));
				}
			}
			break;
		case Upgrades:
			if (play.contains(e.getPoint())) {
				Game.reset();
			} else if (plusOne.contains(e.getPoint())) {
				Game.state = GameState.Resistance;
			} else if (plusFive.contains(e.getPoint())) {
				Game.state = GameState.EnemySlow;
			}
			break;
		case Resistance:
			if (play.contains(e.getPoint())) {
				Game.state = GameState.Upgrades;
				finishedSaving = true;
			} else if (plusOne.contains(e.getPoint()) && finishedSaving) {
				if (Handler.points >= 100) {
					finishedSaving = false;
					Handler.points -= 100;
					Healthbar.resistanceOne += 0.1f;
					Healthbar.resistanceTwo += 0.1f;
					Handler.addData(CurrentUpgrades.f, 1, 1);
					finishedSaving = true;
				}
			} else if (plusFive.contains(e.getPoint()) && finishedSaving) {
				if (Handler.points >= 500) {
					finishedSaving = false;
					Handler.points -= 500;
					Healthbar.resistanceOne += 0.5f;
					Healthbar.resistanceTwo += 0.5f;
					Handler.addData(CurrentUpgrades.f, 1, 5);
					finishedSaving = true;
				}
			} else if (plusTen.contains(e.getPoint()) && finishedSaving) {
				if (Handler.points >= 1000) {
					finishedSaving = false;
					Handler.points -= 1000;
					Healthbar.resistanceOne += 1f;
					Healthbar.resistanceTwo += 1f;
					Handler.addData(CurrentUpgrades.f, 1, 10);
					finishedSaving = true;
				}
			}
			break;
		case EnemySlow:
			if (play.contains(e.getPoint())) {
				Game.state = GameState.Upgrades;
				finishedSaving = true;
			} else if (plusOne.contains(e.getPoint()) && finishedSaving) {
				if (CurrentUpgrades.enemySlow < 25) {
					if (Handler.points >= 25) {
						finishedSaving = false;
						Handler.points -= 25;
						CurrentUpgrades.enemySlow += 1;
						Handler.addData(CurrentUpgrades.f, 2, 1);
						finishedSaving = true;
					}
				}
			} else if (plusFive.contains(e.getPoint()) && finishedSaving) {
				if (CurrentUpgrades.enemySlow < 21) {
					if (Handler.points >= 125) {
						finishedSaving = false;
						Handler.points -= 125;
						CurrentUpgrades.enemySlow += 5;
						Handler.addData(CurrentUpgrades.f, 2, 5);
						finishedSaving = true;
					}
				}
			} else if (plusTen.contains(e.getPoint()) && finishedSaving) {
				if (CurrentUpgrades.enemySlow < 16) {
					if (Handler.points >= 250) {
						finishedSaving = false;
						Handler.points -= 250;
						CurrentUpgrades.enemySlow += 10;
						Handler.addData(CurrentUpgrades.f, 2, 10);
						finishedSaving = true;
					}
				}
			}
			break;
		default:
			break;
		}
	}
}
