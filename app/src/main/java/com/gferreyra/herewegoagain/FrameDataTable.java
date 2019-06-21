package com.gferreyra.herewegoagain;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

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
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.framedata);

        toolbar = findViewById(R.id.fd_toolbar);
        setSupportActionBar(toolbar);

        //set characters name as table title
        String title = getIntent().getExtras().getString("name");
        getSupportActionBar().setTitle(title);
        changeTitleFont();

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
        getMenuInflater().inflate(R.menu.filter_drawer_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        TableLayout currentTable = findViewById(R.id.gridTable);
        View viewCurrentTable;
        ScrollView scrollView;
        int id = item.getItemId();

        if(!item.isChecked()){
            item.setChecked(true);
        }
        else{
            item.setChecked(false);
        }

        if(id == R.id.action_default){
            /*
            ArrayList<View> allViews = new ArrayList<>();
            for(int k = 0; k < currentTable.getChildCount(); k++){
                viewCurrentTable = currentTable.getChildAt(k);
                viewCurrentTable.setVisibility(View.VISIBLE);
                allViews.add(viewCurrentTable);
            }
            setHeightDefault(allViews);
            */
            filterTable("");
            return true;
        }

        //Filters table to only show specific moves
        if (id == R.id.action_rage_art) {
            filterTable("art");
            return true;
        }

        if (id == R.id.action_rage_drive) {
            filterTable("drive");
            return true;
        }

        if (id == R.id.action_homing) {
            filterTable("homing");
            return true;
        }

        if (id == R.id.action_tail_spin) {
            filterTable("tail");
            return true;
        }

        if (id == R.id.action_power_crush) {
            filterTable("power");
            return true;
        }

        if (id == R.id.action_wall_bounce) {
            filterTable("bounce");
            return true;
        }

        if (id == R.id.action_wall_break) {
            filterTable("break");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void filterTable(String filterTitle){
        TableLayout currentTable = findViewById(R.id.gridTable);
        View viewCurrentTable;
        ScrollView scrollView;
        ArrayList<View> tables = new ArrayList<View>();
        ArrayList<View> oldTables = new ArrayList<>();


        for(int i = 0; i < currentTable.getChildCount(); i++){
            viewCurrentTable = currentTable.getChildAt(i);
            oldTables.add(viewCurrentTable);
            TextView textView = viewCurrentTable.findViewById(R.id.row_notes);
            if(!textView.getText().toString().toLowerCase().contains(filterTitle)){
                viewCurrentTable.setVisibility(View.INVISIBLE);
                tables.add(viewCurrentTable);
            }
            else{
                viewCurrentTable.setVisibility(View.VISIBLE);
            }
            viewCurrentTable.requestLayout();
        }

        setHeightDefault(oldTables);
        setInvisibleRowHeight(tables);
        scrollView = findViewById(R.id.scroll_layout);
        scrollView.scrollTo(0,0);
    }

    private void checkTableVisibility(){
        TableLayout tableL = findViewById(R.id.gridTable);
        View viewTable;
        int check = 0;
        for(int i = 0; i < tableL.getChildCount(); i++){
            viewTable = tableL.getChildAt(i);
            if(viewTable.getVisibility() == View.VISIBLE){
                check++;
            }

            Log.d(TAG, "Check is: " + check);
            if(check == 0){
                Toast.makeText(this, "Yo its empty", Toast.LENGTH_SHORT);
            }
        }
    }

    private void setInvisibleRowHeight(ArrayList<View> invisibleTables){
        TextView commandBox, hitLevelBox, damageBox, startBox,
                blockBox, hitBox, notesBox, counterHitBox;

        for(int k = 0; k < invisibleTables.size(); k++){
            commandBox = invisibleTables.get(k).findViewById(R.id.row_command);
            hitLevelBox = invisibleTables.get(k).findViewById(R.id.row_hitlevel);
            damageBox = invisibleTables.get(k).findViewById(R.id.row_damage);
            startBox = invisibleTables.get(k).findViewById(R.id.row_startframe);
            blockBox = invisibleTables.get(k).findViewById(R.id.row_blockframe);
            hitBox = invisibleTables.get(k).findViewById(R.id.row_hitframe);
            notesBox = invisibleTables.get(k).findViewById(R.id.row_notes);
            counterHitBox = invisibleTables.get(k).findViewById(R.id.row_counterhitframe);

            Log.d(TAG, "The height need is: " + String.valueOf(commandBox.getLayoutParams().height));
            commandBox.getLayoutParams().height = 0;
            hitLevelBox.getLayoutParams().height = 0;
            damageBox.getLayoutParams().height = 0;
            startBox.getLayoutParams().height = 0;
            blockBox.getLayoutParams().height = 0;
            hitBox.getLayoutParams().height = 0;
            notesBox.getLayoutParams().height = 0;
            counterHitBox.getLayoutParams().height = 0;
        }
    }

    private void setHeightDefault(ArrayList<View> allTables){
        TextView commandBox, hitLevelBox, damageBox, startBox,
                blockBox, hitBox, notesBox, counterHitBox;

        for(int k = 0; k < allTables.size(); k++){
            commandBox = allTables.get(k).findViewById(R.id.row_command);
            hitLevelBox = allTables.get(k).findViewById(R.id.row_hitlevel);
            damageBox = allTables.get(k).findViewById(R.id.row_damage);
            startBox = allTables.get(k).findViewById(R.id.row_startframe);
            blockBox = allTables.get(k).findViewById(R.id.row_blockframe);
            hitBox = allTables.get(k).findViewById(R.id.row_hitframe);
            notesBox = allTables.get(k).findViewById(R.id.row_notes);
            counterHitBox = allTables.get(k).findViewById(R.id.row_counterhitframe);

            commandBox.getLayoutParams().height = -2;
            hitLevelBox.getLayoutParams().height = -2;
            damageBox.getLayoutParams().height = -2;
            startBox.getLayoutParams().height = -2;
            blockBox.getLayoutParams().height = -2;
            hitBox.getLayoutParams().height = -2;
            notesBox.getLayoutParams().height = -2;
            counterHitBox.getLayoutParams().height = -2;
        }
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
        if(rowNumber % 2 == 0) {
            tableRow.setBackgroundResource(R.drawable.alternative_cell_shape);
        }
        else{
            tableRow.setBackgroundResource(R.drawable.cell_shape);
        }

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

                //test for special cases
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
                final int[] row_size = {0, 0, 0, 0, 0, 0, 0, 0};

                for(int i = 1; i < listBasicMoves.size(); i++){
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

                        //set header row size values and make header appear
                        TableRow headerRow = findViewById(R.id.header_row);
                        header.setVisibility(View.VISIBLE);

                        for(int i = 0; i < row_size.length; i++){
                            Log.d(TAG, row_size[i] + "\n");
                        }
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

    private int checkSize(int currentSize, int checkSize){
        if(checkSize > currentSize){
            currentSize = checkSize;
        }

        return currentSize;
    }

    private void changeTitleFont(){
        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);
            if(view instanceof TextView){
                TextView tv = (TextView) view;
                if(tv.getText().equals(toolbar.getTitle())){
                    tv.setTypeface((Typeface.createFromAsset(this.getAssets(), "fonts/capture.ttf")));
                    break;
                }
            }
        }
    }
}
