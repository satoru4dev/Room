package satoru4dev.room

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import satoru4dev.room.databinding.ActivityUpdateBinding
import satoru4dev.room.db.UserDatabase
import satoru4dev.room.db.UserEntities
import satoru4dev.room.utils.Constance

class AddActivity : AppCompatActivity() {
    private lateinit var db: UserDatabase


    private lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db =
            Room.databaseBuilder(
                context = this,
                klass = UserDatabase::class.java,
                name = Constance.DATABASE_NAME
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        binding.apply {
            btnSave.setOnClickListener {
                db.UserDao()
                    .addUser(UserEntities(0, etName.text.toString(), etAge.text.toString().toInt()))
                finish()
            }
            btnDel.visibility = View.INVISIBLE
        }
    }
}