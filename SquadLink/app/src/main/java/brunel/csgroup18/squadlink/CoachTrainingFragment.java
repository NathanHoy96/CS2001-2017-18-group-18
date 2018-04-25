package brunel.csgroup18.squadlink;


import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CoachTrainingFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private String tpTitle;
    private String tpText;
    private ArrayList<CTPData> arr = new ArrayList<CTPData>();
    private TPAdapter mTPAdapter;

    public CoachTrainingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_coach_training, container,false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mTPAdapter = new TPAdapter(getActivity());
        mRecyclerView.setAdapter(mTPAdapter);

        String id = MainActivity.getUserid();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Coach TP Response","Received response");
                try {

                    Log.i("JSON",response);
                    //JSONObject jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i = 0 ; i < jsonArray.length();i++){

                        JSONObject currentRecord = jsonArray.getJSONObject(i);

                        int id = Integer.parseInt(currentRecord.getString("tp_id"));
                        String title = currentRecord.getString("tpname");
                        if(title.equals("")){
                            title = "*Untitled*";
                        }
                        String text = currentRecord.getString("tptext");

                        arr.add(new CTPData(id,title,text));

                    }

                    Log.i("Array List Values",arr.toString());
                    mTPAdapter.setTitleList(arr);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        CoachTPRequest coachTPRequest = new CoachTPRequest(id,responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(coachTPRequest);

        return view;
    }

}
