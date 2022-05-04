package cl.desafiolatm.challengeandroid.model.moviedatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieDatabase(
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "poster_path")
    var poster_path: String?,
    @ColumnInfo(name = "overview")
    var overview: String?,
    @ColumnInfo(name = "runtime")
    var runtime: Int?,
    @ColumnInfo(name = "genre")
    var genre: String?,
    @ColumnInfo(name = "language")
    var language: String?,
    @ColumnInfo(name = "popularity")
    var popularity: Double?,
    @ColumnInfo(name = "release_date")
    var release_date: String?,
    @ColumnInfo(name = "country")
    var country: String?
)
