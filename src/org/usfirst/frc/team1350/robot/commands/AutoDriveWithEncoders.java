package org.usfirst.frc.team1350.robot.commands;

import org.usfirst.frc.team1350.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1350.robot.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoDriveWithEncoders extends Command {

	int botLength = 32; // length of the bot
	int targetDistance; // this will be the input, overall distance to move
	private double speedL = 0.4;
	private double speedR = 0.42;
	int decelerationRange = 300; // how long to stop
	int accelerationRange = 200; // how long to get to top speed
	boolean isFinishedR, isFinishedL;
	double deltaDistance; // represents how much we must actually move, in
							// encoder raw units
	double encoderScalar = 10.575; // determined experimentally, represents the
									// tic to inch ratio
	double encoderTicksTargets; // the total number of encoder ticks that
								// represent
								// the distance

	private DriveTrain drivetrain;
	double difference = 0.1;

	int distanceToGo = 0;
	private double speed = 0.4;
	private double curve = -0.04;
	private int iterations = 0;

	double angle;
	double tolerance = 3;
	double time = 15;

	double distance;
	final double VMAX = 0.25;
	final double VMIN = 0.2;
	double vel;
	int distanceRemaining;
	final int DECELERATION_RANGE = 200;
	final int ACCELERATION_RANGE = 100;
	double scalar;
	private boolean push;
	int initialLeft, initialRight;
	double speedLSlow = .34;
	double speedRSlow = 0.3;
	double speedLFast = 0.64;
	double speedRFast = 0.6;

	// input will be the overall distance that the bot needs to travel
	protected AutoDriveWithEncoders(int dis) {
		targetDistance = dis;
		encoderTicksTargets = (targetDistance) * encoderScalar;
		requires(DriveTrain.getInstance());
		drivetrain = DriveTrain.getInstance();
	}

	protected void initialize() {
		angle = NavX.getInstance().getHeading();

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

		drivetrain.resetDriveTrainEncoders();

		vel = VMAX;

		distanceRemaining = 0;

		scalar = 1.1;

		initialLeft = drivetrain.getLeftEncoder();
		initialRight = drivetrain.getRightEncoder();
	}

	protected void execute() {

		int left = drivetrain.getLeftEncoder() - initialLeft;
		int right = drivetrain.getRightEncoder() - initialRight;

		SmartDashboard.putString("DB/String 0", "left = " + left);
		SmartDashboard.putString("DB/String 1", "right = " + right);
		SmartDashboard.putString("DB/String 5", "I am in autodrive");

		if (left >= encoderTicksTargets || right >= encoderTicksTargets) {
			isFinishedL = true;
			SmartDashboard.putString("DB/String 8", "" + encoderTicksTargets);
		}
		vel = VMAX;

		distanceRemaining = (int) (encoderTicksTargets - (left + right) / 2);

		if ((!isFinishedR && !isFinishedL)) {

			if ((left + right) / 2 < ACCELERATION_RANGE) {
				// vel = VMIN + ((VMAX - VMIN) / ACCELERATION_RANGE) * (left +
				// right) / 2;
				if (left > right + 3) {
					drivetrain.autoTurn(speedLSlow - difference, speedRSlow + difference);
				} else {
					if (right > left + 3) {
						drivetrain.autoTurn(speedLSlow + difference, speedRSlow - difference);
					} else {
						drivetrain.autoTurn(speedLSlow, speedRSlow);
					}
				}

			} else {
				if (distanceRemaining < DECELERATION_RANGE) {
					// vel = ((VMIN - VMAX) / -DECELERATION_RANGE) *
					// distanceRemaining + VMIN;
					if (left > right + 3) {
						drivetrain.autoTurn(speedLSlow - difference, speedRSlow + difference);
					} else {
						if (right > left + 3) {
							drivetrain.autoTurn(speedLSlow + difference, speedRSlow - difference);
						} else {
							drivetrain.autoTurn(speedLSlow, speedRSlow);
						}
					}

				} else {
					if (left > right + 3) {
						drivetrain.autoTurn(speedLFast - difference, 0.6 + difference);
					} else {
						if (right > left + 3) {
							drivetrain.autoTurn(speedLFast + difference, speedRFast - difference);
						} else {
							drivetrain.autoTurn(speedLFast, speedRFast);
						}
					}

				}
			}
		}

	}

	// Make this return false when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (isFinishedL || isFinishedR);

		// return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		drivetrain.autoTurn(-0.1, -0.1);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

}
