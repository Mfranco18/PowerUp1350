package org.usfirst.frc.team1350.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// PWM

	// Motor Controllers
	public static final int rightMotorController = 0;
	public static final int leftMotorController = 1;

	// Inputs

	// JoySticks
	public static final int right_Joystick = 1;
	public static final int left_Joystick = 2;
	public static final int XboxController = 3;

	public static final int xboxLeftJoystickY = 1;
	public static final int xboxRightJoystickY = 5;

	// Trigger
	public static final int driveTrainSwitch = 1;

	// Compressor
	public static final int Compressor = 0;

	public static final int xboxCompressorButtonOn = 1;
	public static final int xboxCompressorButtonOff = 2;

	// Solenoid
	public static final int SolenoidPort1 = 0;
	public static final int SolenoidPort2 = 1;
	public static final int SolenoidPort3 = 2;
	public static final int SolenoidPort4 = 3;

}
