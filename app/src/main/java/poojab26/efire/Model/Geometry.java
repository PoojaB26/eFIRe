
package poojab26.efire.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry {

    @SerializedName("location")
    @Expose
    private Location_ location;
    @SerializedName("location_type")
    @Expose
    private String locationType;


    public Location_ getLocation_() {
        return location;
    }

    public void setLocation(Location_ location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }


}
