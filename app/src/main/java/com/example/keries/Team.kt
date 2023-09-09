
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import androidx.appcompat.app.AppCompatActivity
import com.example.keries.R
import com.example.keries.TeamAdapter
import com.example.keries.TeamMember
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class Team : Fragment() {


    private val db = FirebaseFirestore.getInstance()
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var reyclerview : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val backbutton = view.findViewById<ImageView>(R.id.backteam)
        backbutton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        reyclerview = view.findViewById<RecyclerView>(R.id.festiveCordi)
        reyclerview.layoutManager  = LinearLayoutManager(context)
        val  ii =  mutableListOf<TeamMember>()
        teamAdapter  = TeamAdapter(ii)
        reyclerview.adapter = teamAdapter
        fetchFirestoreData()

    }

    private fun fetchFirestoreData() {
        db.collection("team")
            .get()
            .addOnSuccessListener {
                val teamMemberList  = mutableListOf<TeamMember>()
                for(document in it){
                    val name = document.getString("name")?:""
                    val wing = document.getString("wing")?:""
                    val url = document.getString("url")?:""
                    teamMemberList.add(TeamMember(name,wing,url))
                }
                teamAdapter = TeamAdapter(teamMemberList) // You need to define this constructor in your TeamAdapter class
                reyclerview.adapter = teamAdapter
            }
            .addOnFailureListener{
                Toast.makeText(requireContext(),"error",Toast.LENGTH_SHORT).show()
            }



    }
}



