package cr.infosgroup.integraciones.liferay.model;

/**
 * Modelo para solicitudes de actualización de temporadas
 * Solo incluye los campos que se pueden modificar
 *
 * @author crodriguez
 */
public class SeasonUpdateRequest {

    private String seasonTitle;
    private String seasonDescription;

    public SeasonUpdateRequest() {}

    public SeasonUpdateRequest(String seasonTitle, String seasonDescription) {
        this.seasonTitle = seasonTitle;
        this.seasonDescription = seasonDescription;
    }

    public String getSeasonTitle() {
        return seasonTitle;
    }

    public void setSeasonTitle(String seasonTitle) {
        this.seasonTitle = seasonTitle;
    }

    public String getSeasonDescription() {
        return seasonDescription;
    }

    public void setSeasonDescription(String seasonDescription) {
        this.seasonDescription = seasonDescription;
    }

    @Override
    public String toString() {
        return "SeasonUpdateRequest{" +
                "seasonTitle='" + seasonTitle + '\'' +
                ", seasonDescription='" + seasonDescription + '\'' +
                '}';
    }
}
