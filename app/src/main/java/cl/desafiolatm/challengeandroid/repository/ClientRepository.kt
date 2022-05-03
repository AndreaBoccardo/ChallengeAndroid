package cl.desafiolatm.challengeandroid.repository

import cl.desafiolatm.challengeandroid.client.RetrofitClient
import cl.desafiolatm.challengeandroid.model.moviedetail.MovieDetail
import cl.desafiolatm.challengeandroid.model.movielist.Response
import retrofit2.Call

class ClientRepository {

    private val client = RetrofitClient.getInstance(RetrofitClient.base_url)

    fun getListMovies(): Call<Response>{
        return client.getMovies()
    }

    fun getDetailMovie(id: Int): Call<MovieDetail>{
        return client.getFilmDetail(id)
    }
}