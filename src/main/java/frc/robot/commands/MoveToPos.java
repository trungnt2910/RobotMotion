/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivebase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class MoveToPos extends SequentialCommandGroup {
  /**
   * Creates a new MoveToPos.
   */
  public MoveToPos(Drivebase drivebase, final double length, final double angle, final double speed) {
    // Add your commands in the super() call, e.g.
    super(new TurnToAngle(drivebase, angle), new DriveNMeters(drivebase, length, speed));
  }
}
