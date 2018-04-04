package cArParkinG;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class progressbar extends JFrame 
{
	JProgressBar progressBar;
	JLabel icon;
	progressbar()
	{ 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		icon=new JLabel("Loading Car Parking.....");
		icon.setBounds(0,0,600,600);
		icon.setIcon(new ImageIcon("progress.jpg"));
		add(icon);

	

		progressBar = new JProgressBar(0,100);
		progressBar.setValue(1);

		progressBar.setStringPainted(true);
		progressBar.setBounds(0, 300, 600, 50);
		icon.add(progressBar);
		progressBar.setBackground(Color.WHITE);

		progressBar.setForeground(Color.decode("#2196F3"));

		setLayout(null);
		setUndecorated(true);
		setSize(600, 600);
		setVisible(true);
		setLocation(400,20);//screen location
		runn();
	}
	public void runn()
	{
		URL urlClick = change.class.getResource("bg1.wav");
		AudioClip click = Applet.newAudioClip(urlClick);
		click.play();
		for(int i=1;i<=100;i++)
		{
			progressBar.setValue(i);
			try
			{
				Thread.sleep(40);

			} 
			catch (Exception e) { }
		}
		//setVisible(false);
		dispose();
		click.stop();
		login.main(new String[]{});
		dispose();
		//JOptionPane.showMessageDialog(null, "Completed");

	}
	public static void main(String args[])
	{
		new progressbar();
	}
	
}


