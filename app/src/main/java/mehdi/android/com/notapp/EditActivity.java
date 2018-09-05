package mehdi.android.com.notapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextWrite;
    private Button btnAddNot;
    private ArrayList<MyNot> myNotList;
    public static final String KEY_EXTRA = "key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        setUpViews();

    }

    private void setUpViews() {
        editTextTitle = findViewById(R.id.et_edit_title);
        editTextWrite = findViewById(R.id.et_edit_titleBox);

        String title = getIntent().getStringExtra(MainActivity.EXTRA_KEY_TITLE);
        final String writeBox = getIntent().getStringExtra(MainActivity.EXTRA_KEY_DESCRIPTION);

        if (title != null &&
                writeBox != null) {
                editTextTitle.setText(title);
                editTextWrite.setText(writeBox);
        }


        btnAddNot = findViewById(R.id.btn_editActivity_add);
        btnAddNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editTextTitle.length() > 0) {
                    if (editTextWrite.length() > 0) {
                        Intent intent = new Intent(EditActivity.this, MainActivity.class);
                        intent.putExtra(MainActivity.EXTRA_KEY_TITLE, editTextTitle.getText().toString());
                        intent.putExtra(MainActivity.EXTRA_KEY_DESCRIPTION, editTextWrite.getText().toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(EditActivity.this,"لطفا فیلد عنوان را پر کنید",
                                Toast.LENGTH_SHORT);
                    }

                } else {
                    Toast.makeText(EditActivity.this,"لطفا فیلد محتوا را پر کنید",
                            Toast.LENGTH_SHORT);
                }


            }
        });


    }
}
