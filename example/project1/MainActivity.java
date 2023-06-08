package com.example.project1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    View dialogView;
    EditText dlgEdtname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> midList = new ArrayList<String>();
        ListView list = (ListView) findViewById(R.id.listview);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, midList);
        list.setAdapter(adapter);

        new FetchNamesTask(this, adapter).execute();

        Button btnAdd = (Button) findViewById(R.id.btnadd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(MainActivity.this, R.layout.activity_dialog, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("회원 정보 등록");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dlgEdtname = (EditText) dialogView.findViewById(R.id.edt1);
                        midList.add(dlgEdtname.getText().toString());
                        Toast.makeText(MainActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                adapter.notifyDataSetChanged();
                dlg.show();
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View otherLayout = inflater.inflate(R.layout.activity_dialog2, null);

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, dialog2.class);
                intent.putExtra("name", midList.get(i));
                startActivity(intent);
            }
        });


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                String[] items = selectedItem.split(" ");

                if (items.length >= 3) {
                    String userId = items[2]; // Assuming the user ID is the third item in the string

                    // Delete the item from the list
                    midList.remove(i);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();

                    // Delete the item from the server
                    new DeleteDataTask(MainActivity.this, new Runnable() {
                        @Override
                        public void run() {
                            // Refresh the list after successful deletion
                            new FetchDataTask(MainActivity.this, list).execute();
                        }
                    }).execute(userId);
                } else {
                    // Handle the case when the selected item doesn't have the expected format
                    Toast.makeText(MainActivity.this, "잘못된 형식의 항목입니다.", Toast.LENGTH_SHORT).show();
                }

                return true; // Return true to indicate that the long click event has been consumed
            }
        });
    }
}
