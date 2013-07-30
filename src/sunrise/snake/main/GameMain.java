/**
 * ��Ϸ�������࣬������Ҫ��ʾ��壬�ߣ�ʯͷ��ʳ��ȣ����������Ҫ��new��Щ����������ص�����
 * ������Щ����������������������Ϸ
 */
package sunrise.snake.main;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import sunrise.snake.ctrl.Controller;
import sunrise.snake.model.Food;
import sunrise.snake.model.Ground;
import sunrise.snake.model.Snake;
import sunrise.snake.util.MetaData;
import sunrise.snake.view.GamePanel;

/**
 * @author lchunxu
 * 
 */
public class GameMain
{
	public static void main(String[] args)
	{
		// ���������Ҵ��ݸ��������Ĺ��캯�����г�ʼ��
		Ground ground = new Ground();
		Snake snake = new Snake();
		Food food = new Food();
		GamePanel gamePanel = new GamePanel();
		Controller controller = new Controller(food, snake, ground, gamePanel);

		/*
		 * ����frame���������ԣ���gamePanel(��panel������)��ӵ�Frame��ʾ��Ϸ���
		 * ע����ӿ�������gamePanel��snake��ʵ�ְ���������ߵ��ƶ���������
		 */
		JFrame mainFrame = new JFrame("Snake");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocation(600, 200);
		mainFrame.setSize(MetaData.CELL.WIDTH * MetaData.CELL.SIZE,
				MetaData.CELL.HEIGHT * MetaData.CELL.SIZE+20);
	
		mainFrame.add(gamePanel, BorderLayout.CENTER);
		
		gamePanel.addKeyListener(controller);
		snake.AddSnakeListener(controller);
		mainFrame.addKeyListener(controller);
		mainFrame.setVisible(true);

		controller.newGame();
	}
}
