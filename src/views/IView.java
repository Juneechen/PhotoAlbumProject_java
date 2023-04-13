package views;

import java.util.List;

import controller.Features;

public interface IView {
  void display();
  void resetFocus();
  void showSnapshot(List<String> info);
  void addFeatures(Features features);
}
