package com.example.task2level4yassine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.result_item.view.*

/**
 * @author Yassine el Aatiaoui
 * Student Nr 500767860
 */

class ResultsAdapter(private val results: List<Result>) :
    RecyclerView.Adapter<ResultsAdapter.ViewHolder>() {

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.result_item,
                parent,
                false
            )
        )
    }

    /**
     * here we get the size of the list back
     */
    override fun getItemCount(): Int {
        return results.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    /**
     * Bind item view with each result
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: Result) {
            itemView.tvWinner.text = result.winnar
            itemView.tvResultTimestamp.text = result.dateTime
            itemView.ivPlayer.setImageResource(ConvertImage.getDrawableFromChoice(result.playerChoice))
            itemView.ivComputer.setImageResource(ConvertImage.getDrawableFromChoice(result.computerChoice))
        }
    }
}