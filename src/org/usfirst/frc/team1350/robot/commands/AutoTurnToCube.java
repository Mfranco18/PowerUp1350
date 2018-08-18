package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1350.robot.util.VisionThread1;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoTurnToCube extends Command {

	private DriveTrain drivetrain;

	double currentPixel = 160; // center of the screen, this is where we are
								// looking at all times
	double targetPixel; // from the vision thread, represents the pixel location
						// of the cube
	double tolerance = 5; // how close to requested pixel value we are willing
							// to
							// turn
	double time = 200; // for now this should not be a concern
	double speed = 0.2; // the allowed speed for turning
	boolean isFinished = false;
	// int delta;
	int counter = 0;
	double delta; // descibes the direction which we should rotate

	protected AutoTurnToCube() {
		requires(DriveTrain.getInstance());
		drivetrain = DriveTrain.getInstance();

	}

	protected void initialize() {
		SmartDashboard.putString("DB/String 0", "auto turn to cube");
	}

	protected void execute() {

		targetPixel = VisionThread1.getCubeCenterX();
		delta = targetPixel - currentPixel; // the distance in pixels that we
											// need to move, pos if we need to
											// move right, negative if we need
											// to move left
		SmartDashboard.putString("DB/String 2", "delta " + delta);

		if ((delta < tolerance && delta > 0) || ((-1 * delta) < tolerance) && delta < 0) {
			counter += 1;
			if (counter > 3) {
				isFinished = true;
			}
		} else {
			counter = 0; // ensure we get a full 3 count every time
			if ((delta < tolerance * 4 && delta > 0) || ((-1 * delta) < tolerance * 4) && delta < 0) {
				if (delta > 0) {
					drivetrain.autoTurn(speed / 2, -speed / 2);
				} else {
					drivetrain.autoTurn(-speed / 2, speed / 2);
				}
			} else {
				if (delta > 0) {
					drivetrain.autoTurn(speed, -speed);
				} else {
					drivetrain.autoTurn(-speed, speed);
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
		SmartDashboard.putString("DB/String 8", "auto turn finished");

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
