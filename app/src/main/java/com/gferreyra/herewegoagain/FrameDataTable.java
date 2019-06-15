package com.gferreyra.herewegoagain;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class FrameDataTable extends AppCompatActivity {
    private String TAG = "FrameDataTable";
    private Realm realm;
    private TableLayout tableLayout;
    private TableLayout header;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.framedata);

        Toolbar toolbar = findViewById(R.id.fd_toolbar);
        setSupportActionBar(toolbar);

        //set characters name as table title
        String title = getIntent().getExtras().getString("name");
        getSupportActionBar().setTitle(title);

        header = findViewById(R.id.header);
        header.setVisibility(View.GONE);

        populateTable();

        //realm.close();



        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("h");
        list.add("7");
        list.add("8");
        list.add("11");
        list.add("5");
        list.add("4");
        list.add("Nothing");

        //TODO EXAMPLE CODE OF ADDING A ROW TO TABLE
        /*
        tableLayout = findViewById(R.id.gridTable);
        TableRow tableRow = (TableRow)LayoutInflater.from(this).inflate(R.layout.layout_row, null);
        ((TextView)tableRow.findViewById(R.id.row_command)).setText(list.get(0));

        tableLayout.addView(tableRow);

        tableLayout = findViewById(R.id.gridTable);
        tableRow = (TableRow)LayoutInflater.from(this).inflate(R.layout.layout_row, null);
        ((TextView)tableRow.findViewById(R.id.row_command)).setText(list.get(1));

        tableLayout.addView(tableRow);
        tableLayout.requestLayout();
        */
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

    private RealmResults<CharacterData> getCharacterDatabase(){
        RealmQuery<CharacterData> query = realm.where(CharacterData.class);
        RealmResults<CharacterData> result = query.findAll();
        return result;
    }

    private RealmResults<BasicMoves> getBasicMovesDatabase(){
        RealmQuery<BasicMoves> query = realm.where(BasicMoves.class);
        RealmResults<BasicMoves> result = query.findAll();
        return result;
    }

    private TableRow createRow(ArrayList<String> rowList, int rowNumber){
        tableLayout = findViewById(R.id.gridTable);
        TableRow tableRow = (TableRow) LayoutInflater.from(this).inflate(R.layout.layout_row, null);
        //if((rowNumber % 2) == 0){
        tableRow.setBackgroundResource(R.drawable.secondary_cell_shape);
        //}

        ((TextView) tableRow.findViewById(R.id.row_command)).setText(rowList.get(0));
        ((TextView) tableRow.findViewById(R.id.row_hitlevel)).setText(rowList.get(1));
        ((TextView) tableRow.findViewById(R.id.row_damage)).setText(rowList.get(2));
        ((TextView) tableRow.findViewById(R.id.row_startframe)).setText(rowList.get(3));
        ((TextView) tableRow.findViewById(R.id.row_blockframe)).setText(rowList.get(4));
        ((TextView) tableRow.findViewById(R.id.row_hitframe)).setText(rowList.get(5));
        ((TextView) tableRow.findViewById(R.id.row_counterhitframe)).setText(rowList.get(6));
        ((TextView) tableRow.findViewById(R.id.row_notes)).setText(rowList.get(7));

        return tableRow;
    }

    private void updateRows(ArrayList<TableRow> rows){
        for(int k = 0; k < rows.size(); k++) {
            tableLayout.addView(rows.get(k));
        }
    }

    //Adds a row to the table for each headline passed in
    private void populateTable() {
        new Thread() {
            @Override
            public void run() {
                final ProgressBar progressBar = findViewById(R.id.loading_circle);

                //set connection to database
                realm = Realm.getDefaultInstance();

                //TESTING IF DATABASE OBJECT CAN BE PASSED AND REALM WILL NEVER HAVE TO BE CALLED AGAIN IF IT PERSISTS
                //IT CAN OMG!!!
                //These objects will be passed through intents until they are needed to be used to acquire database information
                RealmResults<BasicMoves> basicDatabase = getBasicMovesDatabase();

                //testing Akuma's moves
                //EXAMPLE OF HOW TO QUERY FOR SPECIAL MOVES (I.E HOMING, TAIL SPIN ETC)
                String characterName = getIntent().getExtras().getString("name");
                if(characterName.equals("ArmorKing")){
                    characterName = "Armor King";
                }

                if(characterName.equals("DevilJin")){
                    characterName = "Devil Jin";
                }

                if(characterName.equals("Jack7")){
                    characterName = "Jack-7";
                }

                if(characterName.equals("LuckyChloe")){
                    characterName = "Lucky Chloe";
                }

                if(characterName.equals("MasterRaven")){
                    characterName = "Master Raven";
                }


                final RealmResults<BasicMoves> charMoves = basicDatabase.where().equalTo("character.name", characterName).findAll();

                List<BasicMoves> listBasicMoves = realm.copyFromRealm(charMoves);


                ArrayList<String> fullList = new ArrayList<>();

                final ArrayList<TableRow> listOfRows = new ArrayList<>();

                for(int i = 0; i < listBasicMoves.size(); i++){
                    //clear list from any previous move data
                    fullList.clear();

                    //add frame data for specified move
                    fullList.add(listBasicMoves.get(i).getNotation());
                    fullList.add(listBasicMoves.get(i).getHit_level());
                    fullList.add(listBasicMoves.get(i).getDamage());
                    fullList.add(listBasicMoves.get(i).getSpeed());
                    fullList.add(listBasicMoves.get(i).getOn_block());
                    fullList.add(listBasicMoves.get(i).getOn_hit());
                    fullList.add(listBasicMoves.get(i).getOn_ch());
                    fullList.add(listBasicMoves.get(i).getNotes());

                    //create row and insert into table
                    listOfRows.add(createRow(fullList, i));
                }

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        //todo ui update
                        updateRows(listOfRows);
                        progressBar.setVisibility(View.GONE);
                        header.setVisibility(View.VISIBLE);
                    }
                });
                realm.close();
            }
        }.start();
    }

    private File getTempFile(Context context, String url) {
        File file = null;
        try {
            String fileName = "Akuma" + "temp";
            file = File.createTempFile(fileName, null, context.getCacheDir());
        } catch (IOException e) {
            // Error while creating file
        }
        return file;
    }
}
