/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Subsystems.Intake;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivebase extends SubsystemBase {
  public final VictorSP leftLeader = new VictorSP(Constants.klDT1);
  private final VictorSP leftFollower = new VictorSP(Constants.klDT2);
  private final VictorSP rightLeader = new VictorSP(Constants.krDT3);
  private final VictorSP rightFollower = new VictorSP(Constants.krDT4);
  
  private final MotorControllerGroup motorControllerGroupLeft = new MotorControllerGroup(leftLeader, leftFollower);
  private final MotorControllerGroup motorControllerGroupRight = new MotorControllerGroup(rightLeader, rightFollower);


  private DifferentialDrive drivetrain;
  private Intake intake;

  public DifferentialDrive getDrivetrain(){
    return drivetrain;
  }

  public Intake getIntake(){
    return intake;
  }

  // drive train speed, set to 0.x for X% speed
  private final double drivetrainMultiplier = 1.0;

  /**
   * Creates a new Drivebase.
   */
  public Drivebase() {
    double rampRate = .25;
    rightLeader.set(rampRate);
    rightFollower.set(rampRate);
    leftLeader.set(rampRate);
    leftFollower.set(rampRate);

    
    leftFollower.equals(leftLeader);
    rightFollower.equals(rightLeader);


    drivetrain = new DifferentialDrive(motorControllerGroupLeft, motorControllerGroupRight);
    drivetrain.setMaxOutput(drivetrainMultiplier);
  
    intake = new Intake();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void manualControl(double rawAxis, double rawAxis2, boolean button) {
    System.out.println(String.format("Drivetrain raw: X %.2f Y %.2f", rawAxis, rawAxis2));
    
    
    drivetrain.tankDrive(rawAxis, rawAxis2);

    
  }

  
}
