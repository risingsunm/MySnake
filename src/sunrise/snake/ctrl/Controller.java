package sunrise.snake.ctrl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import sunrise.snake.listenr.SnakeListener;
import sunrise.snake.model.Food;
import sunrise.snake.model.Ground;
import sunrise.snake.model.Snake;
import sunrise.snake.util.MetaData;
import sunrise.snake.view.GamePanel;

/*
 * 根据前面的设计，view层产生的按键事件由控制器分发到各个Model对象来处理；
 * 当snake移动后所产生的事件由中央控制器通知视图层GamePanel来更新视图
 * 
 * 因此按键适配器以及移动事件监听器都由中央控制器来实现，
 * 中央控制器通过构造函数达到对食物，蛇，石头，视图面板四个对象的引用
 */

public class Controller extends KeyAdapter implements SnakeListener
{
	private Food food = null;
	private Snake snake = null;
	private Ground ground = null;
	private GamePanel gamePanel = null;

	public Controller(Food food, Snake snake, Ground ground, GamePanel gamePanel)
	{
		this.food = food;
		this.snake = snake;
		this.ground = ground;
		this.gamePanel = gamePanel;
	}

	// 对不同的按键处理
	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_UP:
			{
				snake.ChangeDirection(MetaData.Direction.UP);
				break;
			}
			case KeyEvent.VK_DOWN:
			{
				snake.ChangeDirection(MetaData.Direction.DOWN);
				break;
			}
			case KeyEvent.VK_LEFT:
			{
				snake.ChangeDirection(MetaData.Direction.LETF);
				break;
			}
			case KeyEvent.VK_RIGHT:
			{
				snake.ChangeDirection(MetaData.Direction.RIGHT);
				break;
			}
			default:
			{
				JOptionPane.showMessageDialog(null, "Please use arrow-key!",
						"Worning", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
	}

	// 当蛇移动后，或者蛇吃掉食物产生新的食物后,后者吃到石头碰到了面板才会通知面板更新视图,同时变化蛇的速度，改变游戏难度级别
	@Override
	public void SnakeMoved(Snake snake)
	{
		if (food.isSnakeEatFood(snake))
		{
			snake.EatFood();
			food.NewFood(ground.NewPoint());
		}

		if(ground.isSnakeEatRock(snake) 
				|| snake.isEatSelf())
		{
			snake.Die();
			//return;
		}
		else
		{
			snake.SetSnakeVolity();
		}
		
		gamePanel.DispPanel(food, ground, snake);
	}

	/*
	 * 在我们玩游戏的时候，一旦点击开始就会产生一颗食物，同时蛇也开始移动，游戏也就开始了.
	 * 这个功能由中央控制器调用食物产生的方法和蛇的start方法来实现启动
	 */
	public void newGame()
	{
		food.NewFood(ground.NewPoint());
		snake.start();
	}
}
