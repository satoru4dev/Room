package satoru4dev.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import satoru4dev.room.utils.Constance


@Entity(tableName = Constance.TABLE_NAME)
data class UserEntities(
    @PrimaryKey(autoGenerate = true)
    var id: Int ,
    var name: String,
    var age: Int
)
