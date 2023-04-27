/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;


import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private PWMSparkMax m_intakeMotor;
  private PWMSparkMax m_armMotor;
  /**
   * Creates a new Intake.
   */
  public Intake() {
    m_intakeMotor = new PWMSparkMax(Constants.kIntakeMotor);
    m_armMotor = new PWMSparkMax(Constants.kArmMotor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setIntakeSpeed(double speed) {
    if(speed < -1 || speed > 1) {
      speed = 0;
    }
    m_intakeMotor.set(speed);
  }
  public void setArmSpeed(double speed) {
    if(speed < -1 || speed > 1) {
      speed = 0;
    }
    m_armMotor.set(speed);
  }
  public void manualControl(double rawAxis, double rawAxis2, boolean button) {
    System.out.println(String.format("Drivetrain raw: X %.2f Y %.2f", rawAxis, rawAxis2));
  }
}