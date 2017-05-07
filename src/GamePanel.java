import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int CURRENT_STATE = MENU_STATE;
	public static BufferedImage alienImg;
	public static BufferedImage rocketImg;
	public static BufferedImage bulletImg;
	public static BufferedImage spaceImg;
	public static BufferedImage PowerupImg;
	Font titleFont;
	Font font2;
	Rocketship rocket;
	ObjectManager manager;
	boolean canFire;
	GamePanel() {
		timer = new Timer(1000/60, this);
		titleFont = new Font("Arial", Font.PLAIN, 48);
		font2 = new Font("Arial", Font.PLAIN, 32);
		rocket = new Rocketship(250, 700, 50, 50);
		manager = new ObjectManager();
		manager.addObject(rocket);
		canFire = true;
		try {
			alienImg = ImageIO.read(this.getClass().getResourceAsStream("alien.png"));
			rocketImg = ImageIO.read(this.getClass().getResourceAsStream("rocket.png"));
			bulletImg = ImageIO.read(this.getClass().getResourceAsStream("bullet.png"));
			spaceImg = ImageIO.read(this.getClass().getResourceAsStream("space.png"));
			PowerupImg = ImageIO.read(this.getClass().getResourceAsStream("Powerup.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	void startGame(){
		timer.start();
	}
	
	void updateMenuState(){
		
	}

	void updateGameState(){
		manager.update();
		manager.manageEnemies();
		manager.checkCollision();
		if(rocket.isAlive == false){
			CURRENT_STATE = END_STATE;
			manager.reset();
			rocket = new Rocketship(250, 700, 50, 50);
			manager.addObject(rocket);
		}
		manager.getScore();
	}
	
	void updateEndState(){
		
	}
	
	void drawMenuState(Graphics g){
		g.setFont(titleFont);
		g.setColor(new Color(78, 178, 143));
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setColor(Color.BLACK);
		g.drawString("League Invaders", 75, 350);
		g.setFont(font2);
		g.drawString("Press Enter To Begin", 75, 400);
		g.drawString("Press Space For Instructions", 75, 450);
	}
	
	void drawGameState(Graphics g){
		g.drawImage(spaceImg, 0, 0, LeagueInvaders.width, LeagueInvaders.height, null);
		g.setColor(Color.RED);
		g.drawRect(10, 20, (int)rocket.stamina, 20);
		manager.draw(g);
	}
	
	void drawEndState(Graphics g){
		g.setFont(titleFont);
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.width, LeagueInvaders.height);
		g.setColor(Color.BLACK);
		g.drawString("Game Over", 125, 350);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		if(CURRENT_STATE == MENU_STATE){
			updateMenuState();
		}else if(CURRENT_STATE == GAME_STATE){
			updateGameState();
		}else if(CURRENT_STATE == END_STATE){
			updateEndState();
		}

	}
public void paintComponent(Graphics g){
	if(CURRENT_STATE == MENU_STATE){
		drawMenuState(g);
	}else if(CURRENT_STATE == GAME_STATE){
		drawGameState(g);
	}else if(CURRENT_STATE == END_STATE){
		drawEndState(g);
	}

	}
@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	System.out.println("i luv eli");
}
@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if(e.getKeyCode() == KeyEvent.VK_ENTER){
		CURRENT_STATE++;
	}
	if(CURRENT_STATE > END_STATE){
		CURRENT_STATE = MENU_STATE;
	}
	if(e.getKeyCode() == KeyEvent.VK_UP){
		rocket.up = true;
	}
	if(e.getKeyCode() == KeyEvent.VK_DOWN){
		rocket.down = true;
	}
	if(e.getKeyCode() == KeyEvent.VK_LEFT){
		rocket.left = true;
	}
	if(e.getKeyCode() == KeyEvent.VK_RIGHT){
		rocket.right = true;
	}
	if(e.getKeyCode() == KeyEvent.VK_SPACE && canFire && rocket.stamina >= 10){
		manager.addObject(new Projectile(rocket.x + 20, rocket.y, 10, 10));
		canFire = false;
		rocket.stamina -= 10;
	}
	}
@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	System.out.println("i luv eli most");
	if(e.getKeyCode() == KeyEvent.VK_UP){
		rocket.up = false;
	}
	if(e.getKeyCode() == KeyEvent.VK_DOWN){
		rocket.down = false;
	}
	if(e.getKeyCode() == KeyEvent.VK_LEFT){
		rocket.left = false;
	}
	if(e.getKeyCode() == KeyEvent.VK_RIGHT){
		rocket.right = false;
	}
	if(e.getKeyCode() == KeyEvent.VK_SPACE){
		canFire = true;
	}
}

}
