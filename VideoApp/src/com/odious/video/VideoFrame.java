package com.odious.video;

import static com.googlecode.javacv.cpp.opencv_core.CV_FONT_HERSHEY_TRIPLEX;
import static com.googlecode.javacv.cpp.opencv_core.cvAddS;
import static com.googlecode.javacv.cpp.opencv_core.cvClearMemStorage;
import static com.googlecode.javacv.cpp.opencv_core.cvNot;
import static com.googlecode.javacv.cpp.opencv_core.cvPoint;
import static com.googlecode.javacv.cpp.opencv_core.cvPutText;
import static com.googlecode.javacv.cpp.opencv_core.cvScale;

import java.io.File;

import javax.swing.SwingWorker;
import javax.swing.WindowConstants;

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

public class VideoFrame extends CanvasFrame implements VideoActions {
	
	public static final int RESOLUTION_WIDTH = 640;
	public static final int RESOLUTION_HEIGHT = 480;

	private FrameGrabber grabber;
	private FrameRecorder recorder;
	private IplImage grabbedImage;
	private int deviceId;
	
	public enum Status {
		STARTED,
		PAUSED,
		STOPPED,
		RECORDING
	}
	
	private Status status = Status.STOPPED;
	
	public static VideoFrame newInstance() {
		return new VideoFrame("");
	}
	
	private VideoFrame(String title) {
		super(title);
		setVisible(true);
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
	public void start(int deviceId) throws Exception {
		this.deviceId = deviceId;
		this.status = Status.STARTED;
		Loader.load(opencv_objdetect.class);
		try {
			grabber = FrameGrabber.createDefault(deviceId);
			grabber.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		CvMemStorage storage = CvMemStorage.create();
		grabbedImage = grabber.grab();
		
		videoFile = new File(setVideoFilePath());
//		recorder = new OpenCVFrameRecorder(videoFile, RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
		recorder = FrameRecorder.createDefault(videoFile, RESOLUTION_WIDTH, RESOLUTION_HEIGHT);
		recorder.setVideoCodec(opencv_highgui.CV_FOURCC('M','P','4','2'));
		recorder.setFrameRate(settingsDialog.getFramerate());
		recorder.start();
		
		frame = new CanvasFrame("Record", CanvasFrame.getDefaultGamma()/grabber.getGamma());
		CvFont font;
		
		while (frame.isVisible() && (grabbedImage = grabber.grab()) != null) {
			cvClearMemStorage(storage);
			displayedNumber = ModbusReader.getValue();
			setTextColor();
			setTextPosition();
			
			//text
			font = new CvFont(CV_FONT_HERSHEY_TRIPLEX, settingsDialog.getFontSize(), 1);
			cvPutText(grabbedImage, "" + displayedNumber + "m", cvPoint(xPoint, yPoint), font, color);
			
			//contrast - 
			cvScale(grabbedImage, grabbedImage, EGB1.getContrastValue(), 0);
			
			//brightness - CvScalar(blue, green, red, nothing?)
			brightness = EGB1.getBrightnessValue();
			cvAddS(grabbedImage, new CvScalar(brightness, brightness, brightness, 0), grabbedImage, null);
			
			if(EGB1.isInvertChecked()){
				cvNot(grabbedImage, grabbedImage);
			}
			
			frame.showImage(grabbedImage);
			if(isRecording){
				recorder.record(grabbedImage);
			}
		}
		
		clearVideoResources();
		EGB1.getChronometer().stop();
		if(EGB1.getChronoTimer() != null){
			EGB1.getChronoTimer().cancel();
			EGB1.getChronoTimer().purge();
		}
		EGB1.getSensor().setIcon(sensorOff);
		
	}

	
	public class CameraSwingWorker extends SwingWorker<String, Object>{
		@Override
		protected String doInBackground() throws Exception {
			try {
				start(deviceId);
			} 
			catch (Exception e){
				e.printStackTrace();
				System.out.println("Camera exception!");
			}
			return "Camera started";
		}
	}
	
}
