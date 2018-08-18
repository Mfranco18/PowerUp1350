package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1350.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDrive extends Command {

	private DriveTrain drivetrain;

	int counter = 0;
	double tolerance = 1;
	int timeCounts;
	boolean isFinished = false;
	double speedL = 0.24;
	double speedR = .2; // * 0.8;
	double delta = 0.001; // represents the changes made between motor speeds to
							// drive straight
	int maxCubeheight = 200;
	double angleDelta;
	double startingAngle;
	boolean driveStraight; // determine whether or not we would like to drive
							// straight

	protected AutoDrive(int timeCounts_, boolean driveStraight_) {
		if (timeCounts_ < 0) {
			timeCounts = -timeCounts_;
			speedR = -speedR;
			speedL = -speedL;
		} else {
			timeCounts = timeCounts_;
		}
		driveStraight = driveStraight_;
		requires(DriveTrain.getInstance());
		drivetrain = DriveTrain.getInstance();
	}

	protected void initialize() {
		SmartDashboard.putString("DB/String 3", "wait is beginning");
		startingAngle = NavX.getInstance().getHeading(); // angle at the start
															// of

		// setTimeout(time);

	}

	protected void execute() {
		drivetrain.getEncoder();
		angleDelta = (NavX.getInstance().getHeading() + 360 - startingAngle) % 360;
		counter = counter + 1;

		if (counter < timeCounts) {
			if (driveStraight) {
				if (angleDelta < tolerance || ((360 - angleDelta) < tolerance)) {
					drivetrain.autoTurn(speedL, speedR);
				} else {
					if (angleDelta > 180) {
						speedL = speedL + delta;
						speedR = speedR - delta;
						drivetrain.autoTurn(speedL, speedR);
					} else {
						speedL = speedL - delta;
						speedR = speedR + delta;
						drivetrain.autoTurn(speedL, speedR);
					}
				}
			} else {
				drivetrain.autoTurn(speedL, speedR);
			}

		} else {
			if (timeCounts > 50) {
				drivetrain.autoTurn(-speedL, -speedR);
				if (counter > timeCounts + 4) {
					isFinished = true;
				}
			} else {
				drivetrain.autoTurn(-speedL, -speedR);
				isFinished = true;
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

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
