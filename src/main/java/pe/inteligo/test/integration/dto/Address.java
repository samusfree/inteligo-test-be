package pe.inteligo.test.integration.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("housenumber")
    @Expose
    private String housenumber;
    @SerializedName("postalcode")
    @Expose
    private String postalcode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("geoLocation")
    @Expose
    private GeoLocation geoLocation;
}
