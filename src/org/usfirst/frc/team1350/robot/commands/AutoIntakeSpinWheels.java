package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoIntakeSpinWheels extends Command {

	private Intake intake;

	double count = 0; // allow us to move through 20ms iterations
	double timeCounts; // max number of iterations, dependent on whcih way the
						// motors are spinning
	double speed = 1; // the allowed speed for turning, need to check the
						// issues with going back up
	boolean isFinished = false;
	int counter = 0; // use this to ensure that we stay at the requested angle
						// for at least 60 ms
	double delta; // descibes the direction which we should rotate

	protected AutoIntakeSpinWheels(int direction) {
		if (direction == -1) {
			speed = -1 * speed;
			timeCounts = 5;
		} else {
			timeCounts = 15;
		}
		requires(Intake.getInstance());
		intake = Intake.getInstance();

	}

	protected void initialize() {
		SmartDashboard.putString("DB/String 0", "auto turn to cube");
	}

	protected void execute() {

		count = count + 1;
		if (count < timeCounts) {
			intake.driveIntake(speed);
		} else {
			isFinished = true;
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
