package com.examl.androidtesk.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examl.androidtesk.data.model.PlayerModel
import com.examl.androidtesk.databinding.PlayerItemLayoutBinding

class PlayerAdapter(private val playerList:ArrayList<PlayerModel>):RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>(){

    class PlayerViewHolder(val binding:PlayerItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            PlayerItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.binding.player = playerList[position]
    }

    override fun getItemCount(): Int = playerList.size

    fun updateList(list: ArrayList<PlayerModel>) {
        playerList.clear()
        playerList.addAll(list)
        notifyDataSetChanged()
    }
}