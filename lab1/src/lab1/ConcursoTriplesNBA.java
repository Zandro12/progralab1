/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab1;

/**
 *
 * @author aleja
 */
import java.util.Random;
import java.util.Scanner;

public class ConcursoTriplesNBA {

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1 -> iniciarConcurso();
                case 2 -> {
                    System.out.println("Finalizando el concurso, gracias por participar");
                    continuar = false;
                }
                default -> System.out.println("Opcion invalida. favor intentar de nuevo.");
            }
        }
    }


    private static void mostrarMenu() {
        System.out.println("Concurso de triples de la NBA");
        System.out.println("1. Iniciar concurso");
        System.out.println("2. Finalizar concurso");
        System.out.print("Seleccione una opcion: ");
    }

    private static int obtenerOpcion() {
        return scanner.nextInt();
    }

    private static void iniciarConcurso() {

        Jugador jugador1 = new Jugador("Michael Jordan");
        Jugador jugador2 = new Jugador("Lebron James");

        realizarLanzamientos(jugador1);
        realizarLanzamientos(jugador2);
        imprimirResultados(jugador1, jugador2);
    }

    private static void realizarLanzamientos(Jugador jugador) {
        System.out.println("\nIniciando lanzamientos para: " + jugador.getNombre());

        // 1 y 2
        for (int estante = 1; estante <= 2; estante++) {
            for (int i = 1; i <= 5; i++) {
                int valorBalon = (i == 5) ? 2 : 1;
                lanzarBalon(jugador, estante, i, valorBalon);
            }
        }
        // 3
        for (int i = 1; i <= 5; i++) {
            lanzarBalon(jugador, 3, i, 2);
        }
    }

    private static void lanzarBalon(Jugador jugador, int estante, int posicion, int valorBalon) {
        int numeroAleatorio = random.nextInt(5) + 1;
        boolean encestado = (numeroAleatorio == posicion);

        if (encestado) {
            jugador.incrementarPuntos(valorBalon);
            jugador.incrementarBalonesEncestados(estante, valorBalon);
        }
    }

    // Resultados
    private static void imprimirResultados(Jugador jugador1, Jugador jugador2) {
        System.out.println("\nResultados Finales:");
        System.out.println(jugador1);
        System.out.println(jugador2);

        if (jugador1.getPuntos() > jugador2.getPuntos()) {
            System.out.println("\nGanador: " + jugador1.getNombre());
        } else if (jugador2.getPuntos() > jugador1.getPuntos()) {
            System.out.println("\nGanador: " + jugador2.getNombre());
        } else {

    // Desempate con el estante 3
            if (jugador1.getBalonesEncestadosEstante(3) > jugador2.getBalonesEncestadosEstante(3)) {
                System.out.println("\nEmpate, pero el ganador por desempate es: " + jugador1.getNombre());
            } else if (jugador2.getBalonesEncestadosEstante(3) > jugador1.getBalonesEncestadosEstante(3)) {
                System.out.println("\nEmpate, pero el ganador por desempate es: " + jugador2.getNombre());
            } else {
                System.out.println("\nEmpate total entre ambos jugadores.");
            }
        }
    }
}

class Jugador {
    private final String nombre;
    private int puntos;
    private final int[] balonesEncestados;
    private final int[] puntosPorEstante;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.balonesEncestados = new int[4];
        this.puntosPorEstante = new int[4];
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void incrementarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void incrementarBalonesEncestados(int estante, int valor) {
        balonesEncestados[estante]++;
        puntosPorEstante[estante] += valor;
    }

    public int getBalonesEncestadosEstante(int estante) {
        return balonesEncestados[estante];
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        resultado.append(nombre).append(" - Puntos totales: ").append(puntos).append("\n");
        
        for (int i = 1; i <= 3; i++) {
            resultado.append("Estante #").append(i).append(": Balones encestados = ").append(balonesEncestados[i])
                    .append(", Puntos obtenidos = ").append(puntosPorEstante[i]).append("\n");
        }
        
        return resultado.toString();
    }
}