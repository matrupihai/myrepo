package com.odious.video;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.javacv.cpp.videoInputLib.videoInput;

public class VideoHelper {
	public static final String NO_SOURCE = "NO_SOURCE";
	
	private static List<String> devices = new ArrayList<String>();

	public static void detectVideoDevices() {
		devices.clear();
		int count = videoInput.listDevices();
		if (count > 0) {
			for (int i = 0; i < count; i++) {
				StringBuilder deviceName = new StringBuilder(i).append(" ").append(videoInput.getDeviceName(i));
				devices.add(deviceName.toString());
			}
		} else {
			devices.add(0, NO_SOURCE);
		}

		System.out.println("Video devices #: " + count);
	}
	
}
