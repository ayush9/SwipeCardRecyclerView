package swipe.card.recycler.view.data

import androidx.room.*
import swipe.card.recycler.view.Results


@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(items : List<Results>)

    @Query("UPDATE items SET userStatus=:status WHERE profileId = :profileId")
    fun update(status: String, profileId: Int)

    @Query("SELECT * FROM items")
    fun getAllItems() : List<Results>
}