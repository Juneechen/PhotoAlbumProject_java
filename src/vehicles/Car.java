package vehicles;

import java.util.Comparator;

public class Car implements IVehicle {
  private final double maxSpeed;
  private double currentSpeed;

  public Car ( double maxSpeed) {
    this.maxSpeed = maxSpeed;
    this.currentSpeed = 0.0;
  }

  @Override
  public double getCurrentSpeed() {
    return this.currentSpeed;
  }

  @Override
  public void accelerate(double delta) {
    double target = this.currentSpeed + delta;
    if (target < 0) {
      this.currentSpeed = 0;
      return;
    }
    if (Double.compare(target, this.maxSpeed) > 0) {
      this.currentSpeed = maxSpeed;
      return;
    }
    this.currentSpeed = target;
  }

  @Override
  public void park() {
    this.currentSpeed = 0;
  }
}
