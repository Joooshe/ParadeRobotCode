/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.Move;
import frc.robot.OI;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Spark rightSpark;
  private Spark leftSpark;
  public DifferentialDrive drive;
  public double Yspeed;
  public double Xspeed;

  public DriveTrain() {
    leftSpark = new Spark(RobotMap.leftMotor);
    rightSpark = new Spark(RobotMap.rightMotor);
    drive = new DifferentialDrive(leftSpark, rightSpark);
  }

  public void move(XboxController controller) {
    Yspeed = controller.getY(Hand.kLeft);
    Xspeed = controller.getX(Hand.kRight);
    SmartDashboard.putNumber("Y Left JoyStick", Yspeed);
    SmartDashboard.putNumber("X Right Joystick", Xspeed);
    drive.arcadeDrive(-controller.getY(Hand.kLeft), controller.getX(Hand.kRight));
  }

  public void stop() {
    drive.arcadeDrive(0, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Move());
  }
}
