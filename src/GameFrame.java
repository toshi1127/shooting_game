import java.awt.GraphicsEnvironment;
import java.util.Vector;

public class GameFrame extends MyFrame {
	public void run() {
		int count = 0;
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fs = ge.getAvailableFontFamilyNames();
		for (String name : fs) {
			System.out.println(name);
		}
		// 時機の登場位置
		GameWorld.player = new Player(30, 660, 0, 0);
		// キーボードに反応するよう、イベントリスナー登録
		addKeyListener(GameWorld.player);
		GameWorld.playerBullets = new Vector<PlayerBullet>();
		GameWorld.enemies = new Vector<Enemy>();
		GameWorld.enemies.add(new EnemyBase(100, 50, 1, 0));

		while(true) {
			clear();
			count++;
			GameWorld.player.draw(this);
			GameWorld.player.move();
			movePlayerBullets(GameWorld.player.addInterval());
			moveEnemies();
			checkPlayerAndEnemies();
			checkPlayerBulletsAndEnemies();
			if (GameWorld.enemies.size() == 0) {
				setColor(255, 255, 50);
				drawString("STAGE CLEAR", 55, 300, 60);
			} else if (GameWorld.player.y < 0) {
				setColor(255, 0, 0);
				drawString("GAME OVER", 80, 300, 60);
			}
			if(count<50){
				setColor(255, 255, 50);
				drawString("GAME Start", 80, 300, 60);
			}
			sleep(0.03);
		}
	}
	public void movePlayerBullets(int interval) {
		/**
		 * オートショット+スペースキーでのショット
		 */
//		if (interval % 3 == 0) {
//			GameWorld.player.shoot();
//		}
		int i = 0;
		while (i < GameWorld.playerBullets.size()) {
			PlayerBullet b = GameWorld.playerBullets.get(i);
			b.draw(this);
			b.move();
			if (b.y < 0) {
				GameWorld.playerBullets.remove(i);
			} else {
				i++;
			}
		}
	}
	public void moveEnemies() {
		for (int i = 0; i < GameWorld.enemies.size(); i++){
			Enemy e = GameWorld.enemies.get(i);
			e.draw(this);
			e.move();
		}
		int i = 0;
		while (i < GameWorld.enemies.size()) {
			Enemy e = GameWorld.enemies.get(i);
			if (e.y > 720) {
				GameWorld.enemies.remove(i);
			} else {
				i++;
			}
		}
	}
	public void checkPlayerAndEnemies() {
		for (int i = 0; i < GameWorld.enemies.size(); i++) {
			Enemy e = GameWorld.enemies.get(i);
			if (checkHit(GameWorld.player, e, 30)) {
				GameWorld.player.y = -1000;
			}
		}
	}
	public void checkPlayerBulletsAndEnemies() {
		int i = 0;
		while (i < GameWorld.playerBullets.size()) {
			PlayerBullet b = GameWorld.playerBullets.get(i);
			int j = 0;
			int bulletLife = 1;
			while (j < GameWorld.enemies.size()) {
				Enemy e = GameWorld.enemies.get(j);
				if (checkHit(e, b, 30)) {
					bulletLife--;
					e.life--;
				}
				if (e.life < 0) {
					GameWorld.enemies.remove(j);
				} else {
					j++;
				}
			}
			if (bulletLife <= 0) {
				GameWorld.playerBullets.remove(i);
			} else {
				i++;
			}
		}
	}
	public boolean checkHit(Character a, Character b, int distance) {
		if (Math.abs(a.x - b.x) <= distance && Math.abs(a.y - b.y) <= distance) {
			return true;
		} else {
			return false;
		}
	}
}
