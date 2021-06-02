package swipe.card.recycler.view.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import swipe.card.recycler.view.ResultResponse
import swipe.card.recycler.view.Results
import swipe.card.recycler.view.ProfilesAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object MainRepository {
    private lateinit var itemDao: ItemDao
    private val itemsLiveData = MutableLiveData<List<Results>>()


    fun initializeRepository(application: Application) {
        itemDao = ItemsRoomDatabase.getDatabase(application).itemDao()
    }

    fun getUserProfiles(): LiveData<List<Results>> {
        GlobalScope.launch {
            val items = itemDao.getAllItems()
            if (items != null && !items.isEmpty()) {
                val filterdItems = ArrayList<Results>()
                for (item in items) {
                    filterdItems.add(item)
                }
                itemsLiveData.postValue(filterdItems)
            } else {
                ProfilesAPI().getProfiles().enqueue(object : Callback<ResultResponse> {
                    override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                        Log.e("error occurred", t.message.toString())
                    }

                    override fun onResponse(
                        call: Call<ResultResponse>,
                        response: Response<ResultResponse>
                    ) {
                        response.body()?.let {
                            saveProfiles(it.results)
                            itemsLiveData.postValue(it.results)
                        }
                    }
                })
            }
        }
        return itemsLiveData
    }

    fun saveProfiles(items: List<Results>) {
        GlobalScope.launch(Dispatchers.IO) {
                itemDao.insert(items)
        }
    }

    fun updateProfile(status: String, profileId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            itemDao.update(status, profileId)
        }
    }
}