package utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A command line arguments reader that parse arguments following a preset format,
 * and stores parsed information within itself.
 * Exit the program with an error message if the arguments are invalid.
 */
public class ArgsReader {
  private String inFile;
  private String outFile;
  private String view;
  private int width = 1000; // default width if not specified by args
  private int height = 1000; // default height
  private java.io.PrintWriter output = null;
  private java.io.FileReader input = null;

  /**
   * construct an ArgsReader with the given arguments.
   * @param args and array of arguments
   */
  public ArgsReader(String[] args) {
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
            System.exit(0);
          } catch (NoSuchElementException e) {
            System.out.println("Exit on error: missing a View dimension");
            System.exit(0);
          }
          break;
      }
    }

    if (view == null) {
      System.out.println("Exit on error: need to specify a view.");
      System.exit(0);
    }

    if (view.equalsIgnoreCase("web")) {
      if (outFile == null) {
        System.out.println("Exit on error: need an output file for web view.");
        System.exit(0);
      }
    }

    if (inFile == null) {
      System.out.println("Exit on error: need an input file.");
      System.exit(0);
    }
  }

  /**
   * return the specified view.
   * @return view specified by the arguments.
   */
  public String getView() {
    return view;
  }

  /**
   * return the specified width.
   * @return width specified by the arguments.
   */
  public int getWidth() {
    return width;
  }

  /**
   * return the specified height.
   * @return height specified by the arguments.
   */
  public int getHeight() {
    return height;
  }

  /**
   * create a Readable with the specified input source.
   * @return a Readable associated with the input source
   * @throws FileNotFoundException if input source is not found.
   */
  public Readable getReadable() throws FileNotFoundException {
    java.io.File file = new java.io.File(inFile);
    this.input = new java.io.FileReader(file);

    return this.input;
  }

  /**
   * create an Appendable with the specified output source.
   * @return an Appendable associated with the output source
   * @throws IOException for any IO error.
   */
  public Appendable getAppendable() throws IOException {
    java.io.File file = new java.io.File(outFile);

    if (file.exists()) {
      System.out.println("Exit on error: output file already exists.");
      System.exit(0);
    }

    this.output = new java.io.PrintWriter(file);
    return this.output;
  }

  /**
   * close the Readable.
   */
  public void closeReadable() {
    try {
      this.input.close();
    } catch (IOException e) {
      System.out.println("Exit on error: unable to close input file.");
    }
  }

  /**
   * close the Appendable.
   */
  public void closeAppendable() {
    this.output.close();
  }
}
