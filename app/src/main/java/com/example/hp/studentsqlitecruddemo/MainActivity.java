package com.example.hp.studentsqlitecruddemo;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText efirstname,elastname;

    TextView elist;
DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        efirstname=(EditText) findViewById(R.id.edtFname);
        elastname=(EditText) findViewById(R.id.edtLname);
        elist=(TextView) findViewById(R.id.displayList);
        controller= new DB_Controller(this,"",null,1);
    }

    public void Click_Mthod(View view){

        switch(view.getId()){

            case R.id.btnAddStudents:
                try{
                    controller.insert_data(efirstname.getText().toString(),elastname.getText().toString());
                }catch (SQLiteException ex){
                    Toast.makeText(this, "Student Already Exist", Toast.LENGTH_SHORT).show();

                }

            break;
            case R.id.btnDeleteStudents:
                controller.delete_data(efirstname.getText().toString());
                break;
            case R.id.btnListStudents:

                controller.list_data(elist);
                break;
            case R.id.btnUpdateStudents:
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Enter New First Name");

                final EditText neweditext= new EditText(this);
                dialog.setView(neweditext);
                dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        controller.update_data(efirstname.getText().toString(),neweditext.getText().toString());
                    }
                });
dialog.show();
                break;
        }
    }
}
