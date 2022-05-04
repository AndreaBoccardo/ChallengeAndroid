package cl.desafiolatm.challengeandroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.desafiolatm.challengeandroid.model.moviedatabase.MovieDatabase
import cl.desafiolatm.challengeandroid.model.moviedetail.MovieDetail
import cl.desafiolatm.challengeandroid.model.movielist.MovieList
import cl.desafiolatm.challengeandroid.model.movielist.Response
import cl.desafiolatm.challengeandroid.repository.ClientRepository
import cl.desafiolatm.challengeandroid.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback

class MovieViewModel(application: Application): AndroidViewModel(application) {

    var listMoviesPopular: MutableLiveData<List<MovieList>> = MutableLiveData()
    private val repoApi = ClientRepository()
    private val movieRepo = MovieRepository(application)
    val listMovies = movieRepo.listMovie()
    val movie: MutableLiveData<MovieDatabase> = MutableLiveData()
    val filter = MutableLiveData<String>()

    fun getMoviesPopular(){
        repoApi.getListMovies().enqueue(object : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                response.body().let {
                    listMoviesPopular.postValue(it!!.movieLists)
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.d("Call", t.message.toString())
            }
        })
    }

    fun getMovieDetail(id: Int){
        repoApi.getDetailMovie(id).enqueue(object  : Callback<MovieDetail>{
            override fun onResponse(
                call: Call<MovieDetail>,
                response: retrofit2.Response<MovieDetail>
            ) {
                response.body().let {
                    movieRepo.addMovie(addMovieDetail(it!!.id, it.title!!, it.posterPath!!
                        , it.overview!! , it.runtime!!, it.genres!![0].toString()
                        , it.spokenLanguages!![0].toString(), it.popularity!!, it.releaseDate!!
                        , it.productionCountries!![0].toString()))
                                       }
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                Log.d("Call", t.message.toString())
            }
        })
    }

    fun addMovieDetail(id: Int, title: String, poster_path: String, overview: String, runtime: Int,
        genre: String, language: String, popularity: Double, release_date: String,
        country: String) : MovieDatabase{
        val film = MovieDatabase(id, title, poster_path, overview, runtime, genre,language
            , popularity, release_date, country)
        return film
    }

    fun searchMovie(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            movie.postValue(movieRepo.searchMovie(id))
        }
    }
}