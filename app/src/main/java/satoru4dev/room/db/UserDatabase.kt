package satoru4dev.room.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [UserEntities::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao
}
