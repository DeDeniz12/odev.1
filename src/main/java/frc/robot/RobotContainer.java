package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class RobotContainer {
  
  // Alt sistemler
  private final IntakeSubsystem m_intake = new IntakeSubsystem();
  private final FeederSubsystem m_feeder = new FeederSubsystem();

  // Joystick Tanımlama
  private final Joystick m_driverController = new Joystick(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    
    //TUŞ 1 GÖREVİ
    
    new JoystickButton(m_driverController, 1)
        .whileTrue( // Tuşa basılı tutulduğu sürece
            Commands.run( // 1. Motorlar
                () -> {
                  m_intake.setVoltage(5.0);
                  m_feeder.setVoltage(5.0);
                }, m_intake, m_feeder
            )
            // 2. KOMUT
            .until(() -> m_feeder.isSensorTriggered())
            
            // 3. Komut bittiğinde
            .finallyDo(() -> {
                m_intake.stop();
                m_feeder.stop();
            })
        );


    //TUŞ 2 GÖREVİ
    
    new JoystickButton(m_driverController, 2)
        .whileTrue(
            Commands.run(
                () -> {
                    m_intake.setVoltage(-5.0); // Eksi voltaj yani Geri Yön
                    m_feeder.setVoltage(-5.0);
                }, m_intake, m_feeder
            )
            .finallyDo(() -> {
                m_intake.stop();
                m_feeder.stop();
            })
        );
  }

  public Command getAutonomousCommand() {
    return Commands.none();
  }
}