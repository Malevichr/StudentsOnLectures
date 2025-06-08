package ru.malevichrp.studentsOnLectures.views.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.malevichrp.studentsOnLectures.databinding.ItemTextBinding

class TextRecyclerAdapter(
    private val clickAction: (Long) -> Unit
) : RecyclerView.Adapter<TextViewHolder>() {
    private val textItems = mutableListOf<TextItem>()

    fun update(newTextItems: List<TextItem>) {
        val callback = DiffUtilCallback(newTextItems)
        val diff = DiffUtil.calculateDiff(callback)
        textItems.clear()
        textItems.addAll(newTextItems)
        diff.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder =
        TextViewHolder(
            ItemTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickAction
        )

    override fun getItemCount(): Int = textItems.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) =
        holder.bind(textItems[position])

    private inner class DiffUtilCallback(
        private val newList: List<TextItem>,
    ) : DiffUtil.Callback() {

        override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any {
            return true
        }

        override fun getOldListSize(): Int {
            return textItems.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return textItems[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return textItems[oldItemPosition].text == newList[newItemPosition].text
        }
    }
}

class TextViewHolder(
    private val binding: ItemTextBinding,
    private val clickAction: (Long) -> Unit
) : ViewHolder(binding.root) {
    fun bind(textItem: TextItem) {
        binding.textItem.text = textItem.text
        binding.textItem.setOnClickListener {
            clickAction.invoke(textItem.id)
        }
    }
}

data class TextItem(
    val text: String,
    val id: Long
)