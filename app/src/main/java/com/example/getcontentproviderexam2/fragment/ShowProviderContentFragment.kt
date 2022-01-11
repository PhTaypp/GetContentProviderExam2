package com.example.getcontentproviderexam2.fragment

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.getcontentproviderexam2.R
import com.example.getcontentproviderexam2.adapter.ShowProviderContentAdapter
import com.example.getcontentproviderexam2.model.User

class ShowProviderContentFragment : Fragment() {
    private lateinit var thisContext: Context
    private var layoutManager : RecyclerView.LayoutManager? = null
    private lateinit var adapter: ShowProviderContentAdapter
    private lateinit var listUser : ArrayList<User>
    private lateinit var recyclerView : RecyclerView
//    val URL = ContentProviderP.URL
    val URL = "content://com.example.user/user"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        thisContext = container?.context!!
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_provider_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get list user from Provider
        listUser = getListUser()
        val btnShow = view.findViewById<AppCompatButton>(R.id.btn_show)
        btnShow.setOnClickListener{
            showList()
        }
    }
    private fun getListUser() : ArrayList<User>{
        val list : ArrayList<User> = ArrayList()
        var user : User
        var name = ""
        var phone = ""
        val cursor =
            context?.contentResolver?.query(Uri.parse(URL), null, null, null, null)

        // to print whole table
        cursor.use { cursor ->
            if (cursor!!.moveToFirst()) {
                while (!cursor.isAfterLast) {
                    cursor.getString(cursor.getColumnIndexOrThrow("id"))
                    name =cursor.getString(
                        cursor.getColumnIndexOrThrow(
                            "name"
                        )
                    )
                    phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
                    user = User(name,phone)
                    list.add(user)
                    cursor.moveToNext()
                }

            } else {
                Toast.makeText(thisContext, "User not found", Toast.LENGTH_LONG).show()
            }
        }

        return list
    }
    private fun
            showList(){
        recyclerView = requireView().findViewById(R.id.list_content_provider)
        layoutManager = LinearLayoutManager(activity)
        if (listUser.size > 0) {
            adapter = ShowProviderContentAdapter(requireContext(), listUser)
            recyclerView.layoutManager = layoutManager
            adapter.onItemClickListener = object : ShowProviderContentAdapter.OnClickListener{
                override fun onClickItem(
                    view: View?,
                    position: Int,
                    holder: ShowProviderContentAdapter.ProviderContentViewHolder
                ) {
                    val name = holder.tvName.toString()
                    val phone = holder.tvPhone.toString()
                    showDialogDetail(name,phone)
                }

            }
            recyclerView.adapter = adapter
        }
    }
    private fun showDialogDetail(name:String,phone:String){
        val dialog = Dialog(thisContext, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
        val contentView: View =
            LayoutInflater.from(context).inflate(R.layout.dialog_detail_user, null)
        dialog.setContentView(contentView)
        dialog.setCancelable(false)
        val window: Window? = dialog.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setBackgroundDrawableResource(R.color.transparent)
        val tvName = dialog.findViewById(R.id.tv_name) as TextView
        val tvPhone = dialog.findViewById(R.id.tv_phone) as TextView
        tvName.text = name
        tvPhone.text = phone
        val okBtn = dialog.findViewById(R.id.btn_ok) as Button
        okBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

}