
//2017-01-22test

package org.usfirst.frc.team1350.robot;

import org.usfirst.frc.team1350.robot.commands.AutonomousCommand;
import org.usfirst.frc.team1350.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1350.robot.subsystems.Intake;
import org.usfirst.frc.team1350.robot.subsystems.NavX;
import org.usfirst.frc.team1350.robot.util.VisionThread1;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain drivetrain;
	public static Intake intake;

	// public static OI
	public static NavX navx;

	// not sure if i should integrate the object iden into the auto class itself
	// public static ObjectIdentification obIdentification;

	// Command autonomousCommand;
	Command autoComm;

	SendableChooser<Command> chooser = new SendableChooser<>();

	private VisionThread1 visionThread;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		// chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);

		// activates the drive train
		drivetrain = DriveTrain.getInstance();
		drivetrain.init();

		navx = NavX.getInstance();
		navx.init();

		visionThread = new VisionThread1();
		visionThread.start();

		intake = Intake.getInstance();
		intake.init();

		SmartDashboard.putString("DB/String 0", "");
		SmartDashboard.putString("DB/String 1", "");
		SmartDashboard.putString("DB/String 2", "");
		SmartDashboard.putString("DB/String 3", "");
		SmartDashboard.putString("DB/String 4", "");
		SmartDashboard.putString("DB/String 5", "");
		SmartDashboard.putString("DB/String 6", "");
		SmartDashboard.putString("DB/String 7", "");
		SmartDashboard.putString("DB/String 8", "");
		SmartDashboard.putString("DB/String 9", "");
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		SmartDashboard.putString("DB/String 6", "auto is started1");
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)b
		autoComm = new AutonomousCommand();
		if (autoComm != null)
			autoComm.start();
		SmartDashboard.putString("DB/String 6", "auto is started1");
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autoComm != null)
			autoComm.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		// practice

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
