package brunel.csgroup18.squadlink;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewCTP extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ctp);

        mToolbar = (Toolbar) findViewById(R.id.nav_action_bar);
        setSupportActionBar(mToolbar);

        text = (TextView) findViewById(R.id.ctp_text);

        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("Bundle");
        int pos = args.getInt("Position");
        ArrayList<CTPData> arr = (ArrayList<CTPData>) args.getSerializable("Data");

        CTPData ctpData = arr.get(pos);
        text.setText(ctpData.getText());

    }
}
