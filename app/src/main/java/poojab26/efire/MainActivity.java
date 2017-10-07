package poojab26.efire;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference firRef = database.getReference("fir");
        DatabaseReference recordRef = firRef.child("record-1");


        HashMap<String, String> fields = new HashMap<>();
        fields.put(OFFENCE_TIMESTAMP, "timestamp");
        fields.put(OFFENCE_LOCATION, "location");
        fields.put(REPORT_TIMESTAMP, "reportimestamp");
        fields.put(C_NAME, "cname");
        fields.put(C_ADDRESS, "caddress");
        fields.put(A_NAME, "aname");
        fields.put(DESCRIPTION, "description");
        fields.put(POLICE_STATION, "cname");
        fields.put(SECTIONS, "sections");

        recordRef.setValue(fields);
    }
}
