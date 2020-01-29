package com.example.photoapplication.ui.imagesFrag

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.photoapplication.R
import com.example.photoapplication.data.DatabaseRepository
import com.example.photoapplication.data.album.AlbumAdapter
import com.example.photoapplication.data.images.PicAdapter
import com.example.photoapplication.data.images.PicOfflineAdapter
import com.example.photoapplication.network.ApiService
import com.example.photoapplication.repository.AlbumRepository
import com.example.photoapplication.repository.ImageRepository
import com.example.photoapplication.ui.albumsFrag.AlbumsViewModel
import com.example.photoapplication.ui.albumsFrag.AlbumsViewModelFactory
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
        val apiService = ApiService()
        val repository = ImageRepository(apiService)
        databaseRepository = context?.let { DatabaseRepository.getInstance(it) }!!
        factory = ImagesViewModelFactory( repository ,databaseRepository)
        viewModel = ViewModelProviders.of(this, factory).get(ImagesViewModel::class.java)

        if (isNetworkAvailable(context!!)) {
            viewModel.getImagesByAlbumId(albumId.toString())
            viewModel.pics.observe(viewLifecycleOwner, Observer { pics ->
                recycler_view_images.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = PicAdapter(pics)
                    viewModel.saveAllPicsToDb(pics)
                }
            })
        }else{
            viewModel.getPicsByAlbumId(albumId.toString())
            viewModel.pics.observe(viewLifecycleOwner, Observer { pics ->
                recycler_view_images.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter = PicOfflineAdapter(pics)
                }
            })
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}
