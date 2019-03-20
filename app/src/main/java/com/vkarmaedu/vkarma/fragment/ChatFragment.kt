package com.vkarmaedu.vkarma.fragment

import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vkarmaedu.vkarma.R
import com.vkarmaedu.vkarma.adapters.MessageAdapter
import com.vkarmaedu.vkarma.data.Message
import com.vkarmaedu.vkarma.data.UserRepo
import com.vkarmaedu.vkarma.utility.MESSAGE_CHANNEL_kEY
import com.vkarmaedu.vkarma.viewModel.ChatViewModel
import com.vkarmaedu.vkarma.viewModel.ChatViewModelFactory
import kotlinx.android.synthetic.main.fragment_chat.view.*

class ChatFragment : Fragment() {

    private val viewModel by lazy {
        val application: Application? = activity?.application
        application?.let {
            ViewModelProviders.of(
                this,
                ChatViewModelFactory(application, channel)
            ).get(ChatViewModel::class.java)

        }
    }

    private val messageAdapter = MessageAdapter()
    private lateinit var channel: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val chan = arguments?.getString(MESSAGE_CHANNEL_kEY)
            channel = if (chan != null) chan else ""
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_chat, container, false)
        activity?.actionBar?.show()

        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.stackFromEnd = true
        root.chat_recycler.apply {
            layoutManager = linearLayoutManager
            adapter = messageAdapter
        }

        viewModel?.allMessages?.observe(this, Observer {
            messageAdapter.changeData(it)
            linearLayoutManager.scrollToPosition(it.lastIndex)
        })

        root.chat_message.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                root.chat_send.isClickable = !s.isNullOrEmpty()
            }
        })

        root.chat_send.setOnClickListener {
            viewModel?.insertFirebase(
                Message(
                    UserRepo.name,
                    root.chat_message.text.toString(),
                    System.currentTimeMillis(),
                    null,
                    channel
                )
            )
            root.chat_message.text.clear()
        }
        root.attach.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/jpeg"
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            data.data?.let {
                Log.d(TAG, it.toString())
                viewModel?.insertStorage(it)
            }
        }
    }

    companion object {
        private const val PICK_IMAGE = 1
        private const val TAG = "ChatFragment"
    }
}