package swipe.card.recycler.view

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.facebook.drawee.backends.pipeline.Fresco
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeableMethod
import kotlinx.android.synthetic.main.activity_main.*
import swipe.card.recycler.view.data.MainRepository

class MainActivity : AppCompatActivity(), CardStackListener {

    private val adapter = ProfilesAdapter()
    private lateinit var layoutManager: CardStackLayoutManager
    private val mainViewModel: MainViewModel by lazy {
        getViewModelProvider(this, null).get(
            MainViewModel::class.java
        )
    }

    private fun getViewModelProvider(
        fragment: MainActivity,
        factory: ViewModelProvider.Factory?
    ): ViewModelProvider {
        return ViewModelProviders.of(fragment, factory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

        MainRepository.initializeRepository(application)

        mainViewModel.itemsLiveData.observe(this, Observer {
            if(it.size>0){
                    layoutManager = CardStackLayoutManager(this, this).apply {
                        setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
                        setOverlayInterpolator(LinearInterpolator())
                    }

                    stack_view.layoutManager = layoutManager
                    stack_view.adapter = adapter
                    stack_view.itemAnimator.apply {
                        if (this is DefaultItemAnimator) {
                            supportsChangeAnimations = false
                        }
                    }
                adapter.setProfiles(it)
                }
        })
    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardRewound() {

    }
}
