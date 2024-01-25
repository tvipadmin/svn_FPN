import hctpen.penn;
import hctpen.pope;
import hctpen.pop;
import hctpen.popm;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import javax.swing.JFileChooser;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.awt.Component;
import java.awt.Font;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;




public class notepad extends JFrame  implements ActionListener,Runnable

{
	static { System.loadLibrary("hctpen"); }


private static Logger log = Logger.getLogger(notepad.class);

//all for hctpen dll
boolean b, paintCalled = false, saved=false;

String dirpath=null, filename = null;
Thread check;

//initilastion check and library handle
int isinit=0,libhand=0;
int y,pagecount=1;

Calendar rightNow = Calendar.getInstance();
int dat;
int mone;
int yr;
int ee;
penn forfunc=new penn();
penn forpos=new penn();
Point ptlast=new Point(0,0);
Point pt=new Point(0,0);
Point rptlast=new Point(0,0);
Point rpt=new Point(0,0);
Point rptmid=new Point(0,0);


// all part of gui
PPanel  panel  = new PPanel();
JPanel  contr  = new JPanel();
PButton whatt;

Font font1 = new Font("Courier", Font.BOLD,  12);

Font font5 = new Font(null, Font.BOLD,  16);
Font font4 = new Font(null, Font.BOLD,  14);
Font font3 = new Font("Courier", Font.BOLD,  14);
JLabel l3=new JLabel("Pen Status",JLabel.CENTER);
JLabel l4=new JLabel("Not Ready",JLabel.CENTER);
JLabel l5=new JLabel("Options",JLabel.CENTER);

Graphics2D graph1,graph2;
BufferedImage a;
JTextField field1=new JTextField();
JTextField field2=new JTextField();
JTextField field3=new JTextField();

public notepad(String dirpath,String filename)
{
l3.setForeground(Color.darkGray);
l4.setForeground(Color.darkGray);
l5.setForeground(Color.darkGray);

l3.setFont(font4);
l4.setFont(font3);
l5.setFont(font5);

this.dirpath = dirpath;
this.filename = filename;

//contr.setLocation(20,50);
this.setTitle("digitalpad");
this.setFont(font1);

URL iconURL = this.getClass().getResource("/logo.jpg");
Image image=new ImageIcon(iconURL).getImage();
this.setIconImage(image);

addWindowListener(new WindowAdapter()
	{	public void windowClosing(WindowEvent ev)
		{	dispose();
			b=false;//SET B FALSE TO STOP THE THREAD

			forfunc.Destroy(libhand);
			System.exit(0);
		}
	});


	setResizable(false);
	setBounds(235,5,590,725);
	getContentPane().add("Center",panel);
	getContentPane().add("West",contr);
	contr.setLayout(new BorderLayout());
	Border       br = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,Color.white,Color.gray);
	contr.setBorder(br);
	//contr.add("South",addStatus());

	//addImage(contr,"pen.jpeg" );


	contr.add("North",addButtons());
	contr.add("South",addLogo());
	setVisible(true);


}

public void create()
{
	libhand=forfunc.Libload();
		if(libhand==-1)
		{
		 new pope(this,"Error:Missing Device Drivers");
		}
		else
		{
			isinit=forfunc.Initinstance(libhand);
			if(isinit!=6)
			{
				new pope(this,"Server could not be loaded.\n1)The version requested is not supported by the driver.\n2) The server could not be initialized." );
			}
			else
	 		{
				b=forfunc.Initpen(libhand);
				if(!b)
				{
					new pope(this,"Pen is Not Connected.\nCheck if USB port is open.\nRe-Connect the device.");
				}
				else
				{
					l4.setText("Pen Ready");
					l4.setBackground(Color.green);
					check=new Thread(this,"check");
					check.start();
				}
			}
	}
}


