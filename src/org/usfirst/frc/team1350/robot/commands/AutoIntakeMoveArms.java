package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoIntakeMoveArms extends Command {

	private Intake intake;

	double direction; // this will establish if the arms open or close
	boolean isFinished = false;
	int counter = 0; // use this to ensure that we stay at the requested angle
						// for at least 60 ms
	int timeCounts = 5;

	protected AutoIntakeMoveArms(int direction_) {
		direction = direction_;
		requires(Intake.getInstance());
		intake = Intake.getInstance();

	}

	protected void initialize() {
		SmartDashboard.putString("DB/String 0", "auto turn to cube");
	}

	protected void execute() {

		counter = counter + 1;

		if (counter < timeCounts) {
			if (direction == 1) {
				intake.openIntake();
			} else {
				intake.closeIntake();
			}
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
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
