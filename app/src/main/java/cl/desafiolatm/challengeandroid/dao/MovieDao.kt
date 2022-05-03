package cl.desafiolatm.challengeandroid.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import cl.desafiolatm.challengeandroid.model.moviedatabase.MovieDatabase

@Dao
interface MovieDao {

    @Insert(onConflict = REPLACE)
    fun addMovie(movie: MovieDatabase)

    @Query("select id, title, poster_path, overview, runtime, genre_one, genre_two, language" +
            ", popularity, release_date, country_one, country_two from movie_table")
    fun listMovie(): LiveData<List<MovieDatabase>>

    @Query("select id, title, poster_path, overview, runtime, genre_one, genre_two, language" +
            ", popularity, release_date, country_one, country_two from movie_table where id = :id")
    fun searchMovie(id: Int): MovieDatabase
}