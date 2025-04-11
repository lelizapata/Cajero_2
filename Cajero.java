import java.util.Scanner;
import java.time.LocalDateTime;

public class Cajero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = new Usuario(1234, "salchipapa"); 
        
        // PIN y palabra secreta
        ConfirmarPin confirmador = new ConfirmarPin(usuario, scanner);

        if (!confirmador.validar()) {
            System.out.println("Cuenta bloqueada. Saliendo del sistema...");
            scanner.close();
            return;
        }

        CajeroGroseron cajeroGroseron = new CajeroGroseron(scanner);
        MenuOpciones menuOpciones = new MenuOpciones(50000, scanner);

        int opcion;
        do {
            opcion = cajeroGroseron.mostrarMenu();
            menuOpciones.menurOpcion(opcion);
        } while (opcion != 4);

        scanner.close();
    }
}

class ConfirmarPin {
    private int intentos = 0;
    private boolean cuentaBloqueada = false;
    private Usuario usuario;
    private Scanner scanner;

    public ConfirmarPin(Usuario usuario, Scanner scanner) {
        this.usuario = usuario;
        this.scanner = scanner;
    }

    public boolean validar() {
        while (intentos < 3 && !cuentaBloqueada) {
            System.out.println("Ingrese su pin:");
            int pinIngresado = scanner.nextInt();

            if (usuario.validarPin(pinIngresado)) {
                System.out.println("Bienvenido Pichurria");
                return true;
            } else {
                intentos++;
                System.out.println("Pin Incorrecto. Mosca Pues " + intentos);

                if (intentos == 2) {
                    System.out.println("¿Olvidaste tu PIN? (Obvi/Gordo no)");
                    String respuesta = scanner.next();
                    if (respuesta.equalsIgnoreCase("Obvi")) {
                        if (recuperarCuenta()) {
                            return true;
                        }
                    }
                }

                if (intentos == 3) {
                    cuentaBloqueada = true;
                    System.out.println("Mera Gueva, cuenta bloqueada");
                }
            }
        }
        return false;
    }

    private boolean recuperarCuenta() {
        System.out.println("Para recuperar el PIN, dime la palabra secreta:");
        scanner.nextLine(); 
        String palabra = scanner.nextLine();

        if (usuario.recuperarPin(palabra)) {
            System.out.println("Perfecto, cambiá pues ese pin bebé: ");
            int nuevoPin = scanner.nextInt();
            usuario.cambiarPin(nuevoPin);
            System.out.println("Ya tenés nuevo pin, que no se te olvide pues...");
            intentos = 0;
            return true;
        } else {
            System.out.println("Esa no era la palabra secreta, sons@");
            return false;
        }
    }

    public boolean CuentisBloqueada() {
        return cuentaBloqueada;
    }
}

class CajeroGroseron {
    private Scanner scanner;

    public CajeroGroseron(Scanner scanner) {
        this.scanner = scanner;
    }

    public int mostrarMenu() {
        System.out.println("Bienvenido a su cajero Groserón");
        System.out.println("1. Consultar Chichigua");
        System.out.println("2. Retirar Menuda");
        System.out.println("3. Depositar Money");
        System.out.println("4. Abrirse del Parche");
        return scanner.nextInt();
    }
}

class MenuOpciones {
    private double saldo;
    private Scanner scanner;

    public MenuOpciones(double saldoInicial, Scanner scanner) {
        this.saldo = saldoInicial;
        this.scanner = scanner;
    }

    public void menurOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.println("Ay marica tenes apenas $ " + saldo);
                break;
            case 2:
                System.out.println("¿Cuanta chichigua vas a sacar?");
                double retiro = scanner.nextDouble();
                if (retiro > 0 && retiro <= saldo) {
                    saldo -= retiro;
                    System.out.println("Su nueva chichigua es de: " + saldo);
            
                    // 👉 Mostrar resumen
                    Transaccion resumen = new Transaccion("Retiro", retiro, saldo);
                    resumen.mostrarResumen();
            
                } else {
                    System.out.println("Oigan a esta gueva, si solo tenes " + saldo);
                }
                break;
            
            case 3:
                System.out.println("Empiece a cantar, cuanta plata va a consignar?");
                double deposito = scanner.nextDouble();
                if (deposito > 0) {
                    saldo += deposito;
                    System.out.println("Manoo, su nueva consignacion fue exitosa. Su saldo es: " + saldo);
            
                    // Resumen
                    Transaccion resumen = new Transaccion("Depósito", deposito, saldo);
                    resumen.mostrarResumen();
            
                } else {
                    System.out.println("Despierte pues mijo, ese valor no se puede!!!");
                }
                break;
        }
    }
}

            