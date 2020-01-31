package com.example.photoapplication.ui.usersFrag

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photoapplication.R
import com.example.photoapplication.data.OfflineDB.DatabaseRepository
import com.example.photoapplication.network.ApiService
import com.example.photoapplication.repository.UserRepository
import kotlinx.android.synthetic.main.album_fragment.*
import kotlinx.android.synthetic.main.users_fragment.*

class UsersFragment : Fragment() {
    private lateinit var factory: UserViewModelFactory
    private lateinit var viewModel: UsersViewModel
    private lateinit var databaseRepository: DatabaseRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Loading Your Information...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val apiService = ApiService()
        databaseRepository = context?.let { DatabaseRepository.getInstance(it) }!!
        val repository = UserRepository(apiService)
        factory = UserViewModelFactory(repository, databaseRepository)
        viewModel = ViewModelProviders.of(this, factory).get(UsersViewModel::class.java)

        if (isNetworkAvailable(context!!)) {
            viewModel.getUsers()
            viewModel.users.observe(viewLifecycleOwner, Observer { users ->
                if (users.isEmpty()) {
                    progressDialog.dismiss()
                    Toast.makeText(context,"No User is Loaded before..!!",Toast.LENGTH_LONG).show()
                }else {
                    recycler_view_users.also {
                        it.layoutManager = LinearLayoutManager(requireContext())
                        it.setHasFixedSize(true)
                        it.adapter =
                            UserAdapter(
                                users
                            )
                        viewModel.saveAllUsersToDb(users)
                        progressDialog.dismiss()
                    }
                }
            })
        } else {
            Toast.makeText(context, "No internet Available", Toast.LENGTH_SHORT).show()
            viewModel.getAllUsersFromDb()
            viewModel.users.observe(viewLifecycleOwner, Observer { users ->
                recycler_view_users.also {
                    it.layoutManager = LinearLayoutManager(requireContext())
                    it.setHasFixedSize(true)
                    it.adapter =
                        UserAdapter(
                            users
                        )
                    progressDialog.dismiss()
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
