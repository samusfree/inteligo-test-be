package pe.inteligo.test.integration.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationPoint {
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("type")
    @Expose
    private String type;
}
