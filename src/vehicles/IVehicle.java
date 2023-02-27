package vehicles;

public interface IVehicle {
  double getCurrentSpeed();
  void accelerate(double delta);
  void park();
}
