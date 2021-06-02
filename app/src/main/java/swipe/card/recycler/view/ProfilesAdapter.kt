package swipe.card.recycler.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import swipe.card.recycler.view.data.MainRepository

class ProfilesAdapter : RecyclerView.Adapter<ProfilesAdapter.ProfileViewHolder>() {

    private lateinit var profiles: List<Results>
    private var mainRepo = MainRepository

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProfileViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_profile,
            parent,
            false
        )
    )

    override fun getItemCount() = profiles.size ?: 0

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        profiles.let {
            holder.binding.results = it[position]
            holder.binding.executePendingBindings()
            holder.acceptButton.setOnClickListener {
                onAcceptClick(holder, position)
            }
            holder.rejectButton.setOnClickListener {
                onDeclineClick(holder, position)
            }
        }
    }

    fun setProfiles(profiles: List<Results>) {
        this.profiles = profiles
        notifyDataSetChanged()
    }

    private fun onAcceptClick(
        holder: ProfileViewHolder,
        position: Int
    ) {
        holder.acceptButton.visibility = View.GONE
        holder.rejectButton.visibility = View.GONE
        holder.status.visibility = View.VISIBLE
        holder.status.text = "ACCEPTED"
        mainRepo.updateProfile("ACCEPTED", this.profiles[position].profileId)
    }

    private fun onDeclineClick(
        holder: ProfileViewHolder,
        position: Int
    ) {
        holder.acceptButton.visibility = View.GONE
        holder.rejectButton.visibility = View.GONE
        holder.status.visibility = View.VISIBLE
        holder.status.text = "REJECTED"
        mainRepo.updateProfile("REJECTED", this.profiles[position].profileId)
    }

    inner class ProfileViewHolder(val binding: RecyclerViewProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var acceptButton: ImageView = binding.root.findViewById(R.id.acceptBtn) as ImageView
        var rejectButton: ImageView = binding.root.findViewById(R.id.rejectBtn) as ImageView
        var status: TextView = binding.root.findViewById(R.id.statusText) as TextView

    }

}