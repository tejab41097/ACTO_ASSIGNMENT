package com.example.photoapplication.ui.imagesFrag

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photoapplication.R
import com.example.photoapplication.data.OfflineDB.DatabaseRepository
import com.example.photoapplication.network.ApiService
import com.example.photoapplication.repository.ImageRepository
import kotlinx.android.synthetic.main.album_fragment.*
import kotlinx.android.synthetic.main.images_fragment.*

class ImagesFragment : Fragment() {

    private lateinit var viewModel: ImagesViewModel
    private var albumId: String? = "-1"
    private lateinit var factory: ImagesViewModelFactory
    private lateinit var databaseRepository: DatabaseRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        albumId = arguments!!.getString("id")
        return inflater.inflate(R.layout.images_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var netOn: Boolean
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading Your Information...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val apiService = ApiService()
        val repository = ImageRepository(apiService)
        databaseRepository = context?.let { DatabaseRepository.getInstance(it) }!!
        factory = ImagesViewModelFactory(repository, databaseRepository)
        viewModel = ViewModelProviders.of(this, factory).get(ImagesViewModel::class.java)

        if (isNetworkAvailable(context!!)) {
            viewModel.getImagesByAlbumId(albumId.toString())
            netOn = true
        } else {
            viewModel.getPicsByAlbumId(albumId.toString())
            netOn = false
        }
        viewModel.pics.observe(viewLifecycleOwner, Observer { pics ->
            if (pics.isEmpty()) {
                val activity = recycler_view_images.context as AppCompatActivity
                activity.supportFragmentManager.popBackStack()
                progressDialog.dismiss()
            } else {
                recycler_view_images.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter =
                        PicAdapter(
                            pics,
                            netOn
                        )
                    viewModel.saveAllPicsToDb(pics)
                    progressDialog.dismiss()
                }
            }
        })
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}
