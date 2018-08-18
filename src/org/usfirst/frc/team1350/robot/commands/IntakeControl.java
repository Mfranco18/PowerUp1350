// Octomecanum drivetrain control

package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.OI;
import org.usfirst.frc.team1350.robot.RobotMap;
import org.usfirst.frc.team1350.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeControl extends Command {

	private static IntakeControl instance;

	public static IntakeControl getInstance() {
		if (instance == null) {
			instance = new IntakeControl();
		}
		return instance;
	}

	// throttle variables
	private static double speed; // scalar value if I want to throttle
									// down the speed of the motor
									// controllers

	public IntakeControl() {
		requires(Intake.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		speed = 1; // intializing the scalar for speed
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// send the X and Y axis of the left remote and just the X of the right
		// this allows for the mecanum wheels to work, and to turn in tank mode
		Intake.getInstance().driveIntake(-getSpeed());
		Intake.getInstance().driveIntakeLifter(-getSpeedLifter());
		Intake.getInstance().moveIntake();
		Intake.getInstance().getEncoder();

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	// get the speed for the intake wheels
	private static double getSpeed() {
		return (OI.getInstance().XboxControllerLeft.getRawAxis(RobotMap.xboxLeftJoystickY) * speed);
	}

	// get the speed for the intake lifter
	private static double getSpeedLifter() {
		return (OI.getInstance().XboxControllerLeft.getRawAxis(RobotMap.xboxRightJoystickY) * speed);
	}

}
