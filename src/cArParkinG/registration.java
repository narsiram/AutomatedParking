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
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.core.*;
//import org.opencv.highgui.Highgui;        
//import org.opencv.highgui.VideoCapture;

import com.sun.glass.events.KeyEvent;



public class registration extends JFrame
{
	Connection con;
	final JFileChooser f=new JFileChooser();
	JLabel lno,name,address,city,mobile,pic,title,image,icon;
	JButton New,sav,update,cncl,close,browse,search;
	JTextField txtl,txtn,txtc,txtm,txtp;
    JTextArea ad;
    JPanel pnl,pnl2,pnl12;
    Container c=getContentPane();
    PreparedStatement pst1,pst2;
    
	registration()
	{
		super("REGISTRATION");
		setLayout(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		con=coNNection.docoNNect();
		
		
		final Border brd1=BorderFactory.createLineBorder(Color.decode("#2196F3"), 1, true);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(400, 20);
		//c.setBackground(Color.WHITE);
		
		
		pnl=new JPanel();
		pnl.setLayout(null);
		
		pnl12=new JPanel();
		pnl12.setBounds(0,0,600,100);
		pnl12.setBackground(Color.decode("#2196F3"));
		pnl12.setForeground(Color.WHITE);
		//pnl12.setBorder(brd1);
		add(pnl12);
		
		icon=new JLabel();
		icon.setBounds(0,0,70,100);
		icon.setIcon(resize_img("parking.jpg",icon));
		pnl12.add(icon);
		
		 final JLabel home=new JLabel("Home");
		home.setBounds(550, 105, 20,20);
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
				home.setBorder(brd1);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				// TODO Auto-generated method stub
				MenuBar.main(null);
				dispose();
			}
		});
		
		
		pnl.setBounds(50,180,500,300);
		pnl.setForeground(Color.decode("#2196F3"));
		pnl.setBackground(Color.WHITE);
		pnl.setBorder(brd1);
		add(pnl);
		
		
		pnl2=new JPanel();
		pnl2.setLayout(null);
	
		pnl2.setBounds(50,500,500,60);
		pnl12.setLayout(null);
	    pnl2.setBackground(Color.WHITE);
		pnl2.setBorder(brd1);
		add(pnl2);
		
		title=new JLabel("REGISTRATION FORM");
		title.setBounds(150, 20, 350, 60);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial",Font.CENTER_BASELINE,30));
		pnl12.add(title);
		
		lno=new JLabel("license no.");
		lno.setToolTipText("Please enter your license number here");
		lno.setBounds(100, 120, 200, 20);
		lno.setFont(new Font("Times New Roman",Font.PLAIN,20));
		add(lno);
		
		name=new JLabel("NAME");
		name.setBounds(10, 30, 80, 20);
		name.setFont(new Font("Times New Roman",Font.PLAIN,18));
		pnl.add(name);
		
		address=new JLabel("ADDRESS");
		address.setBounds(10, 70, 120, 20);
		address.setFont(new Font("Times New Roman",Font.PLAIN,18));
		pnl.add(address);
		
		city=new JLabel("CITY");
		city.setBounds(10, 180, 80, 20);
		city.setFont(new Font("Times New Roman",Font.PLAIN,18));
		pnl.add(city);
		
		mobile=new JLabel("MOBILE NO.");
		mobile.setBounds(10, 220, 130, 20);
		mobile.setFont(new Font("Times New Roman",Font.PLAIN,18));
		pnl.add(mobile);
		
		pic=new JLabel("PIC PATH");
		pic.setBounds(10, 260, 120, 20);
		pic.setFont(new Font("Times New Roman",Font.PLAIN,18));
		pnl.add(pic);
		
		
