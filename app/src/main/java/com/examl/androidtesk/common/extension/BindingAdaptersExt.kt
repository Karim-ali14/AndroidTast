package com.examl.androidtesk.common.extension

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.examl.androidtesk.R

/////////////////////////////////////////////////////////////////////////
// Swipe Refresh Binding Adapter.
/////////////////////////////////////////////////////////////////////////

@BindingAdapter(value = ["isRefreshing"])
fun SwipeRefreshLayout.isRefreshing(newValue: Boolean) {
    this.isRefreshing = newValue
}

@BindingAdapter(value = ["onRefresh"])
fun SwipeRefreshLayout.onRefresh(action: () -> Unit) {
    this.setOnRefreshListener {
        action()
    }
}

/////////////////////////////////////////////////////////////////////////
// ImageViews Adapters.
/////////////////////////////////////////////////////////////////////////

@BindingAdapter(value = ["glideImageUrl"])
fun ImageView.glideImageUrl(url: String?) {
    this.run {

        if (!url.isNullOrEmpty()) {
            Glide
                .with(this)
                .load(url)
//                .placeholder(R.drawable.ic_image_placeholder_48)
//                .error(R.drawable.ic_image_placeholder_48)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .dontAnimate()
                .dontTransform()
                .priority(Priority.HIGH)
                .into(this)
        } else {
            this.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_launcher_foreground
                )
            )
        }
    }
}


/////////////////////////////////////////////////////////////////////////
// RecyclerView setup.
/////////////////////////////////////////////////////////////////////////

@BindingAdapter(value = ["recyclerAdapter","isLinear","cols","isHorizontal"], requireAll = false)
fun <T : Any> RecyclerView.setup(customAdapter: RecyclerView.Adapter<*>, isLinear:Boolean = true, cols : Int? = 2, isHorizontal : Boolean = false){
    val orientation = if(isHorizontal) RecyclerView.HORIZONTAL else RecyclerView.VERTICAL
    adapter = customAdapter
    layoutManager = if(isLinear) LinearLayoutManager(context,orientation,false) else GridLayoutManager(context,cols?:2)
}


