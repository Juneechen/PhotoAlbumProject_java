package views;

import controller.Features;

public interface IView {
  void display();
  void resetFocus();
  void addFeatures(Features features);
  void updateInfoPane(String text);
  void addShape(String info);
  void showPopUpMsg(String msg);
  void refresh();
  void clear();
}
