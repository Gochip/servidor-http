package comun;

/**
 *
 * @author Parisi Germán
 */
public class Codigos {

    public static BadRequestException BAD_REQUEST = new BadRequestException();
    public static NotFoundException NOT_FOUND = new NotFoundException();

    public static class HTTPException extends Exception {

        public final String codigo;
        public final String mensaje;
        public final String cuerpo;

        public HTTPException(String codigo, String mensaje, String cuerpo) {
            this.codigo = codigo;
            this.mensaje = mensaje;
            this.cuerpo = cuerpo;
        }

        @Override
        public String toString() {
            return codigo + " " + mensaje;
        }
    }

    public static class BadRequestException extends HTTPException {

        public BadRequestException() {
            super("400", "Bad Request", "Solicitud inválida");
        }

    }

    public static class NotFoundException extends HTTPException {

        public static String htmlCuerpo = "<!DOCTYPE html>\n<html><head><meta charset='utf-8' /></head><body><h1>Página no encontrada</h1></body></html> ";

        public NotFoundException() {
            super("404", "Not Found", htmlCuerpo);
        }

    }

}
