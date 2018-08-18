package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1350.robot.util.VisionThread1;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveToCube extends Command {

	private DriveTrain drivetrain;

	int counter = 0;
	double tolerance = 5; // how many pixels we are willing to be off the center
	int timeCounts;
	boolean isFinished = false;
	double speedL = 0.3;
	double speedR = speedL;// speedL * 0.8;
	double delta = 0.003; // represents the changes made between motor speeds to
							// drive straight
	int maxCubeHeight = 105; // once the cube becomes this large, the auto will
								// go to the pick up method
	double angleDelta;
	double startingAngle;
	double cubeCenter; // taken from vision, this is the center of the cube
	double cubeHeight; // taken from vision, this is our exit parameter

	protected AutoDriveToCube() {
		requires(DriveTrain.getInstance());
		drivetrain = DriveTrain.getInstance();
	}

	protected void initialize() {
		timeCounts = 22;

	}

	protected void execute() {
		cubeCenter = VisionThread1.getCubeCenterX();
		cubeHeight = VisionThread1.getCubeHeight();
		counter = counter + 1;

		if (cubeHeight < maxCubeHeight) {
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
			drivetrain.autoTurn(-speedL, -speedR);
			if (counter > timeCounts + 4) {
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
