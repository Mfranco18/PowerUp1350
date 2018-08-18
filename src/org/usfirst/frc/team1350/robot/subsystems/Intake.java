// Octomecanum DriveTrain

package org.usfirst.frc.team1350.robot.subsystems;

import org.usfirst.frc.team1350.robot.OI;
import org.usfirst.frc.team1350.robot.commands.IntakeControl;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(motorControl);

	}

	// called by the IntakeControl to notify what the commands class requires
	private static Intake instance;

	public static Intake getInstance() {
		if (instance == null) {
			instance = new Intake();
		}
		return instance;
	}

	// Declaring the motor controllers
	VictorSP leftIntakeMotorController;
	VictorSP rightIntakeMotorController;
	VictorSP IntakeLifterMotorControllers;

	// declaring the control for the motor controllers
	static DifferentialDrive intakeWheels;
	DifferentialDrive intakeLifter;

	static DoubleSolenoid intakeSolenoids;
	Trigger IntakePistonsIn;
	Trigger IntakePistonsOut;

	IntakeControl motorControl;

	static Encoder IntakeEncoder;

	// timer
	Timer timer = new Timer();

	double encoderco; // this will be how much each revolution is

	public Intake() {
		// Log.info("Creating Drivetrain subsystem");
		// init();
	}

	public void init() {

		motorControl = IntakeControl.getInstance();

		leftIntakeMotorController = new VictorSP(3);
		rightIntakeMotorController = new VictorSP(4);
		IntakeLifterMotorControllers = new VictorSP(5);

		intakeWheels = new DifferentialDrive(leftIntakeMotorController, rightIntakeMotorController);
		intakeLifter = new DifferentialDrive(IntakeLifterMotorControllers, IntakeLifterMotorControllers);

		intakeSolenoids = new DoubleSolenoid(0, 1);
		intakeSolenoids.set(DoubleSolenoid.Value.kForward);

		IntakePistonsIn = new JoystickButton(OI.getInstance().XboxControllerLeft, 5);
		IntakePistonsOut = new JoystickButton(OI.getInstance().XboxControllerLeft, 6);

		IntakeEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);

		encoderco = 770 / 90;

		IntakeEncoder.setDistancePerPulse(encoderco);
		IntakeEncoder.reset();

	}

	// move the wheels for the intake the speed will include the direction
	public static void driveIntake(double speed) {

		// drive the robot with left remote controlling total direction
		// and the right remote controlling the rotation
		// SmartDashboard.putString("DB/String 4", "driveintake" + speed);

		intakeWheels.arcadeDrive(speed, 0);

		// SmartDashboard.putString("DB/String 8", "encR Raw " +
		IntakeEncoder.getRaw();

	}

	// Moves the intake system up and down
	public void driveIntakeLifter(double speed) {

		// drive the robot with left remote controlling total direction
		// and the right remote controlling the rotation
		intakeLifter.arcadeDrive(speed, 0);
		// SmartDashboard.putString("DB/String 8", "encR Raw " +
		// IntakeEncoder.getRaw());

	}

	public void moveIntake() {
		// move the intakes in and out
		if (IntakePistonsIn.get()) {
			// SmartDashboard.putString("DB/String 1", "The intake was called
			// push");
			intakeSolenoids.set(DoubleSolenoid.Value.kForward);
		}

		if (IntakePistonsOut.get()) {
			// SmartDashboard.putString("DB/String 2", "The intake was called
			// pull");
			intakeSolenoids.set(DoubleSolenoid.Value.kReverse);
		}
		// SmartDashboard.putString("DB/String 8", "encR Raw " +
		// IntakeEncoder.getRaw());

	}

	public static void moveIntakeIn() {
		intakeSolenoids.set(DoubleSolenoid.Value.kForward);
	}

	public static void moveIntakeOut() {
		intakeSolenoids.set(DoubleSolenoid.Value.kReverse);
	}

	// used by auto to close the intake arms
	public void closeIntake() {
		intakeSolenoids.set(DoubleSolenoid.Value.kForward);
	}

	// used by auto to open the intake arms
	public void openIntake() {
		intakeSolenoids.set(DoubleSolenoid.Value.kReverse);
	}

	public void getEncoder() {
		// TODO Auto-generated method stub
		SmartDashboard.putString("DB/String 7", "encR Raw " + IntakeEncoder.getRaw());
	}

	public void resetEncoder() {
		IntakeEncoder.reset();
	}

	public static double currentEncoderValue() {
		// TODO Auto-generated method stub
		return IntakeEncoder.getRaw();
		// return IntakeEncoder.getDistance();
	}

}
