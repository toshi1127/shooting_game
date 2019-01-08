
public class RandomEnemy extends Enemy {
	public RandomEnemy(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		life = 4;
	}
	public void move() {
		super.move();
		vx = Math.random() * 4 - 2;
	}
	public void draw(MyFrame f) {
		f.setColor(100, 100, 100);
		f.fillRect((int)x+8,(int)y, 14, 15);
		f.setColor(100, 100, 100);
		f.fillRect((int)x+10,(int)y+15, 10, 15);
	}
}
