package com.odious.panel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import com.odious.util.VideoVisitable;
import com.odious.util.VideoVisitor;
import com.odious.video.VideoFrame;

public class MainPanel extends JPanel implements VideoVisitable {
	public static final Color BASE_COLOR = new Color(140, 140, 140);
	
	private VideoFrame video;
	private LocationPanel locationPanel;
	private DataPanel dataPanel;
	private VideoControlPanel videoPanel;
	
	JPanel leftPanel = new JPanel(new MigLayout());
	JPanel rightPanel = new JPanel(new MigLayout());
	
	public MainPanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		initVideoPanel();
		initLocationPanel();
		initDataPanel();
		
		JScrollPane scrollPaneLeft = new JScrollPane(leftPanel);
		JScrollPane scrollPaneRight = new JScrollPane(rightPanel);
		
		add(scrollPaneLeft, BorderLayout.CENTER);
		add(scrollPaneRight, BorderLayout.EAST);
	}
	
	@Override
	public void accept(VideoVisitor visitor) {
		visitor.visit(this);
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
		leftPanel.add(videoPanel, "top, left, wrap");
	}
	
	public String getGeoLocation() {
		return locationPanel.getGeoLocation();
	}
	
	public void setVideoFrame(VideoFrame video) {
		this.video = video;
		videoPanel.setVideoFrame(video);
	}

}
