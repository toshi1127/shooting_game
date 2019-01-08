
public class CurveEnemy extends Enemy {
	public CurveEnemy(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		life = 6;
	}
	public void draw(MyFrame f){
		f.setColor(100, 100, 100);
		f.fillOval((int)x, (int)y,30, 30);
		f.setColor(100, 50, 100);
		f.fillOval((int)x+5, (int)y, 20, 30);
	}

	public void move(){
		super.move();
		if(x<GameWorld.player.x){
			x++;
		}
		if(x>GameWorld.player.x){
			x--;
		}
	}
}
