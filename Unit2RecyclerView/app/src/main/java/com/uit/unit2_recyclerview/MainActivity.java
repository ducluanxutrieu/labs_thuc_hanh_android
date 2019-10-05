package com.uit.unit2_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.uit.unit2_recyclerview.gridview_spinner.GridViewActivity;

public class MainActivity extends AppCompatActivity implements MainItemClicked{
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mAdapter;
    TextInputEditText mInputName, mInputMaNV;
    MaterialButton mInsertButton;
    AppCompatTextView mPosition, mValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoGridActivity();

        setupRecyclerView();
        setupView();
    }

    private void gotoGridActivity(){
        Intent intent = new Intent(this, GridViewActivity.class);
        startActivity(intent);
        finish();
    }

    private void setupView() {
        mInputMaNV = findViewById(R.id.et_input_manv);
        mInputName = findViewById(R.id.et_input_name);
        mInsertButton = findViewById(R.id.btn_insert);
        mPosition = findViewById(R.id.tv_position);
        mValue = findViewById(R.id.tv_value);
    }

    public void buttonInsertClicked(View view){
        Editable name = mInputName.getText();
        if (name != null && !TextUtils.isEmpty(name.toString().trim())){
            addNewEmployee();
            mInputName.setText("");
        }
    }

    private void setupRecyclerView() {
        mRecyclerView = findViewById(R.id.rv_main);
        mAdapter = new RecyclerViewAdapter();
        mRecyclerView.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.itemClicked = this;
    }

    @Override
    public void onItemClicked(int position, String value) {
        String pos = getString(R.string.position) + position;
        mPosition.setText(pos);
        String val = getString(R.string.value) + " = " + value;
        mValue.setText(val);
    }

    public void addNewEmployee() {
        Employee employee;
        //Lấy ra đúng id của Radio Button được checked
        RadioGroup group = findViewById(R.id.radio_group);
        int radId = group.getCheckedRadioButtonId();
        if (radId == R.id.radio_full) {
            employee = new EmployeeFullTime();
        }else {
            employee = new EmployeePartTime();
        }

        String id = mInputMaNV.getText().toString();
        String name = mInputName.getText().toString();

        //FullTime hay Partime thì cũng là Employee nên có các hàm này là hiển nhiên
        employee.setId(id);
        employee.setName(name);
        //Đưa employee vào ArrayList
        mAdapter.mListNV.add(employee);
        //Cập nhập giao diện
        mAdapter.notifyItemInserted(mAdapter.mListNV.size());
    }
}
