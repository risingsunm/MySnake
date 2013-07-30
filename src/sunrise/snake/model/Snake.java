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
	// listeners����������Ӷ��������
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	// linkedList������ʾ�����������
	private LinkedList<Point> body = new LinkedList<Point>();
	// �����Ա����
	private int m_nOldDirection = 0;
	private int m_nNewDirection = 0;

	private Point m_OldPoint = null;
	//�����ߵ������Ƿ���ı���,Ҳ������Ϸ�Ƿ�����ı���
	private boolean m_bLife = true;
	
	//������˯��ʱ��ĳ��̣�������Ϸ�Ѷ�
	private int m_nSleepTime = 0;
	// �ڹ��캯���н��г�ʼ��
	public Snake()
	{
		init();
	}

	// �ߵĳ�ʼ������������������Ĵ�С����ʼλ�ã��ƶ������ƶ��ٶ�
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
	 * ÿ�������һ�Σ������������¼����������
	 * ��Ϊ����һ���ƶ�֮������һ�����ƶ�֮ǰ���ܰ�����Σ������ʱ�������û���ƶ����������������һ�ο��ܺ��ߵ�ǰ�ƶ������෴��
	 * ��ֻ�����һ�ε��ƶ�������Ч�ģ���������Ҫ�ģ�������෴����������������
	 */
	public void ChangeDirection(int direction)
	{
		m_nNewDirection = direction;
		System.out.println("Snake's ChangeDirection()");
	}

	/*
	 * �ƶ���ʱ��ÿ�ƶ�һ�����򣬿�����Ϊ��:
	 * ������һ����ͷ,�µ���ͷ��Ӧ�������Ӽ���������һ����β��ȥ����Ӧ����(��)����
	 */
	public void Move()
	{
		int x = body.getFirst().x;
		int y = body.getFirst().y;

		// �����ǰ����(�Ϸ���)���°����ķ���ͬ���͸ı䷽��
		if (!(0 == m_nNewDirection + m_nOldDirection))
		{
			m_nOldDirection = m_nNewDirection;
		}

		//������߽�ʱ�ʹ�������Եķ������
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
		body.addFirst(NewHead);//��ӵ���ͷ

		m_OldPoint = body.removeLast();//ȥ����β�ĵ�
	}

	//������ͷ��Ԫ�񣬿��������ж��Ƿ�Ե�ʳ��/ʯͷ.����Point��ʳ��/ʯͷ�غ�ʱ��˵���߳Ե�������
	public Point GetHead()
	{
		return body.getFirst();
	}

	//����������ĳ��ȣ������ȴﵽĳ��ֵʱ������˯��ʱ�䣬���ƶ��ӿ죬��Ϸ�ѶȼӴ�
	public int GetLength()
	{
		return body.size();
	}

	//�����ߵ��ƶ��ٶȣ�ͨ������˯��ʱ��������
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
	
	//����ͷ�������ĳһ�����غ�ʱ˵���߳Ե����Լ������ֻҪ���������������鿴�Ƿ�������ͷһ�������꼴��
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
	 * �������������ʱ,�������ߵ���������m_bLifeΪfalse
	 * �ߵ������ֳ���⵽�������Ϊfalseʱ�Ͳ���ִ��,������ϷҲ��over�� 
	 */
	public void Die()
	{
		m_bLife = false;
	}
	
	private boolean LifeStatue()
	{
		return m_bLife;
	}
	
	// ��panel�л����ߵ�����
	public void DrawMe(Graphics g)
	{
		g.setColor(Color.BLUE);
		for (Point p : body)
		{
			// ע���API������������һ��ͼ�񷽷�
			g.fill3DRect(p.x * MetaData.CELL.WIDTH, p.y * MetaData.CELL.HEIGHT,
					MetaData.CELL.WIDTH, MetaData.CELL.HEIGHT, true);
		}
	}

	/*
	 * �߳Ե�ʳ�������û�û�а����ı�Ļ�,�ƶ������ԭ����һ��;
	 * ����,�߳���ʳ������䳤�������,�����о��ᷢ��:
	 * �ڳԵ�ʳ���ƶ�ǰ�ͱ䳤��,һ�α���Ҫ�ڻ�û���ƶ�ǰ�����ߵĳ���.
	 * �ڳ���ʳ���,�൱û��
	 * */
	public void EatFood()
	{
		body.addLast(m_OldPoint);
	}

	/*
	 * ������һ���߳�ÿ��һ��ʱ���ִ������ʾ�߲�ͣ���ƶ�
	 * ����ߵ�����״̬Ϊ��(�����߳Ե�ʯͷ���������ʱ��)���߳���ֹ��������Ϸ
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

	// �������ƶ����߳�,��ҪҪ��start()����run()������������һ���µ��߳�
	public void start()
	{
		new Thread(new SnakeDriver()).start();
	}

	// �ṩ��Ӽ������ķ���
	public void AddSnakeListener(SnakeListener sl)
	{
		if (sl != null)
		{
			this.listeners.add(sl);
		}
	}
}
