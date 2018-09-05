package mehdi.android.com.notapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import static mehdi.android.com.notapp.EditActivity.KEY_EXTRA;

public class MainActivity extends AppCompatActivity implements TitleAdapter.NotViewCallBack {

    public static final int ADD_NOTE_REQUEST_ID = 1001;
    public static final int EDIT_NOTE_REQUEST_ID = 1002;

    public static final String EXTRA_KEY_TITLE = "title";
    public static final String EXTRA_KEY_DESCRIPTION = "description";


    private Button btnAddNewNot;
    private RecyclerView recyclerView;
    private TitleAdapter titleAdapter;
    int pandingEditNotPosition = -1;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUpViews();


    }


    private void setUpViews() {

        titleAdapter = new TitleAdapter(this);
        recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(titleAdapter);

        btnAddNewNot = findViewById(R.id.btn_main_addNewNot);


        btnAddNewNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, EditActivity.class), ADD_NOTE_REQUEST_ID);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST_ID
                && resultCode == RESULT_OK) {
            String title = data.getStringExtra(EXTRA_KEY_TITLE);
            String writeBox = data.getStringExtra(EXTRA_KEY_DESCRIPTION);
            MyNot myNot = new MyNot();
            myNot.setTitle(title);
            myNot.setWriteBox(writeBox);
            titleAdapter.addNot(myNot);
        } else if (requestCode == EDIT_NOTE_REQUEST_ID) {

            String title = data.getStringExtra(EXTRA_KEY_TITLE);
            String writeBox = data.getStringExtra(EXTRA_KEY_DESCRIPTION);

            titleAdapter.updateNot(pandingEditNotPosition, title, writeBox);

        }
    }


    @Override
    public void onEditButtonClicked(int position, MyNot myNot) {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        intent.putExtra(EXTRA_KEY_TITLE, myNot.getTitle());
        intent.putExtra(EXTRA_KEY_DESCRIPTION, myNot.getWriteBox());
        startActivityForResult(intent, EDIT_NOTE_REQUEST_ID);
        pandingEditNotPosition = position;


    }

    @Override
    public void onRemovedButtonClicked(int position) {
        titleAdapter.removeNot(position);
    }
}


