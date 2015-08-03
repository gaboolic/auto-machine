package tk.gbl.util.image;

/**
 * Date: 2014/9/25
 * Time: 15:05
 *
 * @author Tian.Dong
 */
public class Output {
  public static void output(int[][] img) {
    System.out.println("***");
    for (int h = 0; h < img.length; h++) {
      for (int w = 0; w < img[h].length; w++) {
        System.out.print(img[h][w]);
      }
      System.out.println();
    }
  }

  public static void outputS(int[][] img) {
    System.out.println("public static int[][] image =");
    System.out.println("{");
    for (int h = 0; h < img.length; h++) {
      System.out.print("{");
      for (int w = 0; w < img[h].length; w++) {
        System.out.print(img[h][w]);
        System.out.print(",");
      }
      System.out.print("},");

      System.out.println("}");
    }
  }

  public static void outputS(int[][] img, String i) {
    System.out.println("public static int[][] image" + i + " =");
    System.out.println("{");
    for (int h = 0; h < img.length; h++) {
      System.out.print("{");
      for (int w = 0; w < img[h].length; w++) {
        System.out.print(img[h][w]);
        System.out.print(",");
      }
      System.out.print("},");
      System.out.println();
    }
    System.out.println("};");
  }

  public static void output(int[] a) {
    for (int i : a) {
      System.out.print(i);
    }
    System.out.println();
  }
}
