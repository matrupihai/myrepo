package com.odious.video;

import static com.googlecode.javacv.cpp.opencv_core.CV_FONT_HERSHEY_TRIPLEX;
import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvPutText;

import java.io.File;

import javax.swing.SwingWorker;

import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.CanvasFrame;
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

public class VideoFrame extends CanvasFrame implements VideoActions {
	
	public static final int RESOLUTION_WIDTH = 640;
	public static final int RESOLUTION_HEIGHT = 480;

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
	private VideoFileManager videoFileManager;
	private CvScalar color;
	private int displayedNumber;
	private int xPoint;
	private int yPoint;
	
	public static VideoFrame newInstance() {
		return new VideoFrame("VideoApp");
	}
	
	private VideoFrame(String title) {
		super(title);
		setVisible(true);
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
			grabber.start();
		} catch (FrameGrabber.Exception e) {
			e.printStackTrace();
		}
		
		CvMemStorage storage = CvMemStorage.create();
		grabbedImage = grabber.grab();
		
		videoFileManager = new VideoFileManager(settings.getSettingsFilePath(), settings.getGeoLocation());
		videoFile = videoFileManager.getVideoFile();
//		recorder = new OpenCVFrameRecorder(videoFile, RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
		recorder = FrameRecorder.createDefault(videoFile, RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
		recorder.setVideoCodec(opencv_highgui.CV_FOURCC('M','P','4','2'));
		recorder.setFrameRate(settings.getFramerate());
		recorder.start();
		
		CvFont font;
		
		while (status.equals(Status.STARTED) && (grabbedImage = grabber.grab()) != null) {
			cvClearMemStorage(storage);
//			displayedNumber = ModbusReader.getValue();
			displayedNumber = 255;
			setTextColor();
			setTextPosition();
			
			//text
			font = new CvFont(CV_FONT_HERSHEY_TRIPLEX, settings.getFontSize(), 1);
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
			
			showImage(grabbedImage);
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
		status = Status.STOPPED;
		if (recorder != null && grabber != null) {
				try {
					recorder.stop();
					grabber.stop();
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
			xPoint = (RESOLUTION_WIDTH / 8) - (RESOLUTION_WIDTH / 16);
			yPoint = (RESOLUTION_HEIGHT / 10);
			break;
		case SettingsDialog.UP_CENTER:
			xPoint = 4 * (RESOLUTION_WIDTH / 10);
			yPoint = (RESOLUTION_HEIGHT / 10);
			break;
		case SettingsDialog.UP_RIGHT:
			xPoint = 5 * (RESOLUTION_WIDTH / 8);
			yPoint = (RESOLUTION_HEIGHT / 10);
			break;
		case SettingsDialog.DOWN_LEFT:
			xPoint = (RESOLUTION_WIDTH / 8) - (RESOLUTION_WIDTH / 16);
			yPoint = 9 * (RESOLUTION_HEIGHT / 10);
			break;
		case SettingsDialog.DOWN_CENTER:
			xPoint = 4 * (RESOLUTION_WIDTH / 10);
			yPoint = 9 * (RESOLUTION_HEIGHT / 10);
			break;
		case SettingsDialog.DOWN_RIGHT:
			xPoint = 5 * (RESOLUTION_WIDTH / 8);
			yPoint = 9 * (RESOLUTION_HEIGHT / 10);
			break;
		default:
			xPoint = 4 * (RESOLUTION_WIDTH / 8);
			yPoint = 6 * (RESOLUTION_HEIGHT / 10);
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
