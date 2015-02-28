package com.odious.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.odious.gui.CustomButton;
import com.odious.model.CustomComboBoxModel;
import com.odious.util.Chronometer;
import com.odious.video.VideoFrame;
import com.odious.video.VideoFrame.Status;
import com.odious.video.VideoHelper;

public class VideoControlPanel extends JPanel {
	private VideoFrame video;
	
	public VideoControlPanel() {
		JPanel panelControls = new JPanel(new MigLayout());
		panelControls.setBorder(BorderFactory.createLineBorder(MainPanel.BASE_COLOR, 1));
		VideoHelper.detectVideoDevices();
		final JComboBox<String> comboDevices = new JComboBox<String>();
		final CustomComboBoxModel comboModel = new CustomComboBoxModel(VideoHelper.getDevices());
		comboDevices.setModel(comboModel);
		Dimension comboDimension = new Dimension(160, 30);
		comboDevices.setMinimumSize(comboDimension);
		comboDevices.setMaximumSize(comboDimension);
		comboDevices.setEditable(false);
		comboDevices.setSelectedIndex(0);

		CustomButton playButton = new CustomButton("play.png",
				"hoverPlay.png", "pressedPlay.png");
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				if (!comboModel.getElementAt(0).equals(VideoHelper.NO_SOURCE)) {
					if (video.getStatus() == Status.STOPPED) {
						final VideoFrame.CameraSwingWorker camera = videoFrame.new CameraSwingWorker();
						camera.execute();
						System.out.println("Camera started!");
						// startSerial();
						chronoLabel.setText("0:0:0");
						KeyboardFocusManager manager = KeyboardFocusManager
								.getCurrentKeyboardFocusManager();
						manager.addKeyEventDispatcher(videoFrame.new CustomDispatcher());
					}
				} else {
					JOptionPane.showMessageDialog(getRootPane(),
							"No video source!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		final CustomButton recordButton = new CustomButton("/record.png",
				"/hoverRecord.png", "/pressedRecord.png");
		recordButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (videoFrame != null) {
					writeToFile(videoFrame.getVideoDestination() + "info.txt",
							getFieldsData());
					if (VideoFrame.isRecording) {
						VideoFrame.isRecording = false;
						sensorLabel.setIcon(sensorOff);
						recordButton.setIcon(hoverPauseRed);
						recordButton.setRolloverIcon(hoverPauseRed);
						chronoLabel.setForeground(base);
						chronoLabel.repaint();
						chronometer.stop();
						if (timerChronos != null) {
							timerChronos.cancel();
							timerChronos.purge();
						}
					} else {
						VideoFrame.isRecording = true;
						sensorLabel.setIcon(sensorOn);
						chronoLabel.setForeground(new Color(255, 9, 9));
						chronoLabel.repaint();
						chronometer.start();
						getTime(chronometer, chronoLabel, Chronometer.delayTime);
						recordButton.setIcon(record);
						recordButton.setRolloverIcon(hoverPause);
						recordButton.setPressedIcon(pressedPause);
					}
				}
			}
		});
		CustomButton stopButton = new CustomButton("/stop.png",
				"/hoverStop.png", "/pressedStop.png");
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (videoFrame != null) {
					int option = JOptionPane.showConfirmDialog(getRootPane(),
							"Stop video?");
					if (option == JOptionPane.YES_OPTION) {
						// videoFrame.clearVideoResources();
						videoFrame.getCanvasFrame().setVisible(false);
						videoFrame.getCanvasFrame().dispose();
						videoFrame = null;
						sensorLabel.setIcon(sensorOff);
						recordButton.setIcon(record);
						recordButton.setRolloverIcon(hoverRecord);
						chronoLabel.setForeground(base);
						chronoLabel.repaint();
						chronometer.stop();
						Chronometer.delayTime = 0;
						if (timerChronos != null) {
							timerChronos.cancel();
							timerChronos.purge();
						}
					}
				}
			}
		});

		CustomButton refreshButton = new CustomButton("/refresh.png",
				"/hoverRefresh.png", "/pressedRefresh.png");
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				detectVideoDevices();
				comboDevices.setModel(new CustomComboBoxModel(deviceNameList));
				comboDevices.setSelectedIndex(0);
			}
		});

		CustomButton screenshotButton = new CustomButton("/screenshot.png",
				"/hoverScreenshot.png", "/pressedScreenshot.png");
		screenshotButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (videoFrame != null) {
					videoFrame.getScreenshot();
				}

			}
		});

		JPanel containerChronometer = new JPanel(new MigLayout());
		chronoLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		chronoLabel.setForeground(base);
		containerChronometer.add(chronoLabel);

		JPanel paramPanel = new JPanel(new MigLayout());
		paramPanel.add(refreshButton, "west");
		paramPanel.add(comboDevices, "west");
		paramPanel.add(sensorLabel, "west, gapleft 20");
		JPanel buttonPanel = new JPanel(new MigLayout());
		buttonPanel.add(playButton, "west");
		buttonPanel.add(recordButton, "west");
		buttonPanel.add(stopButton, "west");
		buttonPanel.add(screenshotButton, "west");
		panelControls.add(paramPanel, "wrap");
		panelControls.add(buttonPanel, "wrap");
		panelControls.add(containerChronometer, "top, center");

	}
	
	public void setVideoFrame(VideoFrame video) {
		this.video = video;
	}
	
}
