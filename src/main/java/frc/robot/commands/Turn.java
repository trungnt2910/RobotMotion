/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivebase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Turn extends PIDCommand {
  /**
   * Creates a new TurnToAngle.
   */
  public Turn(final Drivebase drivebase, final double targetAngle) {
    super(
        // The controller that the command will use
        new PIDController(0.2, 0.01, 1.6),
        // This should return the measurement
        drivebase::getGyroYaw,
        // This should return the setpoint (can also be a constant)
        targetAngle,
        // This uses the output
        output -> {
          drivebase.arcadeDrive(0 , output);
        });
    //This treats the min and max values as the same points.
    getController().enableContinuousInput(-180, 180);
    //Setting our tolerence.
    getController().setTolerance(Constants.Gyro.PositionTolerence, Constants.Gyro.VelocityTolerence);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
