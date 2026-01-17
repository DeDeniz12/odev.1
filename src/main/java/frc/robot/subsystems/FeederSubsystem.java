package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Volts;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants; 

public class feederSubsystem extends SubsystemBase {

  TalonFX feederMotor = new TalonFX(Constants.Feeder.feederID);

  VoltageOut m_feederVoltage = new VoltageOut(0);
  TalonFXConfiguration feederConfig = new TalonFXConfiguration();
  

  DigitalInput sensorFeeder = new DigitalInput(Constants.Feeder.sensorID);

  public feederSubsystem() {
    feederConfig.CurrentLimits.SupplyCurrentLimit = 80;
    feederConfig.CurrentLimits.SupplyCurrentLimitEnable = true;
    feederConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    feederConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    feederMotor.getConfigurator().apply(feederConfig, 0.05);
  }

  public boolean getSensorFeeder() {
    return sensorFeeder.get();
  }

  public void setFeederVoltage(double voltage) {
    feederMotor.setControl(m_feederVoltage.withOutput(Volts.of(voltage)));
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("sensorFeeder", getSensorFeeder());
  }
}