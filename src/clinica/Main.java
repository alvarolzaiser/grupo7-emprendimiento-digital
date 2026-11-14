package clinica;


import java.util.Scanner;

/** Menú por consola. Solo lee datos y muestra mensajes; la lógica
 * está en Clinica.
 */
public class Main {

    public static void main(String[] args) {

        Clinica clinica = new Clinica();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println();
            System.out.println("=== CLÍNICA - GRUPO 7 ===");
            System.out.println("1. Registrar paciente");
            System.out.println("2. Buscar paciente por DNI");
            System.out.println("3. Dar turno regular");
            System.out.println("4. Ver próximo turno");
            System.out.println("5. Atender turno");
            System.out.println("6. Ingresar urgencia");
            System.out.println("7. Atender urgencia");
            System.out.println("8. Deshacer última acción");
            System.out.println("0. Salir");

            opcion = leerEntero(sc, "Opción: ");

            switch (opcion) {

                case 1:
                    String dni = leerTexto(sc, "DNI: ");
                    String nombre = leerTexto(sc, "Nombre: ");
                    String apellido = leerTexto(sc, "Apellido: ");

                    System.out.println(clinica.altaPaciente(dni, nombre, apellido));
                    break;

                case 2:
                    System.out.println(clinica.buscarPaciente(leerTexto(sc, "DNI: ")));
                    break;

                case 3:
                    System.out.println(clinica.darTurnoRegular(leerTexto(sc, "DNI: ")));
                    break;

                case 4:
                    System.out.println(clinica.verProximoTurno());
                    break;

                case 5:
                    System.out.println(clinica.atenderTurno());
                    break;

                case 6:
                    String dniU = leerTexto(sc, "DNI: ");
                    int gravedad = leerEntero(sc, "Gravedad (1 a 5): ");

                    System.out.println(clinica.ingresarUrgencia(dniU, gravedad));
                    break;

                case 7:
                    System.out.println(clinica.atenderUrgencia());
                    break;

                case 8:
                    System.out.println(clinica.deshacerUltimaAccion());
                    break;

                case 0:
                    System.out.println("Hasta luego.");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);

        sc.close();
    }

    private static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    /** Repite la pregunta hasta que se ingrese un número
     * (evita que el programa se corte).
     */
    private static int leerEntero(Scanner sc, String mensaje) {

        while (true) {
            System.out.print(mensaje);

            try {
                return Integer.parseInt(sc.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Ingresá un número válido");
            }
        }
    }
}
