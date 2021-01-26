package com.korearbazar.ecom.UserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.korearbazar.ecom.ApiInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.utils.SessionManager;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AffiliateCodeActivity extends AppCompatActivity {

    SessionManager sessionManager;
    TextView affilate_Link,copy_affilateLink,affilate_html,Copy_affilateHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiliate_code);

        setTitle("Affiliate Code");

        sessionManager = new SessionManager(this);
        affilateCodeData();

        affilate_Link = findViewById(R.id.affilateLink);
        copy_affilateLink = findViewById(R.id.copyaffilateLink);
        affilate_html = findViewById(R.id.affilatehtml);
        Copy_affilateHtml = findViewById(R.id.copyAffilatehtml);

        copy_affilateLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(affilate_Link.getText());
                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        Copy_affilateHtml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager)getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(affilate_html.getText());
                Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void affilateCodeData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getAffilateCode(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        affilateCodeData_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(AffiliateCodeActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void affilateCodeData_writeRecycler(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);
            JSONObject affiliate_code=obj.getJSONObject("user");

            String affiliate_link = obj.getString("link");
            String affiliate_html = obj.getString("html");
            affilate_Link.setText(affiliate_link);
            affilate_html.setText(affiliate_html);

        }catch (Exception e){

        }
    }
}