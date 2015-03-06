package com.odious.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import com.odious.video.VideoPanel;

public class MainPanel extends JPanel {
	public static final Color BASE_COLOR = new Color(140, 140, 140);
	
	private VideoPanel video;
	private LocationPanel locationPanel;
	private DataPanel dataPanel;
	private VideoControlPanel videoControlPanel;
	
	JPanel leftPanel = new JPanel(new MigLayout());
	JPanel rightPanel = new JPanel(new MigLayout());
	
	public MainPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		initVideoControlsPanel();
		initLocationPanel();
		initDataPanel();
		
		JScrollPane scrollPaneLeft = new JScrollPane(leftPanel);
		JScrollPane scrollPaneRight = new JScrollPane(rightPanel);
		
		add(scrollPaneLeft, BorderLayout.CENTER);
		add(scrollPaneRight, BorderLayout.EAST);
	}
	
	private void initLocationPanel() {
		locationPanel = new LocationPanel();
		rightPanel.add(locationPanel, "wrap");
	}
	
	private void initDataPanel() {
		dataPanel = new DataPanel();
		rightPanel.add(dataPanel, "wrap");
	}
	
	private void initVideoControlsPanel() {
		videoControlPanel = new VideoControlPanel();
		leftPanel.add(videoControlPanel, "wrap");
	}
	
	public String getGeoLocation() {
		return locationPanel.getGeoLocation();
	}
	
	public void setVideo(VideoPanel video) {
		this.video = video;
		videoControlPanel.setVideo(video);
	}

}
