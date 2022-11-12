package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.TeleopDrive;

public class DriveTrain extends SubsystemBase {
    public WPI_TalonFX frontLeft;
    public WPI_TalonFX frontRight;
    private WPI_TalonFX backLeft;
    private WPI_TalonFX backRight;
    private WPI_TalonFX[] allMotors = {frontLeft, frontRight, backLeft, backRight};
    //these two motors drive the robot
    public DifferentialDrive motors = new DifferentialDrive(frontLeft, frontRight);
    public TeleopDrive teleopDrive = new TeleopDrive(this);

    public DriveTrain() {
        frontLeft = new WPI_TalonFX(3);
        frontRight = new WPI_TalonFX(1);
        backLeft = new WPI_TalonFX(2);
        backRight = new WPI_TalonFX(4);
    }
    public void periodic() {
         
    }
    public void simulationPeriodic() {

    }
    public void configMotors() {
        for(WPI_TalonFX motor : allMotors) {
            motor.setNeutralMode(NeutralMode.Brake);
        }
        frontLeft.setInverted(TalonFXInvertType.CounterClockwise);
        frontRight.setInverted(TalonFXInvertType.Clockwise);
        backLeft.setInverted(TalonFXInvertType.CounterClockwise);
        backRight.setInverted(TalonFXInvertType.Clockwise);

        backLeft.follow(frontLeft);
        backRight.follow(frontRight);
    }
}