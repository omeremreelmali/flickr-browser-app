package net.fixedbugs.flickrbrowser.data


import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {

    @GET("photos_public.gne")
    suspend fun listPhoto(@Query("tags") tags: String,
                  @Query("format") format: String ="json",
                  @Query("nojsoncallback") nojsoncallback: String) : Response<PhotoData>

    companion object{
        const val API_URL = "https://api.flickr.com/services/feeds/"
    }
}

object FlickerApi{
    private  var retrofit: Retrofit?= null
    fun getClient(): Retrofit{
        if (retrofit==null){
            retrofit= Retrofit.Builder()
                    .baseUrl(FlickrApiService.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }

        return retrofit as Retrofit
    }
}