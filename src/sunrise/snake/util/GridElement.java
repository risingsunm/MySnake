package sunrise.snake.util;

import java.awt.Point;
import java.util.Random;

public class GridElement
{
	public Point NewPoint()//ע���api��������Щ����������ķ���
	{
		Random rnd = new Random();
		int x = rnd.nextInt(MetaData.CELL.WIDTH);
		int y = rnd.nextInt(MetaData.CELL.HEIGHT);
		return new Point(x,y);
	}
}
