// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Driving.DriveDistance;
import frc.robot.commands.Intaking.Intake;
import frc.robot.commands.Intaking.IntakeAnglePID;
import frc.robot.commands.Shooting.AdvanceNote;
import frc.robot.subsystems.AprilVisionSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ZachTwoNote extends SequentialCommandGroup {
  /** Creates a new ZachTwoNote. */
  public ZachTwoNote(DriveSubsystem drive, ShooterSubsystem shoot, IntakeSubsystem intake, AprilVisionSubsystem aprilVision) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelCommandGroup(

        new SmartShoot(shoot, aprilVision),
        new WaitCommand(3).andThen(new AdvanceNote(shoot).withTimeout(0.1))
      ).withTimeout(3.1),
      new ParallelCommandGroup(
        new DriveDistance(drive, 2),
        new IntakeAnglePID(intake, () -> 195)
      ),
      new ParallelRaceGroup(
        new DriveDistance(drive, 1),
        new Intake(intake)
      ),
      new ParallelCommandGroup(
        new SmartShoot(shoot, aprilVision),
        new WaitCommand(3).andThen(new AdvanceNote(shoot).withTimeout(0.1))
      ).withTimeout(3.1)
    );
  }
}
