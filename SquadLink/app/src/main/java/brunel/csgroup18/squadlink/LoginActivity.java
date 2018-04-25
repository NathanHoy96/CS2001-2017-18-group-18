package brunel.csgroup18.squadlink;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private Spinner sUserType;
    private EditText etUsername;
    private EditText etPassword;
    private Button bLogin;
    private Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_provisional);

        sUserType = (Spinner) findViewById(R.id.spinner_usertype);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        bLogin = (Button) findViewById(R.id.button_login);
        bRegister = (Button) findViewById(R.id.button_register);

        ArrayAdapter<String> userTypeAdapter = new ArrayAdapter<String>
                (LoginActivity.this, android.R.layout.simple_selectable_list_item,getResources().getStringArray(R.array.userTypes));

        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sUserType.setAdapter(userTypeAdapter);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String userType = sUserType.getSelectedItem().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.i("Response","Received response");
                        try {

                            Log.i("JSON",response);

                            boolean success;
                            JSONObject jsonResponse = null;

                            if(response.isEmpty()){
                                success = false;
                            }
                            else {
                                jsonResponse = new JSONObject(response.substring(response.indexOf("{"), response.lastIndexOf("}") + 1));

                                success = jsonResponse.getBoolean("success");
                                Log.i("Success", jsonResponse.toString());
                            }

                            if(success){

                                String idReceived = jsonResponse.getString("id");
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("username",username);
                                bundle.putString("id",idReceived);
                                intent.putExtras(bundle);
                                LoginActivity.this.startActivity(intent);
                                Log.i("Login","Success");
                                finish();
                            }
                            else{
                                createLoginFailureDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, userType, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });

    }

    private void createLoginFailureDialog(){
        Log.i("Login","Fail");
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Login Failed")
                .setNegativeButton("Retry", null)
                .create()
                .show();
    }

}
