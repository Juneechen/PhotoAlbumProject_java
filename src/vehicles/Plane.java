package vehicles;

public class Plane implements IFlyingVehicle {
  private final double maxSpeed;
  private double currentSpeed;
  private boolean inTheAir;

  public Plane(double maxSpeed) {
    this.maxSpeed = maxSpeed;
    this.currentSpeed = 0.0;
    this.inTheAir = false;
  }
  @Override
  public void takeOff() {
    if (Double.compare(this.currentSpeed, 100.00) > 0) {
      this.inTheAir = true;
    }
  }

  @Override
  public void land() {
    if (Double.compare(this.currentSpeed, 10.00) < 0) {
      return; // should not increase speed if current is less than 10 right?
    }
    this.inTheAir = false;
    this.currentSpeed = 10;
  }

  @Override
  public boolean inTheAir() {
    return this.inTheAir;
  }

  @Override
  public double getCurrentSpeed() {
    return this.currentSpeed;
  }

  // did not extend abstract class and push accelerate() up
  // as I'm not sure if that would break the required class signature
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
    if (! this.inTheAir()) {
      this.currentSpeed = 0;
    }
  }
}
