package com.example.keries.fragments

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.keries.R
import android.content.Intent
import android.net.Uri
import android.widget.Toast

class about : Fragment() {
    private lateinit var text : TextView
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView
    private lateinit var insta: ImageView
    private lateinit var facebook: ImageView
    private lateinit var gamail: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        insta = view.findViewById<ImageView>(R.id.instaImageView)
        facebook = view.findViewById<ImageView>(R.id.facebookImageView)
        gamail = view.findViewById<ImageView>(R.id.gmailImageView)
        val effelogo = view.findViewById<ImageView>(R.id.imageView3)
        effelogo.setOnClickListener {
            Toast.makeText(requireContext(),"Effe Faad Hoga",Toast.LENGTH_SHORT).show()
        }




        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "About us"
        notifyTool.setVisibility(View.GONE)
        logoTool.setImageResource(R.drawable.backsvg)
        logoTool.setVisibility(View.VISIBLE)

        logoTool.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        text = view.findViewById(R.id.aboutText)
        text.text = "Nationally acknowledged for its array of unforgettable evenings, EFFERVESCENCE is the once in a year cultural epitome of IIITA illuminating the essence and ethos of IIITA witnessing a footfall of 21k+ per year.Effervesence has made a name for itself for the phenomenally successful online versions of the fest due to generous views and comments during the talk shows, comedy gigs and body-grooving musical performances. Each event reaches a different ke audience segment and provides a fun, entertaining and engaging way to wow the audience."



        insta.setOnClickListener {
            openInstagramProfile()
        }

        facebook.setOnClickListener {
            openFacebookPage()
        }

        gamail.setOnClickListener {
            composeEmail()
        }
    }

    private fun openInstagramProfile() {
        val uri = Uri.parse("https://www.instagram.com/goeffervescence/")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.instagram.android")
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/goeffervescence/")))
        }
    }
    private fun openFacebookPage() {
        val uri = Uri.parse("https://www.facebook.com/effervescence.iiita/")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.facebook.katana")

        // Check if Facebook app is installed, if not, open in a web browser
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/your_facebook_page/")))
        }
    }

    // Function to compose an email
    // Function to open Gmail for composing an email
    private fun composeEmail() {
        val recipientEmail = "effervescence@iiita.ac.in"
        val subject = "Subject"
        val message = "Message body"

        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$recipientEmail")
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(requireContext(),"Somehting Went wrong", Toast.LENGTH_SHORT).show()
        }
    }

}