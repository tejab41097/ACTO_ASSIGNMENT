package com.example.photoapplication.ui.viewImageFrag

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.photoapplication.R
import com.example.photoapplication.data.OfflineDB.DatabaseRepository
import com.example.photoapplication.data.images.Pic
import com.example.photoapplication.network.ApiService
import com.example.photoapplication.repository.GlideApp
import com.example.photoapplication.repository.ImageRepository
import com.example.photoapplication.ui.usersFrag.UserAdapter
import com.example.photoapplication.ui.usersFrag.UserViewModelFactory
import kotlinx.android.synthetic.main.recyclerview_images.*
import kotlinx.android.synthetic.main.users_fragment.*
import kotlinx.android.synthetic.main.view_image_fragment.*

class ViewImageFragment : Fragment() {

    private lateinit var imageId: String
    private lateinit var databaseRepository: DatabaseRepository
    private lateinit var viewModel: ViewImageViewModel
    private lateinit var factory: ViewImageViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        imageId = arguments!!.getString("id").toString()
        return inflater.inflate(R.layout.view_image_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading Your Information...")
        progressDialog.setCancelable(false)
        progressDialog.show()
        databaseRepository = DatabaseRepository.getInstance(context!!)
        val apiService = ApiService()
        val repository = ImageRepository(apiService)
        factory = ViewImageViewModelFactory(repository = repository,databaseRepository = databaseRepository)
        viewModel = ViewModelProvider(viewModelStore,factory).get(ViewImageViewModel::class.java)

//        if (isNetworkAvailable(context!!)) {
            viewModel.getImageDetailFromDb(imageId)
            viewModel.picDetails.observe(viewLifecycleOwner, Observer {picDetails->run{
                textViewImageTitle.text = picDetails.title
                textViewURL.text = picDetails.albumName
                textViewAlbumTitle.text = picDetails.userName
                GlideApp.with(context!!)
                        .load(picDetails.url)
                    .placeholder(R.drawable.offline_image)
                    .fitCenter()
                    .into(imageViewOriginal)
                progressDialog.cancel()
            }
             })
//        } else {
//            Toast.makeText(context, "No internet Available", Toast.LENGTH_SHORT).show()
//        }

    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}
