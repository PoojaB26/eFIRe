package poojab26.efire;

import poojab26.efire.Model.NearbyPolice;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pblead26 on 04-Oct-17.
 */

public interface ApiInterface {
/*

    @Headers("user_key: c031832bac8def9a5efadcb27718dfeb")
    @GET("geocode")
    Call<RestaurantList> getRestaurantList(@Query("lat")String lat, @Query("lon")String lon);
*/


    @GET("nearbysearch/json")
    Call<NearbyPolice> getNearbyPolice(
            @Query("location") String location,
            @Query("radius") int radius,
            @Query("type") String type,
            @Query("key") String key
            );



}
