package Exception;

public class NoSeEncuentraPlacaException extends Exception {
	public NoSeEncuentraPlacaException() {
		super("No se encontro una placa que coincida con la ingresada");
	}
	
}
