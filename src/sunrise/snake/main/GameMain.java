/**
 * 游戏启动的类，首先需要显示面板，蛇，石头，食物等，因此我们需要先new这些对象并设置相关的属性
 * 有了这些对象后由中央控制器启动游戏
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
		// 创建对象并且传递给控制器的构造函数进行初始化
		Ground ground = new Ground();
		Snake snake = new Snake();
		Food food = new Food();
		GamePanel gamePanel = new GamePanel();
		Controller controller = new Controller(food, snake, ground, gamePanel);

		/*
		 * 创建frame并设置属性，将gamePanel(有panel派生的)添加到Frame显示游戏面板
		 * 注意添加控制器到gamePanel和snake中实现按键处理和蛇的移动监听处理
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
