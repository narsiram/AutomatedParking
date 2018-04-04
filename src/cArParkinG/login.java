package cArParkinG;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
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
import javax.swing.JTextField;
import javax.swing.border.Border;

public class login extends JFrame
{

	JPanel pnl,pnl1,pnl2;
	String id,psd,mob;
	JPasswordField pass=new JPasswordField();
	Connection con;
	JLabel login,pwd,car,car1,car2,title;
	boolean a,d;
	JButton lgn,chnge;
	JTextField login1,pwd1;
	PreparedStatement pst;
	JLabel icon,pic;
	  Container c=getContentPane();
	login()
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
		setLocation(400, 20);
		final Color blue=Color.decode("#2196F3");
		Border brd=BorderFactory.createLineBorder(blue, 1, true);
		c.setBackground(Color.decode("#e3f2fd"));
		
		
		
		pnl=new JPanel();
		pnl.setLayout(null);
		pnl.setBackground(blue);
		pnl.setBounds(0,0,600,65);
		
		add(pnl);
		
		icon=new JLabel();
		icon.setBounds(0,0,64,64);
		icon.setIcon(resize_img("parking.jpg",icon));
		pnl.add(icon);
		
		
		
		pnl1=new JPanel();
		pnl1.setBounds(300,80,250,400);
		pnl1.setBackground(Color.decode("#e3f2fd"));
		pnl1.setLayout(null);
		add(pnl1);
		
		title=new JLabel(" Parking LogIn");
		title.setBounds(95, 20,300, 40);
		title.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,35));
		title.setForeground(Color.WHITE);
		pnl.add(title);
		
		car1=new JLabel();
		car1.setBounds(0,180,280,190);
		car1.setIcon(resize_img("car2.png",car1));
		add(car1);
		
		car=new JLabel();
		car.setBounds(0, 480, 600, 95);
		car.setIcon(resize_img("pnl.png",car));
		add(car);
		
		car2=new JLabel("© 1997-2016 Parking.All rights reserved.");
		car2.setBounds(20,10,250,20);
		car2.setForeground(Color.WHITE);
		car.add(car2);
		
		login=new JLabel("UserId");
		login.setBounds(20,140,70,20);
		login.setForeground(blue);
		login.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,11));
		pnl1.add(login);
		
		pic=new JLabel();
		pic.setBounds(50, 10, 150, 140);
		pic.setIcon(resize_img1("login.png"));
		pnl1.add(pic);
		
		
		login1=new JTextField();
		login1.setBounds(20,170,200,20);
		login1.setBorder(brd);
		login1.setFont(new Font("Arial",Font.PLAIN,10));
		pnl1.add(login1);
		
		login1.addFocusListener(new FocusListener() 
		{
			
			@Override
			public void focusLost(FocusEvent e) {
				try 
				{
					pst=con.prepareStatement("select * from login ");
					ResultSet s=pst.executeQuery();
					while(s.next())
					{
						id=s.getString(1);
						psd=s.getString(2);
						mob=s.getString(3);
					}
					if((login1.getText()).equals(id))
					{
						d=true;
					}
					
					login1.setBackground(Color.WHITE);
				
				} 
				
				catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				login1.setBackground(blue);
				
			}
		});
		
				
		pwd=new JLabel("Password");
		pwd.setBounds(20,195,70,20);
		pwd.setForeground(blue);
		pwd.setFont(new Font("Arial",Font.PLAIN|Font.BOLD,11));
		pnl1.add(pwd);
		
		pass=new JPasswordField();
		pass.setBounds(20,220,200,20);
		pass.setBorder(brd);
		pass.setFont(new Font("Arial",Font.PLAIN,10));
		pnl1.add(pass);
		
		pass.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if((pass.getText()).equals(psd))
				{
					a=true;
				}
				
				pass.setBackground(Color.WHITE);
			}
			
			@Override
			public void focusGained(FocusEvent e)
			{
				if(login1.getText().length()==0)
				{
					login1.requestFocus();
					
				}
				else
					pass.setBackground(blue);
			}
		});
		
		final JLabel frgt=new JLabel("forgot password ?");
		frgt.setBounds(150,250,90,15);
		
		frgt.setFont(new Font("Arial",Font.PLAIN,10));
		frgt.setForeground(blue);
		pnl1.add(frgt);
		
		frgt.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				frgt.setForeground(Color.decode("#2196F3"));
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				frgt.setForeground(Color.RED);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				
				/*try
				{
					Thread.sleep(800);
				}
				catch(Exception ex){}*/
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				System.out.println(mob+"");
				String ss=nextpwd();
				String msg=SST_SMS.bceSunSoftSend(mob, "Your new password for login Vehicle Parking is "+ss);
				if(msg.equals("Your message is successfully sent to:91"+mob))
				{
					JOptionPane.showMessageDialog(null, "New password has been sent to your number ");
					try 
					{
						pst=con.prepareStatement("update login set pwd=?");
						pst.setString(1, ss);
						int u=pst.executeUpdate();
					
					}
					catch (SQLException e1)
					{
						e1.printStackTrace();
					}
					
				}
				else
				JOptionPane.showMessageDialog(null,"com/internet con. problem");
				
				
				
			}
		});
		
		
		
		
		lgn=new JButton("Login");
		lgn.setBackground(blue);
		lgn.setForeground(Color.WHITE);
		lgn.setBounds(25, 280, 200, 20);
		pnl1.add(lgn);
		
		lgn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				if(a==true && d==true)
				{
					
					//progressbar.main(null);
					MenuBar.main(new String []{});
					dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "Incorrect Loginid and password");
			}
		});
		
		chnge=new JButton("Change Password");
		chnge.setBackground(blue);
		chnge.setForeground(Color.WHITE);
		chnge.setBounds(25, 330, 200, 20);
		pnl1.add(chnge);
		
		chnge.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				change.main(null);
				
			}
		});
		
		
		
		
		
		setVisible(true);
		setSize(600,600);
	}
	
	String nextpwd()
	{
		SecureRandom r=new SecureRandom();
		return (new BigInteger(10,r).toString(10));
	}
	
	
	public ImageIcon resize_img (String p,JLabel j)
	{
		 ImageIcon full=new ImageIcon(p);
		Image img=full.getImage().getScaledInstance(j.getWidth(),j.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bhai=new ImageIcon(img);
		return bhai;
	
	}
	public ImageIcon resize_img1 (String p)
	{
		 ImageIcon full=new ImageIcon(p);
		Image img=full.getImage().getScaledInstance(pic.getWidth(),pic.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bhai=new ImageIcon(img);
		return bhai;
	
	}
	public static void main(String[] args)
	{
		new login();

	}

}
