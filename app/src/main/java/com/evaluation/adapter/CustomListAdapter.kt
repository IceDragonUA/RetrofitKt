package com.evaluation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.evaluation.adapter.CustomListAdapter.ListAdapterHolder
import com.evaluation.command.ICommand
import com.evaluation.model.search.SearchResult
import com.evaluation.retrofitkt.R

class CustomListAdapter(private val context: Context, private val searchResultList: List<SearchResult>, private val clickCommand: ICommand<SearchResult>) : RecyclerView.Adapter<ListAdapterHolder>() {

    companion object {
        private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185"
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ListAdapterHolder {
        return ListAdapterHolder(context, LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item, viewGroup, false))
    }

    override fun onBindViewHolder(searchListAdapterHolder: ListAdapterHolder, position: Int) {
        searchListAdapterHolder.bind(getItem(position), clickCommand)
    }

    private fun getItem(position: Int): SearchResult = searchResultList[position]

    override fun getItemCount(): Int = searchResultList.size

    class ListAdapterHolder(private val mContext: Context, view: View?) : RecyclerView.ViewHolder(view!!) {

        @BindView(R.id.title)
        lateinit var titleView: TextView

        @BindView(R.id.thumbnail)
        lateinit var thumbnailView: ImageView

        init {
            ButterKnife.bind(this, view!!)
        }

        fun bind(selectedSearchResult: SearchResult, assetClickCommand: ICommand<SearchResult>) {
            titleView.text = selectedSearchResult.title
            Glide.with(mContext)
                .load(BASE_IMAGE_URL + selectedSearchResult.posterPath)
                .into(thumbnailView)

            itemView.setOnClickListener {
                assetClickCommand.execute(
                    selectedSearchResult
                )
            }
        }
    }
}