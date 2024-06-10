package ciudadaniaseuropeas.exception;

public class TramiteNoEncontradoExcepcion extends Exception {
	public TramiteNoEncontradoExcepcion() {
		super("El tramite no existe.");
	}

	public TramiteNoEncontradoExcepcion(Long idTramite) {
		super("El tramite " + idTramite + " no existe.");
	}

	public TramiteNoEncontradoExcepcion(String mensaje) {
		super(mensaje);
	}
}