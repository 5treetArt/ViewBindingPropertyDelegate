@file:Suppress("RedundantVisibilityModifier", "unused")

package by.kirich1409.viewbindingdelegate

import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

class DialogFragmentViewBindingProperty<F : DialogFragment, T : ViewBinding>(
    viewBinder: (F) -> T
) : ViewBindingProperty<F, T>(viewBinder) {

    override fun getLifecycleOwner(thisRef: F): LifecycleOwner {
        return if (thisRef.view != null) thisRef.viewLifecycleOwner else thisRef
    }
}

/**
 * Create new [ViewBinding] associated with the [DialogFragment]
 */
@JvmName("viewBindingDialogFragment")
@Deprecated(
    "Use viewBinding delegate instead",
    ReplaceWith(
        "viewBinding(viewBinder)",
        imports = ["by.kirich1409.viewbindingdelegate.viewBinding"]
    )
)
public fun <F : DialogFragment, T : ViewBinding> DialogFragment.dialogViewBinding(
    viewBinder: (F) -> T
): ViewBindingProperty<F, T> {
    return viewBinding(viewBinder)
}

/**
 * Create new [ViewBinding] associated with the [DialogFragment]
 *
 * @param vbFactory Function that create new instance of [ViewBinding].
 *                  `MyViewBinding::bind` can be used
 */
@JvmName("viewBindingDialogFragment")
@Deprecated(
    "Use viewBinding delegate instead",
    ReplaceWith(
        "viewBinding(vbFactory, viewProvider)",
        imports = ["by.kirich1409.viewbindingdelegate.viewBinding"]
    ),
)
public inline fun <F : DialogFragment, T : ViewBinding> DialogFragment.dialogViewBinding(
    crossinline vbFactory: (View) -> T,
    crossinline viewProvider: (F) -> View
): ViewBindingProperty<F, T> {
    return viewBinding(vbFactory, viewProvider)
}

/**
 * Create new [ViewBinding] associated with the [DialogFragment][this]
 *
 * @param vbFactory Function that create new instance of [ViewBinding].
 *                  `MyViewBinding::bind` can be used
 * @param viewBindingRootId Id of the root view from your custom view
 */
@Suppress("unused")
@JvmName("viewBindingDialogFragment")
public inline fun <T : ViewBinding> DialogFragment.dialogViewBinding(
    crossinline vbFactory: (View) -> T,
    @IdRes viewBindingRootId: Int
): ViewBindingProperty<DialogFragment, T> {
    return viewBinding(vbFactory) { fragment ->
        fragment.dialog!!.window!!.decorView.findViewById(viewBindingRootId)
    }
}