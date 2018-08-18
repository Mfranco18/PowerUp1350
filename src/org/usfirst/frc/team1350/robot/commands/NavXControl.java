package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class NavXControl extends Command {

	private static NavXControl instance;

	public static NavXControl getInstance() {
		if (instance == null) {
			instance = new NavXControl();
		}
		return instance;
	}

	public NavXControl() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(NavX.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putString("DB/String 1", "Nav = init ");

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		float temp = NavX.getInstance().getHeading();
		SmartDashboard.putString("DB/String 6", "Nav = " + temp);
		NavX.getInstance().showXDisplacment();
		if (SmartDashboard.getBoolean("DB/Button 1", false)) {
			NavX.getInstance().zeroNavX();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
