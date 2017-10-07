
package poojab26.efire.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NearbyPolice {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = null;
    @SerializedName("results")
    @Expose
    private List<PoliceResult> policeResults = null;
    @SerializedName("status")
    @Expose
    private String status;


    public List<PoliceResult> getPoliceResults() {
        return policeResults;
    }

    public void setPoliceResults(List<PoliceResult> policeResults) {
        this.policeResults = policeResults;
    }

}
