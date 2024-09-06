package satoru4dev.room.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import satoru4dev.room.utils.Constance


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserEntities)

    @Update
    fun updateUser(user: UserEntities)

    @Delete
    fun deleteUser(user: UserEntities)

    @Query("SELECT * FROM ${Constance.TABLE_NAME}")
    fun getAllUser(): MutableList<UserEntities>

    @Query("SELECT * FROM ${Constance.TABLE_NAME} WHERE id LIKE :id")
    fun getUser(id: Int): UserEntities

}