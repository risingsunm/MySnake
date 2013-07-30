package sunrise.snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import sunrise.snake.util.MetaData;

/*
 * 整个面板看成是一个二维数组,数组的大小是面板单元格的长和宽
 * 当数组为边界时,设置其标志位为1,这个可以在构造函数中完成.
 * 石头--边界的显示可以在drawMe中,根据标志位是否为1决定填充
 * 当蛇头与石头重合时,说明蛇吃到了石头
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

	//循环判断蛇是否吃到食物,如果是则返回true,否则返回假
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
	 * 里也有一个产生点的函数，是因为产生的点不能和石头重叠
	 * 否则，蛇即使吃到了食物同时也吃到了石头，游戏同样over了
	 * */
	public Point NewPoint()//注意查api看看有哪些产生随机数的方法
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
