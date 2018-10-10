package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener {

	public boolean[] keyDown = { false, false, false, false, false, false, false, false };

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_W) {
			if (!keyDown[0]) {
				if (Handler.playerOne != null) {
					if (Handler.playerTwo != null) {
						if (!(Handler.playerOne.tile.second == Handler.playerTwo.tile.second + 1)
								|| Handler.playerOne.tile.first != Handler.playerTwo.tile.first) {
							Handler.playerOne.tile.second--;
						}
						keyDown[0] = true;
					} else {
						Handler.playerOne.tile.second--;
						keyDown[0] = true;
					}
				}
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_A) {
			if (!keyDown[1]) {
				if (Handler.playerOne != null) {
					if (Handler.playerTwo != null) {
						if (!(Handler.playerOne.tile.first == Handler.playerTwo.tile.first + 1)
								|| Handler.playerOne.tile.second != Handler.playerTwo.tile.second) {
							Handler.playerOne.tile.first--;
						}
						keyDown[1] = true;
					} else {
						Handler.playerOne.tile.first--;
						keyDown[1] = true;
					}
				}
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_S) {
			if (!keyDown[2]) {
				if (Handler.playerOne != null) {
					if (Handler.playerTwo != null) {
						if (!(Handler.playerOne.tile.second == Handler.playerTwo.tile.second - 1)
								|| Handler.playerOne.tile.first != Handler.playerTwo.tile.first) {
							Handler.playerOne.tile.second++;
						}
						keyDown[2] = true;
					} else {
						Handler.playerOne.tile.second++;
						keyDown[2] = true;
					}
				}
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_D) {
			if (!keyDown[3]) {
				if (Handler.playerOne != null) {
					if (Handler.playerTwo != null) {
						if (!(Handler.playerOne.tile.first == Handler.playerTwo.tile.first - 1)
								|| Handler.playerOne.tile.second != Handler.playerTwo.tile.second) {
							Handler.playerOne.tile.first++;
						}
						keyDown[3] = true;
					} else {
						Handler.playerOne.tile.first++;
						keyDown[3] = true;
					}
				}
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			if (!keyDown[4]) {
				if (Handler.playerTwo != null) {
					if (Handler.playerOne != null) {
						if (!(Handler.playerTwo.tile.second == Handler.playerOne.tile.second + 1)
								|| Handler.playerTwo.tile.first != Handler.playerOne.tile.first) {
							Handler.playerTwo.tile.second--;
						}
						keyDown[4] = true;
					} else {
						Handler.playerTwo.tile.second--;
						keyDown[4] = true;
					}
				}
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			if (!keyDown[5]) {
				if (Handler.playerTwo != null) {
					if (Handler.playerOne != null) {
						if (!(Handler.playerTwo.tile.first == Handler.playerOne.tile.first + 1)
								|| Handler.playerTwo.tile.second != Handler.playerOne.tile.second) {
							Handler.playerTwo.tile.first--;
						}
						keyDown[5] = true;
					} else {
						Handler.playerTwo.tile.first--;
						keyDown[5] = true;
					}
				}
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			if (!keyDown[6]) {
				if (Handler.playerTwo != null) {
					if (Handler.playerOne != null) {
						if (!(Handler.playerTwo.tile.second == Handler.playerOne.tile.second - 1)
								|| Handler.playerTwo.tile.first != Handler.playerOne.tile.first) {
							Handler.playerTwo.tile.second++;
						}
						keyDown[6] = true;
					} else {
						Handler.playerTwo.tile.second++;
						keyDown[6] = true;
					}
				}
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (!keyDown[7]) {
				if (Handler.playerTwo != null) {
					if (Handler.playerOne != null) {
						if (!(Handler.playerTwo.tile.first == Handler.playerOne.tile.first - 1)
								|| Handler.playerTwo.tile.second != Handler.playerOne.tile.second) {
							Handler.playerTwo.tile.first++;
						}
						keyDown[7] = true;
					} else {
						Handler.playerTwo.tile.first++;
						keyDown[7] = true;
					}
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_W) {
			keyDown[0] = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_A) {
			keyDown[1] = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_S) {
			keyDown[2] = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_D) {
			keyDown[3] = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			keyDown[4] = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			keyDown[5] = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			keyDown[6] = false;
		}
		if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			keyDown[7] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
