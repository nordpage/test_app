package xyz.mobcoder.testapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import xyz.mobcoder.testapp.api.models.Plan
import xyz.mobcoder.testapp.viewmodel.repositories.MainRepository

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val plan = MutableLiveData<Plan>()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getPlan(hash: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getPlan(hash)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    plan.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}