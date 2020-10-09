package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final SinchClient sinchClient = Sinch.getSinchClientBuilder()
                .context(this)
                .userId("current-user-id")
                .applicationKey("0bb35785-a241-4653-8775-86289d50c7c6")
                .applicationSecret("7AZJ5LAbuEyQhfrmerBeHQ==")
                .environmentHost("clientapi.sinch.com")
                .build();

        sinchClient.setSupportCalling(true);
        //sinchClient.start();

        sinchClient.startListeningOnActiveConnection();


        // Telling the Sinch Client to make a call
        sinchClient.setSupportCalling(true);
        sinchClient.start();
        Button call_button = (Button) findViewById(R.id.call_button);
        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // make a call
                sinchClient.getCallClient().callUser("call-recipient-id");
            }
        });
        sinchClient.start();

    }
        private class SinchCallListener implements CallListener {


            @Override
            public void onCallProgressing(Call call) {
                Toast.makeText(getApplicationContext(), "Ringing...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCallEstablished(Call call) {
                Toast.makeText(getApplicationContext(), "Call established",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCallEnded(Call call) {
                Toast.makeText(getApplicationContext(), "Call ended",Toast.LENGTH_SHORT).show();
               // call = null;
                call.hangup();
            }

            @Override
            public void onShouldSendPushNotification(Call call, List<PushPair> list) {

            }
        }

    private class sinchCallClientListener implements CallClientListener{

        @Override
        public void onIncomingCall(CallClient callClient, Call call) {
            // Open dialog for new incoming dialog
        }
    }
}