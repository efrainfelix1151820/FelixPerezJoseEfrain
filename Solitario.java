import java.util.*;

public class Solitario {

    private static final int NUM_COLUMNAS = 7;
    private static final int NUM_PALOS = 4;
    private static final int NUM_CARTAS_PALO = 10;

    private List<List<Integer>> columnas;
    private List<Integer> mazo;

    public Solitario() {
        columnas = new ArrayList<>();
        mazo = new ArrayList<>();
        inicializarMazo();
        barajar();
        repartirCartas();
    }

    private void inicializarMazo() {
        for (int i = 1; i <= NUM_PALOS; i++) {
            for (int j = 1; j <= NUM_CARTAS_PALO; j++) {
                mazo.add(j);
            }
        }
    }

    private void barajar() {
        Collections.shuffle(mazo);
    }

    private void repartirCartas() {
        for (int i = 0; i < NUM_COLUMNAS; i++) {
            columnas.add(new ArrayList<>());
        }
        int cartaIndex = 0;
        for (List<Integer> columna : columnas) {
            for (int j = 0; j <= cartaIndex; j++) {
                columna.add(mazo.get(0));
                mazo.remove(0);
            }
            cartaIndex++;
        }
    }

    private void mostrarTablero() {
        columnas.forEach(columna -> {
            System.out.print("Columna: ");
            columna.forEach(carta -> System.out.print(carta + " "));
            System.out.println();
        });
    }

    private void moverCarta(int origen, int destino) {
        if (origen < 1 || origen > NUM_COLUMNAS || destino < 1 || destino > NUM_COLUMNAS) {
            System.out.println("Columna inválida");
            return;
        }

        List<Integer> origenColumna = columnas.get(origen - 1);
        List<Integer> destinoColumna = columnas.get(destino - 1);

        if (origenColumna.isEmpty()) {
            System.out.println("La columna de origen está vacía");
            return;
        }

        int carta = origenColumna.remove(origenColumna.size() - 1);
        if (destinoColumna.isEmpty() || carta < destinoColumna.get(destinoColumna.size() - 1)) {
            destinoColumna.add(carta);
        } else {
            System.out.println("Movimiento no válido");
            origenColumna.add(carta);
        }
    }

    public void jugar() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            mostrarTablero();
            System.out.print("Ingrese el número de columna de origen (-1 para salir): ");
            int origen = scanner.nextInt();
            if (origen == -1) {
                System.out.println("Gracias por jugar");
                break;
            }
            System.out.print("Ingrese el número de columna de destino: ");
            int destino = scanner.nextInt();
            moverCarta(origen, destino);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Solitario solitario = new Solitario();
        solitario.jugar();
    }
}
