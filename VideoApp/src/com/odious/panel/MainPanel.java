package com.odious.panel;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import com.odious.video.VideoFrame;

public class MainPanel extends JPanel {
	public static final Color BASE_COLOR = new Color(140, 140, 140);
	
	private VideoFrame video;
	private LocationPanel locationPanel;
	private DataPanel dataPanel;
	private VideoControlPanel videoPanel;
	
	private JScrollPane scrollPaneLeft = new JScrollPane();
	private JScrollPane scrollPaneRight = new JScrollPane();
	JPanel leftPanel = new JPanel(new MigLayout());
	JPanel rightPanel = new JPanel(new MigLayout());
	
	public MainPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("flowy"));
		
		initLocationPanel();
		initDataPanel();
		initVideoPanel();
		
		scrollPaneLeft.add(leftPanel);
		scrollPaneRight.add(rightPanel);
		
		add(scrollPaneLeft, "wrap");
		add(scrollPaneRight, "top, wrap");
	}
	
	private void initLocationPanel() {
		locationPanel = new LocationPanel();
		rightPanel.add(locationPanel, "wrap");
	}
	
	private void initDataPanel() {
		dataPanel = new DataPanel();
		rightPanel.add(dataPanel, "wrap");
	}
	
	private void initVideoPanel() {
		videoPanel = new VideoControlPanel();
		videoPanel.setVideoFrame(video);
		leftPanel.add(videoPanel, "top, left, wrap");
	}
	
	public void setVideoFrame(VideoFrame video) {
		this.video = video;
	}
}
