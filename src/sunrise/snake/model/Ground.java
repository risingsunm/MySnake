package sunrise.snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import sunrise.snake.util.MetaData;

/*
 * ������忴����һ����ά����,����Ĵ�С����嵥Ԫ��ĳ��Ϳ�
 * ������Ϊ�߽�ʱ,�������־λΪ1,��������ڹ��캯�������.
 * ʯͷ--�߽����ʾ������drawMe��,���ݱ�־λ�Ƿ�Ϊ1�������
 * ����ͷ��ʯͷ�غ�ʱ,˵���߳Ե���ʯͷ
 */
public class Ground extends Point
{
	private int rocks[][] = new int[MetaData.CELL.WIDTH][MetaData.CELL.HEIGHT];

	public Ground()
	{
		for (int  i= 0; i < MetaData.CELL.WIDTH; i++)
		{
			rocks[0][i] = 1;
			rocks[MetaData.CELL.HEIGHT - 1][i] = 1;
			
			rocks[i][0] = 1;
			rocks[i][MetaData.CELL.WIDTH - 1] = 1;
		}
	}

	public void DrawMe(Graphics g)
	{
		g.setColor(Color.BLACK);
		for (int x = 0; x < MetaData.CELL.WIDTH; x++)
		{
			for (int y = 0; y < MetaData.CELL.HEIGHT; y++)
			{
				if (rocks[x][y] == 1)
				{
					g.fill3DRect(x * MetaData.CELL.WIDTH, y * MetaData.CELL.HEIGHT, 
							MetaData.CELL.WIDTH, MetaData.CELL.HEIGHT, 
							true);
				}
			}
		}
	}

	//ѭ���ж����Ƿ�Ե�ʳ��,������򷵻�true,���򷵻ؼ�
	public boolean isSnakeEatRock(Snake snake)
	{
		for (int x = 0; x < MetaData.CELL.WIDTH; x++)
		{
			for (int y = 0; y < MetaData.CELL.HEIGHT; y++)
			{
				if ((rocks[x][y] == 1)
					&& (x==snake.GetHead().x && y == snake.GetHead().y))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	/*
	 * ��Ҳ��һ��������ĺ���������Ϊ�����ĵ㲻�ܺ�ʯͷ�ص�
	 * �����߼�ʹ�Ե���ʳ��ͬʱҲ�Ե���ʯͷ����Ϸͬ��over��
	 * */
	public Point NewPoint()//ע���api��������Щ����������ķ���
	{
		Random rnd = new Random();
		int x = 0;
		int y = 0;
		do
		{
			x = rnd.nextInt(MetaData.CELL.WIDTH);
			y = rnd.nextInt(MetaData.CELL.HEIGHT);
		}while(rocks[x][y] == 1);

		return new Point(x,y);
	}
}
