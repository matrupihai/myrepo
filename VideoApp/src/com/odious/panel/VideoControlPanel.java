package com.odious.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.odious.gui.CustomButton;
import com.odious.gui.ImageHelper;
import com.odious.model.CustomComboBoxModel;
import com.odious.util.Chronometer;
import com.odious.video.VideoHelper;
import com.odious.video.VideoPanel;
import com.odious.video.VideoPanel.Status;

public class VideoControlPanel extends JPanel {
	private VideoPanel video;
	
	private static ImageIcon sensorOff, sensorOn, upActive, upInactive, downActive, 
			downInactive, restActive, restInactive, record, hoverPause, pressedPause, 
			hoverPauseRed, hoverRecord;
	private JLabel sensorLabel;
	private final JLabel chronoLabel = new JLabel("0:0:0");
	private static Timer timerChronos;
	
	static {
		ImageHelper imgHelper = new ImageHelper();

		try {
			sensorOff = imgHelper.loadImageIcon("sensorOff.png");
			sensorOn = imgHelper.loadImageIcon("sensorOn.png");
			upActive = imgHelper.loadImageIcon("upActive.png");
			upInactive = imgHelper.loadImageIcon("upInactive.png");
			downActive = imgHelper.loadImageIcon("downActive.png");
			downInactive = imgHelper.loadImageIcon("downInactive.png");
			restActive = imgHelper.loadImageIcon("restActive.png");
			restInactive = imgHelper.loadImageIcon("restInactive.png");
			record = imgHelper.loadImageIcon("record.png");
			hoverPause = imgHelper.loadImageIcon("hoverPause.png");
			pressedPause = imgHelper.loadImageIcon("pressedPause.png");
			hoverPauseRed = imgHelper.loadImageIcon("hoverPauseRed.png");
			hoverRecord = imgHelper.loadImageIcon("hoverRecord.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public VideoControlPanel() {
		JPanel panelControls = new JPanel(new MigLayout());
		panelControls.setBorder(BorderFactory.createLineBorder(MainPanel.BASE_COLOR, 1));
		VideoHelper.detectVideoDevices();
		final JComboBox<String> comboDevices = new JComboBox<String>();
		final CustomComboBoxModel comboModel = new CustomComboBoxModel(
				VideoHelper.getDevices());
		comboDevices.setModel(comboModel);
		Dimension comboDimension = new Dimension(160, 30);
		comboDevices.setMinimumSize(comboDimension);
		comboDevices.setMaximumSize(comboDimension);
		comboDevices.setEditable(false);
		comboDevices.setSelectedIndex(0);
		sensorLabel = new JLabel(sensorOff);
		
		CustomButton playButton = new CustomButton("play.png", "hoverPlay.png", "pressedPlay.png");
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				if (!comboModel.getElementAt(0).equals(VideoHelper.NO_SOURCE)) {
					if (video != null && video.getStatus() == Status.STOPPED) {
						final VideoPanel.CameraSwingWorker camera = video.new CameraSwingWorker();
						camera.execute();
						System.out.println("Camera started!");
						// startSerial();
						chronoLabel.setText("0:0:0");
						KeyboardFocusManager manager = KeyboardFocusManager
								.getCurrentKeyboardFocusManager();
						// manager.addKeyEventDispatcher(video.new
						// CustomDispatcher());
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
				if (video != null) {
//					writeToFile(videoFrame.getVideoDestination() + "info.txt",
//							getFieldsData());
					Status status = video.getStatus();
					if (status.equals(Status.RECORDING)) {
						video.pause();
						sensorLabel.setIcon(sensorOff);
						recordButton.setIcon(hoverPauseRed);
						recordButton.setRolloverIcon(hoverPauseRed);
						chronoLabel.setForeground(MainPanel.BASE_COLOR);
						chronoLabel.repaint();
						Chronometer.stop();
						if (timerChronos != null) {
							timerChronos.cancel();
							timerChronos.purge();
						}
					} else {
						video.record();
						sensorLabel.setIcon(sensorOn);
						chronoLabel.setForeground(new Color(255, 9, 9));
						chronoLabel.repaint();
						Chronometer.start();
						getTime(chronoLabel, Chronometer.delayTime);
						recordButton.setIcon(record);
						recordButton.setRolloverIcon(hoverPause);
						recordButton.setPressedIcon(pressedPause);
					}
				}
			}
		});
		
		CustomButton stopButton = new CustomButton("stop.png",
				"hoverStop.png", "pressedStop.png");
		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Status status = video.getStatus();
				if (Status.RECORDING.equals(status) || Status.PAUSED.equals(status)) {
					int option = JOptionPane.showConfirmDialog(getRootPane(),
							"Stop recording?");
					if (option == JOptionPane.YES_OPTION) {
						video.stop();
						sensorLabel.setIcon(sensorOff);
						recordButton.setIcon(record);
						recordButton.setRolloverIcon(hoverRecord);
						chronoLabel.setForeground(MainPanel.BASE_COLOR);
						chronoLabel.repaint();
						Chronometer.stop();
						Chronometer.delayTime = 0;
						if (timerChronos != null) {
							timerChronos.cancel();
							timerChronos.purge();
						}
					}
				}
			}
		});

		CustomButton refreshButton = new CustomButton("refresh.png",
				"hoverRefresh.png", "pressedRefresh.png");
		refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VideoHelper.detectVideoDevices();
				comboDevices.setModel(new CustomComboBoxModel(VideoHelper.getDevices()));
				comboDevices.setSelectedIndex(0);
			}
		});

//		CustomButton screenshotButton = new CustomButton("/screenshot.png",
//				"/hoverScreenshot.png", "/pressedScreenshot.png");
//		screenshotButton.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (videoFrame != null) {
//					videoFrame.getScreenshot();
//				}
//
//			}
//		});
//
		JPanel containerChronometer = new JPanel(new MigLayout());
		chronoLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		chronoLabel.setForeground(MainPanel.BASE_COLOR);
		containerChronometer.add(chronoLabel);

		JPanel paramPanel = new JPanel(new MigLayout());
		paramPanel.add(refreshButton, "west");
		paramPanel.add(comboDevices, "west");
		paramPanel.add(sensorLabel, "west, gapleft 20");
		JPanel buttonPanel = new JPanel(new MigLayout());
		buttonPanel.add(playButton, "west");
		buttonPanel.add(recordButton, "west");
		buttonPanel.add(stopButton, "west");
//		buttonPanel.add(screenshotButton, "west");
		panelControls.add(paramPanel, "wrap");
		panelControls.add(buttonPanel, "wrap");
		
		add(panelControls);
		panelControls.add(containerChronometer, "top, center");

	}
	
	public void getTime(final JLabel label, final int delay) {
		timerChronos = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				label.setText("" + Chronometer.getElapsedSeconds(delay));
			}
		};
		timerChronos.schedule(task, 0, 1000);
	}
	
	public void setVideo(VideoPanel video) {
		this.video = video;
	}

}
