package main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class CurrentUpgrades {
	
	public static File f = new File("upgrades");
	
	public static int enemySlow;

	public static void loadUpgrades() {
		if (!f.exists()) {
			try {
				f.createNewFile();
				PrintWriter pw = new PrintWriter(f);
				pw.println();
				pw.println();
				pw.println();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Healthbar.resistanceOne = (((float) Handler.loadData(f, 1)) / 10f) + 1;
		Healthbar.resistanceTwo = (((float) Handler.loadData(f, 1)) / 10f) + 1;
		Handler.points = Handler.loadData(f, 0);
		enemySlow = Handler.loadData(f, 2);
	}

}
