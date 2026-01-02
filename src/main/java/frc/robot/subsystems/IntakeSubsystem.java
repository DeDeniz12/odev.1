package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
  
  private final TalonFX leftMotor = new TalonFX(10);
  private final TalonFX rightMotor = new TalonFX(11);
  private final VoltageOut m_request = new VoltageOut(0);

  public IntakeSubsystem() {
    TalonFXConfiguration leftConfig = new TalonFXConfiguration();
    leftConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    leftConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    leftMotor.getConfigurator().apply(leftConfig);

    TalonFXConfiguration rightConfig = new TalonFXConfiguration();
    rightConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    rightConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    rightMotor.getConfigurator().apply(rightConfig);
  }

  public void setVoltage(double volts) {
    leftMotor.setControl(m_request.withOutput(Volts.of(volts)));
    rightMotor.setControl(m_request.withOutput(Volts.of(volts)));
  }

  public void stop() {
    leftMotor.setControl(m_request.withOutput(Volts.of(0)));
    rightMotor.setControl(m_request.withOutput(Volts.of(0)));
  }
}