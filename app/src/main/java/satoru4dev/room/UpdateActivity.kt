package satoru4dev.room

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import satoru4dev.room.databinding.ActivityUpdateBinding
import satoru4dev.room.db.UserDatabase
import satoru4dev.room.db.UserEntities
import satoru4dev.room.utils.Constance

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var id = 0


        val db: UserDatabase =
            Room.databaseBuilder(
                context = this,
                klass = UserDatabase::class.java,
                name = Constance.DATABASE_NAME
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        intent.extras.let {
            if (it != null) {
                id = it.getInt(Constance.EXTRA_ID)
            }
        }
        binding.apply {
            val name = db.UserDao().getUser(id).name

            val age = db.UserDao().getUser(id).age.toString()
            etName.setText(name)
            etAge.setText(age)
            btnSave.setOnClickListener {
                db.UserDao().updateUser(
                    UserEntities(
                        id, etName.text.toString(),
                        etAge.text.toString().toInt()
                    )

                )
                finish()
            }

            btnDel.setOnClickListener {
                db.UserDao().deleteUser(
                    UserEntities(
                        id, name, age.toInt()

                    )

                )
                finish()
            }

        }


    }
}