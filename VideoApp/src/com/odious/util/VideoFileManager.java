package com.odious.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VideoFileManager {
	
	public static String setVideoFilePath() {
		File mainDir;
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat time = new SimpleDateFormat("hh_mm_ss");
		String settingsFilePath = settingsDialog.getFilePath();
		if(settingsFilePath.isEmpty()){
			mainDir = new File(System.getProperty("user.dir"), fileLocation + " " + date.format(new Date()));
		} else {
			mainDir = new File(settingsFilePath, fileLocation + " " + date.format(new Date()));
		}
		String filePath = null;
		String timestamp = time.format(new Date());
		if(!mainDir.exists()){
			if(mainDir.mkdir()){
				filePath = mainDir.getAbsolutePath() + "\\" + fileLocation +  " " + 
							timestamp + ".wmv";
				screenshotDir = new File(mainDir.getAbsolutePath(), "foto " + fileLocation + " " + timestamp);
				screenshotDir.mkdir();
				}
		} else {
				filePath = mainDir.getAbsolutePath() + "\\" + fileLocation +  " " + 
							timestamp + ".wmv";
				screenshotDir = new File(mainDir.getAbsolutePath(), "foto " + fileLocation + " " + timestamp);
				screenshotDir.mkdir();
		}	
		return filePath;
	}
	
}
