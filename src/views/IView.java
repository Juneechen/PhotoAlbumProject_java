package views;

import controller.Features;

public interface IView {
  void display();
  void resetFocus();
  void addShape(String info);
  void updateInfoPane(String text);
  void refresh();
  //void showSnapshot(List<String> info);
//  void showShapes(List<IShape> shapes);
  void addFeatures(Features features);
  void clear();
}
