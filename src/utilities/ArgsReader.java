package utilities;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A command line arguments reader that parse arguments following a preset format.
 * Exit the program with an error message if the arguments are invalid.
 */
public class ArgsReader {
  private String inFile;
  private String outFile;
  private String view;
  private int width = 1000;
  private int height = 1000;
  public ArgsReader(String args[]) {
    List<String> arguments = Arrays.stream(args).toList();
    Iterator<String> it = arguments.iterator();

    while (it.hasNext()) {
      String arg = it.next();

      switch (arg.toLowerCase()) {
        case "-in":
          inFile = it.next();
          break;

        case "-out":
          outFile = it.next();
          break;

        case "-v":
        case "-view":
          view = it.next();
          break;

        default:
          try {
            width = Integer.parseInt(arg);
            height = Integer.parseInt(it.next());
          } catch (NumberFormatException e) {
            System.out.println("Exit on error: wrong number format for View dimensions.");
            System.exit(1);
          } catch (NoSuchElementException e) {
            System.out.println("Exit on error: missing a View dimension");
            System.exit(1);
          }
          break;
      }
    }

    if (view == null) {
      System.out.println("Exit on error: need to specify a view.");
      System.exit(1);
    }

    if (view.equalsIgnoreCase("web") && outFile == null) {
      System.out.println("Exit on error: need an output file for web view.");
      System.exit(1);
    }

    if (inFile == null) {
      System.out.println("Exit on error: need an input file.");
      System.exit(1);
    }
  }

  public String getInFile() {
    return inFile;
  }

  public String getOutFile() {
    return outFile;
  }

  public String getView() {
    return view;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }
}
