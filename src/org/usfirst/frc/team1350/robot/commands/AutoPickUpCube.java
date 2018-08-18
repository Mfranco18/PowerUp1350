package org.usfirst.frc.team1350.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPickUpCube extends CommandGroup {

	public AutoPickUpCube() {
		// parameter for movetoangle will be 0,1,2 representing which position
		// the intake should be moved to, 0 representing top, 2 bottom

		// parameter for spin wheels will be positive 1 to push out and negative
		// 1 to pull in

		// parameter for move arms, positive 1 will be close, and negative 1
		// will
		// be open

		// addSequential(new AutoDriveWithEncoders(10));
		// addSequential(new AutoIntakeToPosition(1));
		addSequential(new AutoIntakeSpinWheels(1));

		// addSequential(new AutoIntakeMoveArms(1));
		//
		// addSequential(new AutoIntakeToPosition(2));
		//
		// // addSequential(new AutoTurnToCube());
		//
		// addSequential(new AutoIntakeMoveArms(-1));
		// addSequential(new AutoIntakeSpinWheels(-1));
		//
		// addSequential(new AutoIntakeToPosition(1));
		//
		// // --addSequential(new AutoDrive(21, true));
		// addSequential(new AutoIntakeSpinWheels(1));

		// addSequential(new AutoDrive(-10, true));
		//
		// addSequential(new AutoTurnWithEncoders(30));
		//
		// addSequential(new AutoDriveWithEncoders(10));
		//
		// addSequential(new AutoIntakeToPosition(2));
		// addSequential(new AutoIntakeMoveArms(1));
		// addSequential(new AutoIntakeSpinWheels(-1));
		//
		// addSequential(new AutoIntakeToPosition(1));
		// addSequential(new AutoTurn(1));
		//
		// addSequential(new AutoIntakeToPosition(1));
		// addSequential(new AutoIntakeSpinWheels(1));

	}
}