//		JLabel cam = new JLabel("cam");
//		cam.setBounds(360, 200, 50, 20);
//		cam.addMouseListener(new MouseListener() {
//				
//				@Override
//				public void mouseReleased(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mousePressed(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mouseExited(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mouseEntered(MouseEvent e) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void mouseClicked(MouseEvent e)
//				{
//
//					System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//					VideoCapture camera = new VideoCapture(0);
//					if(!camera.isOpened())
//					{
//						System.out.println("No Camera");
//					}
//					else
//					{
//						Mat frame = new Mat();
//						VideoWriter vwrite = new VideoWriter();
//						while(true)
//						{
//							if(camera.read(frame))
//							{
//								System.out.println("Frame Obtained");
//								System.out.println("Image width : "+frame.width()+"\nHeight : "+frame.height());
//								vwrite.write(frame);
//								Imgcodecs.imwrite("camera.jpg", frame);
//								//path="camera.jpg";
//								//pc.setIcon(Resize(path));
//								System.out.println("OK");
//								break;
//							}
//						}
//					}
//					camera.release();
//				}
//		    });  
//		pnl.add(cam);
		
		
		
		image=new JLabel("");
		image.setBounds(360, 30, 100, 100);
		image.setIcon(resize_img("pic2.jpg",image));
		image.setBorder(brd1);
		pnl.add(image);
		
		txtl=new JTextField();
		txtl.setBounds(220, 120, 150, 20);
		txtl.setForeground(Color.decode("#2196F3"));
		txtl.setFont(new Font("Times New Roman",Font.PLAIN,12));
		add(txtl);
		
		txtl.addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent e) 
			{
				if(txtl.getText().length()==0)
					txtl.requestFocus();
					
			}
			
			public void focusGained(FocusEvent e) 
			{
			}
		});
	
		
		txtn=new JTextField();
		txtn.setBounds(150, 30, 150, 20);
		txtn.setForeground(Color.decode("#2196F3"));
		txtn.setFont(new Font("Times New Roman",Font.PLAIN,12));
		pnl.add(txtn);
		
		txtn.addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent e) 
			{
						
			}
			
			public void focusGained(FocusEvent e) 
			{
				if(txtl.getText().length()==0)
				{
					txtl.requestFocus();
					
				}			
			}
		});
		
		ad=new JTextArea("Fill address here",5,50);
		JScrollPane scroll=new JScrollPane(ad);
		scroll.setBounds(150,70,160,90);
		ad.setSelectedTextColor(Color.BLACK);
		//ad.getSelectedText();
		ad.setForeground(Color.decode("#2196F3"));
		ad.setBorder(BorderFactory.createLineBorder(Color.black));
		pnl.add(scroll);
		
		txtc=new JTextField();
		txtc.setBounds(150, 180, 150, 20);
		txtc.setForeground(Color.decode("#2196F3"));
		txtc.setFont(new Font("Times New Roman",Font.PLAIN,12));
		pnl.add(txtc);
		
		txtm=new JTextField();
		txtm.setBounds(150, 220, 150, 20);
		txtm.setForeground(Color.decode("#2196F3"));
		txtm.setFont(new Font("Times New Roman",Font.PLAIN,12));
		pnl.add(txtm);
		txtm.addFocusListener(new FocusListener(){
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			String mobile="^[789]\\d{9}$";

			boolean b=txtm.getText().matches(mobile);
			if(b==false)
			{
			JOptionPane.showMessageDialog(null, "Invalid Mobile no");
			txtm.requestFocusInWindow();
			}
			//else
			//lerr.setText("");
			//txtv.setBackground(Color.white);
		}	
		
		public void focusGained(FocusEvent e) {}
		});

			
		txtp=new JTextField();
		txtp.setBounds(150, 260, 150, 20);
		txtp.setForeground(Color.decode("#2196F3"));
		txtp.setEditable(false);
		txtp.setFont(new Font("Times New Roman",Font.PLAIN,12));
		pnl.add(txtp);
		
		search=new JButton("Search");
		search.setForeground(Color.WHITE);
		search.setBackground(Color.decode("#2196F3"));
		search.setBounds(400, 120, search.getPreferredSize().width,20);
	    search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
			    if(txtl.getText().length()!=0)
			    {
			    	try
			    	{
			    		pst1=con.prepareStatement("SELECT * FROM registration where LicenseNo=?");
					    pst1.setString(1,txtl.getText());
					    ResultSet rs=pst1.executeQuery();
					    while(rs.next())
					    {
					    	txtl.setText(rs.getString(1));
					    	txtn.setText(rs.getString(2));
					    	ad.setText(rs.getString(3));
					    	txtc.setText(rs.getString(4));
					    	txtm.setText(rs.getString(5));
					    	txtp.setText(rs.getString(6));
					    	image.setIcon(resize_img(rs.getString(6),image));
					    	
					    }
			    	}
			    	
			    	catch (SQLException e1) {
					
						e1.printStackTrace();
					}
			    }
			    else
			    {
			    	txtl.setRequestFocusEnabled(true);
			    }
			
			}
		});
	    
	    add(search);
		browse=new JButton("Browse");
		browse.setForeground(Color.WHITE);
		browse.setBackground(Color.decode("#2196F3"));
		browse.setFont(new Font("Arial",Font.PLAIN,15));
	    browse.setBounds(360, 170,browse.getPreferredSize().width,20);
	    browse.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
			    
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				int r=f.showSaveDialog(null);
                  if(r==JFileChooser.APPROVE_OPTION)
                  {
                	  File sfile=f.getSelectedFile();
                	   String path=sfile.getAbsolutePath();
                	 txtp.setText(path);
                 	image.setIcon(resize_img(path,image));
                  }		
			
			}
		});
	    
	    pnl.add(browse);

	    New=new JButton("New");
	    New.setMnemonic(KeyEvent.VK_N);
	    New.setForeground(Color.WHITE);
		New.setBackground(Color.decode("#2196F3"));
	    New.setFont(new Font("Arial",Font.PLAIN,15));
	    New.setBounds(50, 20,New.getPreferredSize().width,20);
	    New.addActionListener(new ActionListener()
	    {
			
			public void actionPerformed(ActionEvent e) 
			{
				
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				
				txtn.setText("");
				txtl.setText("");
				txtc.setText("");
				txtm.setText("");
				txtp.setText("");
				ad.setText("Fill address here");
				image.setIcon(resize_img("pic2.jpg",image));
				txtl.requestFocus();
			}
		});
	    pnl2.add(New);
	    
	    
	    sav=new JButton("Save");
	    sav.setForeground(Color.WHITE);
		sav.setBackground(Color.decode("#2196F3"));
	    sav.setFont(new Font("Arial",Font.PLAIN,15));
	    sav.setMnemonic(KeyEvent.VK_S);
	    sav.setBounds(120, 20,sav.getPreferredSize().width,20);
	    sav.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				
				try 
				{
					
					if(txtl.getText().length()!=0)
					{
						if(txtn.getText().length()!=0){
					pst1=con.prepareStatement("insert into registration values(?,?,?,?,?,?,CURRENT_DATE,CURRENT_TIME)");
                    pst1.setString(1, txtl.getText());
                    pst1.setString(2, txtn.getText());
                    pst1.setString(3, ad.getText());
                    pst1.setString(4, txtc.getText());
                    pst1.setString(5, txtm.getText());
                    pst1.setString(6, txtp.getText());
                    int i=pst1.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, " records saved");
					}
						else
						{
							JOptionPane.showMessageDialog(null, "fill your all details please..");
							txtn.requestFocus();	
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "fill your all details please..");
							txtl.requestFocus();	
					}
				} 
				catch (SQLException e1) 
				{
					
					e1.printStackTrace();
				}
			}
		});
	    pnl2.add(sav);
	    
	    
	    update=new JButton("Update");
	    update.setMnemonic(KeyEvent.VK_U);
	    update.setForeground(Color.WHITE);
		update.setBackground(Color.decode("#2196F3"));
	    update.setFont(new Font("Arial",Font.PLAIN,15));
	    update.setBounds(195, 20,update.getPreferredSize().width,20);
	    update.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
                
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				
				if(txtl.getText().length()!=0)
                  {
                	  try 
                	  {
                				
                		  pst2=con.prepareStatement("update registration set caddress=?,Mobile=?,city=?,picpath=? where licenseNo=?");
                		  pst2.setString(5, txtl.getText());
                		  pst2.setString(1, ad.getText());
                		  pst2.setString(3, txtc.getText());
                		  pst2.setString(2, txtm.getText());
                		  pst2.setString(4, txtp.getText());
                		  
                		   int i=pst2.executeUpdate();
                    
                		  JOptionPane.showMessageDialog(null, " records updated");
                			
                    
                	  } 
                	  catch (SQLException e1) 
                	  {
					
                		  e1.printStackTrace();
                	  }
			}
                  else
                  {
                	  JOptionPane.showMessageDialog(null, "Must specify the license number");
                  }
			}
		});
	    pnl2.add(update);
	    
	    
	    cncl=new JButton("Cancel");
	    cncl.setForeground(Color.WHITE);
		cncl.setBackground(Color.decode("#2196F3"));
	    cncl.setFont(new Font("Arial",Font.PLAIN,15));
		cncl.setBounds(280, 20,cncl.getPreferredSize().width,20);
	    cncl.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				try 
				{
					pst1=con.prepareStatement("delete from registration where LicenseNo=?");
					pst1.setString(1, txtl.getText());
					int i=pst1.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registration have been Cancelled");
					txtn.setText("");
					txtl.setText("");
					txtc.setText("");
					txtm.setText("");
					txtp.setText("");
					ad.setText("");
					image.setIcon(resize_img("pic2.jpg",image));				
				} 
				
				
				catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
	    pnl2.add(cncl);
	    
	    
	    close=new JButton("Close");
	    close.setMnemonic(KeyEvent.VK_C);
		close.setBackground(Color.WHITE);
	    close.setFont(new Font("Arial",Font.PLAIN,15));
	    close.setBounds(370, 20,close.getPreferredSize().width,20);
	    close.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				
				dispose();
				MenuBar.main(null);
			}
			
			});
	    pnl2.add(close);
	    
	   
	    
	
	
		setVisible(true);
		
		setSize(600,650);
	
	
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
		Image img=full.getImage().getScaledInstance(icon.getWidth(),icon.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bhai=new ImageIcon(img);
		return bhai;
	
	}
	
	
	public static void main(String[] args) 
	{
		
		registration r=new registration();
	}

}
