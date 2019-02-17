package net.hdhuu.cache.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.hdhuu.cache.post.models.CachedPost

@Dao
abstract class PostDAO {

    @Query("select * from Post")
    abstract fun getAllPosts(): List<CachedPost>

    @Query("DELETE FROM Post")
    abstract fun clearAllPosts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPost(cachedBufferoo: CachedPost)

}