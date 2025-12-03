package manuscritos;

import manuscritos.util.ClueUtils;
import org.junit.jupiter.api.Test;

import java.util.List; // Import ya existe, ¡bien!

import static org.junit.jupiter.api.Assertions.*;

public class ClueUtilsTest {

    @Test
    public void testContainsArtifactClue_DiagonalTrue() {
        // CAMBIO: String[] a List<String>
        List<String> manuscript = List.of( // Usar List.of()
                "RTHGQW",
                "XRLORE",
                "NARURR",
                "REVRAL",
                "EGSILE",
                "BRINDS"
        );
        assertTrue(ClueUtils.containsArtifactClue(manuscript),
                "Se esperaba true porque hay 4 'R' en diagonal");
    }

    @Test
    public void testContainsArtifactClue_HorizontalTrue() {
        // CAMBIO: String[] a List<String>
        List<String> manuscript = List.of( // Usar List.of()
                "AAAAZZ",
                "BCDEFG",
                "HIJKLM"
        );
        // En la primera fila hay "AAAA" consecutivas
        assertTrue(ClueUtils.containsArtifactClue(manuscript));
    }

    @Test
    public void testContainsArtifactClue_VerticalTrue() {
        // CAMBIO: String[] a List<String>
        List<String> manuscript = List.of( // Usar List.of()
                "AAX",
                "ABX",
                "ACX",
                "ADX",
                "EFG"
        );
        // Columna 2 ('X') tiene 4 X hacia abajo: filas 0..3 en la columna 2
        assertTrue(ClueUtils.containsArtifactClue(manuscript));
    }

    @Test
    public void testContainsArtifactClue_NoClue() {
        // CAMBIO: String[] a List<String>
        List<String> manuscript = List.of( // Usar List.of()
                "ABCD",
                "EFGH",
                "IJKL",
                "MNOP"
        );
        assertFalse(ClueUtils.containsArtifactClue(manuscript),
                "Se esperaba false porque no hay 4 letras iguales consecutivas");
    }

    @Test
    public void testContainsArtifactClue_EmptyOrNull() {
        // CAMBIO: Pasamos List.of() para el caso vacío y null directamente
        assertFalse(ClueUtils.containsArtifactClue(List.of())); // Lista vacía
        assertFalse(ClueUtils.containsArtifactClue(null));
    }
}