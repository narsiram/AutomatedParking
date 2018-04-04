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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class searching extends JFrame 
{
	JScrollPane scrollPane;
	JPanel pnl12,pnl1,pnl2;
	Connection con;
	JLabel icon;
	PreparedStatement pstmt;
	ResultSet rs;
	JTable table;
	JComboBox txtl,txtv;
	JButton loadall,print;
	JLabel lno=new JLabel("License No");
	JLabel vno=new JLabel("Vehicle No");
	Border brd1;
	searching()
	{
		
		setLayout(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		con=coNNection.docoNNect();
		
		
		brd1=BorderFactory.createLineBorder(Color.decode("#2196F3"), 1, true);
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
		
		JLabel title=new JLabel("Status of Vehicle");
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
		pnl12.setBounds(50,60,500,70);
		pnl12.setBorder(brd1);
		pnl12.setLayout(null);
		add(pnl12);
		
		
		
		pnl2=new JPanel();
		pnl2.setBounds(50,150,500,70);
		pnl2.setBorder(brd1);
		pnl2.setLayout(null);
		add(pnl2);
		
		lno.setBounds(20,15,100,30);
		lno.setFont(new Font("Times New Roman",Font.PLAIN,20));
		pnl12.add(lno);
		
		vno.setBounds(20,15,100,30);
		vno.setFont(new Font("Times New Roman",Font.PLAIN,20));
		pnl2.add(vno);
		
		txtl=new JComboBox();
		txtl.setForeground(Color.decode("#2196F3"));
		txtl.setBounds(130, 15, 130, 30);
		txtl.setEditable(true);
		pnl12.add(txtl);
		
		txtv=new JComboBox();
		txtv.setEditable(true);
		txtv.setForeground(Color.decode("#2196F3"));
		txtv.setBounds(130, 15, 130, 30);
		pnl2.add(txtv);
		
		
		loadall=new JButton("Search");
		loadall.setBackground(Color.decode("#2196F3"));
		loadall.setForeground(Color.WHITE);
		loadall.setBounds(270, 15, 100, 30);
		pnl12.add( loadall);
		loadall.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
			txtv.removeAllItems();
			filltable();
			}
		});
		
		
		print=new JButton("Search");
		print.setBackground(Color.decode("#2196F3"));
		print.setForeground(Color.WHITE);
		print.setBounds(270, 15, 100, 30);
		pnl2.add( print);
		print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				txtl.removeAllItems();
					filltable1();
								
			}
		});

	
		setSize(600,600);
		setVisible(true);
		
		
	}
	
	
	void filltable()
	{
	try
	{
		String sql = "Select * from entry where License=? ";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, String.valueOf(txtl.getSelectedItem()));
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
		txtv.addItem(rs.getString(2));
		data.addElement(row);
		}
		rs.close();
		//pstmt.close();
		table = new JTable(data,columnNames);
		scrollPane = new JScrollPane( table );
		table.setBackground(Color.WHITE);
		table.setSelectionBackground(Color.decode("#2196F3"));
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );//to get horizontal scroll bar
		scrollPane.setBounds(80, 300, 450, 200);
		scrollPane.setBorder(brd1);
		add( scrollPane );

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

	}

	
	void filltable1()
	{
	try
	{
		String sql = "Select * from entry where vno=? ";
		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, String.valueOf(txtv.getSelectedItem()));
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
		txtl.addItem(rs.getString(1));
		data.addElement(row);
		}
		rs.close();
		//pstmt.close();
		table = new JTable(data,columnNames);
		JScrollPane scrollPane = new JScrollPane( table );
		table.setBackground(Color.WHITE);
		table.setSelectionBackground(Color.decode("#2196F3"));
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );//to get horizontal scroll bar
		scrollPane.setBounds(80, 300, 450, 200);
		scrollPane.setBorder(brd1);
		//scrollPane.setBackground(Color.decode("#2196F3"));
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
		new searching();
	}

}
