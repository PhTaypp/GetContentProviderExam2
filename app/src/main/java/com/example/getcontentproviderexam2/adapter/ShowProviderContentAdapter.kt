package com.example.getcontentproviderexam2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.getcontentproviderexam2.R
import com.example.getcontentproviderexam2.model.User

class ShowProviderContentAdapter(var context: Context, var listUser: ArrayList<User>) :
    RecyclerView.Adapter<ShowProviderContentAdapter.ProviderContentViewHolder>() {
    var onItemClickListener: OnClickListener? = null
    inner class ProviderContentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.name)
        var tvPhone : TextView = itemView.findViewById(R.id.phone)


    }
    interface OnClickListener {
        fun onClickItem(view: View?,position: Int,holder: ProviderContentViewHolder)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShowProviderContentAdapter.ProviderContentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView: View = inflater.inflate(R.layout.item_user, parent, false)
        return ProviderContentViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ShowProviderContentAdapter.ProviderContentViewHolder,
        position: Int
    ) {
        val user : User = listUser[position]
        holder.setIsRecyclable(false)
        holder.tvName.text = user.name
        holder.tvPhone.text = user.phone
        holder.itemView.setOnClickListener{
            onItemClickListener?.onClickItem(holder.itemView,position,holder)
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}