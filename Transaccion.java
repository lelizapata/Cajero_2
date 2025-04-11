import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaccion {
    private String tipo;
    private double monto;
    private double saldoFinal;
    private LocalDateTime fecha;

    public Transaccion(String tipo, double monto, double saldoFinal) {
        this.tipo = tipo;
        this.monto = monto;
        this.saldoFinal = saldoFinal;
        this.fecha = LocalDateTime.now();
    }

    public void mostrarResumen() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("\n====== Comprobante Retiro ======");
        System.out.println("Tipo de movimiento: " + tipo);
        System.out.println("Monto: $" + monto);
        System.out.println("Fecha: " + fecha.format(formato));
        System.out.println("Saldo final: $" + saldoFinal);
        System.out.println("=========================\n");
    }
}
