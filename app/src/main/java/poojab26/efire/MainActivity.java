package poojab26.efire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import poojab26.efire.Model.Geometry;
import poojab26.efire.Model.LatLong;
import poojab26.efire.Model.NearbyPolice;
import poojab26.efire.Model.PoliceResult;
import poojab26.efire.Utilities.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    String TAG = "Firebase";
    String OFFENCE_TIMESTAMP = "office_timestamp",
    REPORT_TIMESTAMP = "report_timestamp",
    OFFENCE_LOCATION = "offence_location",
    C_NAME = "c_name",
    C_ADDRESS = "c_address",
    A_NAME = "a_name",
    DESCRIPTION = "desc",
    POLICE_STATION = "police_station",
    SECTIONS = "sections";

    ApiInterface apiInterface;

    String police_station_value;
    String offence_location_address = "Indiranagar Metro Station";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database


        getLatLong(offence_location_address);
    }

    private void sendToFirebase(String police_station_value){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference firRef = database.getReference("fir");
        DatabaseReference recordRef = firRef.child("record-6");
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        HashMap<String, String> fields = new HashMap<>();
        fields.put(OFFENCE_TIMESTAMP, "timestamp");
        fields.put(OFFENCE_LOCATION, "location");
        fields.put(REPORT_TIMESTAMP, String.valueOf(currentTimestamp));

        fields.put(C_NAME, "cname");
        fields.put(C_ADDRESS, "caddress");
        fields.put(A_NAME, "aname");
        fields.put(DESCRIPTION, "description");
        fields.put(POLICE_STATION, police_station_value);
        fields.put(SECTIONS, "sections");

        recordRef.setValue(fields);

    }
    private void loadNearbyPolice(String location) {
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        Call<NearbyPolice> call = apiInterface.getNearbyPolice(location, 500, "police", getString(R.string.places_key));
        call.enqueue(new Callback<NearbyPolice>() {
            @Override
            public void onResponse(Call<NearbyPolice> call, Response<NearbyPolice> response) {

                List<PoliceResult> policeResults = response.body().getPoliceResults();
                Log.d("TEST", policeResults.get(0).getName() );
                police_station_value = policeResults.get(0).getName();
                sendToFirebase(police_station_value);



            }

            @Override
            public void onFailure(Call<NearbyPolice> call, Throwable t) {
                Log.d("Error", t.getMessage());


            }
        });

    }

    private void getLatLong(String offence_location_address) {
        apiInterface = APIClient.getClient().create(ApiInterface.class);

        Call<LatLong> call = apiInterface.getLatLong(offence_location_address, getString(R.string.geocode_key));
        call.enqueue(new Callback<LatLong>() {
            @Override
            public void onResponse(Call<LatLong> call, Response<LatLong> response) {

                Geometry geometry = response.body().getLatLongResults().get(0).getGeometry();
                String lat = String.valueOf(geometry.getLocation_().getLat()),
                        lng = String.valueOf(geometry.getLocation_().getLng());
                loadNearbyPolice(lat + ", " + lng);


            }

            @Override
            public void onFailure(Call<LatLong> call, Throwable t) {
                Log.d("Error", t.getMessage());


            }
        });

    }

}
