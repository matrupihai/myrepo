package com.odious.video;

import com.googlecode.javacv.CanvasFrame;

public class VideoFrame extends CanvasFrame implements VideoActions {
	
	public enum Status {
		STARTED,
		PAUSED,
		STOPPED,
		RECORDING
	}
	
	public static VideoFrame newInstance() {
		return new VideoFrame("");
	}
	
	private VideoFrame(String title) {
		super(title);
		setVisible(true);
	}

	@Override
	public void play() {
		
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
	
}
