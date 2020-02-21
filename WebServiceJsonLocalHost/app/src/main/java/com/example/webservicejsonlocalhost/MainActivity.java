package com.example.webservicejsonlocalhost;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSorgula;
    EditText pid, pName, cName, unitP;
    RequestQueue mQueue;
    Context context = this;
    String productName, categoryName, unitPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pid = findViewById(R.id.pid);
        pName = findViewById(R.id.pName);
        cName = findViewById(R.id.cName);
        unitP = findViewById(R.id.unitP);
        btnSorgula = findViewById(R.id.btnSorgula);
        mQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void onClick(View v) {

        int product_id = Integer.parseInt(pid.getText().toString());
        String url = "http://192.168.1.6/?ProductId="+product_id;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    productName = jsonObject.getString("ProductName");
                    categoryName = jsonObject.getString("CategoryName");
                    unitPrice = jsonObject.getString("UnitPrice");
                    pName.setText(productName);
                    cName.setText(categoryName);
                    unitP.setText(unitPrice);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "HATA", Toast.LENGTH_SHORT).show();

            }
        });
        mQueue.add(request);

    }
}
