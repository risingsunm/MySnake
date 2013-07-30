/**
 * 
 */
package sunrise.snake.adapter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author lchunxu
 *
 */

class GameWindowMonitor extends WindowAdapter
{
	public void windowClosing(WindowEvent e) 
	{
		System.out.println("Window Closing");	
		System.exit(0);
	}

	public void windowClosed(WindowEvent e) //这个函数没起作用？？？
	{
		System.out.println("Window Closed");	
		System.exit(0);
	}
	
	public void windowActivated(WindowEvent e) 
	{
		System.out.println("windowActivated");	
		//System.exit(0);
	}
	
	 public void windowDeactivated(WindowEvent e)  
	 {
	 	System.out.println("windowDeactivated");	
		//System.exit(0);
	 }
}

