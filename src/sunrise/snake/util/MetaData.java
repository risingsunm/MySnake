package sunrise.snake.util;


public class MetaData
{
	public class Direction
	{
		public static final int UP = -1;
		public static final int DOWN = 1;
		public static final int LETF = 2;
		public static final int RIGHT = -2;
	}
	
	public class CELL
	{
		//��Ԫ�������
		public static final int SIZE = 21;
		//ÿ����Ԫ��Ŀ��
		public static final int WIDTH = 20;
		//ÿ����Ԫ��ĸ߶�
		public static final int HEIGHT = 20;
	}
	
	public class Init
	{
		public static final int SNAKE_LENGTH = 3;
	}
	
	public class GameLevel
	{
		public static final int EASY = 500;
		public static final int MEDI = 300;
		public static final int SENIOR = 150;
		
	}
}
