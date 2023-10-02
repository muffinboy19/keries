package com.example.keries.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.R
import com.example.keries.adapter.TeamAdapter
import com.example.keries.dataClass.TeamMember
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class Team : Fragment() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var Crv: RecyclerView
    private lateinit var frv: RecyclerView
    private lateinit var enmrv: RecyclerView
    private lateinit var crrv: RecyclerView
    private lateinit var prrv: RecyclerView
    private lateinit var blrv: RecyclerView
    private lateinit var hrv: RecyclerView
    private lateinit var mrv: RecyclerView
    private lateinit var flrv: RecyclerView
    private lateinit var crerv: RecyclerView
    private lateinit var trv: RecyclerView
    private lateinit var wrv: RecyclerView
    private lateinit var arv: RecyclerView
    private lateinit var orv: RecyclerView
    private lateinit var toolText : TextView
    private lateinit var logoTool : ImageView
    private lateinit var notifyTool : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_team, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Fresco.initialize(requireContext())

        toolText = requireActivity().findViewById(R.id.titleText)
        notifyTool = requireActivity().findViewById(R.id.notifyLogo)
        logoTool = requireActivity().findViewById(R.id.logoView)
        toolText.text = "Teams"
        notifyTool.setVisibility(View.GONE)
        logoTool.setImageResource(R.drawable.backsvg)
        logoTool.setVisibility(View.VISIBLE)

        logoTool.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        Crv = view.findViewById(R.id.festiveCordi)
        frv = view.findViewById(R.id.financerelcyler)
        enmrv = view.findViewById(R.id.eventesmangereycl)
        crrv  = view.findViewById(R.id.coroprate)
        prrv = view.findViewById(R.id.pr)
        blrv  = view.findViewById(R.id.brnding)
        hrv = view.findViewById(R.id.hospitalllty)
        mrv = view.findViewById(R.id.mediaaaa)
        flrv = view.findViewById(R.id.flimingdevven)
        crerv = view.findViewById(R.id.creativesreyc)
        trv  = view.findViewById(R.id.tecnialreycler)
        wrv = view.findViewById(R.id.wed)
        arv = view.findViewById(R.id.apppp)
        orv = view.findViewById(R.id.cco)

        Crv.layoutManager = LinearLayoutManager(context)
        frv.layoutManager = LinearLayoutManager(context)
        enmrv.layoutManager = LinearLayoutManager(context)
        crrv.layoutManager = LinearLayoutManager(context)
        arv.layoutManager = LinearLayoutManager(context)
        trv.layoutManager = LinearLayoutManager(context)
        prrv.layoutManager = LinearLayoutManager(context)
        mrv.layoutManager = LinearLayoutManager(context)
        wrv.layoutManager = LinearLayoutManager(context)
        orv.layoutManager = LinearLayoutManager(context)
        flrv.layoutManager = LinearLayoutManager(context)
        blrv.layoutManager = LinearLayoutManager(context)
        crerv.layoutManager = LinearLayoutManager(context)
        hrv.layoutManager = LinearLayoutManager(context)

        fetchAndPopulateData("Coordinator", Crv)
        fetchAndPopulateData("FINANCE", frv)
        fetchAndPopulateData("EVENTS & MANAGEMENT", enmrv)
        fetchAndPopulateData("CORPORATE RELATIONS", crrv)
        fetchAndPopulateData("APP OPERATIONS", arv)
        fetchAndPopulateData("TECHNICAL", trv)
        fetchAndPopulateData("PUBLIC RELATIONS", prrv)
        fetchAndPopulateData("MEDIA", mrv)
        fetchAndPopulateData("WEB OPERATIONS", wrv)
        fetchAndPopulateData("OOC", orv)
        fetchAndPopulateData("FILMING", flrv)
        fetchAndPopulateData("BRANDING & LOGISTICS", blrv)
        fetchAndPopulateData("CREATIVES", crerv)
        fetchAndPopulateData("HOSPITALITY & TRAVEL", hrv)
    }
    private fun fetchAndPopulateData(wing: String, recyclerView: RecyclerView) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val teamMemberList = fetchDataFromFirestore(wing)
                val teamAdapter = TeamAdapter(teamMemberList)
                recyclerView.adapter = teamAdapter
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error fetching data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private suspend fun fetchDataFromFirestore(wing: String): List<TeamMember> = withContext(
        Dispatchers.IO) {
        try {
            val querySnapshot = db.collection("team")
                .whereEqualTo("wing", wing)
                .get()
                .await()

            val teamMemberList = mutableListOf<TeamMember>()
            for (document in querySnapshot) {
                val noString = document.get("no")?.toString() ?: ""
                val no = noString.toIntOrNull() ?: 0
                val name = document.getString("name") ?: ""
                val url = document.getString("url") ?: ""
                teamMemberList.add(TeamMember(name, wing, url, no))
            }
            return@withContext teamMemberList
        } catch (e: Exception) {
            throw e
        }
    }
}



