package com.odious.util;

import com.odious.panel.MainPanel;
import com.odious.panel.SettingsDialog;

public interface VideoVisitor {
	public void visit(SettingsDialog settingsDialog);
	public void visit(MainPanel mainPanel);
}
