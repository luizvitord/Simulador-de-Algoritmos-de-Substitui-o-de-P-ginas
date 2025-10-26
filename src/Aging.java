import java.util.*;

public class Aging {
    private static class Pagina {
        int numero;
        int contador;
        Pagina(int n) { numero = n; contador = 0; }
    }

    public static int simular(int[] pages, int frames) {
        List<Pagina> memoria = new ArrayList<>();
        int faltas = 0;

        for (int p : pages) {
            boolean hit = false;

            for (Pagina pag : memoria) {
                pag.contador >>= 1; // desloca bits para “envelhecer”
                if (pag.numero == p) {
                    pag.contador |= 1 << 7; // bit mais significativo = página foi usada
                    hit = true;
                }
            }

            if (!hit) {
                faltas++;

                if (memoria.size() < frames) {
                    Pagina nova = new Pagina(p);
                    nova.contador |= 1 << 7;
                    memoria.add(nova);
                } else {
                    Pagina substituir = Collections.min(memoria, Comparator.comparingInt(pg -> pg.contador));
                    memoria.remove(substituir);
                    Pagina nova = new Pagina(p);
                    nova.contador |= 1 << 7;
                    memoria.add(nova);
                }
            }
        }
        return faltas;
    }
}
