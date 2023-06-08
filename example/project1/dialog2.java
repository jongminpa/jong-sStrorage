package com.example.project1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class dialog2 extends AppCompatActivity {
    View dialogView;
    TextView tv1;
    ListView lv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog2);
        tv1 = (TextView)findViewById(R.id.tv1);
        lv2 = (ListView)findViewById(R.id.lv2);

        List<String> userList = new ArrayList<String>();
        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,userList);
        lv2.setAdapter(userAdapter);

        Button btninfo = (Button) findViewById(R.id.btnInfo);

        String name = getIntent().getStringExtra("name");
        tv1.setText(name);

        FetchDataTask fetchDataTask = new FetchDataTask(this, lv2);
        new FetchDataTask(dialog2.this, lv2).execute(name);

        lv2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                String selectedItem = adapterView.getItemAtPosition(position).toString();
                String userId = selectedItem.split(" ")[1]; // Assuming the user ID is the first item in the string
                Log.d("MyApp", "Item long clicked");

                // Delete the user
                new DeleteDataTask(dialog2.this, new Runnable() {
                    @Override
                    public void run() {
                        // Refresh the list
                        new FetchDataTask(dialog2.this, lv2).execute(name);
                    }
                }).execute(userId);

                return true; // Return true to indicate that the long click was handled
            }
        });


        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(dialog2.this, R.layout.activity_data_set, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(dialog2.this);
                dlg.setTitle("회원 정보 설정");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText edtHeight = dialogView.findViewById(R.id.edtHeight);
                        String height = edtHeight.getText().toString();
                        EditText edtWeight = dialogView.findViewById(R.id.edtWeight);
                        String weight = edtWeight.getText().toString();
                        DatePicker edtDate = dialogView.findViewById(R.id.mCurrentDate);

                        int mYear = edtDate.getYear();
                        int mMonth = edtDate.getMonth() + 1;
                        int mDay = edtDate.getDayOfMonth();

                        String date = String.valueOf(mYear)+'-'+String.valueOf(mMonth)+'-'+String.valueOf(mDay);

                        double heightValue = Double.parseDouble(height) / 100;
                        double weightValue = Double.parseDouble(weight);
                        double bmiValue = weightValue / (heightValue * heightValue);
                        String bmi = Double.toString(bmiValue);

                        // 데이터 전송
                        new SendDataTask(dialog2.this, new Runnable() {
                            @Override
                            public void run() {
                                new FetchDataTask(dialog2.this, lv2).execute(name);
                            }
                        }).execute(name, height, weight, bmi, date);


                        Toast.makeText(dialog2.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(dialog2.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                dlg.show();
            }
        });
    }
}
