package cl.desafiolatm.challengeandroid.client

import cl.desafiolatm.challengeandroid.service.MovieService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        const val base_url = "https://api.themoviedb.org/3/movie/"
        private var client: Retrofit? = null

        fun getInstance(url:String) : MovieService
        {
            if(client == null)
            {
                client = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return client!!.create(MovieService::class.java)
        }
    }
}