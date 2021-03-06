package Analizador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Jhony Alejandro Caro Junio 2022
 */
public class ConstructorArchivos {

    private String camino = "../AnalizadorLexico/src/Analizador/Lexico.flex";
    private String camino1 = "../AnalizadorLexico/src/Analizador/LexicoCup.flex";
    private String[] caminoS = {"-parser", "Sintaxis", "../AnalizadorLexico/src/Analizador/Sintaxis.cup"};

    public ConstructorArchivos() throws Exception {
        generarArchivos(camino, camino1, caminoS);
    }

    private static void generarArchivos(String camino, String camino1, String[] caminoS) throws IOException, Exception {
        File arc;
        arc = new File(camino);
        JFlex.Main.generate(arc);
        arc = new File(camino1);
        JFlex.Main.generate(arc);
        java_cup.Main.main(caminoS);

        Path caminoSym = Paths.get("../AnalizadorLexico/src/Analizador/sym.java");
        if (Files.exists(caminoSym)) {
            Files.delete(caminoSym);
        }
        Files.move(
                Paths.get("../AnalizadorLexico/sym.java"),
                Paths.get("../AnalizadorLexico/src/Analizador/sym.java")
        );
        Path caminoSint = Paths.get("../AnalizadorLexico/src/Analizador/Sintaxis.java");
        if (Files.exists(caminoSint)) {
            Files.delete(caminoSint);
        }
        Files.move(
                Paths.get("../AnalizadorLexico/Sintaxis.java"),
                Paths.get("../AnalizadorLexico/src/Analizador/Sintaxis.java")
        );
    }
}
