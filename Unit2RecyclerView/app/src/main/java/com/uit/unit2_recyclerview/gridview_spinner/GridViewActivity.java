package com.uit.unit2_recyclerview.gridview_spinner;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.uit.unit2_recyclerview.R;

import java.util.Objects;

public class GridViewActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    GridViewAdapter mAdapter;
    TextInputEditText mInputName;
    MaterialButton mInsertButton;
    AppCompatSpinner mSpinner;
    AppCompatCheckBox mCheckPromotion;
    int image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        setupView();
        setupRecyclerView();
        setupSpinner();
    }

    private void setupView() {
        mInputName = findViewById(R.id.et_input_name);
        mInsertButton = findViewById(R.id.btn_insert);
        mSpinner = findViewById(R.id.spinner);
        mCheckPromotion =  findViewById(R.id.check_promotion);
        mRecyclerView = findViewById(R.id.rv_grid);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Thumbnail thumbnail = (Thumbnail) parent.getItemAtPosition(position);
                image = thumbnail.getImg();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void buttonInsertClicked(View view){
        Editable name = mInputName.getText();
        if (name != null && !TextUtils.isEmpty(name.toString().trim())){
            addNewEmployee();
        }
    }

    private void setupSpinner(){
        mSpinner.setAdapter(new SpinnerAdapter(this, R.layout.item_spinner_dish, Thumbnail.values()));
    }

    private void setupRecyclerView() {
        mAdapter = new GridViewAdapter();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addNewEmployee() {
        DishModel model = new DishModel();
        model.setName(Objects.requireNonNull(mInputName.getText()).toString());
        model.setPromotion(mCheckPromotion.isChecked());
        //model.setImage(R.mipmap.first_thumbnail);
        model.setImage(image);
        mAdapter.addDishItem(model);
        mRecyclerView.smoothScrollToPosition(0);
    }
}
