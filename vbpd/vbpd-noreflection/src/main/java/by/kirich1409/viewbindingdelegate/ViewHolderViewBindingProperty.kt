package by.kirich1409.viewbindingdelegate

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@Suppress("unused")
class ViewHolderViewBindingProperty<in V : RecyclerView.ViewHolder, T : ViewBinding>(
    private val viewBinder: (V) -> T
) : ReadOnlyProperty<V, T> {

    private var viewBinding: T? = null

    override fun getValue(thisRef: V, property: KProperty<*>): T {
        return viewBinding ?: viewBinder(thisRef).also { viewBinding ->
            this.viewBinding = viewBinding
        }
    }

    fun clear() {
        viewBinding = null
    }
}