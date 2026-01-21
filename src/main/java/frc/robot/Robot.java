package frc.robot;

import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  PS5Controller driverJoystick = new PS5Controller(0);

  public Robot() {
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {

    if (driverJoystick.getRawButton(1)) {
      if (RobotContainer.feeder.getFeederSensor()) {
        RobotContainer.intake.setIntakeVoltage(0);
        RobotContainer.feeder.setFeederVoltage(0);
      } else {
        RobotContainer.intake.setIntakeVoltage(5);
        RobotContainer.feeder.setFeederVoltage(5);
      }
    } else if (driverJoystick.getRawButton(2)) {
      RobotContainer.intake.setIntakeVoltage(-5);
      RobotContainer.feeder.setFeederVoltage(-5);
    } else {
      RobotContainer.intake.setIntakeVoltage(0);
      RobotContainer.feeder.setFeederVoltage(0);
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}