package jp.hiroshi.nov.m.a7minuteworkout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_status.view.*


/** Adapter displaying the progress of each exercise. */
class ExerciseStatusAdapter(
    /** List of exercises for the adapter. */
    val items: ArrayList<ExerciseModel>,
    /** Context used to inflate layouts. */
    val context: Context
) : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    /** Creates view holders for status items. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_exercise_status,
                parent,
                false
            )
        )
    }

    /** Binds an exercise status item at the given position. */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]

        holder.tvItem.text = model.getId().toString()

        if(model.getIsSelected()){
            holder.tvItem.background = ContextCompat.getDrawable(
                context,
                R.drawable.item_circular_thin_color_accent_border
            )
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }else if(model.getIsCompleted()){
            holder.tvItem.background = ContextCompat.getDrawable(
                context,
                R.drawable.item_circular_color_accent_background
            )
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            holder.tvItem.background = ContextCompat.getDrawable(
                context,
                R.drawable.item_circular_color_gray_background
            )
            holder.tvItem.setTextColor(Color.parseColor("#212121"))
        }
    }

    /** Returns the number of status items. */
    override fun getItemCount(): Int {
        return items.size
    }

    /** Holder for an individual status view. */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        /** Text view showing the exercise number. */
        val tvItem = view.tvItem
    }
}
