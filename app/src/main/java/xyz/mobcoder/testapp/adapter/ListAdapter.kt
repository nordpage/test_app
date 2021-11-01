package xyz.mobcoder.testapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.mobcoder.testapp.databinding.ListItemBinding
import xyz.mobcoder.testapp.sqlite.DBOpenHelper
import xyz.mobcoder.testapp.sqlite.models.ListItem

class ListAdapter(var adapterListener: AdapterListener, private var dbOpenHelper: DBOpenHelper) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private var itemList = dbOpenHelper.getAllItems()

    interface AdapterListener{
        fun onClick(hash: String)
    }
    inner class ViewHolder(private val binding: ListItemBinding) :  RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListItem) {
            binding.listItem  = item
            binding.root.setOnClickListener {
                adapterListener.onClick(item.title!!)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bindItem = itemList[position]
        holder.bind(bindItem)
    }

    override fun getItemCount(): Int = itemList.size
}