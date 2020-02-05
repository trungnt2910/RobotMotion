/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Drivebase;


public class DriveNMetersForward extends CommandBase {
  /**
   * Creates a new DriveNMeters.
   */

  double l;
  double s;
  Drivebase drivebase;
  boolean isNegative = false;
  boolean set = false;
  Command keep_straight;

  public DriveNMetersForward(Drivebase __drivebase, final double length, final double speed) {
    l = length;
    s = Math.abs(speed);
   // isNegative = (length < 0);
    if (isNegative) s = -s;
    // Use addRequirements() here to declare subsystem dependencies.
//    addRequirements(Robot.m_robotContainer.m_Drivebase);
    drivebase = __drivebase;
    //This command is unstable
    keep_straight = new TurnToAngle(drivebase, 0, s);
    //addRequirements(drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    drivebase.setSelectedSensorPosition(0);
    keep_straight.schedule();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    /*double factor = 1;
    if ((drivebase.RightMaster.getSelectedSensorPosition() != 0)&&(!set))
    {
      double next = drivebase.LeftMaster.getSelectedSensorPosition()/drivebase.RightMaster.getSelectedSensorPosition();
      if (Math.abs(factor - 1) < 0.001) set = true;
      if (!set) factor *= next;
    }
    SmartDashboard.putNumber("Calculated Factor: ", factor);
    drivebase.tankDrive(factor*s, s);*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (!interrupted) {
      CommandScheduler.getInstance().cancel(keep_straight);
      drivebase.Stop();
    }
    else;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    if (!isNegative)
    return (drivebase.getSensorMetricPosition() > l);
    else return (drivebase.getSensorMetricPosition() < l);
  }
}
