package cArParkinG;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
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
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.apache.poi.util.SystemOutLogger;

import sun.util.resources.ar.CurrencyNames_ar_TN;
import cArParkinG.Entry;


public class Exit extends JFrame 
{
	Connection con;
	
	JLabel vno,flr,wheel,date,time,flr1,icon,wheel1,date1,time1,slot,slot1,rent,rent1,cdate1,cdate,ctime,ctime1;
	JButton fetch,done,print,calc;
	JLabel hr,day,bill,hr1,day1,bill1,home;
	JTextField txtv;
    JPanel pnl,pnl1,pnl2;
    Container c=getContentPane();
    PreparedStatement pst1,pst2;
    Date d,date12;
    int ds;
    Date ddd; 
    Time t,t1; 
    long different,days,hours;
    public Exit()
    {
    	setLayout(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		con=coNNection.docoNNect();
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(400, 20);
		final Color blue=Color.decode("#2196F3");
		final Border brd=BorderFactory.createLineBorder(blue, 1, true);
		
		pnl=new JPanel();
		pnl.setBackground(blue);
		pnl.setLayout(null);
		pnl.setBounds(0,0,600,65);
		add(pnl);
		
		icon=new JLabel();
		icon.setBounds(0,0,64,64);
		icon.setIcon(resize_img("parking.jpg",icon));
		pnl.add(icon);
		
		JLabel title=new JLabel("EXIT & BILLING");
		title.setBounds(170, 20, 350, 60);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial",Font.CENTER_BASELINE,30));
		pnl.add(title);
		
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
		vno=new JLabel("Vehicle No.");
		vno.setBounds(150, 80,80,30);
		add(vno);
		
		txtv=new JTextField();
		txtv.setBounds(240, 80, 120, 30);
		add(txtv);
		
				
		pnl1=new JPanel();
		pnl1.setBounds(100, 130, 400, 200);
		pnl1.setLayout(null);
		pnl1.setBackground(Color.WHITE);
		add(pnl1);
		
		flr=new JLabel("Floor");
		flr.setBounds(30, 20, 50, 20);
		pnl1.add(flr);
		
		flr1=new JLabel();
		flr1.setBounds(100, 20, 80, 20);
		flr1.setForeground(blue);
		flr1.setBorder(brd);
		pnl1.add(flr1);
		
		slot=new JLabel("Slot");
		slot.setBounds(200, 20, 50, 20);
		pnl1.add(slot);
		
		slot1=new JLabel();
		slot1.setBounds(240, 20, 80, 20);
		slot1.setBorder(brd);
		slot1.setForeground(blue);
		pnl1.add(slot1);
		
		wheel=new JLabel("Wheeler");
		wheel.setBounds(30,55,60,20);
		pnl1.add(wheel);
		
		wheel1=new JLabel();
		wheel1.setBounds(150,55,100,20);
		wheel1.setForeground(blue);
		wheel1.setBorder(brd);
		pnl1.add(wheel1);
		
		date=new JLabel("Date Of Parking");
		date.setBounds(30,90,100,20);
		pnl1.add(date);
		
		date1=new JLabel();
		date1.setBounds(150,90,100,20);
		date1.setForeground(blue);
		date1.setBorder(brd);
		pnl1.add(date1);
		
		 JLabel date2=new JLabel("(yyyy-mm-dd)");
		date2.setBounds(250,90,100,20);
		pnl1.add(date2);
		
		
		time=new JLabel("Time Of Parking");
		time.setBounds(30,125,100,20);
		pnl1.add(time);
		
		time1=new JLabel();
		time1.setForeground(blue);
		time1.setBounds(150,125,100,20);
		time1.setBorder(brd);
		pnl1.add(time1);
		
		rent=new JLabel("Fare(Rs. per hr)");
		rent.setBounds(30,160,100,20);
		pnl1.add(rent);
		
		rent1=new JLabel();
		rent1.setBounds(150,160,100,20);
		rent1.setForeground(blue);
		rent1.setBorder(brd);
		pnl1.add(rent1);
		
		
		pnl2=new JPanel();
		pnl2.setBounds(100, 340, 400, 80);
		pnl2.setLayout(null);
		pnl2.setBackground(Color.WHITE);
		add(pnl2);
		
		cdate=new JLabel("Current Date");
		cdate.setBounds(30, 10, 100, 20);
		pnl2.add(cdate);
		
		cdate1=new JLabel();
		cdate1.setForeground(blue);
		cdate1.setBounds(150, 10, 100, 20);
		cdate1.setBorder(brd);
		pnl2.add(cdate1);
		
		
		ctime=new JLabel("Current Time");
		ctime.setBounds(30,40,100,20);
		pnl2.add(ctime);
		
		ctime1=new JLabel();
		ctime1.setBounds(150,40,100,20);
		ctime1.setForeground(blue);
		ctime1.setBorder(brd);
		pnl2.add(ctime1);
		
		
		
		fetch=new JButton("Fetch");
		fetch.setBounds(370, 80, fetch.getPreferredSize().width, 30);
		fetch.setBackground(blue);
		fetch.setForeground(Color.white);
		add(fetch);
		fetch.addActionListener(new ActionListener()
		{
			
			
			public void actionPerformed(ActionEvent e)
			
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				try
				{
				
					
					pst1=con.prepareStatement("select * from entry where vno=? and status=1");
					pst1.setString(1,txtv.getText());
					ResultSet rs=pst1.executeQuery();
					while(rs.next())
					{
						int i=rs.getInt(3);
						if(i==2)
							rent1.setText("60");
						else
							rent1.setText("100");
						wheel1.setText(String.valueOf(rs.getInt(3)));
						flr1.setText(String.valueOf(rs.getInt(4)));
						slot1.setText(String.valueOf(rs.getInt(5)));
						
						SimpleDateFormat de=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
						String g=String.valueOf(rs.getDate(7));
						String g1=String.valueOf(rs.getTime(8));
						String x=g+" "+g1;
						ddd=de.parse(x);
											
						date1.setText(String.valueOf(rs.getDate(7)));
						
						time1.setText(String.valueOf(rs.getTime(8)));
						
						ds=rs.getInt(6);
					
					}
					
					if(ds==0)
						JOptionPane.showMessageDialog(null, "no vehicle exists of this number");
					
					d=new Date();
					
					cdate1.setText(DateFormat.getDateInstance().format(d));
					ctime1.setText(convert(String.valueOf(DateFormat.getTimeInstance(DateFormat.SHORT).format(d))));
					
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
				
			}
		});
		
		
		
		calc=new JButton("Calculate Bill");
		calc.setBounds(100, 430, 400, 20);
		calc.setForeground(Color.WHITE);
		calc.setBackground(blue);
		add(calc);
		
		calc.addActionListener(new ActionListener()
		{
			
			
			public void actionPerformed(ActionEvent e) 
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
					
					long l=Difference(ddd,d);
					
					calc(l);
			}
		});
		
		day=new JLabel("Days");
		day.setBounds(130,460,100,20);
		add(day);
		
		hr=new JLabel("Hours");
		hr.setBounds(130,485,100,20);
		add(hr);
		
		bill=new JLabel("Bill (in Rs)");
		bill.setFont(new Font("Times New Roman",Font.BOLD,20));
		bill.setBounds(130,510,100,20);
		add(bill);
		
		
		day1=new JLabel();
		day1.setBounds(250,460,100,20);
		day1.setBorder(brd);
		add(day1);
		
		hr1=new JLabel();
		hr1.setBorder(brd);
		hr1.setBounds(250,485,100,20);
		add(hr1);
		
		bill1=new JLabel();
		bill1.setFont(new Font("Times New Roman",Font.BOLD,20));
		bill1.setBorder(brd);
		bill1.setBounds(250,510,100,20);
		add(bill1);
		
		
		done=new JButton("Done");
		done.setBounds(180,540,80,20);
		done.setBackground(blue);
		done.setForeground(Color.WHITE);
		add(done);
		
		done.addActionListener(new ActionListener()
		{
			
			
			public void actionPerformed(ActionEvent e) 
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				try 
				{
					pst1=con.prepareStatement("insert into Bill values(?,?,?,?,1)");
					pst1.setString(1, txtv.getText());
					pst1.setString(2, date1.getText()+" "+time1.getText());
					pst1.setString(3, cdate1.getText()+" "+ctime1.getText());
					pst1.setString(4, bill1.getText());
					
					int y=pst1.executeUpdate();
					
					
					
					pst1=con.prepareStatement("update entry set status=0 where vno=?");
					pst1.setString(1,txtv.getText());
					int we=pst1.executeUpdate();
					
					pst2=con.prepareStatement("update layout set status=1 where slotno=?");
					pst2.setInt(1, Integer.parseInt(slot1.getText()));
					we=pst2.executeUpdate();
					
					String v=null;
					String mob=null;
					pst1=con.prepareStatement("select * from registration where LicenseNo=?");
					pst2=con.prepareStatement("select * from entry where vno=? and status=1");
					pst2.setString(1,txtv.getText());
					ResultSet rs=pst2.executeQuery();
					while(rs.next())
					{
						v=rs.getString(1);
					}
					pst1.setString(1, v);
					rs=pst1.executeQuery();
					while(rs.next())
					{
						mob=rs.getString(5);	
					}
					
					
					String msg=SST_SMS.bceSunSoftSend(mob, "Your Veh.No. is "+txtv.getText()+" days= "+day1.getText()+" hours "+hr1.getText()+"  Payable Amount is "+bill1);
					if(msg.equals("Your message is successfully sent to:91"+mob))
					JOptionPane.showMessageDialog(null, msg);
					else
					JOptionPane.showMessageDialog(null,"com/internet con. problem for message");

					
					JOptionPane.showMessageDialog(null, "billing successfully done");
				}
				catch (Exception e1) 
				{
					
					//e1.printStackTrace();
				}
				
				
				
			
			}
		});
		
		
		
