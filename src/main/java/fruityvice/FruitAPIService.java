package fruityvice;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface FruitAPIService {

    @GET("/api/fruit/all")
    Call<List<Fruit>> getAllFruits ();

    @GET("/api/fruit/{name}")
    Call<Fruit> getFruit(@Path("name") String name);

}
