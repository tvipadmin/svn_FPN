import java.awt.*;
import java.awt.event.*;

class MessageBox extends Dialog implements ActionListener
{
    java.awt.Label           label1		= new java.awt.Label();
    java.awt.Button          okButton	= new java.awt.Button("OK");

    MessageBox(Frame owner, String title, boolean modal, String msg)
    {
        super(owner, title, modal);
        setLayout(null);
        setBounds(300, 300, 440, 150);
        setVisible(false);
        label1.setAlignment(java.awt.Label.CENTER);
        label1.setText(msg);
        add(label1);
        label1.setBounds(8, 50, 422, 19);
        add(okButton);
        okButton.setBounds(186, 100, 66, 27);
        okButton.addActionListener(this);
        setTitle(title);
		setBackground(java.awt.Color.lightGray);
        //pack();
        setVisible(true);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent evt)
            {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent evt)
    {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        this.dispose();
    }
}
