package views;

import controller.Features;

public interface IView {
  void display();
  void resetFocus();
  void addFeatures(Features features);
}
