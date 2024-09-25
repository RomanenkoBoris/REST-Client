package fruityvice;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

public class FruitRepository {
    private final FruitAPIService fruitAPIService;
    public FruitRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.fruityvice.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.fruitAPIService = retrofit.create(FruitAPIService.class);
    }
    public Fruit getFruitByName(String name) throws IOException {
        Call<Fruit> fruitCall = fruitAPIService.getFruit(name);
        Response<Fruit> fruitResponse = fruitCall.execute();
        if (fruitResponse.isSuccessful() && fruitResponse.body() != null)
            return fruitResponse.body();
        else
            throw new IOException("Fruit not found or incorrect name provided.");
    }

    public List<Fruit> getAllFruits() throws IOException {
        Call<List<Fruit>> call = fruitAPIService.getAllFruits();
        Response <List<Fruit>> response = call.execute();
        if (response.isSuccessful() && response.body() != null)
            return response.body();
        else
            throw new IOException("Failed to fetch fruits.");
    }
}
