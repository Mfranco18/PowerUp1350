package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1350.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class waitAuto extends Command {

	private DriveTrain drivetrain;

	int counter = 0;
	int timeCounts;
	boolean isFinished = false;

	protected waitAuto(int timeCounts_) {
		timeCounts = timeCounts_;
	}

	protected void initialize() {
		SmartDashboard.putString("DB/String 3", "wait is beginning");

		// setTimeout(time);

		// SmartDashboard.putString("DB/String 0", "");
		// SmartDashboard.putString("DB/String 1", "");
		// SmartDashboard.putString("DB/String 2", "");
		// SmartDashboard.putString("DB/String 3", "");
		// SmartDashboard.putString("DB/String 4", "");
		// SmartDashboard.putString("DB/String 5", "");
		// SmartDashboard.putString("DB/String 6", "");
		// SmartDashboard.putString("DB/String 7", "");
		// SmartDashboard.putString("DB/String 8", "");
		// SmartDashboard.putString("DB/String 9", "");

	}

	protected void execute() {
		if (counter < timeCounts) {
			counter = counter + 1;
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
		SmartDashboard.putString("DB/String 3", "wait is finished");
		Intake.getInstance().driveIntakeLifter(1);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
