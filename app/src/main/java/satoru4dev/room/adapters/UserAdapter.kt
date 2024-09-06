package satoru4dev.room.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import satoru4dev.room.UpdateActivity
import satoru4dev.room.databinding.ItemViewBinding
import satoru4dev.room.db.UserEntities
import satoru4dev.room.utils.Constance

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private lateinit var binding: ItemViewBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return UserViewHolder()
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        holder.setData(differ.currentList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount() = differ.currentList.size

    inner class UserViewHolder() : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(user: UserEntities) {
            binding.apply {
                tvShow.text = "id : ${user.id} \nname : ${user.name} \nage : ${user.age}"
                btnMore.setOnClickListener {
                    context.startActivity(
                        Intent(context, UpdateActivity::class.java).putExtra(
                            Constance.EXTRA_ID,
                            user.id
                        )
                    )
                }
            }
        }

    }

    private val diffCallback = object : DiffUtil.ItemCallback<UserEntities>() {
        override fun areItemsTheSame(oldItem: UserEntities, newItem: UserEntities) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserEntities, newItem: UserEntities) =
            oldItem == newItem
    }
    val differ = AsyncListDiffer(this, diffCallback)

}