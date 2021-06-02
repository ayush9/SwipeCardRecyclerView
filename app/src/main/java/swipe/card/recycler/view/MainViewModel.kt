package swipe.card.recycler.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import swipe.card.recycler.view.data.MainRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {


    val itemsLiveData: LiveData<List<Results>> = MainRepository.getUserProfiles()

}
