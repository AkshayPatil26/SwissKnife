package com.example.akshay.swissknife;


import android.content.Intent;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import static android.R.attr.x;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onButtonClick(View v)
    {

        if (v.getId() == R.id.Bcontact)
        {
            Intent i = new Intent();

            i.setAction(Intent.ACTION_VIEW);
            i.setData(ContactsContract.Contacts.CONTENT_URI);
            startActivity(i);
        }


        if (v.getId() == R.id.Bredial)
        {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_VIEW);
            i.setType(CallLog.Calls.CONTENT_TYPE);
            startActivity(i);
        }


        if (v.getId()== R.id.Bsms)
        {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("sms:"));
            sendIntent.putExtra("sms_body", x);
            startActivity(sendIntent);
        }


            if (v.getId()== R.id.Balarm)
        {
            Intent i = new Intent(MainActivity.this, AlarmTask.class);
            startActivity(i);
        }


        if (v.getId()== R.id.Bstopwatch)
        {
            Intent i = new Intent(MainActivity.this, Stopwatch.class);
            startActivity(i);
        }


        if (v.getId()== R.id.Bmap)
        {
            EditText loc_text = (EditText)findViewById(R.id.Mtext);
            String location = loc_text.getText().toString();

            Intent i= new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("geo:0,0?q=" + Uri.encode(location)));
            startActivity(i);
        }

    }

}