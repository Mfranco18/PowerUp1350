package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1350.robot.subsystems.Intake;
import org.usfirst.frc.team1350.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ResetNavXandEncoder extends Command {

	private DriveTrain drivetrain;

	boolean isFinished = false;

	protected ResetNavXandEncoder() {
		requires(NavX.getInstance());

		NavX.getInstance().zeroNavX(); // zero our navx to our current heading

		Intake.getInstance().resetEncoder();

		DriveTrain.getInstance().resetDriveTrainEncoders();

	}

	protected void initialize() {
		SmartDashboard.putString("DB/String 3", " reset called");

		// setTimeout(time);

		SmartDashboard.putString("DB/String 0", "");
		SmartDashboard.putString("DB/String 1", "");
		SmartDashboard.putString("DB/String 2", "");
		SmartDashboard.putString("DB/String 3", "");
		SmartDashboard.putString("DB/String 4", "");
		SmartDashboard.putString("DB/String 5", "");
		SmartDashboard.putString("DB/String 6", "");
		SmartDashboard.putString("DB/String 7", "");
		SmartDashboard.putString("DB/String 8", "");
		SmartDashboard.putString("DB/String 9", "");

	}

	protected void execute() {
		isFinished = true;
	}

	// Make this return false when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (isTimedOut() || isFinished);
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
