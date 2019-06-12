package com.gferreyra.herewegoagain;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class FrameDataTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.framedata);

        Toolbar toolbar = findViewById(R.id.fd_toolbar);
        setSupportActionBar(toolbar);

        //set characters name as table title
        getSupportActionBar().setTitle("Character Name's Frame Data");

        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("h");
        list.add("7");
        list.add("8");
        list.add("11");
        list.add("5");
        list.add("4");
        list.add("Nothing");

        /*
        TableLayout tableLayout = findViewById(R.id.gridTable);
        TableRow tableRow = new TableRow(this);
        for(int i = 0; i < 7; i++) {
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(lp);
            TextView tv = new TextView(this);
            tv.setText("Yo whats up");
            tableRow.addView(tv);
        }
        tableLayout.addView(tableRow);
        */

        TableLayout tableLayout = findViewById(R.id.gridTable);
        TableRow tableRow = (TableRow)LayoutInflater.from(this).inflate(R.layout.layout_row, null);
        ((TextView)tableRow.findViewById(R.id.row_command)).setText(list.get(0));

        tableLayout.addView(tableRow);

        tableLayout = findViewById(R.id.gridTable);
        tableRow = (TableRow)LayoutInflater.from(this).inflate(R.layout.layout_row, null);
        ((TextView)tableRow.findViewById(R.id.row_command)).setText(list.get(1));

        tableLayout.addView(tableRow);
        tableLayout.requestLayout();
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
    protected void onDestroy() {
        super.onDestroy();
    }
}
