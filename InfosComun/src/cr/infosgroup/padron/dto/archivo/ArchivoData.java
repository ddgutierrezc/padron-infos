package cr.infosgroup.padron.dto.archivo;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class ArchivoData {

    //private static final Logger log = LogManager.getLogger(ArchivoData.class);

    private static final Map<String, String> EXTENSION_TO_CONTENT_TYPE = new HashMap<>();
    private static final Map<String, String> CONTENT_TYPE_TO_EXTENSION = new HashMap<>();
    private static final Set<String> IMAGE_CONTENT_TYPES = Set.of("image/png", "image/jpeg", "image/jpg", "image/gif");

    static {
        EXTENSION_TO_CONTENT_TYPE.put(".png", "image/png");
        EXTENSION_TO_CONTENT_TYPE.put(".jpeg", "image/jpeg");
        EXTENSION_TO_CONTENT_TYPE.put(".jpg", "image/jpeg");
        EXTENSION_TO_CONTENT_TYPE.put(".gif", "image/gif");
        EXTENSION_TO_CONTENT_TYPE.put(".pdf", "application/pdf");

        EXTENSION_TO_CONTENT_TYPE.forEach((ext, type) -> CONTENT_TYPE_TO_EXTENSION.put(type, ext));
    }

    private byte[] content;
    private String contentType;

    public ArchivoData() {}

    public static String codificar(String contentType, byte[] content) {
        return "data:" + contentType + ";base64," + Base64.getEncoder().encodeToString(content);
    }

    public static ArchivoData decodificar(String base64Data) {
        String[] parts = base64Data.split(";base64,");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Formato base64 inválido");
        }

        String contentType = parts[0].replace("data:", "");
        byte[] content = Base64.getDecoder().decode(parts[1]);

        ArchivoData archivo = new ArchivoData();
        archivo.setContentType(contentType);
        archivo.setContent(content);
        return archivo;
    }

    public static ArchivoData decodificar(String base64Data, String nombreDocumentoConExtension) {
        ArchivoData archivo = decodificar(base64Data);

        if (archivo.getContentType() == null || archivo.getContentType().isBlank()) {
            String extension = getExtensionFromFilename(nombreDocumentoConExtension);
            String contentType = EXTENSION_TO_CONTENT_TYPE.get(extension.toLowerCase());

            if (contentType != null) {
                archivo.setContentType(contentType);
            } else {
                throw new IllegalArgumentException("No se pudo determinar el contentType desde la extensión: " + extension);
            }
        }

        return archivo;
    }

    private static String getExtensionFromFilename(String filename) {
        int index = filename.lastIndexOf('.');
        return (index != -1) ? filename.substring(index) : "";
    }

    public static String imgHtml(String style, String contentType, byte[] content) {
        return "<img style='" + style + "' src='" + codificar(contentType, content) + "' />";
    }

    public static String getExtension(String contentType) {
        return CONTENT_TYPE_TO_EXTENSION.getOrDefault(contentType, "");
    }

    public static String getContentType(String ruta) {
        String extension = getExtensionFromFilename(ruta).toLowerCase();
        return EXTENSION_TO_CONTENT_TYPE.getOrDefault(extension, "");
    }

    public static boolean isContentTypeImage(String contentType) {
        return IMAGE_CONTENT_TYPES.contains(contentType.toLowerCase());
    }

    public static String getContentAsImgHtml(String contentType, byte[] content) {
        String style = "max-width: 650px; max-height: 920px";
        return imgHtml(style, contentType, content);
    }

    // Getters y Setters
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
