package rustam.urazov.shikimori.core.extention

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import rustam.urazov.shikimori.core.exception.Failure

fun <T : Any, L : LiveData<T>> observe(lifecycleOwner: LifecycleOwner, liveData: L, body: (T?) -> Unit) =
    liveData.observe(lifecycleOwner, Observer(body))

fun <L : LiveData<Failure>> failure(lifecycleOwner: LifecycleOwner, liveData: L, body: (Failure?) -> Unit) =
    liveData.observe(lifecycleOwner, Observer(body))