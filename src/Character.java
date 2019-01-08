
public class Character {
	double x;
	double y;
	double vx;
	double vy;
	public Character(double x, double y, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	public void move() {
		x += vx;
		y += vy;
	}
	public void draw(MyFrame f) {
		f.setColor(0,128,0);
		f.fillRect((int)x,(int)y,30,30);
	}
}
