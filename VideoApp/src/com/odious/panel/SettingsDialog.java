package com.odious.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import net.miginfocom.swing.MigLayout;

import com.odious.user.UserSettings;
import com.odious.util.LoadAndSave;
import com.odious.util.VideoVisitable;
import com.odious.util.VideoVisitor;

public class SettingsDialog extends JDialog implements VideoVisitable {
	private static final long serialVersionUID = 7526472295622776147L;
	
	public static final String SETTINGS_FILENAME = "settings.egb";
	public static final String UP_LEFT = "sus-stanga";
	public static final String UP_CENTER = "sus-centru";
	public static final String UP_RIGHT = "sus-dreapta";
	public static final String DOWN_LEFT = "jos-stanga";
	public static final String DOWN_CENTER = "jos-centru";
	public static final String DOWN_RIGHT = "jos-dreapta";

	private JButton okButton, cancelButton, browseButton;
	private JTextField filePathField;
	private JComboBox<String> colorCombo, positionCombo;
	private JComboBox<Integer> framerateCombo, baudCombo;
	private JComboBox<Double> sizeCombo;
	private String filePath = null;

	private UserSettings userSettings;

	public SettingsDialog(JFrame parent) {
		getContentPane().setLayout(new BorderLayout());

		JPanel containerPanel = new JPanel(new BorderLayout());
		containerPanel.setBorder(BorderFactory
				.createEmptyBorder(15, 15, 15, 15));
		containerPanel.add(loadVideoSettingsPanel(), BorderLayout.NORTH);
		containerPanel.add(loadSerialPanel(), BorderLayout.CENTER);
		containerPanel.add(loadButtons(), BorderLayout.SOUTH);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(containerPanel);

		initSettings();

		setTitle("Setari");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		setAlwaysOnTop(true);
		setSize(430, 355);
		setLocationRelativeTo(null);
	}
	
	@Override
	public void accept(VideoVisitor visitor) {
		visitor.visit(this);
	}
	
