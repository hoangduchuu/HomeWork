package net.hdhuu.cache.room


import androidx.room.*
import net.hdhuu.cache.post.models.CachedPost

@Dao
abstract class PostDAO {

    @Query("select * from Post")
    abstract fun getAllPosts(): List<CachedPost>

    @Query("DELETE FROM Post")
    abstract fun clearAllPosts()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPost(cachedBufferoo: CachedPost)

    @Query(" delete from post where id =:postID")
    abstract fun deletePostByID(postID: String)

    @Delete
    abstract fun deletePost(post: CachedPost)

    @Update
    abstract fun updatePost(post: CachedPost)

}