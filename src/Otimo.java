import java.util.*;

public class Otimo {
    public static int simular(int[] pages, int frames) {
        Set<Integer> memoria = new HashSet<>();
        int faltas = 0;

        for (int i = 0; i < pages.length; i++) {
            int pagina = pages[i];
            if (!memoria.contains(pagina)) {
                if (memoria.size() == frames) {
                    int paginaSubstituir = encontrarPaginaSubstituir(memoria, pages, i + 1);
                    memoria.remove(paginaSubstituir);
                }
                memoria.add(pagina);
                faltas++;
            }
        }

        return faltas;
    }

    private static int encontrarPaginaSubstituir(Set<Integer> memoria, int[] pages, int posAtual) {
        int maisLonge = -1;
        int paginaMaisLonge = -1;

        for (int pag : memoria) {
            int j;
            for (j = posAtual; j < pages.length; j++) {
                if (pages[j] == pag) {
                    if (j > maisLonge) {
                        maisLonge = j;
                        paginaMaisLonge = pag;
                    }
                    break;
                }
            }
            if (j == pages.length)
                return pag; // Página nunca mais usada
        }

        return (paginaMaisLonge == -1) ? memoria.iterator().next() : paginaMaisLonge;
    }
}
