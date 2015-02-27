package com.odious.util;

public class Chronometer {
	private long startTime = 0;
	private long stopTime = 0;
	private boolean running = false;
	public static int delayTime = 0;

	public void start() {
		this.startTime = System.nanoTime();
		this.running = true;
	}

	public void stop() {
		this.stopTime = System.nanoTime();
		this.running = false;
	}

	public String getElapsedSeconds(int delay) {
		Double seconds;
		if (running) {
			seconds = (System.nanoTime() - startTime) / 1000000000.0;
		} else {
			seconds = (stopTime - startTime) / 1000000000.0;
		}
		int s = seconds.intValue() + delay;
		delayTime = s;
		System.out.println("" + s);
		return s / 24 / 60 + ":" + s / 60 % 24 + ":" + s % 60;
	}
}
