package utility;

public class DistanceConverter {
  private static final double MILE_TO_KILOMETER = 1.609;
  private static final double KILOMETER_TO_MILE = 0.6215;
  private static final int MILE_TO_FATHOM = 880;
  private static final int MILE_TO_LEAGUE = 3;
  public static double toKilometers(double miles) {
    return Math.abs(miles) * MILE_TO_KILOMETER;
  }

  public static double toMiles(double kilometers) {
    return Math.abs(kilometers) * KILOMETER_TO_MILE;
  }

  public static double toFathoms(double miles) {
    return Math.abs(miles) * MILE_TO_FATHOM;
  }

  public static double toLeagues(double miles) {
    return Math.abs(miles) * MILE_TO_LEAGUE;
  }
}
