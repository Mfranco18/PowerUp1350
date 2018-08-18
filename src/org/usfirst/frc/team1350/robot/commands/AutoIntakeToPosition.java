package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoIntakeToPosition extends Command {

	private Intake intake;

	double currentEncoderValue; // encoder raw value
	double targetValue; // based on input, 0 = top, 1 = switch and 2 = vault
	double encoder0Value = 0; // the initial straight up position
	double encoder1Value = 950; // tested, raw encoder value for shooting into
								// the switch
	double encoder2Value = 3300; // tested, raw encoder value for grabbing cubes
									// and vault
	double tolerance = 50; // how close to target raw value
	double time = 200; // for now this should not be a concern
	double speed = 0.9; // the allowed speed for turning, need to check the
						// issues with going back up
	boolean isFinished = false;
	int counter = 0; // use this to ensure that we stay at the requested angle
						// for at least 60 ms
	double delta; // descibes the direction which we should rotate

	protected AutoIntakeToPosition(int pos) {
		if (pos == 1) {
			targetValue = encoder1Value;
		} else {
			if (pos == 2) {
				targetValue = encoder2Value;
			} else {
				targetValue = encoder0Value;
			}
		}
		requires(Intake.getInstance());
		intake = Intake.getInstance();

	}

	protected void initialize() {
		SmartDashboard.putString("DB/String 0", "auto turn to cube");
	}

	protected void execute() {

		currentEncoderValue = Intake.currentEncoderValue();

		delta = targetValue - currentEncoderValue; // the distance in encoder
													// raw values that the motor
													// must move

		if ((delta < tolerance && delta > 0) || ((-1 * delta) < tolerance) && delta < 0) {
			counter += 1;
			if (counter > 3) {
				isFinished = true;
			}
		} else {
			counter = 0; // ensure we get a full 3 count every time
			if ((delta < tolerance * 4 && delta > 0) || ((-1 * delta) < tolerance * 4) && delta < 0) {
				if (delta > 0) {
					intake.driveIntakeLifter(-speed / 2);
				} else {
					intake.driveIntakeLifter(speed / 2);
				}
			} else {
				if (delta > 0) {
					intake.driveIntakeLifter(-speed);
				} else {
					intake.driveIntakeLifter(speed);
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
