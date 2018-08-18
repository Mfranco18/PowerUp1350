package org.usfirst.frc.team1350.robot.subsystems;

import org.usfirst.frc.team1350.robot.OI;
import org.usfirst.frc.team1350.robot.RobotMap;
import org.usfirst.frc.team1350.robot.commands.TeleOpDriveTrain;
import org.usfirst.frc.team1350.robot.util.VisionThread1;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(tankDrive);
	}

	// called by the TeleOpDriveTrain to notify what the commands class requires
	private static DriveTrain instance;

	public static DriveTrain getInstance() {
		if (instance == null) {
			instance = new DriveTrain();
		}
		return instance;
	}

	// Declaring the motor controllers
	private VictorSP leftMotorController;
	private VictorSP rightMotorController;
	private DifferentialDrive robotDrive;
	private TeleOpDriveTrain tankDrive;

	// compressor
	private Compressor compressor;

	// Trigger to turn compressor on
	Trigger compressorValueOn, compressorValueOff;

	// encoder on the left driveTrain
	static Encoder driveTrainLeftEncoder;
	// encoder on the right driveTrain
	static Encoder driveTrainRightEncoder;

	// variables for the autoDrive
	double autoSpeed = 0.5;
	double autoCurve = 0.0;

	// timer
	Timer timer = new Timer();

	public DriveTrain() {
		// Log.info("Creating Drivetrain subsystem");
		// init();
	}

	public void init() {

		// declarations for the drive system
		tankDrive = TeleOpDriveTrain.getInstance();
		leftMotorController = new VictorSP(RobotMap.rightMotorController);
		rightMotorController = new VictorSP(RobotMap.leftMotorController);
		robotDrive = new DifferentialDrive(leftMotorController, rightMotorController);

		driveTrainLeftEncoder = new Encoder(13, 12, false, Encoder.EncodingType.k1X);
		driveTrainLeftEncoder.setDistancePerPulse(770 / 770);
		driveTrainLeftEncoder.reset();

		driveTrainRightEncoder = new Encoder(14, 15, false, Encoder.EncodingType.k1X);
		driveTrainLeftEncoder.setDistancePerPulse(770 / 770);
		driveTrainRightEncoder.reset();

		// compressor
		compressor = new Compressor(RobotMap.Compressor);
		// default the compressor to be off
		compressor.setClosedLoopControl(false);

		// trigger for the compressor with the xbox controller
		compressorValueOn = new JoystickButton(OI.getInstance().XboxControllerLeft, RobotMap.xboxCompressorButtonOn);
		compressorValueOff = new JoystickButton(OI.getInstance().XboxControllerLeft, RobotMap.xboxCompressorButtonOff);

	}

	public void tankDrive(double left, double right, boolean squaredInputs) {

		// gearForwardDrive.whenInactive(driveNormal(left, right, false));
		SmartDashboard.putString("DB/String 9", "cube height" + VisionThread1.cubePixelHeight);
		robotDrive.tankDrive(left, right, false);
		compressorOn();
		// checkIntake();
		// checkIntakeWheels();
		SmartDashboard.putString("DB/String 4", "encLDT Raw " + driveTrainLeftEncoder.getRaw());
		SmartDashboard.putString("DB/String 3", "encRDT Raw " + driveTrainRightEncoder.getRaw());

	}

	// monitors the compressor for the rest of the match
	private void compressorOn() {

		if (compressorValueOn.get()) {
			SmartDashboard.putString("DB/String 0", "Compressor on");
			compressor.setClosedLoopControl(true);
			driveTrainLeftEncoder.reset();
		}

		if (compressorValueOff.get()) {
			SmartDashboard.putString("DB/String 0", "Compressor off");
			compressor.setClosedLoopControl(false);
		}

	}

	public void resetDriveTrainEncoders() {
		driveTrainLeftEncoder.reset();
		driveTrainRightEncoder.reset();
	}

	public int getLeftEncoder() {
		return driveTrainLeftEncoder.getRaw();
	}

	public int getRightEncoder() {
		return driveTrainRightEncoder.getRaw();
	}

	private Command driveNormal(double left, double right, boolean b) {
		robotDrive.tankDrive(left, right, b);
		return null;
	}

	private Command driveReverse(double left, double right, boolean temp) {
		robotDrive.tankDrive(-right, -left, temp);
		return null;
	}

	public void driveLeftMotor(double speed, double time) {
		leftMotorController.set(speed);
		Timer.delay(time);
		leftMotorController.set(0);
	}

	public void driveRightMotor(double speed, double time) {
		rightMotorController.set(-speed);
		Timer.delay(time);
		rightMotorController.set(0);
	}

	public void autoTurn(double left, double right) {
		// TODO Auto-generated method stub
		robotDrive.tankDrive(left, right, false);
	}

	public void getEncoder() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("DB/String 4", "encLDT Raw " + driveTrainLeftEncoder.getRaw());
		double temp = (driveTrainLeftEncoder.getRaw()) / 10.5;
		// SmartDashboard.putString("DB/String 3", "inches traveled = " + temp);

	}

}
