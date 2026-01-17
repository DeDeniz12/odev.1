package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;


  private final Joystick stick_0 = new Joystick(0);

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }


  @Override
  public void teleopPeriodic() {
    

    boolean sensorDolu = RobotContainer.feeder.getSensorFeeder();


    
    if (sensorDolu == false) {

      RobotContainer.hood.setHoodAngle(30);
    } 
    else {

      
      if (stick_0.getRawButton(1)) {

        RobotContainer.hood.setHoodAngle(50);
      } 
      else if (stick_0.getRawButton(2)) {

        RobotContainer.hood.setHoodAngle(80);
      }
    }
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }
  
  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }
}