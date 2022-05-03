package cl.desafiolatm.challengeandroid.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.desafiolatm.challengeandroid.dao.MovieDao
import cl.desafiolatm.challengeandroid.model.moviedatabase.MovieDatabase

@Database (entities = [MovieDatabase::class], version = 1)
abstract class ProjectDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var instance: ProjectDatabase? = null



        fun getDatabase(context: Context) : ProjectDatabase
        {
            if (instance == null)
            {
                synchronized(this)
                {
                    instance = Room.databaseBuilder(context,
                        ProjectDatabase::class.java,
                        "proyecto_db").build()
                }
            }
            return  instance!!
        }
    }
}