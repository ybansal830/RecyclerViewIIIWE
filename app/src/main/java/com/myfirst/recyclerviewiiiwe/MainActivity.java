package com.myfirst.recyclerviewiiiwe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListener{

    private ArrayList<Employee> employeeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CardView mEditCardVIew;
    private EditText mEtAge;
    private EditText mEtName;
    private EditText mEtAddress;
    private Button mBtnDone;
    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        buildEmployeeList();
        setRecyclerView();
    }

    private void setRecyclerView() {
        employeeAdapter = new EmployeeAdapter(employeeList,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(employeeAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void buildEmployeeList() {
        for(int i=0;i<50;i++){
            Employee employee = new Employee("Gori Man",i+1,"Gagalehri Mandi");
            employeeList.add(employee);
        }
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        mEditCardVIew = findViewById(R.id.editCardView);
        mEtName = findViewById(R.id.etName);
        mEtAge = findViewById(R.id.etAge);
        mEtAddress = findViewById(R.id.etAddress);
        mBtnDone = findViewById(R.id.btnDone);
    }

    @Override
    public void onItemClick(int position, Employee employee) {
        mEditCardVIew.setVisibility(View.VISIBLE);
        mEtName.setText(employee.getName());
        mEtAge.setText(employee.getAge() + "");
        mEtAddress.setText(employee.getAddress());
        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditCardVIew.setVisibility(View.GONE);
                Employee new_employee = new Employee(mEtName.getText().toString(),
                        Integer.parseInt(mEtAge.getText().toString()),mEtAddress.getText().toString());
                employeeList.set(position,new_employee);
                employeeAdapter.notifyDataSetChanged();
            }
        });
    }
}