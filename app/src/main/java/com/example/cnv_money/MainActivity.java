package com.example.cnv_money;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    EditText inputFrom;
    EditText inputTo;
    EditText inputAmount;
    TextView resutlFinal;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // result text
        resutlFinal = (TextView) findViewById(R.id.resultText);

        // inputs
        inputFrom = (EditText) findViewById(R.id.inputFrom);
        inputTo = (EditText) findViewById(R.id.inputTo);
        inputAmount = (EditText) findViewById(R.id.inputAmount);

        // Button
        b1 = (Button) findViewById(R.id.buttonConvert);
        b1.setOnClickListener(myHandler1);

//        convertMoney();
    }

    View.OnClickListener myHandler1 = new View.OnClickListener() {
        public void onClick(View v) {

            if (inputFrom.getText().toString().isEmpty() && inputTo.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                return;
            }
            // calling a method to post the data and passing our name and job.


            postData(parseDouble(inputAmount.getText().toString()), inputFrom.getText().toString(),inputTo.getText().toString());


        }
    };



    private void postData(Double amount, String from, String to) {


        System.out.println(amount);
        // on below line we are creating a retrofit
        // builder and passing our base url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.101:8080/")
                // as we are sending data in json format so
                // we have to add Gson converter factory
                .addConverterFactory(GsonConverterFactory.create())
                // at last we are building our retrofit builder.
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // passing data from our text fields to our modal class.
        Money money = new Money(amount, from, to);

        System.out.println(money.getAmount());
        // calling a method to create a post and passing our modal class.
        Call<JsonObject> call = retrofitAPI.convertMoney(money);

        // on below line we are executing our method.
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                // on below line we are setting empty text
                // to our both edit text.
//                inputAmount.setText("");
//                inputFrom.setText("");
//                inputTo.setText("");

                // we are getting response from our body
                // and passing it to our modal class.
                JsonObject responseFromAPI = response.body();

                System.out.println(responseFromAPI);
                resutlFinal.setText(responseFromAPI.get(to).toString());

                Toast.makeText(MainActivity.this, "Money Converted Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "ERROR OCCURED");
            }
        });
    }

}