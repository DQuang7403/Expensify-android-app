package com.example.expensify;

import android.app.DatePickerDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.expensify.RoomDB.AppDatabase;
import com.example.expensify.RoomDB.Info;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextDate;
    EditText editTextExpenseName;
    EditText editTextExpenseAmount;
    EditText editTextAddress;
    Spinner spinnerExpenseCategory;
    SwitchCompat switchExpensePaid;
    Button buttonAddExpense;
    ImageButton datePickerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        // Ánh xạ các thành phần trong layout
        editTextDate = findViewById(R.id.editTextDate);
        editTextExpenseName = findViewById(R.id.editTextExpenseName);
        editTextExpenseAmount = findViewById(R.id.editTextExpenseAmount);
        editTextAddress = findViewById(R.id.editTextAddress);
        spinnerExpenseCategory = findViewById(R.id.spinnerExpenseCategory);
        switchExpensePaid = findViewById(R.id.switchExpensePaid);
        buttonAddExpense = findViewById(R.id.buttonAddExpense);
        datePickerBtn = findViewById(R.id.date_picker_btn);

        datePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get current date
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a new DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                // Set the selected date to the EditText
                                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                                editTextDate.setText(selectedDate);
                            }
                        },
                        year, month, day);

                // Show the DatePickerDialog
                datePickerDialog.show();
            }
        });

        // Đặt sự kiện click cho nút "Thêm"
        buttonAddExpense.setOnClickListener(view -> {
            // Lấy thông tin từ các trường nhập liệu
            String date = editTextDate.getText().toString();
            String expenseName = editTextExpenseName.getText().toString();
            String expenseAmount = editTextExpenseAmount.getText().toString();
            String expenseCategory = spinnerExpenseCategory.getSelectedItem().toString();
            String expenseAddress = editTextAddress.getText().toString();
            boolean expensePaid = switchExpensePaid.isChecked();

            if(date.isEmpty() || expenseName.isEmpty() || expenseAmount.isEmpty() || expenseCategory.isEmpty() || expenseAddress.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
                return;
            }
            // Xử lý dữ liệu theo nhu cầu của ứng dụng
            // (ví dụ: lưu vào cơ sở dữ liệu, thực hiện các thao tác khác)
            new Thread (() -> {
                Info info = new Info();
                info.name = expenseName;
                info.amount = expenseAmount;
                info.category = expenseCategory;
                info.address = expenseAddress;
                info.paid = expensePaid;
                info.date = date;

                db.infoDAO().insert(info);
                Log.d("TAG", "Inserted: " + info);
            }).start();

            // Hiển thị thông báo hoặc thực hiện các hành động khác
            Toast.makeText(MainActivity.this, "Đã thêm khoản chi phí: " + expenseName, Toast.LENGTH_SHORT).show();
        });

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Màn hình được xoay ngang

            // Xử lý các thay đổi cần thiết ở đây
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Màn hình được xoay dọc
            // Xử lý các thay đổi cần thiết ở đây
        }
    }
}