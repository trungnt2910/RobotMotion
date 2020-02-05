/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.PIDController;
import frc.robot.subsystems.Drivebase;
import static frc.robot.Constants.Turn.*;

public class TurnToAngle extends CommandBase {
  /**
   * Creates a new TurnToAngle.
   */
  Drivebase __drivebase;

  PIDController speedTuner;
  double tAngle;

  double current_error;

  public TurnToAngle(Drivebase drivebase, final double targetAngle) 
  {
    tAngle = targetAngle;
    __drivebase = drivebase;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(__drivebase);
    speedTuner = new PIDController(0.02, turn_kI, turn_kD);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    current_error = tAngle - __drivebase.getGyroAngle();
    double output = speedTuner.output(current_error);
    double current_speed =  MathUtil.clamp(output, -0.17, 0.17);
    SmartDashboard.putNumber("Current error: ", current_error);
    
    SmartDashboard.putNumber("Current speed: ", current_speed);
    __drivebase.tankDrive(current_speed, -current_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    __drivebase.Stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    // return Math.abs(current_error) < angleErrorTolerence;
    return false;
  }
}
