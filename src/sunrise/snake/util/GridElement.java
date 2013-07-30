package sunrise.snake.util;

import java.awt.Point;
import java.util.Random;

public class GridElement
{
	public Point NewPoint()//注意查api看看有哪些产生随机数的方法
	{
		Random rnd = new Random();
		int x = rnd.nextInt(MetaData.CELL.WIDTH);
		int y = rnd.nextInt(MetaData.CELL.HEIGHT);
		return new Point(x,y);
	}
}
