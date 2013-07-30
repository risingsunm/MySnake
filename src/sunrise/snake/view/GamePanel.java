package sunrise.snake.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import sunrise.snake.model.Food;
import sunrise.snake.model.Ground;
import sunrise.snake.model.Snake;
import sunrise.snake.util.MetaData;

public class GamePanel extends JPanel
{
	private Food food = null;
	private Snake snake = null;
	private Ground ground = null;

	public void DispPanel(Food food, Ground ground, Snake snake)
	{
		this.food = food;
		this.ground = ground;
		this.snake = snake;

		this.repaint();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		// TODO Auto-generated method stub
		// super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, MetaData.CELL.WIDTH * MetaData.CELL.SIZE,
				MetaData.CELL.HEIGHT * MetaData.CELL.SIZE);
		if (food != null && snake != null && ground != null)
		{
			this.food.DrawMe(g);
			this.snake.DrawMe(g);
			this.ground.DrawMe(g);
		}
	}

}
