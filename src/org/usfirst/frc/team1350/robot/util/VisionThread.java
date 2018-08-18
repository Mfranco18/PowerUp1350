package org.usfirst.frc.team1350.robot.util;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class VisionThread extends Thread {

	@Override
	public void run() {

		// Get the UsbCamera from CameraServer
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		// Set the resolution
		camera.setResolution(640, 480);
		// int dashData0 = (int) SmartDashboard.getNumber("DB/Slider
		// 0",
		// 0.0);
		// camera.setExposureManual(dashData0);
		// int dashData0 = (int) SmartDashboard.getNumber("DB/Slider
		// 0",
		// 0.0);
		camera.setExposureManual(80);

	}

}