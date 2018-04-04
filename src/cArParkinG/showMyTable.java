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
import java.awt.print.PrinterException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class showMyTable extends JFrame
{
	JPanel pnl12,pnl1;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	JLabel icon;
	JTable table;
	JButton loadall,print;
	showMyTable()
	{
		
		setLayout(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		con=coNNection.docoNNect();
		
		
		final Border brd1=BorderFactory.createLineBorder(Color.decode("#2196F3"), 1, true);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocation(400, 20);
		//c.setBackground(Color.WHITE);
		
		pnl1=new JPanel();
		pnl1.setBounds(0,0,600,65);
		pnl1.setBackground(Color.decode("#2196F3"));
		pnl1.setLayout(null);
		add(pnl1);

		icon=new JLabel();
		icon.setBounds(0,0,64,64);
		icon.setIcon(resize_img("parking.jpg",icon));
		pnl1.add(icon);
		
		JLabel title=new JLabel("Detail of Registrations");
		title.setBounds(150, 20, 350, 60);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial",Font.CENTER_BASELINE,30));
		pnl1.add(title);

		
		final JLabel home=new JLabel("Home");
		home.setBounds(560, 70, 20,20);
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
				// TODO Auto-generated method stub
				
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				MenuBar.main(null);
				dispose();
			}
		});
		
		
		pnl12=new JPanel();
		pnl12.setBounds(50,90,500,100);
		pnl12.setBorder(brd1);
		pnl12.setLayout(null);
		add(pnl12);
		
		
		loadall=new JButton("All Registers");
		loadall.setBackground(Color.decode("#2196F3"));
		loadall.setForeground(Color.WHITE);
		loadall.setBounds(50, 30, 200, 50);
		pnl12.add( loadall);
		loadall.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				filltable();
				
			}
		});
		
		
		print=new JButton("Print");
		print.setBackground(Color.decode("#2196F3"));
		print.setForeground(Color.WHITE);
		print.setBounds(250, 30, 200, 50);
		pnl12.add( print);
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				// TODO Auto-generated method stub
				try {
					table.print();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	
		
		
		
		setSize(600,600);
		setVisible(true);
		
		
	}
	
	
	void filltable()
	{
	try
	{
		String sql = "Select * from registration";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		ResultSetMetaData md = rs.getMetaData();
		Vector<String> columnNames = new Vector<String>();
		int columns = md.getColumnCount();
		for (int i = 1; i <= columns; i++)
		{
		columnNames.addElement( md.getColumnName(i) );
		}
		//--------------------------------------------------
		Vector<Object> data = new Vector<Object>();
		while (rs.next())
		{
		Vector<Object> row = new Vector<Object>(columns);
		for (int i = 1; i <= columns; i++)
		{
		row.addElement( rs.getObject(i) );
		}
		data.addElement(row);
		}
		rs.close();
		pstmt.close();
		table = new JTable(data,columnNames);
		JScrollPane scrollPane = new JScrollPane( table );
		table.setBackground(Color.WHITE);
		table.setSelectionBackground(Color.decode("#2196F3"));
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );//to get horizontal scroll bar
		scrollPane.setBounds(100, 220, 400, 300);
		add( scrollPane );

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
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
		new showMyTable();

	}

}
