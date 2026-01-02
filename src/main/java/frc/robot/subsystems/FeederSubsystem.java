package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.wpilibj.DigitalInput; // Sensör için
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase {

  // 1 Motor ve 1 Sensör
  private final TalonFX feederMotor = new TalonFX(12);
  private final DigitalInput noteSensor = new DigitalInput(0); // RIO üzerindeki DIO 0 portu
  
  private final VoltageOut m_request = new VoltageOut(0);

  public FeederSubsystem() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    feederMotor.getConfigurator().apply(config);
  }

  public void setVoltage(double volts) {
    feederMotor.setControl(m_request.withOutput(Volts.of(volts)));
  }

  public void stop() {
    feederMotor.setControl(m_request.withOutput(Volts.of(0)));
  }

  // Sensörün durumu
  public boolean isSensorTriggered() {
  
    return noteSensor.get(); 
  }

  @Override
  public void periodic() {
 
    SmartDashboard.putBoolean("Feeder Sensoru", isSensorTriggered());
  }
}