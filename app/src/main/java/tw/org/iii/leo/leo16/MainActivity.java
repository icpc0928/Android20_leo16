package tw.org.iii.leo.leo16;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView mesg ;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mesg = findViewById(R.id.mesg);
        img = findViewById(R.id.img);



    }
//拿頁面原始碼 可作爬蟲
    public void test1(View view){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://www.iii.org.tw",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("leo",response);
                        mesg.setText(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        MainApp.queue.add(request);

    }
    //解json
    public void test2(View view ){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelFood.aspx",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJSON(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        MainApp.queue.add(request);
    }



    private void parseJSON(String json){
        mesg.setText("");
        try {
            JSONArray root = new JSONArray(json);
            for (int i=0; i<root.length(); i++){
                JSONObject row = root.getJSONObject(i);
                mesg.append(row.getString("Name") + ":"
                        + row.getString("Address") + "\n");
            }
        }catch (Exception e){
            Log.v("leo", e.toString());
        }


    }

    public void test3(View view ){
        ImageRequest request = new ImageRequest(
                "https://ezgo.coa.gov.tw/Uploads/opendata/TainmaMain01/APPLY_D/20151007173924.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        img.setImageBitmap(response);
                    }
                },
                0,0,
                Bitmap.Config.ARGB_8888,
                null
        );

        MainApp.queue.add(request);
    }



    public void test4(View view){
        JsonArrayRequest request = new JsonArrayRequest(
                "https://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelFood.aspx",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        parseJSON2(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        MainApp.queue.add(request);
    }

    private void parseJSON2(JSONArray root){
        mesg.setText("");
        try {
            for (int i=0; i<root.length(); i++){
                JSONObject row = root.getJSONObject(i);
                mesg.append(row.getString("Name") + ":"
                        + row.getString("Address") + "\n");
            }
        }catch (Exception e){
            Log.v("leo", e.toString());
        }
    }


}
