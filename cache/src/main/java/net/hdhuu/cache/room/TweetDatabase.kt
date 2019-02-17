package net.hdhuu.cache.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.hdhuu.cache.post.models.CachedPost

@Database(entities = arrayOf(CachedPost::class), version = 3)
abstract class TweetDatabase : RoomDatabase() {

    abstract fun postDAO(): PostDAO

    private var INSTANCE: TweetDatabase? = null

    private val sLock = Any()

    fun getInstance(context: Context): TweetDatabase {
        if (INSTANCE == null) {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            TweetDatabase::class.java, "bufferoos.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
        return INSTANCE!!
    }

}