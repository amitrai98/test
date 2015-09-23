package com.example.amitrai.internettest.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.amitrai.internettest.Fragments.Fragment_Home;
import com.example.amitrai.internettest.R;

public class MainActivity extends AppCompatActivity implements Fragment_Home.OnFragmentInteractionListener{

    private FrameLayout container = null;

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    //initializing view elements
    private void initView(){
        container = (FrameLayout) findViewById(R.id.container);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new Fragment_Home())
                .addToBackStack(Fragment_Home.class.getSimpleName())
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onBackPressed() {
        int count = 0;

        count = getFragmentManager().getBackStackEntryCount();
        Log.e(TAG, "count is "+count);
        if(count>1)
            getFragmentManager().popBackStackImmediate();
        else
            showExitDialog();
    }

    private void showExitDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("You want to Exit ?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //implement your logic for YES
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //implement your logic for NO
                dialog.dismiss();
            }
        });
        builder.setOnCancelListener(null);
        builder.show();
    }
}
