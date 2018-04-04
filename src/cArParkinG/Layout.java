package cArParkinG;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
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


public class Layout extends JFrame
{

	Connection con;
	JLabel flr,capacity,title,home;
	JRadioButton one,two;
	JTextField txtc;
	JComboBox fl;
	JPanel pnl1,pnl3;
	JButton sav,cls,reset;
	int i,j;
	int indx,mn;
	int slot;
	PreparedStatement pst;
	
	Layout()
	{
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		con=coNNection.docoNNect();
		
		
		final Color blue=Color.decode("#2196F3");
		final Border brd=BorderFactory.createLineBorder(blue, 1, true);
		
		pnl1=new JPanel();
	
		pnl1.setBackground(blue);
		pnl1.setLayout(null);
		pnl1.setBounds(0,0,600,65);
		add(pnl1);	
		
		pnl3=new JPanel();
		pnl3.setLayout(null);
		pnl3.setBackground(Color.WHITE);
		pnl3.setBorder(brd);
		pnl3.setBounds(70,100,450,375);
		add(pnl3);
		
		
		title=new JLabel("PARKING LAYOUT");
		title.setForeground(Color.WHITE);
		title.setBounds(110, 5, 350, 50);
		title.setFont(new Font("Times New Roman",Font.PLAIN,35));
		pnl1.add(title);
		
		JLabel icon=new JLabel();
		icon.setBounds(0,0,64,64);
		icon.setIcon(resize_img("parking.jpg",icon));
		pnl1.add(icon);
		
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
		
		flr=new JLabel("Floor");
		flr.setBounds(60,50,100,30);
		//flr.setBorder(brd);
		flr.setFont(new Font("Times New Roman",Font.PLAIN|Font.BOLD,20));
		pnl3.add(flr);
		
		capacity=new JLabel("Capacity");
		capacity.setBounds(60,120,150,30);
		//capacity.setBorder(brd);
		capacity.setFont(new Font("Times New Roman",Font.PLAIN|Font.BOLD,20));
		pnl3.add(capacity);
		
		String []a={"select","0","1","2"};
		fl=new JComboBox(a);
		fl.setBounds(180, 50,100,30);
		fl.setForeground(blue);
	//	fl.setFont(new Font("Times New Roman",Font.PLAIN,18));
		pnl3.add(fl);
		
		
		txtc=new JTextField();
		txtc.setBounds(180, 120, 100, 30);
		txtc.setForeground(blue);
//		txtc.get
		txtc.setFont(new Font("Times New Roman",Font.PLAIN,18));
		pnl3.add(txtc);
		
		
		
		one=new JRadioButton("2 wheeler");
		two=new JRadioButton("4 wheeler");
	    one.setBounds(90, 200, 100, 30);
	    pnl3.add(one);
		
	    two.setBounds(210,200,100,30);
	    pnl3.add(two);
	    
	    
	    
	    sav=new JButton("save");
	    sav.setBounds(130, 300, sav.getPreferredSize().width, 30);
	    sav.setBackground(blue);
	    sav.setForeground(Color.WHITE);
	    pnl3.add(sav);
	    
	    sav.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e)
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				try 
				{
					getSlot();
					if(slot==1)
					{
						int r=Integer.parseInt(String.valueOf(fl.getSelectedItem()));
						
						if(r==0)
							slot=Integer.parseInt(String.valueOf(fl.getSelectedItem())+"0"+slot);
						else
							if(r==1)
								slot=Integer.parseInt(String.valueOf(fl.getSelectedItem())+"0"+slot);
							else
								slot=Integer.parseInt(String.valueOf(fl.getSelectedItem())+"0"+slot);
							
					}
					indx=indx+1;
					indx=Integer.parseInt(String.valueOf(fl.getSelectedItem()));
					pst=con.prepareStatement("insert into layout values(?,?,?,?)");
					int r=checker();
					if(r!=0)
					{
					for(i=1 ;i<=Integer.parseInt(txtc.getText());i++,slot++)
					{
					pst.setInt(1,indx);
						//pst.setInt(2,Integer.parseInt(indx+"0"+slot));
					
						pst.setInt(2,slot);
					pst.setInt(3, r);
					pst.setInt(4, 1);
					int w=pst.executeUpdate();
					}
					
					JOptionPane.showMessageDialog(null, "your data is safely saved Sir..");
					}
				
				
				}
					catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	    
	    
	    cls=new JButton("close");
	    cls.setBackground(blue);
	    cls.setForeground(Color.WHITE);
	    cls.setBounds(220, 300,cls.getPreferredSize().width, 30);
	    pnl3.add(cls);
	    cls.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				dispose();
				
			}
		});
	    
	    reset=new JButton("reset");
	    reset.setBackground(blue);
	    reset.setForeground(Color.WHITE);
	    reset.setBounds(310, 300,reset.getPreferredSize().width, 30);
	    pnl3.add(reset);
	    
	    reset.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				try 
				{   
					int t=JOptionPane.showConfirmDialog(null, "This will delete your all previous data.\nDo you want to continue..");
					if(t==0)
					{

						pst=con.prepareStatement("delete from layout where floor=?");
						pst.setInt(1, Integer.parseInt(String.valueOf(fl.getSelectedItem())));
						int g=pst.executeUpdate();
						JOptionPane.showMessageDialog(null, g+" records deleted");
					}
				} 
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	    
	    
	    
	    
		
		
		setLocation(400,20);
		setVisible(true);
		setSize(600,600);
		
		
	}
    public ImageIcon resize_img (String p,JLabel j)
	{
		 ImageIcon full=new ImageIcon(p);
		Image img=full.getImage().getScaledInstance(j.getWidth(),j.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bhai=new ImageIcon(img);
		return bhai;
	
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
	
	void getSlot()
	{
		slot=1;
		boolean b=false;
		try
		{
			
			pst=con.prepareStatement("select max(slotno) as 'max' from layout where floor=?");
			pst.setInt(1, Integer.parseInt(String.valueOf(fl.getSelectedItem())));
			ResultSet rs1=pst.executeQuery();
			while(rs1.next())
			{
				b=true;
				slot=rs1.getInt("max");
			}
			
			
			 
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		if(b==true)
			slot=slot+1;
		
		
			
	}
	
	
	
	
	
	public static void main(String[] args) {
          Layout l=new Layout();
	}

}