public void run(){

while (b)
{

		forpos=forfunc.Pennotify(libhand);


			pt.x=250+(forpos.x/20);
			pt.y=forpos.y/20;


		switch ( forpos.ev)
			{
			case 1:
				//Pen is moving
				graph1.setColor(Color.black);
				graph1.drawLine(ptlast.x,ptlast.y,pt.x,pt.y);
				ptlast.x = pt.x;
				ptlast.y=pt.y;
				repaint();
				break;
			case 2:

				//Pen is up

				break;
			case 3:
				//Pen is down
				ptlast.x = pt.x;
				ptlast.y=pt.y;
				break;
			}
		try
		{
			//Sleep 1 millisec to avoid 100% CPU and race condition.
		Thread.sleep(1);
		}
		catch(Exception e)
		{
			break;
		}
  }


}

private JPanel addLogo()
{
	try
	{
	InputStream imageStream = this.getClass().getResourceAsStream("/logo1.jpg");
	BufferedImage image = ImageIO.read(imageStream);
	ImageBackgroundPanel imp = new ImageBackgroundPanel(image);
	imp.setPreferredSize(new Dimension(80,94));
	return(imp);
	}
	catch(Exception e)
	{
		log.error("display logo error " + e);
		return null;
	}


}

private JPanel addButtons()
{
	JPanel panel = new JPanel();
	panel.setPreferredSize(new Dimension(80,200));
	panel.setLayout(new GridLayout(6,1,1,4));
	Dimension size4 = l4.getPreferredSize();
	l4.setOpaque(true);
	l4.setBackground(Color.red);
	panel.add(l4);
	Insets insets = panel.getInsets();
	l4.setBounds( 5+insets.right, 25+insets.top,size4.width, size4.height);

	panel.add(l5);
	for (int i=0; i < 4; i++)
	{
		PButton jb = new PButton(i);
		jb.addActionListener(this);
		panel.add(jb);
	}
	return(panel);
}

/*
private JPanel addStatus()
{
	JPanel panel=new JPanel(new GridLayout(4,0));
	panel.setPreferredSize(new Dimension(80,150));
	Container c=panel;
	c.setLayout(null);
	Insets insets = c.getInsets();
	Dimension size3 = l3.getPreferredSize();
	Dimension size4 = l4.getPreferredSize();

	panel.add(l3);
	l4.setOpaque(true);
	l4.setBackground(Color.red);
	panel.add(l4);
l4.setBounds( 5+insets.right, 25+insets.top,size4.width, size4.height);
	return panel;
}
*/

private JPanel addBox()
{

	JPanel box=new JPanel();
	JPanel but=new JPanel(new GridLayout(0,1));
	box.setPreferredSize(new Dimension(80,200));
	but.setPreferredSize(new Dimension(80,60));
	JPanel field=new JPanel(new GridLayout(0,1));

	field1.setColumns(7);
	field2.setColumns(7);
    field3.setColumns(7);


	box.add(field,BorderLayout.NORTH);
	box.add(but,BorderLayout.SOUTH);

return (box);
}


public void actionPerformed(ActionEvent ae)
{

	 if (ae.getSource() instanceof PButton)
	{
		whatt = (PButton)ae.getSource();

	if(whatt.type==0)
	{
		try {


				File file = new File(dirpath+"\\" +filename+"-"+pagecount+".jpg");
				BufferedImage bi = a.getSubimage(0,0,a.getWidth(),a.getHeight()); // retrieve image
				ImageIO.write(bi, "jpg", file);
				saved = true;
                new pop(this,"Page Content Saved");


    		}
	 		catch (IOException e)
    		{
			new pope(this,"Image creation failed");
            			return;
			}


	}

	if( whatt.type==1)
		{
			try {


				BufferedImage bi = a.getSubimage(0,0,a.getWidth(),a.getHeight()); // retrieve image
				File outputfile = new File(dirpath+"\\" +filename+"-"+pagecount+".jpg");
				ImageIO.write(bi, "jpg", outputfile);
				//saved = true;
				graph1.setColor(Color.white);
				graph1.fillRect(0,0,a.getWidth(),a.getHeight());
				repaint();
				graph1.setColor(Color.black);
                pagecount++;


				}
		 	catch (Exception e)
	    		{
				new pope(this,"Image creation failed");
	            			System.exit(1);
			}


	}

if( whatt.type==2)
		{
			try {

				graph1.setColor(Color.white);
				graph1.fillRect(0,0,a.getWidth(),a.getHeight());
				repaint();
				graph1.setColor(Color.black);


				}
		 	catch (Exception e)
	    		{
				new pope(this,"Image creation failed");
	            			System.exit(1);
			}


	}
	if( whatt.type==3)
	{
		System.exit(1);
	}
	}

}


