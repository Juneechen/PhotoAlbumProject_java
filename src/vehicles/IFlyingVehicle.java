package vehicles;

public interface IFlyingVehicle extends IVehicle {
  void takeOff();
  void land();
  boolean inTheAir(); // returns true if vehicle is in the air
}
