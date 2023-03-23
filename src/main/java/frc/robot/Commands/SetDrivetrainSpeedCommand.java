/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Drivebase;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class SetDrivetrainSpeedCommand extends CommandBase {
  private final Drivebase m_Drivebase;
  private final DoubleSupplier m_leftAxis;
  private final DoubleSupplier m_rightAxis;
  private final BooleanSupplier m_turbo;

  public SetDrivetrainSpeedCommand(DoubleSupplier leftAxis, DoubleSupplier rightAxis, BooleanSupplier turbo, Drivebase drivebase) {
    m_Drivebase = drivebase;
    m_leftAxis = leftAxis;
    m_rightAxis = rightAxis;
    m_turbo = turbo;
    addRequirements(drivebase);
  }

  @Override
  public void execute() {
    // Default speed multiplier = 70%
    double speedMultiplier = .7;
    double leftAxis = m_leftAxis.getAsDouble();
    double rightAxis = m_rightAxis.getAsDouble();

    // If the turbo button is not pressed, change the speed multipier to 70%
    if(!m_turbo.getAsBoolean()) {
      leftAxis *= speedMultiplier;

      //if(!Constants.isCurvatureDrive) {
      rightAxis *= 0.8;
      
      if(rightAxis > 0) {
        rightAxis *= rightAxis;
      } else {
        rightAxis *= rightAxis;
        rightAxis *= -1;
      }
      //}
    }

    m_Drivebase.manualControl(leftAxis, rightAxis, m_turbo.getAsBoolean());
  }

   // Make this return true when this Command no longer needs to run execute()
   @Override
   public boolean isFinished() {
     return false; // Runs until interrupted
   }
}
