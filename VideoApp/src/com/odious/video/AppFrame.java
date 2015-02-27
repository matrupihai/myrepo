package com.odious.video;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.odious.gui.CustomButton;
import com.odious.gui.CustomUI;
import com.odious.panel.AppMenuBar;

public class AppFrame {
	
	public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public int width = screenSize.width;
	public int height = screenSize.height;
	
	private VideoFrame video = VideoFrame.newInstance();
	
	public AppFrame() {
		initGui();
	}
	
	private void initGui() {
		CustomUI.setCustomUI();
		video.setExtendedState(JFrame.MAXIMIZED_BOTH);
		video.setLayout(new BorderLayout());
		initMenuBar();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);
		panel.setPreferredSize(new Dimension(width/5, height));
		
		CustomButton playButton = new CustomButton("play.png", "hoverPlay.png", "pressedPlay.png");
		
		video.add(panel, BorderLayout.EAST);
		video.add(playButton, BorderLayout.NORTH);
		
		video.setVisible(true);
	}
	
	private void initMenuBar() {
		AppMenuBar bar = new AppMenuBar();
		bar.setAppFrame(this);
		video.setJMenuBar(bar.getMenuBar());
	}
}
