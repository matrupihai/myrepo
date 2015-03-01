package com.odious.video;

import static com.googlecode.javacv.cpp.opencv_core.CV_FONT_HERSHEY_TRIPLEX;
import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvPutText;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.FrameGrabber;
import com.googlecode.javacv.FrameRecorder;
import com.googlecode.javacv.cpp.opencv_core.CvFont;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.CvScalar;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_highgui;
import com.googlecode.javacv.cpp.opencv_objdetect;
import com.odious.panel.SettingsDialog;
import com.odious.util.VideoFileManager;
import com.odious.util.VideoSettings;

public class VideoPanel extends JPanel implements VideoActions {
	
	public static final int DISPLAY_WIDTH = 1280;
	public static final int DISPLAY_HEIGHT = 720;
	
	private FrameGrabber grabber;
	private FrameRecorder recorder;
	private IplImage grabbedImage;
	private int deviceId;
	
	private VideoSettings settings;

	public enum Status {
		STARTED,
		PAUSED,
		STOPPED,
		RECORDING
	}
	
	private Status status = Status.STOPPED;
	private File videoFile;
	private JLabel image;
	private VideoFileManager videoFileManager;
	private CvScalar color;
	private int displayedNumber;
	private int xPoint;
	private int yPoint;
	
	public static VideoPanel newInstance() {
		return new VideoPanel();
	}
	
	private VideoPanel() {
		setLayout(new BorderLayout());
		image = new JLabel();
		add(image, BorderLayout.CENTER);
	}

	public void setSettings(VideoSettings settings) {
		this.settings = settings;
	}
	
	@Override
	public void pause() {
		
	}

	@Override
	public void stop() {
		
	}

	@Override
	public void record() {
		
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public void start(int deviceId) throws FrameGrabber.Exception, FrameRecorder.Exception {
		this.deviceId = deviceId;
		this.status = Status.STARTED;
		Loader.load(opencv_objdetect.class);
		try {
			grabber = FrameGrabber.createDefault(deviceId);
			grabber.setImageWidth(DISPLAY_WIDTH);
			grabber.setImageHeight(DISPLAY_HEIGHT);
			grabber.start();
		} catch (FrameGrabber.Exception e) {
			e.printStackTrace();
		}
		
		CvMemStorage storage = CvMemStorage.create();
		grabbedImage = grabber.grab();
		
		videoFileManager = new VideoFileManager(settings.getSettingsFilePath(), settings.getGeoLocation());
		videoFile = videoFileManager.getVideoFile();
//		recorder = new OpenCVFrameRecorder(videoFile, RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
		recorder = FrameRecorder.createDefault(videoFile, DISPLAY_WIDTH, DISPLAY_HEIGHT);
		recorder.setVideoCodec(opencv_highgui.CV_FOURCC('M','P','4','2'));
		recorder.setFrameRate(settings.getFramerate());
		recorder.start();
		
		CvFont font = new CvFont(CV_FONT_HERSHEY_TRIPLEX, settings.getFontSize(), 1);
		while (status.equals(Status.STARTED) && (grabbedImage = grabber.grab()) != null) {
			cvClearMemStorage(storage);
//			displayedNumber = ModbusReader.getValue();
			displayedNumber = 255;
			setTextColor();
			setTextPosition();
			
			//text
			font.vscale((float) settings.getFontSize());
			cvPutText(grabbedImage, "" + displayedNumber + "m", cvPoint(xPoint, yPoint), font, color);
			
//			//contrast - 
//			cvScale(grabbedImage, grabbedImage, EGB1.getContrastValue(), 0);
//			
//			//brightness - CvScalar(blue, green, red, nothing?)
//			brightness = EGB1.getBrightnessValue();
//			cvAddS(grabbedImage, new CvScalar(brightness, brightness, brightness, 0), grabbedImage, null);
//			
//			if(EGB1.isInvertChecked()){
//				cvNot(grabbedImage, grabbedImage);
//			}
			
			image.setIcon(new ImageIcon(grabbedImage.getBufferedImage()));
			image.repaint();
			if (Status.RECORDING.equals(status)) {
				recorder.record(grabbedImage);
			}
		}
		
		clearVideoResources();
//		EGB1.getChronometer().stop();
//		if(EGB1.getChronoTimer() != null){
//			EGB1.getChronoTimer().cancel();
//			EGB1.getChronoTimer().purge();
//		}
//		EGB1.getSensor().setIcon(sensorOff);
		
	}
	
	public void clearVideoResources(){
		if (recorder != null && grabber != null) {
				try {
					recorder.stop();
					grabber.stop();
					status = Status.STOPPED;
				} catch (com.googlecode.javacv.FrameRecorder.Exception
						| com.googlecode.javacv.FrameGrabber.Exception e) {
					e.printStackTrace();
				}
		}
		videoFileManager.deleteTempVideo();
		videoFileManager.deleteScreenshotDir();
	}

	public VideoSettings getSettings() {
		return settings;
	}

	private void setTextColor() {
		switch (settings.getFontColor()) {
		case "verde":
			color = CvScalar.GREEN;
			break;
		case "alb":
			color = CvScalar.WHITE;
			break;
		case "rosu":
			color = CvScalar.RED;
			break;
		case "magenta":
			color = CvScalar.MAGENTA;
			break;
		default:
			color = CvScalar.GREEN;
			break;
		}
	}

	private void setTextPosition() {
		switch (settings.getFontPosition()) {
		case SettingsDialog.UP_LEFT:
			xPoint = (DISPLAY_WIDTH / 8) - (DISPLAY_WIDTH / 16);
			yPoint = (DISPLAY_HEIGHT / 10);
			break;
		case SettingsDialog.UP_CENTER:
			xPoint = 4 * (DISPLAY_WIDTH / 10);
			yPoint = (DISPLAY_HEIGHT / 10);
			break;
		case SettingsDialog.UP_RIGHT:
			xPoint = 5 * (DISPLAY_WIDTH / 8);
			yPoint = (DISPLAY_HEIGHT / 10);
			break;
		case SettingsDialog.DOWN_LEFT:
			xPoint = (DISPLAY_WIDTH / 8) - (DISPLAY_WIDTH / 16);
			yPoint = 9 * (DISPLAY_HEIGHT / 10);
			break;
		case SettingsDialog.DOWN_CENTER:
			xPoint = 4 * (DISPLAY_WIDTH / 10);
			yPoint = 9 * (DISPLAY_HEIGHT / 10);
			break;
		case SettingsDialog.DOWN_RIGHT:
			xPoint = 5 * (DISPLAY_WIDTH / 8);
			yPoint = 9 * (DISPLAY_HEIGHT / 10);
			break;
		default:
			xPoint = 4 * (DISPLAY_WIDTH / 8);
			yPoint = 6 * (DISPLAY_HEIGHT / 10);
			break;
		}
	}

	public class CameraSwingWorker extends SwingWorker<String, Object> {
		@Override
		protected String doInBackground() throws Exception {
				try {
					start(deviceId);
				} catch (com.googlecode.javacv.FrameGrabber.Exception
						| com.googlecode.javacv.FrameRecorder.Exception e) {
					e.printStackTrace();
					System.out.println("Error starting camera.");
				}
			return "Camera started";
		}
	}
	
}
