package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.feederSubsystem;
import frc.robot.subsystems.hoodSubsystem;
import frc.robot.subsystems.intakeSubsystem;

public class RobotContainer {
    public static final intakeSubsystem intake = new intakeSubsystem();
  public static final feederSubsystem feeder = new feederSubsystem();
  public static final hoodSubsystem hood = new hoodSubsystem();
  
  public RobotContainer() {

  }

  public Command getAutonomousCommand() {
    return null;
  }
}