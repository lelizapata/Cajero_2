public class Usuario {
    private int pin;
    private String palabraSecret;

    public Usuario(int pin, String palabraSecret) {
        this.pin = pin;
        this.palabraSecret = palabraSecret.toLowerCase();
    }

    public boolean validarPin(int pinIngresado) {
        return this.pin == pinIngresado;
    }

    public boolean recuperarPin(String palabraIngresada) {
        return this.palabraSecret.equals(palabraIngresada.toLowerCase());
    }

    public void cambiarPin(int nuevoPin) {
        this.pin = nuevoPin;
    }

    public int getPin() {
        return pin;
    }
}
