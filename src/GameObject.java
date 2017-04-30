import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {
	Rectangle collisionBox;
	int x;
	int y;
	int width;
	int height;
	public boolean isAlive;

	GameObject() {
		x = 10;
		y = 10;
		isAlive = true;
		collisionBox = new Rectangle(x, y, width, height);
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g) {

	}
}
