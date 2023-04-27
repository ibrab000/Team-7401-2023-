/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands.autonomous.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.Subsystems.Drivebase;
import frc.robot.Subsystems.Intake;

public class SetDrivetrainSpeedForTime extends CommandBase {
  private final Drivebase drivebase;
  private final Intake intake;
  private final double leftSpeed, rightSpeed;
  private final double durationS;
  private double startTime;

  /**
   * Creates a new SetDrivetrainSpeedForTime.
   */
  public SetDrivetrainSpeedForTime(double leftSpeed, double rightSpeed, double duration, Drivebase drivebase, Intake intake) {
    this.leftSpeed = leftSpeed;
    this.drivebase = drivebase;
    this.rightSpeed = rightSpeed;
    this.intake = intake;

    durationS = duration;
    
    addRequirements(drivebase);
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
    drivebase.manualControl(leftSpeed, rightSpeed, false);
    System.out.println(startTime);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivebase.manualControl(leftSpeed, rightSpeed, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivebase.manualControl(0.0, 0.0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean finished = Timer.getFPGATimestamp() - startTime >= durationS;
    System.out.println(Timer.getFPGATimestamp() + " " + startTime + " " + durationS);
    System.out.println(finished + " " + Timer.getFPGATimestamp());
    return Timer.getFPGATimestamp() - startTime >= durationS;
  }
}
