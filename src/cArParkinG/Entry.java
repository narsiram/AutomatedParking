package cArParkinG;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import jdk.management.resource.internal.inst.SimpleAsynchronousFileChannelImplRMHooks;

public class Entry extends JFrame 
{

	
	DateFormat df=new SimpleDateFormat("yyyy-mm-dd");
	Date n=new Date();
	Date f;
	Connection con;
	JLabel lic,vno,flr,title,slots,home,icon;
	JRadioButton one,two;
	JTextField txtv;
	JComboBox fl,slt,lc;
	JPanel pnl1,pnl2;
	JButton sav,cls;
	int i,j;
	PreparedStatement pst,pst1;
	
	Entry()
	{
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		con=coNNection.docoNNect();
		
		
		final Color blue=Color.decode("#2196F3");
		final Border brd=BorderFactory.createLineBorder(blue, 1, true);
		
		pnl1=new JPanel();
		pnl1.setLayout(null);
		pnl1.setBackground(blue);
		pnl1.setBounds(0,0,600,65);
		add(pnl1);	
		
		icon=new JLabel();
		icon.setBounds(0,0,64,64);
		icon.setIcon(resize_img("parking.jpg",icon));
		pnl1.add(icon);
		
		
		pnl2=new JPanel();
		pnl2.setLayout(null);
		pnl2.setBackground(Color.WHITE);
		//pnl2.setBorder(brd);
		pnl2.setBounds(70,100,450,375);
		add(pnl2);
		
		
		home=new JLabel("Home");
		home.setBounds(550, 70, 20,20);
		home.setIcon(resize_img("home.png",home));
		add(home);
		
		home.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				home.setBorder(null);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				home.setBorder(brd);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				MenuBar.main(null);
				dispose();
			}
		});
		
		title=new JLabel("VEHICLE ENTRY");
		title.setForeground(Color.WHITE);
		title.setBounds(180, 5, 350, 50);
		title.setFont(new Font("Times New Roman",Font.PLAIN,35));
		pnl1.add(title);
		
		lic=new JLabel("License No");
		lic.setBounds(20, 20, 100, 30);
		lic.setFont(new Font("Times New Roman",Font.PLAIN,20));
		pnl2.add(lic);
		
		vno=new JLabel("Vehicle No");
		vno.setBounds(20, 80, 100, 30);
		vno.setFont(new Font("Times New Roman",Font.PLAIN,20));
		pnl2.add(vno);
		
		
		
		one=new JRadioButton("2 wheeler");
		two=new JRadioButton("4 wheeler");
		ButtonGroup grp=new ButtonGroup();
		grp.add(one);
		grp.add(two);
		
		one.setBounds(150, 140, 100, 30);
	   

	    two.setBounds(270,140,100,30);
	    pnl2.add(one);
	    pnl2.add(two);
	    
	    one.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				fl.removeAllItems();
				slt.removeAllItems();
				try 
				{
					i=0;
					pst=con.prepareStatement("select * from layout where wheeler=2 and status=1");
					
					ResultSet res=pst.executeQuery();
					while(res.next())
					{
						if(i<1)
						{
							int w=res.getInt(1);
							fl.addItem(String.valueOf(w));
						}
						int w1=res.getInt(2);
						slt.addItem(String.valueOf(w1));
						i++;
					}
				
				} 
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
	    
	    two.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				fl.removeAllItems();
				slt.removeAllItems();
				try 
				{
					i=0;
					pst=con.prepareStatement("select * from layout where wheeler=4 and status=1");
					ResultSet res=pst.executeQuery();
					while(res.next())
					{
						int w=res.getInt(2);
						if(i<1)
						{
							int w1=res.getInt(1);
							fl.addItem(String.valueOf(w1));
						}
						slt.addItem(String.valueOf(w));
						i++;
					}
				
				} 
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	    
		flr=new JLabel("Floor");
		flr.setBounds(40, 200, 70, 30);
		flr.setFont(new Font("Times New Roman",Font.PLAIN,20));
		pnl2.add(flr);
		
		slots=new JLabel("Slots");
		slots.setBounds(250, 200, 50, 30);
		slots.setFont(new Font("Times New Roman",Font.PLAIN,16));
		pnl2.add(slots);
		
		
		fl=new JComboBox();
		fl.setBounds(120,200,70,30);
		fl.setForeground(blue);
		fl.setFont(new Font("Times New Roman",Font.PLAIN,12));
		pnl2.add(fl);
		fl.addItem("--select--");
		
		
		slt=new JComboBox();
		slt.setBounds(330,200,70,30);
		slt.setForeground(blue);
		slt.setFont(new Font("Times New Roman",Font.PLAIN,12));
		pnl2.add(slt);
		slt.addItem("--select--");
		
		lc=new JComboBox();
		lc.setBounds(150,20,180,30);
		lc.setForeground(blue);
		lc.setFont(new Font("Times New Roman",Font.PLAIN,16));
		pnl2.add(lc);
		lc.addItem("--select--");
		getLic();
		
		txtv=new JTextField();
		txtv.setForeground(blue);
		txtv.setBounds(150, 80, 180, 30);
		txtv.setFont(new Font("Times New Roman",Font.PLAIN,20));
		pnl2.add(txtv);
		
		sav=new JButton("save");
		sav.setBounds(150,300,sav.getPreferredSize().width,30);
		sav.setBackground(blue);
		sav.setForeground(Color.WHITE);
		pnl2.add(sav);
		
		sav.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				try {
					
					int t=checker();
					pst=con.prepareStatement("insert into Entry values(?,?,?,?,?,?,CURRENT_DATE,CURRENT_TIME)");
					pst.setString(1,String.valueOf(lc.getSelectedItem()));
					pst.setString(2, txtv.getText());
					pst.setInt(3,t);
					pst.setInt(4, Integer.parseInt((String.valueOf(fl.getSelectedItem()))));
					pst.setInt(5, Integer.parseInt((String.valueOf(slt.getSelectedItem()))));
					pst.setInt(6, 1);
					
					/*try 
					{
						
					f = df.parse(DateFormat.getDateInstance(DateFormat.SHORT).format(n));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}*/
					//java.sql.Date n1=new java.sql.Date(n.getTime());
					//pst.setDate(7,n1);
				/*	try {
						f = df.parse(DateFormat.getTimeInstance(DateFormat.SHORT).format(n));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}*/
					//java.sql.Time n2=new java.sql.Time(n.getTime());
					//pst.setTime(8,n2);
					
					
					int ri=pst.executeUpdate();
					
					
				} 
				
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				try {
					Object s[]=slt.getSelectedObjects();
					String ss="";
					for(int i=0;i<s.length;i++)
					{
						ss+=s[i].toString();
					}
					pst1=con.prepareStatement("update layout set status=? where slotno=?");
					pst1.setInt(1, 0);
					pst1.setInt(2, Integer.parseInt(ss));
					int u=pst1.executeUpdate();
				
					JOptionPane.showMessageDialog(null, "Entry is successfull and table updated");
				
				
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				
				
			}
		});
		
		 cls=new JButton("close");
		    cls.setBackground(blue);
		    cls.setForeground(Color.WHITE);
		    cls.setBounds(250, 300,cls.getPreferredSize().width, 30);
		    pnl2.add(cls);
		    cls.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					URL urlClick = change.class.getResource("bg.wav");
					AudioClip click = Applet.newAudioClip(urlClick);
					click.play();
					
					dispose();
					
				}
			});
		
		
		setVisible(true);
		setSize(600,600);
		setLocation(400,20);
		
	}
	void getLic()
	{
		try 
		{
			pst=con.prepareStatement("select LicenseNo from registration");
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				lc.addItem(rs.getString(1));
			}
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	int checker()
	   {
	    if(one.isSelected())
		{
			if(two.isSelected())
			
			{
			  JOptionPane.showMessageDialog(null,"Please select any one");
			 //continue v;
			}
			if(two.isSelected()==false)
			{
				j=2;
			}
		}
		else
		if(one.isSelected()==false)
			{
			if(two.isSelected())
			j=4;
			}
		else
			{
			JOptionPane.showMessageDialog(null,"Please select one");
			}
	    return j;
	   }
	   public ImageIcon resize_img (String p,JLabel j)
		{
			 ImageIcon full=new ImageIcon(p);
			Image img=full.getImage().getScaledInstance(j.getWidth(),j.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon bhai=new ImageIcon(img);
			return bhai;
		
		}
		
	
	
	
	public static void main(String[] args) 
	{
		Entry e=new Entry();
	}

}
