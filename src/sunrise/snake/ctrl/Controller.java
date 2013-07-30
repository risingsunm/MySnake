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
 * ����ǰ�����ƣ�view������İ����¼��ɿ������ַ�������Model����������
 * ��snake�ƶ������������¼������������֪ͨ��ͼ��GamePanel��������ͼ
 * 
 * ��˰����������Լ��ƶ��¼����������������������ʵ�֣�
 * ���������ͨ�����캯���ﵽ��ʳ��ߣ�ʯͷ����ͼ����ĸ����������
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

	// �Բ�ͬ�İ�������
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

	// �����ƶ��󣬻����߳Ե�ʳ������µ�ʳ���,���߳Ե�ʯͷ���������Ż�֪ͨ��������ͼ,ͬʱ�仯�ߵ��ٶȣ��ı���Ϸ�Ѷȼ���
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
	 * ����������Ϸ��ʱ��һ�������ʼ�ͻ����һ��ʳ�ͬʱ��Ҳ��ʼ�ƶ�����ϷҲ�Ϳ�ʼ��.
	 * ����������������������ʳ������ķ������ߵ�start������ʵ������
	 */
	public void newGame()
	{
		food.NewFood(ground.NewPoint());
		snake.start();
	}
}
