package org.usfirst.frc.team1350.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

	public AutonomousCommand() {

		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		char scale = gameData.charAt(0);

		addSequential(new ResetNavXandEncoder());
		// addSequential(new waitAuto(50));

		// addSequential(new AutoDriveWithEncoders(200));
		addSequential(new AutoDriveWithEncoders(140));
		//
		// // addSequential(new AutoTurn(270));
		// addSequential(new AutoTurnWithEncoders(280));
		// if (scale == 'R') {
		// // addSequential(new AutoPickUpCube());
		// addSequential(new AutoDriveWithEncoders(50));
		// } else {
		// addSequential(new AutoDriveWithEncoders(150));
		// }
		// // //
		// addSequential(new AutoTurnWithEncoders(150));
		// // // addSequential(new AutoDriveWithEncoders(20));
		// //
		// // //
		// // // // addSequential(new AutoTurnToCube());
		// // // // addSequential(new AutoDriveToCube());
		// // // // addSequential(new AutoIntakeToPosition(1));
		// // // // addSequential(new waitAuto(50));
		// // // // addSequential(new AutoTurn(30));
		// addSequential(new AutoPickUpCube());

	}

}
