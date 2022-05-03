package cl.desafiolatm.challengeandroid.repository

import android.content.Context
import androidx.lifecycle.LiveData
import cl.desafiolatm.challengeandroid.model.moviedatabase.MovieDatabase
import cl.desafiolatm.challengeandroid.room.ProjectDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieRepository(var context: Context) {

    private val db = ProjectDatabase.getDatabase(context)

    fun addMovie(movie: MovieDatabase){
        CoroutineScope(Dispatchers.IO).launch {
            db.movieDao().addMovie(movie)
        }
    }

    fun listMovie(): LiveData<List<MovieDatabase>>{
        return db.movieDao().listMovie()
    }

    fun searchMovie(id: Int): MovieDatabase{
        return db.movieDao().searchMovie(id)
    }
}