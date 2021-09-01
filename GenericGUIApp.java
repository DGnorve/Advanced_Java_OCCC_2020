//David Norvell
//Advanced Java @ OCCC Fall 2020 Online
//GenericGUIApp.class
//Final Project "WX_Prowler"

package WXApp;
import java.awt.*;
import javax.swing.*;

public class GenericGUIApp extends JFrame 
{
	private static final long serialVersionUID = -1417493487968092039L;
	private final int WIDTH = 600;
	private final int HEIGHT = 800;
	public GenericGUIApp ax;
	public JPanel jp;
	
	
	public GenericGUIApp()
	{
		super("WX_PROWLER V0.1");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,1));
		setSize(WIDTH,HEIGHT);
		jp = new WXProwlerJPanel();
		add(jp);
		setResizable(true);
		setVisible(true);
	}




}
