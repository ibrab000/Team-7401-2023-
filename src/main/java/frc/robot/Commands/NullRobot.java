/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Subsystems.Intake;
import frc.robot.Subsystems.Drivebase;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class NullRobot extends InstantCommand {

  private Intake m_intake;
  private Drivebase m_drivebase;

  public NullRobot(Intake intake, Drivebase drivebase) {

    m_intake = intake;
    m_drivebase = drivebase;
    addRequirements(intake);
    addRequirements(drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setArmSpeed(0);
    m_intake.setIntakeSpeed(0);
    m_drivebase.setRobotSpeed(0);
  }
}
