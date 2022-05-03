package cl.desafiolatm.challengeandroid.model.moviedatabase

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "movie_table")
data class MovieDatabase(
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "poster_path")
    var poster_path: String,
    @ColumnInfo(name = "overview")
    var overview: String,
    @ColumnInfo(name = "runtime")
    var runtime: Int,
    @ColumnInfo(name = "genre_one")
    var genre_one: String,
    @ColumnInfo(name = "genre_two")
    var genre_two: String,
    @ColumnInfo(name = "language")
    var language: String,
    @ColumnInfo(name = "popularity")
    var popularity: Double,
    @ColumnInfo(name = "release_date")
    var release_date: String,
    @ColumnInfo(name = "country_one")
    var country_one: String,
    @ColumnInfo(name = "country_two")
    var country_two: String,
)
