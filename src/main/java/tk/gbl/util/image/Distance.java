package tk.gbl.util.image;

/**
 * Date: 2014/9/25
 * Time: 10:48
 *
 * @author Tian.Dong
 */
public class Distance {



  public static int hamDistance(int[][] a, int[][] b) {
    int h = a.length < b.length ? a.length : b.length;
    int w = a[0].length < b[0].length ? a[0].length : b[0].length;
    int count = 0;
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        if (a[i][j] != b[i][j]) {
          count++;
        }
      }
    }
    return count;
  }




  public static int editDistance(int[][] a, int[][] b) {
    int[] a1 = new int[a.length * a[0].length];
    int[] b1 = new int[b.length * b[0].length];

    for (int h = 0; h < a.length; h++) {
      for (int w = 0; w < a[h].length; w++) {
        a1[h*a[0].length + w] = a[h][w];
      }
    }

    for (int h = 0; h < b.length; h++) {
      for (int w = 0; w < b[h].length; w++) {
        b1[h*b[0].length + w] = b[h][w];
      }
    }
    return editDistance(a1, b1);
  }
  public static int editDistance(int[][] a, int[] tmg) {
    int[] a1 = new int[a.length * a[0].length];
    for (int h = 0; h < a.length; h++) {
      for (int w = 0; w < a[h].length; w++) {
        a1[h*a[0].length + w] = a[h][w];
      }
    }

    return editDistance(a1, tmg);
  }

  public static int editDistance(int[] a, int[] b) {
    int n = a.length;
    int m = b.length;
    if (n == 0) {
      return m;
    } else if (m == 0) {
      return n;
    }

    int[][] d = new int[n + 1][m + 1];
    for (int i = 0; i <= n; i++) {
      //初始化第一列
      d[i][0] = i;
    }
    for (int j = 0; j <= m; j++) {
      //初始化第一行
      d[0][j] = j;
    }

    for (int i = 1; i <= n; i++) {
      int a1 = a[i - 1];
      for (int j = 1; j <= m; j++) {
        int b1 = b[j - 1];
        int temp = 1;
        if (a1 == b1) {
          temp = 0;
        }
        d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
      }
    }
    return d[n][m];
  }

  public static int hamDistance(int[] a, int[] b) {
    int count = 0;
    for (int i = 0; i < min(a.length, b.length); i++) {
      if (a[i] != b[i]) {
        count++;
      }
    }
    return count;
  }

  private static int min(int a, int b, int c) {
    return a < b ?
        (a < c ? a : c)
        :
        (b < c ? b : c);
  }



  private static int min(int a, int b) {
    return a < b ? a : b;
  }

  private int max(int a, int b) {
    return a > b ? a : b;
  }



}
