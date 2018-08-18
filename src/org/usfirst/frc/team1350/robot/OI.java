// Marco Franco 2017 

package org.usfirst.frc.team1350.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	// Singleton
	private static OI instance;

	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}

	public Joystick leftStick = new Joystick(1);
	public Button solenoidSwitch = new JoystickButton(leftStick, 1);

	public Joystick rightStick = new Joystick(2);
	public Button JoyRightTrigger = new JoystickButton(rightStick, 1);

	public Joystick XboxControllerLeft = new Joystick(3);

	// test

}
