package controller;

import java.util.List;

public interface Features {
  void exit();
  // void selectSnapshot(String id);
  void getSnapshot(int i);
  List<String> getSelectionItems();
}
