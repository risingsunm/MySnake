package sunrise.snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import sunrise.snake.listenr.SnakeListener;
import sunrise.snake.util.MetaData;

public class Snake
{
	// listeners可以用来添加多个监听器
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	// linkedList用来表示蛇身体的坐标
	private LinkedList<Point> body = new LinkedList<Point>();
	// 方向成员变量
	private int m_nOldDirection = 0;
	private int m_nNewDirection = 0;

	private Point m_OldPoint = null;
	//控制蛇的生命是否存活的变量,也就是游戏是否结束的变量
	private boolean m_bLife = true;
	
	//设置蛇睡眠时间的长短，更改游戏难度
	private int m_nSleepTime = 0;
	// 在构造函数中进行初始化
	public Snake()
	{
		init();
	}

	// 蛇的初始化函数，包括蛇身体的大小，起始位置，移动方向，移动速度
	private void init()
	{
		int x = MetaData.CELL.WIDTH / 2;
		int y = MetaData.CELL.HEIGHT / 2;

		for (int i = 0; i < 3; i++)
		{
			body.add(new Point(x--, y));
		}

		m_nNewDirection = m_nOldDirection = MetaData.Direction.RIGHT;
		m_bLife = true;
		m_nSleepTime = MetaData.GameLevel.EASY;
	}

	/*
	 * 每按方向键一次，用这个变量记录保存起来，
	 * 因为在这一次移动之后与下一次蛇移动之前可能按键多次，在这个时间段内蛇没有移动，连续按键的最后一次可能和蛇当前移动方向相反，
	 * 而只有最后一次的移动才是有效的，是我们需要的，如果是相反方向则抛弃不处理
	 */
	public void ChangeDirection(int direction)
	{
		m_nNewDirection = direction;
		System.out.println("Snake's ChangeDirection()");
	}

	/*
	 * 移动的时候每移动一个方向，可以认为是:
	 * 增加了一个蛇头,新的蛇头相应坐标增加减，减少了一个蛇尾，去掉相应坐标(点)即可
	 */
	public void Move()
	{
		int x = body.getFirst().x;
		int y = body.getFirst().y;

		// 如果当前方向(老方向)和新按键的方向不同，就改变方向
		if (!(0 == m_nNewDirection + m_nOldDirection))
		{
			m_nOldDirection = m_nNewDirection;
		}

		//当到达边界时就从另外相对的方向进入
		switch (m_nOldDirection)
		{
			case MetaData.Direction.UP:
			{
				y--;
				if (y < 0)
				{
					y = MetaData.CELL.HEIGHT -1;
				}
				break;
			}
			case MetaData.Direction.DOWN:
			{
				y++;
				if (y >= MetaData.CELL.HEIGHT)
				{
					y = 0;
				}
				break;
			}
			case MetaData.Direction.LETF:
			{
				x--;
				if (x < 0)
				{
					x = MetaData.CELL.WIDTH - 1;
				}
				break;
			}
			case MetaData.Direction.RIGHT:
			{
				x++;
				if (x >= MetaData.CELL.WIDTH)
				{
					x = 0;
				}
				break;
			}
			default:
				break;
		}

		Point NewHead = new Point(x, y);
		body.addFirst(NewHead);//添加到蛇头

		m_OldPoint = body.removeLast();//去掉蛇尾的点
	}

	//返回蛇头单元格，可以用来判断是否吃到食物/石头.当此Point与食物/石头重合时，说明蛇吃到了他们
	public Point GetHead()
	{
		return body.getFirst();
	}

	//返回蛇身体的长度，当长度达到某个值时，减短睡眠时间，蛇移动加快，游戏难度加大
	public int GetLength()
	{
		return body.size();
	}

	//设置蛇的移动速度，通过减少睡眠时间来控制
	public void SetSnakeVolity()
	{
		if (GetLength() <= 5)
		{
			m_nSleepTime = MetaData.GameLevel.EASY;
		}
		else if (GetLength() <= 10)
		{
			m_nSleepTime = MetaData.GameLevel.MEDI;
		}
		else
		{
			m_nSleepTime = MetaData.GameLevel.SENIOR;
		}
	}
	
	//当蛇头与自身的某一个点重合时说明蛇吃到了自己，因此只要在蛇身体的链表里查看是否有与蛇头一样的坐标即可
	public boolean isEatSelf()
	{
		if(0 != body.indexOf(body.getFirst()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/*
	 * 当调用这个函数时,将会置蛇的生命变量m_bLife为false
	 * 蛇的生命现场检测到这个变量为false时就不在执行,这样游戏也就over了 
	 */
	public void Die()
	{
		m_bLife = false;
	}
	
	private boolean LifeStatue()
	{
		return m_bLife;
	}
	
	// 在panel中绘制蛇的身体
	public void DrawMe(Graphics g)
	{
		g.setColor(Color.BLUE);
		for (Point p : body)
		{
			// 注意查API，看下填充绘制一般图像方法
			g.fill3DRect(p.x * MetaData.CELL.WIDTH, p.y * MetaData.CELL.HEIGHT,
					MetaData.CELL.WIDTH, MetaData.CELL.HEIGHT, true);
		}
	}

	/*
	 * 蛇吃到食物后如果用户没有按键改变的话,移动方向和原来的一样;
	 * 另外,蛇吃了食物后本身会变长这个过程,经过研究会发现:
	 * 在吃掉食物移动前就变长了,一次必须要在还没有移动前更新蛇的长度.
	 * 在吃了食物后,相当没有
	 * */
	public void EatFood()
	{
		body.addLast(m_OldPoint);
	}

	/*
	 * 这里用一个线程每隔一段时间的执行来表示蛇不停的移动
	 * 如果蛇的生命状态为假(即当蛇吃到石头或者自身的时候)则线程终止，结束游戏
	 */
	private class SnakeDriver implements Runnable
	{
		@Override
		public void run()
		{
			while (Snake.this.LifeStatue())
			{
				Move();
				for (SnakeListener sl : listeners)
				{
					sl.SnakeMoved(Snake.this);
				}

				try
				{
					Thread.sleep(m_nSleepTime);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	// 启动蛇移动的线程,主要要由start()调用run()才能真正开启一个新的线程
	public void start()
	{
		new Thread(new SnakeDriver()).start();
	}

	// 提供添加监听器的方法
	public void AddSnakeListener(SnakeListener sl)
	{
		if (sl != null)
		{
			this.listeners.add(sl);
		}
	}
}
