package satoru4dev.room

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import satoru4dev.room.adapters.UserAdapter
import satoru4dev.room.databinding.ActivityMainBinding
import satoru4dev.room.db.UserDatabase
import satoru4dev.room.utils.Constance

private lateinit var binding: ActivityMainBinding
private val userAdapter by lazy { UserAdapter() }
private lateinit var db: UserDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db =
            Room.databaseBuilder(
                context = this,
                klass = UserDatabase::class.java,
                name = Constance.DATABASE_NAME
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
        binding.apply {

            mainBtn.setOnClickListener {
                startActivity(Intent(this@MainActivity, AddActivity::class.java))
            }

        }


        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "res", Toast.LENGTH_SHORT).show()
        load()
    }

    fun load() {
        userAdapter.differ.submitList(db.UserDao().getAllUser())

        binding.mainRec.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = userAdapter
        }
    }
}