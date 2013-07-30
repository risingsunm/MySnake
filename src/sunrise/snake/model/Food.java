package sunrise.snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import sunrise.snake.util.GridElement;
import sunrise.snake.util.MetaData;

public class Food extends Point
{
	public void DrawMe(Graphics g)
	{
		System.out.println("Food's DrawMe()");
		g.setColor(Color.RED);
		g.fill3DRect(x * MetaData.CELL.WIDTH, y * MetaData.CELL.HEIGHT,
				MetaData.CELL.WIDTH, MetaData.CELL.HEIGHT, true);
	}

	public boolean isSnakeEatFood(Snake snake)
	{
		System.out.println("Food's isSnakeEatFood()");
		return this.equals(snake.GetHead());
	}

	// 产生一颗新食物
	public void NewFood(Point p)
	{
		this.setLocation(p);
	}

}
