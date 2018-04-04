package cArParkinG;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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

public class Bill extends JFrame
{

	Connection con;
	PreparedStatement pst;
	JPanel pnl1;
	JTable table;
	String s;
	JLabel icon;
	Bill()
	{
		setLayout(null);
		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		con=coNNection.docoNNect();
//		 
		
		Border brd1=BorderFactory.createLineBorder(Color.decode("#2196F3"), 1, true);
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
		icon.setIcon(resize_img("parking.jpg"));
		pnl1.add(icon);
		
		
		 JLabel title=new JLabel("RECIEPT OF BILL");
		title.setBounds(150, 20, 350, 60);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial",Font.CENTER_BASELINE,30));
		pnl1.add(title);
	
		
		filltable();
	
		JButton p=new JButton("Print");
		p.setBounds(100,450, 100, 20);
		p.setBackground(Color.decode("#2196F3"));
		p.setForeground(Color.WHITE);
		add(p);
		p.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				URL urlClick = change.class.getResource("bg.wav");
				AudioClip click = Applet.newAudioClip(urlClick);
				click.play();
				
				// TODO Auto-generated method stub
				try 
				{
					table.print();
					
					try 
					{
						pst=con.prepareStatement("update Bill set status=0 where Vno=?");
						pst.setString(1, s);
						int y=pst.executeUpdate();
					} 
					catch (SQLException e1) 
					{
						e1.printStackTrace();
					}
					
					
				} 
				catch (PrinterException e1) 
				{
					//e1.printStackTrace();
				}
				dispose();
				MenuBar.main(null);
				
			}
		});
	

		setSize(600,600);
		setVisible(true);
		
		
	}
	
	
	void filltable()
	{
	try
	{
		String sql = "Select * from Bill where status=1;";
		pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
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
			s=rs.getString(1);
		Vector<Object> row = new Vector<Object>(columns);
		for (int i = 1; i <= columns; i++)
		{
		row.addElement( rs.getObject(i) );
		}
		data.addElement(row);
		}
		rs.close();
		pst.close();
		 table = new JTable(data,columnNames);
		JScrollPane scrollPane = new JScrollPane( table );
		table.setBackground(Color.WHITE);
		table.setSelectionBackground(Color.decode("#2196F3"));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );//to get horizontal scroll bar
		scrollPane.setBounds(100, 200, 300, 200);
		add( scrollPane );

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
	}

	
	public ImageIcon resize_img (String p)
	{
		 ImageIcon full=new ImageIcon(p);
		Image img=full.getImage().getScaledInstance(icon.getWidth(),icon.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon bhai=new ImageIcon(img);
		return bhai;
	
	}



}
