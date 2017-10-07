
package poojab26.efire.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LatLong {

    @SerializedName("results")
    @Expose
    private List<LatLongResult> latLongResults = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<LatLongResult> getLatLongResults() {
        return latLongResults;
    }

    public void setLatLongResults(List<LatLongResult> latLongResults) {
        this.latLongResults = latLongResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