public class PButton extends JButton
{
	int type;

public PButton(int type)
{
  	this.type = type;
}


public void paintComponent(Graphics g)

     {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D)g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	g2.setStroke(new BasicStroke(1.2f));
	if (type == 0)
	g.drawString("      Save",7,20);
	if (type == 1)
	g.drawString("     New Page",7,20);
	if (type == 2)
	g.drawString("     Clear",7,20);
	if (type == 3)
	g.drawString("      Exit",7,20);
	if (type == 4)
	g.drawString("Disconnect",7,20);


	if (hasFocus())
	{
		g.setColor(Color.gray);
		g.drawRect(4,3,getWidth()-9,getHeight()-7);
	}
        }

}


class ImageBackgroundPanel extends JPanel {
    BufferedImage image;

    ImageBackgroundPanel(BufferedImage image) {
        this.image = image;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
/*
class YesNoAction implements ActionListener {
	Dialog d;
	public YesNoAction(Dialog aDialog)
	{
	d = aDialog;
	}

		public void actionPerformed(ActionEvent e) {
		Button b = (Button)e.getSource();

		d.dispose();
	}

}
*/
public class PPanel extends JPanel
{

 	BufferedImage image;
	Graphics2D    graph;
	BufferedImage logo;


public PPanel()
{
 	setBackground(Color.white);

}

public void paintComponent(Graphics g)
{

	super.paintComponent(g);
	if (image == null || image.getWidth(null) != getWidth() ||  image.getHeight(null) != getHeight())
	{
		image = (BufferedImage)createImage(getWidth(),getHeight());
		a=image;
		graph = (Graphics2D)image.getGraphics();
		graph.setColor(Color.white);
		graph.setStroke(new BasicStroke(1.1f));

		RenderingHints renderHints =
		  new RenderingHints(RenderingHints.KEY_ANTIALIASING,
		                     RenderingHints.VALUE_ANTIALIAS_ON);
		renderHints.put(RenderingHints.KEY_RENDERING,
		                RenderingHints.VALUE_RENDER_QUALITY);

		graph.setRenderingHints(renderHints);

		graph.fillRect(0,0,getWidth(),getHeight());
	}

try
{
	logo = ImageIO.read(new File("televitallogo_small.gif"));
}
catch(Exception e)
{

}

	g.drawImage(image,0,0,null);


	graph1=graph;
	graph2=graph;

	graph1.setColor(Color.black);
	//graph1.drawImage(logo,0,0,null);
	graph1.drawString("Powered By TeleVital",image.getWidth()-150,image.getHeight()-5);
	paintCalled = true;
}

}



public static void main(String[] args)
{
	try
	{
		if(args.length != 2)
		{
			log.error("Invalid Usage");
		}



		// Set cross-platform Java L&F (also called "Metal")
		UIManager.setLookAndFeel(
			UIManager.getSystemLookAndFeelClassName());


	}
	catch (UnsupportedLookAndFeelException e)
	{
		new popm("error");
		// handle exception
	}
	catch (ClassNotFoundException e)
	{
		new popm("error");//ndle exception
	}
	catch (InstantiationException e)
	{
		new popm("error");
		// handle exception
	}
	catch (IllegalAccessException e)
	{
		new popm("error");
		// handle exception
	}

	notepad np = new notepad(args[0],args[1]);

	np.create();




}



}