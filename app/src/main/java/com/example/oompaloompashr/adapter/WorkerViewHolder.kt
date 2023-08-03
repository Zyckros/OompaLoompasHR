package com.example.oompaloompashr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.oompaloompashr.R
import com.example.oompaloompashr.model.Result
import com.example.oompaloompashr.ui.workerlists.workerlist.WorkerListFragment
import com.example.oompaloompashr.ui.workerlists.workerlist.WorkerListFragmentDirections
import kotlin.coroutines.coroutineContext

class WorkerViewHolder(
    view: View) : RecyclerView.ViewHolder(view) {
    private val name: TextView = view.findViewById(R.id.first_name)
    private val image: ImageView = view.findViewById(R.id.image_view)
    private var result: Result? = null

    init {
        view.setOnClickListener {
            result?.idResult.let {
                val arg = it ?: 1L
                view.findNavController().navigate(WorkerListFragmentDirections.actionWorkerListFragmentToWrokerDetailFragment(arg))
            }

        }
    }

    fun bind(result: Result?){
        if (result == null) {
            val resources = itemView.resources
            name.text = resources.getString(R.string.loading)
            image.visibility = View.GONE
        } else {
            showRepoData(result)
        }
    }

    private fun showRepoData(result: Result) {
        this.result = result
        name.text = result.firstName
        Glide.with(this.itemView.context).load(result.image).into(image)
    }

    companion object {
        fun create(parent: ViewGroup): WorkerViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.worker_list_item, parent, false)
            return WorkerViewHolder(view)
        }
    }
}
