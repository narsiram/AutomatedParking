package cArParkinG;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.border.Border;



public class change extends JFrame 
{
	JPanel pnl,pnl1;
	JPasswordField o,ne,c; 
	JLabel icon,old,n,confirm,err,err1;
	JButton confrm;
	Connection con;
	PreparedStatement pst;
	String p,mob;
	change()
	{
		setLayout(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		con=coNNection.docoNNect();
	/*	if(con!=null)
			JOptionPane.showMessageDialog(null, "Connected to Server");
		else
			JOptionPane.showMessageDialog(null, "Connected Failed");
		*/
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(500, 100);
		final Color blue=Color.decode("#2196F3");
		Border brd=BorderFactory.createLineBorder(blue, 1, true);
		getContentPane().setBackground(Color.decode("#e3f2fd"));
		
		
		
		pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBackground(blue);
		pnl.setBounds(0,0,600,65);
		
		add(pnl);
		
		icon=new JLabel();
		icon.setBounds(0,0,64,64);
		icon.setIcon(resize_img("parking.jpg"));
		pnl.add(icon);
		
		JLabel title=new JLabel("Change Password");
		title.setBounds(120, 20, 350, 60);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial",Font.CENTER_BASELINE,20));
		pnl.add(title);
		
		
		old=new JLabel("Old Password");
		old.setBounds(110,90,100,30);
		old.setForeground(blue);
		old.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,12));
		add(old);
			
		n=new JLabel("New Password");
		n.setBounds(110,140,100,30);
		n.setForeground(blue);
		n.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,12));
		add(n);
		
		confirm=new JLabel("Confirm Password");
		confirm.setBounds(110,190,120,30);
		confirm.setForeground(blue);
		confirm.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,12));
		add(confirm);
		
		
		o=new JPasswordField();
		o.setBounds(110,120,120,25);
		o.setBorder(brd);
		o.setFont(new Font("Arial",Font.PLAIN,15));
		add(o);
		
		
		ne=new JPasswordField();
		ne.setBounds(110,170,120,25);
		ne.setBorder(brd);
		ne.setFont(new Font("Arial",Font.PLAIN,15));
		add(ne);
		
		c=new JPasswordField();
		c.setBounds(110,220,120,25);
		c.setBorder(brd);
		c.setFont(new Font("Arial",Font.PLAIN,15));
		add(c);
		
		c.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(ne.getText().equals(c.getText()))
				{}
				else
					err.setText("??");
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		err=new JLabel();
		err.setBounds(250, 220, 50, 25);
		err.setFont(new Font("Arial",Font.PLAIN,12));
		err.setForeground(Color.RED);
		add(err);
		
		err1=new JLabel();
		err1.setBounds(250, 120, 50, 25);
		err1.setFont(new Font("Arial",Font.PLAIN,12));
		err1.setForeground(Color.RED);
		add(err1);
		
		
		confrm=new JButton("Confirm");
		confrm.setBounds(115,280,110,30);
		confrm.setBackground(blue);
		confrm.setForeground(Color.WHITE);
		add(confrm);
		confrm.addActionListener(new ActionListener() 
		{
			
			
			public void actionPerformed(ActionEvent e) 
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				
				try 
				{
					pst=con.prepareStatement("select * from login ");
					ResultSet s=pst.executeQuery();
					while(s.next())
					{
						//id=s.getString(1);
						p=s.getString(2);
						mob=s.getString(3);
					}
					String r=o.getText();
					if(r.equals(p))
					{
						String h=c.getText();
						String msg=SST_SMS.bceSunSoftSend(mob, "Your new password for login Vehicle Parking is "+h);
						if(msg.equals("Your message is successfully sent to:91"+mob))
						{
							JOptionPane.showMessageDialog(null, "New password has been sent to your number ");
							try 
							{
								pst=con.prepareStatement("update login set pwd=?");
								pst.setString(1, h);
								int u=pst.executeUpdate();
								new login();
								dispose();
							
							}
							catch (SQLException e1)
							{
								e1.printStackTrace();
							}
							
						}
						else
						JOptionPane.showMessageDialog(null,"Internet connection error");
					}
					
					else
						{
							err1.setText("??");
							
						}
				
					
						
					
					
				}
				catch(Exception ev){}
				
			}
		});
		
		
		
		
		
		setVisible(true);
		setSize(400,400);
	}
	
	
	
	
	
	
	 public ImageIcon resize_img (String p)
		{
			 ImageIcon full=new ImageIcon(p);
			Image img=full.getImage().getScaledInstance(icon.getWidth(),icon.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon bhai=new ImageIcon(img);
			return bhai;
		
		}
	 public static void main(String []s)
	 {
		 new change();
	 }
	    
}
