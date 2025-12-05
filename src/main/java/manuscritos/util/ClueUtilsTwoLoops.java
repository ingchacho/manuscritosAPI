package manuscritos.util;

import java.util.List;

public class ClueUtilsTwoLoops {

    private static final int N = 4;

    /**
     * Solución con SOLO 2 ciclos for:
     *  - Primer ciclo revisa horizontal y vertical.
     *  - Segundo ciclo revisa diagonales ↘ y ↙.
     *
     * Complejidad: O(R*C)
     */
    public static boolean containsArtifactClueTwoLoops(List<String> manuscript) {
        if (manuscript == null || manuscript.isEmpty()) return false;

        int rows = manuscript.size();
        int cols = manuscript.stream().mapToInt(s -> s != null ? s.length() : 0).max().orElse(0);

        // Normalizamos a una matriz de caracteres
        char[][] grid = new char[rows][cols];
        for (int r = 0; r < rows; r++) {
            String line = manuscript.get(r) == null ? "" : manuscript.get(r);
            for (int c = 0; c < cols; c++)
                grid[r][c] = (c < line.length()) ? line.charAt(c) : '\0';
        }

        // ---------------------------------------------
        //PRIMER CICLO: Horizontal y Vertical
        // ---------------------------------------------
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = grid[r][c];
                if (ch == '\0') continue;

                // Horizontal →
                if (c + 3 < cols &&
                        ch == grid[r][c + 1] &&
                        ch == grid[r][c + 2] &&
                        ch == grid[r][c + 3])
                    return true;

                // Vertical ↓
                if (r + 3 < rows &&
                        ch == grid[r + 1][c] &&
                        ch == grid[r + 2][c] &&
                        ch == grid[r + 3][c])
                    return true;
            }
        }

        // ---------------------------------------------
        //SEGUNDO CICLO: Diagonales ↘ y ↙
        // ---------------------------------------------
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = grid[r][c];
                if (ch == '\0') continue;

                // Diagonal ↘
                if (r + 3 < rows && c + 3 < cols &&
                        ch == grid[r + 1][c + 1] &&
                        ch == grid[r + 2][c + 2] &&
                        ch == grid[r + 3][c + 3])
                    return true;

                // Diagonal ↙
                if (r + 3 < rows && c - 3 >= 0 &&
                        ch == grid[r + 1][c - 1] &&
                        ch == grid[r + 2][c - 2] &&
                        ch == grid[r + 3][c - 3])
                    return true;
            }
        }

        return false;
    }
}
