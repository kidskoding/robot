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
    @Override
    public void execute() {
        double leftTrigger = Robot.m_robotContainer.getDriverLeftTrigger(); //double 0-1
        double rightTrigger = Robot.m_robotContainer.getDriverRightTrigger();
        double joystick = Robot.m_robotContainer.getDriverRawJoystick();

        final boolean isMovingForward = rightTrigger != 0;
        final boolean isMovingBackward = leftTrigger != 0;

        controlDriving(joystick, isMovingForward, isMovingBackward, rightTrigger, leftTrigger);
        controlJoystick(joystick, isMovingForward, isMovingBackward, rightTrigger, leftTrigger);
    }
       
    private void controlDriving(double joystick, final boolean isMovingForward, final boolean isMovingBackward, double rightTrigger, double leftTrigger) {
        if(isMovingBackward && !isMovingForward) {
            driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, leftTrigger * -1);
            driveTrain.frontRight.set(TalonFXControlMode.PercentOutput, rightTrigger * -1);
        }
        else if(!isMovingBackward && isMovingForward) {
            driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, leftTrigger);
            driveTrain.frontRight.set(TalonFXControlMode.PercentOutput, rightTrigger);
        }
        else if(isMovingBackward && isMovingForward || (!isMovingBackward && !isMovingForward)) {
            driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, 0);
            driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, 0);
        }
    } 
    private void controlJoystick(double joystick, final boolean isMovingForward, final boolean isMovingBackward, double rightTrigger, double leftTrigger) {
        if(joystick < 0 && isMovingForward) driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, rightTrigger - joystick);
        else if(joystick < 0 && isMovingBackward) driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, leftTrigger - joystick);
        else if(joystick > 0 && isMovingBackward) driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, leftTrigger - joystick * -1);
        else if(joystick > 0 && isMovingForward) driveTrain.frontLeft.set(TalonFXControlMode.PercentOutput, rightTrigger - joystick * -1);
    }

    @Override
    public void initialize() {
        
    }
    @Override
    public void end(boolean interrputed) {

    }
}