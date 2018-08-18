package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1350.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoTurn extends Command {

	private DriveTrain drivetrain;

	double angleToGo = 0;
	double targetHeading;
	double currentAngle = 0;
	int sideToTurn; // this will represent which way to turn
	double tolerance = 2; // how close to requested angle we are willing to
							// turn
	double time = 200; // for now this should not be a concern
	double speed = 0.2; // the allowed speed for turning
	boolean isFinished = false;
	// int delta;
	int counter = 0;
	double delta; // descibes the direction which we should rotate

	protected AutoTurn(int targetAngle_) {
		SmartDashboard.putString("DB/String 8", "auto turn beginning");
		targetHeading = targetAngle_;
		// delta = angleDelta;
		requires(DriveTrain.getInstance());
		drivetrain = DriveTrain.getInstance();

	}

	protected void initialize() {
		SmartDashboard.putString("DB/String 8", "auto turn beginning");
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
		SmartDashboard.putString("DB/String 0", "AngleToGo = " + angleToGo);

	}

	protected void execute() {

		currentAngle = NavX.getInstance().getHeading();
		delta = (currentAngle + 360 - targetHeading) % 360;
		SmartDashboard.putString("DB/String 2", "current angle = " + NavX.getInstance().getHeading());

		// drivetrain.autoTurn(speed, -speed);
		if (delta < tolerance || (360 - delta) < tolerance) {
			counter += 1;
			if (counter > 3) {
				isFinished = true;
			}
		} else {
			counter = 0; // ensure we get a full 3 count everytime
			if (delta < tolerance * 4 || (360 - delta) < tolerance * 4) {
				if (delta > 180) {
					drivetrain.autoTurn(speed / 2, -speed / 2);
				} else {
					drivetrain.autoTurn(-speed / 2, speed / 2);
				}
			} else {
				if (delta > 180) {
					drivetrain.autoTurn(speed, -speed);
				} else {
					drivetrain.autoTurn(-speed, speed);
				}
			}
		}
		// if (currentAngle < targetHeading - tolerance || currentAngle >
		// targetHeading + tolerance) {
		// counter = 0;
		// if ((currentAngle + 360 - angleToGo) % 360 < 180) {
		// drivetrain.autoTurn(speed, -speed);
		// } else {
		// drivetrain.autoTurn(-speed, speed);
		// }
		//
		// // drivetrain.autoTurn(sideToTurn * speed, -sideToTurn * speed);
		// // if (currentAngle - angleToGo < 0) {
		// // drivetrain.autoTurn(-speed, speed);
		// // } else {
		// // drivetrain.autoTurn(speed, -speed);
		// // }
		// } else {
		// counter += 1;
		// if (counter > 3) {
		// isFinished = true;
		// }
		// }
		// if (sideToTurn == 1) {
		// if (currentAngle < angleToGo - tolerance || currentAngle > angleToGo
		// + tolerance) {
		// drivetrain.autoTurn(speed, -speed);
		// }
		// } else {
		// drivetrain.autoTurn(0, 0);
		// }
		SmartDashboard.putString("DB/String 9", "NavX Angle = " + NavX.getInstance().getHeading());
		// if (side == 0) {
		// drivetrain.autoDrive(0.4, 0, 0);
		// } else {
		// drivetrain.autoDrive(-0.4, 0, 0);
		// }

		// drivetrain.autoDrive(0.4, 0, 0);

		// System.out.println("This works");

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
