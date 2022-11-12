package frc.robot.commands;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.*;
public class TeleopDrive extends CommandBase { 
    private final DriveTrain driveTrain;

    public TeleopDrive(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }
    //only touch frontLeft and frontRight motors.
    //called repeatedly while command is scheduled
    @Override 
    public void execute() {
        double leftTrigger = Robot.m_robotContainer.getDriverLeftTrigger(); //double 0-1
        double rightTrigger = Robot.m_robotContainer.getDriverRightTrigger();
        //double joyStick = Robot.m_robotContainer.getDriverRawJoystick();
        if(leftTrigger > 0 && rightTrigger == 0) {
            driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, leftTrigger * -1);
            driveTrain.frontRight.set(TalonFXControlMode.PercentOutput, rightTrigger * -1);
        }
        else if(leftTrigger == 0 && rightTrigger > 0) {
            driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, leftTrigger);
            driveTrain.frontRight.set(TalonFXControlMode.PercentOutput, rightTrigger);
        }
        else if(leftTrigger > 0 && rightTrigger > 0) {
            driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, 0);
            driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, 0);
        }
    }    
    @Override
    public void initialize() {
        
    }
    @Override
    public void end(boolean interrputed) {

    }
}