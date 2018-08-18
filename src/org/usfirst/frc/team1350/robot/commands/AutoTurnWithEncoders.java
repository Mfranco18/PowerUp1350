package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1350.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoTurnWithEncoders extends Command {

	private DriveTrain drivetrain;

	double angleToGo = 0;
	double targetValue;
	double currentAngle = 0;
	int sideToTurn; // this will represent which way to turn
	double tolerance = 2; // how close to requested angle we are willing to
							// turn
	double time = 200; // for now this should not be a concern
	double speed = 0.2; // the allowed speed for turning
	boolean isFinished = false;
	// double delta;
	int counter = 0;
	double delta = .05; // descibes the direction which we should rotate
	double speedL = -0.45;
	double speedR = 0.45;
	int initialLeft, initialRight;

	protected AutoTurnWithEncoders(int input) {
		SmartDashboard.putString("DB/String 8", "auto turn beginning");
		targetValue = input;
		// delta = angleDelta;
		requires(DriveTrain.getInstance());
		drivetrain = DriveTrain.getInstance();

	}

	protected void initialize() {
		SmartDashboard.putString("DB/String 0", "this guy started");
		counter = 0;
		setTimeout(time);

		currentAngle = NavX.getInstance().getHeading(); // angle at the start of
		// the command

		// angleToGo = (currentAngle + delta + 360) % 360;
		//
		// if (delta > 0) {
		// sideToTurn = 1;
		// } else {
		// sideToTurn = -1;
		// }

		// to be
		initialLeft = drivetrain.getLeftEncoder();
		initialRight = drivetrain.getRightEncoder();
		SmartDashboard.putString("DB/String 0", "AngleToGo = " + angleToGo);

	}

	protected void execute() {

		SmartDashboard.putString("DB/String 5", "I am in autoturn");

		int left = drivetrain.getLeftEncoder() - initialLeft;
		int right = drivetrain.getRightEncoder() - initialRight;

		SmartDashboard.putString("DB/String 0", "left = " + left);
		SmartDashboard.putString("DB/String 1", "right = " + right);

		counter = counter + 1;

		if (right > targetValue) {
			isFinished = true;
		} else {
			int sum = right + left;
			if (sum > 3) {
				drivetrain.autoTurn(speedL - delta, speedR - delta);
			} else {
				if (sum < -3) {
					drivetrain.autoTurn(speedL + delta, speedR + delta);
				} else {
					drivetrain.autoTurn(speedL, speedR);
				}
			}
		}

	}

	// Make this return false when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (isFinished);
		// return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		SmartDashboard.putString("DB/String 0", "this guy finished");

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
