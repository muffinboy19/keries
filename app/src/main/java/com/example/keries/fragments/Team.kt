package com.example.keries.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.keries.R
import com.example.keries.adapter.TeamAdapter
import com.example.keries.dataClass.TeamMember
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.firestore.FirebaseFirestore

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
    lateinit var shimmerCoordi: ShimmerFrameLayout
    lateinit var shimmerFinace: ShimmerFrameLayout
    lateinit var shimmerEvent: ShimmerFrameLayout
    lateinit var shimmerCr: ShimmerFrameLayout
    lateinit var shimmerPr: ShimmerFrameLayout
    lateinit var shimmerBrand: ShimmerFrameLayout
    lateinit var shimmerHosp: ShimmerFrameLayout
    lateinit var shimmerMedia: ShimmerFrameLayout
    lateinit var shimmerFilm: ShimmerFrameLayout
    lateinit var shimmerCreat: ShimmerFrameLayout
    lateinit var shimmerTech: ShimmerFrameLayout
    lateinit var shimmerWeb: ShimmerFrameLayout
    lateinit var shimmerAppi: ShimmerFrameLayout
    lateinit var shimmerOoc: ShimmerFrameLayout


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_team, container, false)

        shimmerCoordi = root.findViewById(R.id.shimmerCoordi)
        shimmerFinace = root.findViewById(R.id.shimmerFinance)
        shimmerEvent = root.findViewById(R.id.shimmerEvent)
        shimmerCr = root.findViewById(R.id.shimmerCr)
        shimmerPr = root.findViewById(R.id.shimmerPr)
        shimmerBrand = root.findViewById(R.id.shimmerBrand)
        shimmerHosp = root.findViewById(R.id.shimmerHosp)
        shimmerMedia = root.findViewById(R.id.shimmerMedia)
        shimmerFilm = root.findViewById(R.id.shimmerFilm)
        shimmerCreat = root.findViewById(R.id.shimmerCreat)
        shimmerTech = root.findViewById(R.id.shimmerTech)
        shimmerWeb = root.findViewById(R.id.shimmerWeb)
        shimmerAppi = root.findViewById(R.id.shimmerAppi)
        shimmerOoc = root.findViewById(R.id.shimmerOoc)

        val shimmer = Shimmer.AlphaHighlightBuilder()
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setDuration(3000)
            .setAutoStart(true)
            .build()
        shimmerCoordi.setShimmer(shimmer)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Fresco.initialize(requireContext())
        val backbutton = view.findViewById<ImageView>(R.id.backteam)
        backbutton.setOnClickListener {
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


        val ii = mutableListOf<TeamMember>()
        teamAdapter = TeamAdapter(ii)
        Crv.adapter = teamAdapter
        fetchFirestoreDataForWing("Coordinator", Crv)
        fetchFirestoreDataForWing("FINANCE", frv)
        fetchFirestoreDataForWing("EVENTS & MANAGEMENT", enmrv)
        fetchFirestoreDataForWing("CORPORATE RELATIONS", crrv)
        fetchFirestoreDataForWing("APP OPERATIONS", arv)
        fetchFirestoreDataForWing("TECHNICAL", trv)
        fetchFirestoreDataForWing("PUBLIC RELATIONS", prrv)
        fetchFirestoreDataForWing("MEDIA", mrv)
        fetchFirestoreDataForWing("WEB OPERATIONS", wrv)
        fetchFirestoreDataForWing("OOC", orv)
        fetchFirestoreDataForWing("FILMING", flrv)
        fetchFirestoreDataForWing("BRANDING & LOGISTICS", blrv)
        fetchFirestoreDataForWing("CREATIVES", crerv)
        fetchFirestoreDataForWing("HOSPITALITY & TRAVEL", hrv)



    }

    private fun fetchFirestoreDataForWing(wing: String, recyclerView: RecyclerView) {
        db.collection("team")
            .whereEqualTo("wing", wing)
            .get()
            .addOnSuccessListener {
                val teamMemberList = mutableListOf<TeamMember>()
                for (document in it) {
                    val noString = document.get("no")?.toString() ?: ""
                    val no = noString.toIntOrNull() ?: 0
                    val name = document.getString("name") ?: ""
                    val url = document.getString("url") ?: ""
                    teamMemberList.add(TeamMember(name, wing, url,no))
                }
                teamAdapter = TeamAdapter(teamMemberList)
                recyclerView.layoutManager =
                    LinearLayoutManager(requireContext())// You need to define this constructor in your TeamAdapter class
                recyclerView.adapter = teamAdapter

                shimmerCoordi.stopShimmer()
                shimmerFinace.stopShimmer()
                shimmerEvent.stopShimmer()
                shimmerCr.stopShimmer()
                shimmerPr.stopShimmer()
                shimmerBrand.stopShimmer()
                shimmerHosp.stopShimmer()
                shimmerMedia.stopShimmer()
                shimmerFilm.stopShimmer()
                shimmerCreat.stopShimmer()
                shimmerTech.stopShimmer()
                shimmerWeb.stopShimmer()
                shimmerAppi.stopShimmer()
                shimmerOoc.stopShimmer()
                shimmerCoordi.isVisible=false
                shimmerFinace.isVisible = false
                shimmerEvent.isVisible = false
                shimmerCr.isVisible = false
                shimmerPr.isVisible = false
                shimmerBrand.isVisible = false
                shimmerHosp.isVisible = false
                shimmerMedia.isVisible = false
                shimmerFilm.isVisible = false
                shimmerCreat.isVisible = false
                shimmerTech.isVisible = false
                shimmerWeb.isVisible = false
                shimmerAppi.isVisible = false
                shimmerOoc.isVisible = false
                Crv.isVisible=true
                frv.isVisible=true
                enmrv.isVisible=true
                crrv.isVisible=true
                prrv.isVisible=true
                blrv.isVisible=true
                hrv.isVisible=true
                mrv.isVisible=true
                flrv.isVisible=true
                crerv.isVisible=true
                trv.isVisible=true
                wrv.isVisible=true
                arv.isVisible=true
                orv.isVisible=true
//                Crv.isVisible=true

            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
            }


    }
}



