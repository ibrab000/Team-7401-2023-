/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Commands.NullArm;
import frc.robot.Commands.RunArmCC;
import frc.robot.Commands.RunArmCW;
import frc.robot.Commands.TurnOffIntake;
import frc.robot.Commands.IntakeCC;
import frc.robot.Commands.IntakeCW;
import frc.robot.Commands.autonomous.commands.SetDrivetrainSpeedForTime;
import frc.robot.Commands.autonomous.modes.DriveStraightAuton;
import frc.robot.Commands.autonomous.modes.NoopAuton;
import frc.robot.Subsystems.Drivebase;
import frc.robot.Commands.autonomous.commands.Turn90;
import frc.robot.Commands.autonomous.commands.TurnNegative90;
import edu.wpi.first.wpilibj.XboxController;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to each mode, as described in the TimedRobot 
 * documentation. If you change the name of this class or the package after creating this project, you must also update the build.gradle file in the 
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private Command m_setDrivetrainSpeedForTime;
  private Command m_driveStraightAuto;
  private Command m_nothing;
  private Command m_turn90;
  private Command m_turnNegative90;
  private Command m_intakeCW;
  private Command m_intakeCC;
  private Command m_turnOffIntake;
  private Command m_runArmCW;
  private Command m_runArmCC;
  private Command m_nullArm;
  
  private RobotContainer m_robotContainer;

  private final XboxController m_xboxController = new XboxController(1);

  private SendableChooser<Command> chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   * @param(m_robotContainer.drivebase.getIntake())
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    if(m_robotContainer == null){
      m_robotContainer = new RobotContainer();
    }

    // UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture();
    // camera0.setResolution(320, 240);
    // camera0.setFPS(20);
    m_nothing = new NoopAuton();
    m_driveStraightAuto = new DriveStraightAuton(m_robotContainer.drivebase);
    m_setDrivetrainSpeedForTime = new SetDrivetrainSpeedForTime(0.25, 0.25, 1, m_robotContainer.drivebase);
    m_turn90 = new Turn90(m_robotContainer.drivebase);
    m_turnNegative90 = new TurnNegative90(m_robotContainer.drivebase);
    m_intakeCW = new IntakeCW(m_robotContainer.drivebase.getIntake());
    m_intakeCC = new IntakeCC(m_robotContainer.drivebase.getIntake());
    m_turnOffIntake = new TurnOffIntake(m_robotContainer.drivebase.getIntake());
    m_runArmCW = new RunArmCW(m_robotContainer.drivebase.getIntake());
    m_runArmCC = new RunArmCC(m_robotContainer.drivebase.getIntake());
    m_nullArm = new NullArm(m_robotContainer.drivebase.getIntake());

    chooser.addOption("Nothing", m_nothing);
		chooser.addOption("Drive forward", m_driveStraightAuto);
    chooser.addOption("SetDrivetrainSpeedForTime", m_setDrivetrainSpeedForTime);
    chooser.addOption("Turn 90", m_turn90);
    chooser.addOption("Turn -90", m_turnNegative90);
    chooser.setDefaultOption("Drive forward", m_driveStraightAuto);
    SmartDashboard.putData("Auto Selector", chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  
  // This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
  @Override
  public void autonomousInit() {
    
    Command command = chooser.getSelected();

    // schedule the autonomous command (example)
    if(command != null){
      command.schedule();
    }
  }

  
  
  // This function is called periodically during autonomous.
  @Override
  public void autonomousPeriodic() {
    m_robotContainer.drivebase.getDrivetrain().feed();

  }
  
  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    JoystickButton yButton = new JoystickButton(m_xboxController, XboxController.Button.kY.value);
    // JoystickButton xButton = new JoystickButton(m_xboxController, XboxController.Button.kX.value);
    JoystickButton aButton = new JoystickButton(m_xboxController, XboxController.Button.kA.value);
    JoystickButton bButton = new JoystickButton(m_xboxController, XboxController.Button.kB.value);
    JoystickButton lBumper = new JoystickButton(m_xboxController, XboxController.Button.kLeftBumper.value);
    JoystickButton rBumper = new JoystickButton(m_xboxController, XboxController.Button.kRightBumper.value);
    
    // yButton.onTrue(m_turn90);
    // xButton.onTrue(m_turnNegative90);

    // aButton.onTrue(m_intakeCC);
    // yButton.onTrue(m_intakeCW);
    // bButton.onTrue(m_turnOffIntake);
    // lBumper.toggleOnTrue(m_runArmCC);
    // lBumper.toggleOnFalse(m_nullArm);
    // rBumper.toggleOnTrue(m_runArmCW);
    // rBumper.toggleOnFalse(m_nullArm);
  }
  
  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    
    m_robotContainer.drivebase.getDrivetrain().tankDrive(m_xboxController.getLeftY()*0.8, m_xboxController.getRightY()*0.8);
    // m_robotContainer.drivebase.getIntake().setArmSpeed(m_xboxController.getRightTriggerAxis());
  }
  
  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }
  
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}