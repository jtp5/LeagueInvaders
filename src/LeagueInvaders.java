import javax.swing.*;

public class LeagueInvaders {
	JFrame frame;
	GamePanel panel;
	public static final int width = 500;
	public static final int height = 800;
	LeagueInvaders(){
		frame = new JFrame();
		panel = new GamePanel();
		frame.add(panel);
		frame.addKeyListener(panel);
		setup();
	}
public static void main(String[] args) {
	LeagueInvaders game = new LeagueInvaders();
}
void setup(){
	frame.add(panel);
	frame.setSize(width, height);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	panel.startGame();
}
}