		print=new JButton("Print Receipt");
		print.setBounds(300,540,130,20);
		print.setBackground(blue);
		print.setForeground(Color.WHITE);
		add(print);
		
		print.addActionListener(new ActionListener()
		{
			
			
			public void actionPerformed(ActionEvent e)
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				new Bill();
				
			}
		});
		
		
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
    

    
    String convert(String t)
    {
    	DateFormat f1=new SimpleDateFormat("hh:mm a");
    	Date d=null;
    	try
    	{
    		d=f1.parse(t);
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	DateFormat f2=new SimpleDateFormat("HH:mm");
    	String s=f2.format(d);
    	
    	return s;
    	
    }
    
    public long Difference(Date x,Date y)
	  {
    	 
    	if(y.after(x))
    	{
    	different=y.getTime()-x.getTime();
    	   	   	
    	long sec=1000;
		long m=sec*60;
		long h=m*60;
		long d=h*24;

		days=different/d-305;
		day1.setText(String.valueOf(days));
		different=different%d;
		

		hours=different/h;
		hr1.setText(String.valueOf(hours));
		different=different%h;
		

		long min=different/m;
		different=different%m;
		
		if(min>40)
			hours=hours+1;

		long sc=different/sec;
    	}
		
	return (hours+(days*24));
}
    
    
    void calc(long l)
    {
    	int f=Integer.parseInt(rent1.getText());
    	long u=l*f;
    	
    	bill1.setText(String.valueOf(u));
    }
	

	public static void main(String[] args) 
	{
		new Exit();

	}

}
