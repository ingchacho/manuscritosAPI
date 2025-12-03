package manuscritos.util;

import java.util.List;

public class ClueUtils {
    /**
     * True si encuentra 4 letras iguales consecutivas en cualquier dirección.
     */
    public static boolean containsArtifactClue(List<String> manuscript) {
        if (manuscript == null || manuscript.isEmpty()) return false;

        String[] manuscriptArray = manuscript.toArray(new String[0]);

        int rows = manuscriptArray.length;
        int cols = 0;
        for (String s : manuscript) if (s != null && s.length() > cols) cols = s.length();
        if (cols == 0) return false;

        char[][] grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            String line = manuscriptArray[r] == null ? "" : manuscriptArray[r];
            for (int c = 0; c < cols; c++) {
                grid[r][c] = (c < line.length()) ? line.charAt(c) : '\0';
            }
        }

        int[][] dirs = {
                {0, 1}, {0, -1}, {1, 0}, {-1, 0},
                {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        final int N = 4;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = grid[r][c];
                if (ch == '\0') continue;
                for (int[] d : dirs) {
                    int dr = d[0], dc = d[1];
                    int rr = r, cc = c;
                    boolean ok = true;
                    for (int k = 1; k < N; k++) {
                        rr += dr; cc += dc;
                        if (rr < 0 || rr >= rows || cc < 0 || cc >= cols) { ok = false; break; }
                        if (grid[rr][cc] != ch) { ok = false; break; }
                    }
                    if (ok) return true;
                }
            }
        }
        return false;
    }
}
