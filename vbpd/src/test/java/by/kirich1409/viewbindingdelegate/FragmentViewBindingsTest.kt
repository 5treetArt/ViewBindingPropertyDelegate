package by.kirich1409.viewbindingdelegate

import android.os.Build
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import by.kirich1409.viewbindingdelegate.databinding.FragmentSample1Binding
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
class FragmentViewBindingsTest {

    @Test
    @Config(sdk = [Build.VERSION_CODES.O_MR1])
    fun name() {
        val scenario = launchFragment {
            TestFragment1()
        }
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onFragment { fragment ->
            fragment.binding
        }
    }
}

class TestFragment1 : Fragment(R.layout.fragment_sample1) {

    val binding: FragmentSample1Binding by viewBinding(FragmentSample1Binding::bind)
}
