package com.odious.util;

import com.odious.panel.MainPanel;
import com.odious.panel.SettingsDialog;

public class VideoSettings implements VideoVisitor {
	private int framerate;
	private String geoLocation;
	private String settingsFilePath;
	private double fontSize;
	private String fontColor;
	private String fontPosition;
	
	
	@Override
	public void visit(MainPanel mainPanel) {
		this.geoLocation = mainPanel.getGeoLocation();
	}
	
	@Override
	public void visit(SettingsDialog settingsDialog) {
		this.framerate = settingsDialog.getFramerate();
		this.settingsFilePath = settingsDialog.getFilePath();
		this.fontSize = settingsDialog.getFontSize();
		this.fontColor = settingsDialog.getFontColor();
		this.fontPosition = settingsDialog.getFontPosition();
	}
	
	public String getFontPosition() {
		return fontPosition;
	}

	public String getFontColor() {
		return fontColor;
	}

	public int getFramerate() {
		return framerate;
	}
	
	public String getSettingsFilePath() {
		return settingsFilePath;
	}
	
	public double getFontSize() {
		return fontSize;
	}

	public String getGeoLocation() {
		return geoLocation;
	}
	
}	