	private JPanel loadVideoSettingsPanel() {
		JPanel panelSettings = new JPanel(new MigLayout());
		panelSettings.setBorder(BorderFactory.createTitledBorder("Video"));
		JLabel destinationLabel = new JLabel("Destinatie");
		JLabel fontLabel = new JLabel("Font");
		JLabel framerateLabel = new JLabel("Framerate");
		filePathField = new JTextField(25);
		filePathField.setEditable(false);

		browseButton = new JButton("...");
		browseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				setDestinationFolder();
			}
		});

		String[] colors = new String[] { "verde", "alb", "rosu", "magenta" };
		colorCombo = new JComboBox<String>(colors);
		colorCombo.setSelectedItem("verde");
		colorCombo.setEditable(false);

		Integer[] framerate = new Integer[] { 25, 20, 30 };
		framerateCombo = new JComboBox<Integer>(framerate);

		Double[] size = new Double[] { 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9,
				1.0, 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0, 2.1,
				2.2, 2.3, 2.4, 2.5, 2.6, 2.7, 2.8 };
		sizeCombo = new JComboBox<Double>(size);

		String[] position = new String[] { UP_LEFT, UP_CENTER, UP_RIGHT,
				DOWN_LEFT, DOWN_CENTER, DOWN_RIGHT };

		positionCombo = new JComboBox<String>(position);
		positionCombo.setSelectedIndex(0);

		JPanel comboContainer = new JPanel(new MigLayout());

		comboContainer.add(framerateLabel, "west, gapright 12");
		comboContainer.add(framerateCombo, "west, wrap");

		JPanel fontContainer = new JPanel(new MigLayout());
		fontContainer.add(fontLabel, "west, gapright 10");
		fontContainer.add(colorCombo, "west, gapright 10");
		fontContainer.add(sizeCombo, "west");
		fontContainer.add(positionCombo, "west, gapleft 10");

		panelSettings.add(destinationLabel, "wrap");
		panelSettings.add(filePathField, "top, left");
		panelSettings.add(browseButton, "top, left, wrap");
		panelSettings.add(comboContainer, "wrap");
		panelSettings.add(fontContainer);

		return panelSettings;
	}

	private JPanel loadButtons() {
		JPanel buttonsPanel = new JPanel();
		okButton = new JButton("Ok");
		okButton.setPreferredSize(new Dimension(100, 30));
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveSettings();
				dispose();
			}
		});

		cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(100, 30));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelAction();
			}
		});
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);

		return buttonsPanel;
	}
	
	private void cancelAction() {
		if (userSettings != null) {
			filePathField.setText(userSettings.getFilePath());
			colorCombo.setSelectedItem(userSettings.getFontColor());
			baudCombo.setSelectedItem(userSettings.getBaudrate());
			sizeCombo.setSelectedItem(userSettings.getFontSize());
			positionCombo.setSelectedItem(userSettings
					.getFontPosition());
			framerateCombo.setSelectedItem(userSettings.getFramerate());
		} else {
			filePathField.setText(null);
			colorCombo.setSelectedIndex(0);
			positionCombo.setSelectedItem(DOWN_CENTER);
			baudCombo.setSelectedIndex(0);
			sizeCombo.setSelectedItem(1.0);
			framerateCombo.setSelectedIndex(0);
		}
		
		dispose();
	}

	private JPanel loadSerialPanel() {
		JPanel serialPanel = new JPanel(new MigLayout());
		serialPanel.setBorder(BorderFactory.createTitledBorder("Serial"));
		JLabel baudLabel = new JLabel("Baudrate");
		Integer[] baudValues = new Integer[] { 9600, 4800 };
		baudCombo = new JComboBox<Integer>(baudValues);

		serialPanel.add(baudLabel, "west, gapright 10");
		serialPanel.add(baudCombo, "west");

		return serialPanel;
	}

	private void initSettings() {
		// load serialized settings or create new ones
		if (LoadAndSave.load(SETTINGS_FILENAME) instanceof UserSettings) {
			userSettings = (UserSettings) LoadAndSave.load(SETTINGS_FILENAME);
			filePathField.setText(userSettings.getFilePath());
			framerateCombo.setSelectedItem(userSettings.getFramerate());
			colorCombo.setSelectedItem(userSettings.getFontColor());
			sizeCombo.setSelectedItem(userSettings.getFontSize());
			positionCombo.setSelectedItem(userSettings.getFontPosition());
			baudCombo.setSelectedItem(userSettings.getBaudrate());
			System.out.println("User settings loaded");
		} else {
			userSettings = new UserSettings(getFontColor(), getFramerate(), null,
					getBaud(), getFontSize(), getFontPosition());
			System.out.println("User settings created");
		}
	}

	private void setDestinationFolder() {
		JFileChooser pathChooser = new JFileChooser();
		pathChooser.setMultiSelectionEnabled(false);
		pathChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		pathChooser.setApproveButtonMnemonic(KeyEvent.VK_ENTER);
		pathChooser.showDialog(this, "OK");
		File f = pathChooser.getSelectedFile();
		if (f != null) {
			filePath = f.getAbsolutePath();
			filePathField.setText(filePath);
		}
	}

	private void saveSettings() {
		userSettings.setFilePath(getFilePath());
		userSettings.setBaudrate(getBaud());
		userSettings.setFontSize(getFontSize());
		userSettings.setFontPosition(getFontPosition());
		userSettings.setFontColor(getFontColor());
		userSettings.setFramerate(getFramerate());
		LoadAndSave.serialize(userSettings, SETTINGS_FILENAME);
	}

	public UserSettings getUserSettings() {
		return userSettings;
	}

	public double getFontSize() {
		return (Double) sizeCombo.getSelectedItem();
	}

	public String getFontPosition() {
		return (String) positionCombo.getSelectedItem();
	}

	public String getFilePath() {
		return filePathField.getText();
	}

	public Integer getFramerate() {
		return (Integer) framerateCombo.getSelectedItem();
	}

	public Integer getBaud() {
		return (Integer) baudCombo.getSelectedItem();
	}

	public String getFontColor() {
		return (String) colorCombo.getSelectedItem();
	}

}
