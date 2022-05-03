package cl.desafiolatm.challengeandroid.service

import cl.desafiolatm.challengeandroid.model.moviedetail.MovieDetail
import cl.desafiolatm.challengeandroid.model.movielist.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {

    @GET("popular?api_key=5b5bb95711f375995913fa5e4c6355d9&language=en-US&page=1")
    fun getMovies(): Call<Response>

    @GET("{movie_id}?api_key=5b5bb95711f375995913fa5e4c6355d9&language=en-US")
    fun getFilmDetail(@Path("movie_id") movie_id:Int): Call<MovieDetail>
}