package com.odious.video;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.odious.gui.CustomUI;
import com.odious.panel.AppMenuBar;
import com.odious.panel.MainPanel;

public class AppFrame {
	
	public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public int width = screenSize.width;
	public int height = screenSize.height;
	
	private VideoFrame video = VideoFrame.newInstance();
	private MainPanel panel;
	
	public AppFrame() {
		initGui();
	}
	
	private void initGui() {
		CustomUI.setCustomUI();
		initVideo();
		initMenuBar();
		initMainPanel();
		
		
//		CustomButton playButton = new CustomButton("play.png", "hoverPlay.png", "pressedPlay.png");
		
		
		video.setVisible(true);
	}
	
	private void initVideo() {
		video.setExtendedState(JFrame.MAXIMIZED_BOTH);
		video.setLayout(new BorderLayout());
	}
	
	private void initMainPanel() {
		panel = new MainPanel();
		panel.setPreferredSize(new Dimension(width/5, height));
		panel.setVideoFrame(video);
		video.add(panel, BorderLayout.EAST);
	}
	
	private void initMenuBar() {
		AppMenuBar bar = new AppMenuBar();
		bar.setAppFrame(this);
		video.setJMenuBar(bar.getMenuBar());
	}
}
