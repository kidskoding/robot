package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private XboxController driveController = new XboxController(0);
  public RobotContainer() {
    configureButtonBindings();
  }
  
  private void configureButtonBindings() {

  }

  public double getDriverRightTrigger() { //double from 0-1
    return driveController.getRightTriggerAxis();
  }

  public double getDriverLeftTrigger() { //double from 0-1
    return driveController.getLeftTriggerAxis();
  }

  public double getDriverRawJoystick() {
    return driveController.getLeftX();
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}
