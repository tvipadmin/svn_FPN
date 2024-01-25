package hctpen;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.border.*;
import java.awt.Component;

public class popm extends JFrame{
JLabel l1=new JLabel("test");
String disp="test";
SpringLayout layout = new SpringLayout();

public popm(String a)
{

	l1.setText(a);
	Container c=getContentPane();
	c.setLayout(null);
	c.add(l1);
	setBounds(400,400,400,100);
	Insets insets = c.getInsets();
	Dimension size = l1.getPreferredSize();
	l1.setBounds(100 + insets.right, 25+ insets.top,size.width, size.height);
	setResizable(false);
	setVisible(true);
	

}





}