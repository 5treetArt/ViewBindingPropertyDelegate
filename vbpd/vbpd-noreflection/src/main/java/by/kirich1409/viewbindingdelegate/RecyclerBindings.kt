package by.kirich1409.viewbindingdelegate

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

@Suppress("unused")
fun <V : RecyclerView.ViewHolder, T : ViewBinding> V.viewBinding(
    viewBinder: (V) -> T
): ViewHolderViewBindingProperty<V, T> {
    return ViewHolderViewBindingProperty(viewBinder)
}

@Suppress("unused")
inline fun <V : RecyclerView.ViewHolder, T : ViewBinding> V.viewBinding(
    crossinline vbFactory: (View) -> T,
    crossinline viewProvider: (V) -> View = RecyclerView.ViewHolder::itemView
): ViewHolderViewBindingProperty<V, T> {
    return viewBinding { viewHolder: V ->
        viewProvider(viewHolder).let(vbFactory)
    }
}

@Suppress("unused")
inline fun <V : RecyclerView.ViewHolder, T : ViewBinding> V.viewBinding(
    crossinline vbFactory: (View) -> T,
    @IdRes viewBindingRootId: Int
): ViewHolderViewBindingProperty<V, T> {
    return viewBinding { viewHolder: V ->
        viewHolder.itemView.findViewById<View>(viewBindingRootId).let(vbFactory)
    }
}