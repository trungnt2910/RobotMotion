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
public class AutonomousCommand extends SequentialCommandGroup {
  /**
   * Creates a new AutonomousCommand.
   */
  public AutonomousCommand(final Drivebase drivebase) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    
    //This is the commmand we are going to use:

    //super(new MoveToPos(drivebase, 45, 5.0, 0.6)); //The robot should turn right by 45 degrees, then go straight 5.0 meters with 0.6 output.
    super(new TurnToAngle(drivebase, 180).withTimeout(5));
  }
}
