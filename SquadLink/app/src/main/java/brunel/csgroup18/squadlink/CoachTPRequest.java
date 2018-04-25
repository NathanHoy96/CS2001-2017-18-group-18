package brunel.csgroup18.squadlink;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nathan Hoy on 29/03/2018.
 */

public class CoachTPRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://www.squadlink.co.uk/CoachTPRequest.php/";
    private Map<String, String> params;

    public CoachTPRequest( String id, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("id", id);
        Log.i("Put Params","Inserted all");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}