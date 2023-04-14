package controller;

import java.util.List;

public interface Features {
  void exit();
  void getNext();
  void getPrev();
  void getSnapshot(int i);
  List<String> getSelectionOptions();
}
