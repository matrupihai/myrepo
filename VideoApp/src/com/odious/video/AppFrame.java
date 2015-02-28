package com.odious.video;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import com.odious.gui.CustomUI;
import com.odious.panel.AppMenuBar;
import com.odious.panel.MainPanel;
import com.odious.panel.SettingsDialog;
import com.odious.util.VideoSettings;

public class AppFrame {
	
	public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public int width = screenSize.width;
	public int height = screenSize.height;
	
	private VideoFrame video = VideoFrame.newInstance();;
	private MainPanel panel;
	private SettingsDialog settingsDialog;
	
	public AppFrame() {
		initGui();
	}
	
	private void initGui() {
		CustomUI.setCustomUI();
		settingsDialog = new SettingsDialog(video);
		
		initVideo();
		initMenuBar();
		initMainPanel();
		
		VideoSettings settings = new VideoSettings();
		settings.visit(panel);
		settings.visit(settingsDialog);
		video.setSettings(settings);
		
		video.setVisible(true);
	}
	
	private void initVideo() {
		video.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	private void initMainPanel() {
		panel = new MainPanel();
		panel.setPreferredSize(new Dimension(width/5, height));
		panel.setVideoFrame(video);
		video.getContentPane().add(panel, BorderLayout.EAST);
	}
	
	private void initMenuBar() {
		AppMenuBar bar = new AppMenuBar();
		bar.setAppFrame(this);
		video.setJMenuBar(bar.getMenuBar());
	}
	
	public SettingsDialog getSettingsDialog() {
		return settingsDialog;
	}

	public VideoFrame getVideo() {
		return video;
	}
	
}
