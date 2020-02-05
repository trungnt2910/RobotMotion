/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivebase;


public class DriveNMeters extends CommandBase {
  /**
   * Creates a new DriveNMeters.
   */

  double l;
  double s;
  Drivebase __drivebase;
  boolean isNegative = false;

  double factor;

  public DriveNMeters(Drivebase drivebase, final double length, final double speed) {
    l = length;
    s = Math.abs(speed);
    isNegative = (length < 0);
    if (isNegative) s = -s;
    // Use addRequirements() here to declare subsystem dependencies.
//    addRequirements(Robot.m_robotContainer.m_Drivebase);
    __drivebase = drivebase;
    factor = 1;
    addRequirements(__drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    __drivebase.setSelectedSensorPosition(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
  // Old code to keep the robot going straight by the calculation of the ratio of the two wheels in runtime.
    if (__drivebase.RightMaster.getSelectedSensorPosition() != 0)
    {
      double next = __drivebase.LeftMaster.getSelectedSensorPosition()/__drivebase.RightMaster.getSelectedSensorPosition();
      factor *= next;
    }
    __drivebase.tankDrive(factor*s, s);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    __drivebase.Stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    if (!isNegative)
    return (__drivebase.getSensorMetricPosition() > l);
    else return (__drivebase.getSensorMetricPosition() < l);
  }
}
