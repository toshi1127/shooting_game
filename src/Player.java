import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Character implements KeyListener {
	private Boolean pressLeft = false;
	private Boolean pressRight = false;
	public Player(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
	}
	public void draw(MyFrame f) {
		f.setColor(0, 255, 255);
		f.fillRect(x, y, 30, 30);
	}
	public void shoot() {
		GameWorld.playerBullets.add(new PlayerBullet(x, y, 0, -20));
	}

	private int count;
	public int addInterval() {
		count++;
		if (count >= 4) {
			count = 1;
		}
		return count;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			vx = -5;
			pressLeft = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			vx = 5;
			pressRight = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			GameWorld.playerBullets.add(new PlayerBullet(x, y, 0, -10));
			GameWorld.playerBullets.add(new PlayerBullet(x, y, 4, -10));
			GameWorld.playerBullets.add(new PlayerBullet(x, y, -4, -10));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (!pressRight) {
				vx = 0;
			}
			pressLeft = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (!pressLeft) {
				vx = 0;
			}
			pressRight = false;
		}
	}

	public void move() {
		super.move();
		if (x < 8) { x = 8; }
		if (462 < x) { x = 462; }
	}
}
