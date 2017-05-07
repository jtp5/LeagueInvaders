import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Powerup extends GameObject{
double time = 0;
Powerup(int x, int y, int width, int height){
	super();
	this.x = x;
	this.y = y;
	this.width = width;
	this. height = height;
}
void update(){
	super.update();
	y++;
	x += (int)(Math.cos(time) * 3);
	time += .1;
}
void draw(Graphics g){
	g.drawImage(GamePanel.PowerupImg, x, y, width, height, null);
}
}
