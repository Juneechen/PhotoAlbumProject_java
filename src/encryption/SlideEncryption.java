package encryption;

public class SlideEncryption implements IEncryption {
  private String original;
  private String encoded;

  public SlideEncryption(String msg) {
    if (msg == null) {
      this.original = "";
    } else {
      this.original = msg;
    }
    this.encoded = "";
  }

  @Override
  public String encode(int encoding) {
    int size = this.original.length();
    if (size == 0) {
      return "";
    }
    int offset = encoding % size;
    int head = (size - offset) % size;
    this.encoded = this.original.substring(head) + this.original.substring(0, head);
    return this.encoded;
  }

  @Override
  public String toString() {
    // explanation for my output format just in case I'm wrong about it:
    // I asked whether the printed words should have double quotes around them
    // as shown in the example output,
    // TA says no so here are my words printed without quotes:
    return this.encoded + "\n" + this.original;
  }
}
