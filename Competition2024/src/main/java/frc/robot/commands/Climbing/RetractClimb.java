// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climbing;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class RetractClimb extends Command {
  /** Creates a new RetractClimb. */
  private ClimbSubsystem climb;
  public RetractClimb(ClimbSubsystem p_climb) {
    climb = p_climb;
    addRequirements(climb);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climb.retractClimb();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climb.stopClimb();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return climb.isRetracted();
  }
}
