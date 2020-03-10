package com.nf98.moviecatalogue.database

import android.content.Context
import android.net.Uri
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nf98.moviecatalogue.api.model.Movie
import com.nf98.moviecatalogue.api.model.TVShow
import com.nf98.moviecatalogue.helper.DataConverter

@Database(entities = [Movie::class, TVShow::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var INSTANCE: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "MovieDB"
                ).build()
            }

        val CONTENT_MOVIE: Uri = Uri.Builder().scheme("content")
            .authority("com.nf98.moviecatalogue")
            .appendPath("movie")
            .build()

        val CONTENT_TV: Uri = Uri.Builder().scheme("content")
            .authority("com.nf98.moviecatalogue")
            .appendPath("tv_show")
            .build()
    }
}