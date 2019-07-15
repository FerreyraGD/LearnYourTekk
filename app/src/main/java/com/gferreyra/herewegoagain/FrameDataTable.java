package com.gferreyra.herewegoagain;

import android.content.Context;
import android.content.res.Configuration;
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

//Activity where the information about a character's moveset is displayed in a table for view to go through and filter through
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

        //hide header table row until table is populated with data from database
        header = findViewById(R.id.header);
        header.setVisibility(View.GONE);

        //table is created and populated with data from database about the specific character
        populateTable();

    }

    //Creates the drawer menu of filtering options
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter_drawer_view, menu);
        return true;
    }

    //IF user selects an option from the options filtering menu then will filter table accordingly
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //if the item is already checked then will uncheck item, otherwise sets check marker for item
        if(!item.isChecked()){
            item.setChecked(true);
        }
        else{
            item.setChecked(false);
        }

        //calls method to filter table of data based off selected filtering option
        if(id == R.id.action_default){
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

    //Filters the table based off filtering option selected in menu
    //IF Default is selected, simply resets table to the full set of data
    //Can be an empty table if the character has no moves fitting that filtering option
    private void filterTable(String filterTitle){
        TableLayout currentTable = findViewById(R.id.gridTable);
        View viewCurrentTable;
        ScrollView scrollView;
        ArrayList<View> tables = new ArrayList<View>();
        ArrayList<View> oldTables = new ArrayList<>();


        //Goes through each tuple in table, checks the "Notes" section for the filtering word and saves the tuples
        //as a copy and sets that table as the new table in recyclerview
        for(int i = 0; i < currentTable.getChildCount(); i++){
            viewCurrentTable = currentTable.getChildAt(i); //gets row view inside current table
            oldTables.add(viewCurrentTable); //saves view as a copy for future use
            TextView textView = viewCurrentTable.findViewById(R.id.row_notes); //finds row notes TextView Object for that tuple in order to search through it for filtering keyword

            //IF Notes section does not include filtering word then sets tuple to be invisible
            if(!textView.getText().toString().toLowerCase().contains(filterTitle)){
                viewCurrentTable.setVisibility(View.INVISIBLE);
                tables.add(viewCurrentTable);
            }

            //NOTES SECTION does include filtering word so we keep tuple as VISIBLE.
            //IF filtering from a previously filtered table list, then we need to set a possibly invisible tuple to be visible
            else{
                viewCurrentTable.setVisibility(View.VISIBLE);
            }
            //requests layout to be shown in activity
            viewCurrentTable.requestLayout();
        }

        //sets height of table and height of tuples to be set based off filter table size
        //and defaults view to be at the top of the scrollable list/table so it bounces to top with filtered results
        setHeightDefault(oldTables);
        setInvisibleRowHeight(tables); //sets invisible tuples to have height of 0 so they do not take up unneeded space
        scrollView = findViewById(R.id.scroll_layout);
        scrollView.scrollTo(0,0);
    }

    //All tuples that are set to Invisible still take up space in the table so it sets all their heights/widths to 0
    //This allows our filtered table list to appear at the top and for unneeded empty space to be removed in table
    private void setInvisibleRowHeight(ArrayList<View> invisibleTables){
        TextView commandBox, hitLevelBox, damageBox, startBox,
                blockBox, hitBox, notesBox, counterHitBox;

        //goes through each textView in a tuple and sets height to 0
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

    //sets height of VISIBLE tuples to be Wrap_Content in order to properly fit all the data in the rows
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

    //Queries database for the JSON information of a Character's Move set data
    private RealmResults<BasicMoves> getBasicMovesDatabase(){
        RealmQuery<BasicMoves> query = realm.where(BasicMoves.class);
        RealmResults<BasicMoves> result = query.findAll();
        return result;
    }

    //Creates a row Object, sets its layout and its information based off the passed ArrayList which is a copy of the RealmObject containing the database information for ONE move in a character's moveset
    private TableRow createRow(ArrayList<String> rowList, int rowNumber){
        tableLayout = findViewById(R.id.gridTable);
        TableRow tableRow = (TableRow) LayoutInflater.from(this).inflate(R.layout.layout_row, null);

        //alternates row colors so that every other row is a different color
        if(rowNumber % 2 == 0) {
            tableRow.setBackgroundResource(R.drawable.alternative_cell_shape);
        }
        else{
            tableRow.setBackgroundResource(R.drawable.cell_shape);
        }

        //sets a Move's data into the textViews in a tuple
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


    //INSERTS row objects into the tableLayout object
    private void updateRows(ArrayList<TableRow> rows){
        for(int k = 0; k < rows.size(); k++) {
            tableLayout.addView(rows.get(k));
        }
    }

    //Connects to database, gets the JSON data EVERY character's entire moveset and saves it into the custom RealmResults object
    //Then uses that object to query for a specific character(whomever was selected in the character select activity) and saves that character's move set data into
    //a more specific RealmResult Object (this is how we query and access our Realm database)
    //Using that data we now created the rows for each move in a character's move set and create, populate and display a recyclerview table for user to view
    private void populateTable() {
        //Spliting the large load of the data set of a character into a different Thread in order to speed up the loading of the recyclerview table
        new Thread() {
            @Override
            public void run() {
                //progress bar for user to see while they wait for table to be fully loaded
                final ProgressBar progressBar = findViewById(R.id.loading_circle);

                //Set connection to database
                realm = Realm.getDefaultInstance();

                //Queries database for ACCESS to entire character's data for all 45 characters
                //saved into RealmResult object that can be further queried in future
                //THIS IS THE ONLY TIME DATABASE HAS TO BE QUERIES/CALLED TO AND ACCESSED
                //After we access database, we are essentially creating a link to the database table of BasicMoves to be queried or accessed at any future time in this class
                RealmResults<BasicMoves> basicDatabase = getBasicMovesDatabase();

                //Obtain character's name from passed intent(so whoever we selected in character select activity, we get their name here)
                String characterName = getIntent().getExtras().getString("name");

                //test for special cases
                //certain character's names are typed differently in the JSON data so we fix those issues here
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


                //QUERIES RealmResults from before and get the JSON data from database for a specific Character
                //So entire JSON data set for a character is saved into charMoves
                final RealmResults<BasicMoves> charMoves = basicDatabase.where().equalTo("character.name", characterName).findAll();

                //In order to speed up the application, I created a copy of the RealmResults Object which contains a List Object of the BasicMoves Objects from the Realm database
                //So instead of accessing the database over and over to get the data and fill the table with it, we created a copy of the data I needed from database for a specific character
                //and used the copy instead since accessing a List Object is much more efficient and faster than accessing a database over and over.
                List<BasicMoves> listBasicMoves = realm.copyFromRealm(charMoves);


                ArrayList<String> fullList = new ArrayList<>();
                final ArrayList<TableRow> listOfRows = new ArrayList<>();

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

                //RUNS this on the ORIGINAL thread where the UI is and initalized
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        updateRows(listOfRows); //INSERTS rows into recyclerview
                        progressBar.setVisibility(View.GONE); //hides progress bar since table is fulled loaded now

                        //set header row size values and make header appear
                        TableRow headerRow = findViewById(R.id.header_row);
                        header.setVisibility(View.VISIBLE);
                    }
                });
                realm.close(); //close access to database since it is no longer needed
            }
        }.start();
    }

    //Changes the font of the title to a custom font
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
