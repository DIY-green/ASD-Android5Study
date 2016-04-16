package com.diygreen.android5.newwidget2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.diygreen.android5.R;

public class MaterialDialogActivity extends AppCompatActivity
            implements DatePickerDialog.OnDateSetListener,
            TimePickerDialog.OnTimeSetListener {

    private static final String MD_DIALOG_TITLE = "DIY Title";
    private static final String MD_DIALOG_MESSAGE = "DIY Message\n演示 Material Dialog，这是演示使用的 Message。";
    private static final String MD_DIALOG_POSITIVE_BUTTON_TEXT = "确定";
    private static final String MD_DIALOG_NEGATIVE_BUTTON_TEXT = "取消";
    private static final String MD_DIALOG_NEUTRAL_BUTTON_TEXT = "呵呵";
    private static final String MD_PROGRESSDIALOG_MESSAGE = "DIY ProgressDialog Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materialdialog);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_datepickerdialog:
                showDatePickerDialog();
                break;
            case R.id.btn_timepickerdialog:
                showTimePickerDialog();
                break;
            case R.id.btn_alertdialogonebutton:
                showAlertDialogOneButton();
                break;
            case R.id.btn_alertdialogtwobutton:
                showAlertDialogTwoButton();
                break;
            case R.id.btn_alertdialogthreebutton:
                showAlertDialogThreeButton();
                break;
            case R.id.btn_alertdialoginput:
                showAlertDialogInput();
                break;
            case R.id.btn_alertdialogsinglechoice:
                showAlertDialogSingleChoice();
                break;
            case R.id.btn_alertdialogmultichoice:
                showAlertDialogMultiChoice();
                break;
            case R.id.btn_alertdialoglist:
                showAlertDialogList();
                break;
            case R.id.btn_alertdialogcustom:
                showAlertDialogCuston();
                break;
            case R.id.btn_progressdialogcycle:
                showProgressDialogCycle();
                break;
            case R.id.btn_progressdialoghorizontal:
                showProgressDialogHorizontal();
                break;
        }
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, 2016, 4, 15);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, R.style.DIYMaterialDialog, this, 12, 12, true);
        timePickerDialog.show();
    }

    private void showAlertDialogOneButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DIYMaterialDialog);
        builder.setTitle(MD_DIALOG_TITLE);
        builder.setMessage(MD_DIALOG_MESSAGE);
        builder.setPositiveButton(MD_DIALOG_POSITIVE_BUTTON_TEXT, null);
        builder.create().show();
    }

    private void showAlertDialogTwoButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DIYMaterialDialog);
        builder.setTitle(MD_DIALOG_TITLE);
        builder.setMessage(MD_DIALOG_MESSAGE);
        builder.setNegativeButton(MD_DIALOG_NEGATIVE_BUTTON_TEXT, null);
        builder.setPositiveButton(MD_DIALOG_POSITIVE_BUTTON_TEXT, null);
        builder.create().show();
    }

    private void showAlertDialogThreeButton() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DIYMaterialDialog);
        builder.setTitle(MD_DIALOG_TITLE);
        builder.setMessage(MD_DIALOG_MESSAGE);
        builder.setNegativeButton(MD_DIALOG_NEGATIVE_BUTTON_TEXT, null);
        builder.setNeutralButton(MD_DIALOG_NEUTRAL_BUTTON_TEXT, null);
        builder.setPositiveButton(MD_DIALOG_POSITIVE_BUTTON_TEXT, null);
        builder.create().show();
    }

    private void showAlertDialogInput() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DIYMaterialDialog);
        builder.setTitle(MD_DIALOG_TITLE);
        builder.setView(new EditText(this));
        builder.setNegativeButton(MD_DIALOG_NEGATIVE_BUTTON_TEXT, null);
        builder.setPositiveButton(MD_DIALOG_POSITIVE_BUTTON_TEXT, null);
        builder.create().show();
    }

    private void showAlertDialogSingleChoice() {
        final String[] itemArr = new String[]{"唐三藏", "孙悟空", "猪八戒", "沙和尚"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DIYMaterialDialog);
        builder.setTitle(MD_DIALOG_TITLE);
        builder.setSingleChoiceItems(itemArr, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MaterialDialogActivity.this, itemArr[which], Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(MD_DIALOG_NEGATIVE_BUTTON_TEXT, null);
        builder.setPositiveButton(MD_DIALOG_POSITIVE_BUTTON_TEXT, null);
        builder.create().show();
    }


    private void showAlertDialogMultiChoice() {
        final String[] itemArr = new String[]{"唐三藏", "孙悟空", "猪八戒", "沙和尚"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DIYMaterialDialog);
        builder.setTitle(MD_DIALOG_TITLE);
        builder.setMultiChoiceItems(itemArr, new boolean[]{true, false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(MaterialDialogActivity.this, itemArr[which], Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(MD_DIALOG_NEGATIVE_BUTTON_TEXT, null);
        builder.setPositiveButton(MD_DIALOG_POSITIVE_BUTTON_TEXT, null);
        builder.create().show();
    }

    private void showAlertDialogList() {
        final String[] itemArr = new String[]{"唐三藏", "孙悟空", "猪八戒", "沙和尚"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DIYMaterialDialog);
        builder.setTitle(MD_DIALOG_TITLE);
        builder.setItems(itemArr, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MaterialDialogActivity.this, itemArr[which], Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(MD_DIALOG_NEGATIVE_BUTTON_TEXT, null);
        builder.setPositiveButton(MD_DIALOG_POSITIVE_BUTTON_TEXT, null);
        builder.create().show();
    }

    private void showAlertDialogCuston() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_meterialdialog, (ViewGroup) findViewById(R.id.rl_meterialdialog));
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DIYMaterialDialog);
        builder.setTitle(MD_DIALOG_TITLE);
        builder.setView(view);
        builder.setNegativeButton(MD_DIALOG_NEGATIVE_BUTTON_TEXT, null);
        builder.setPositiveButton(MD_DIALOG_POSITIVE_BUTTON_TEXT, null);
        builder.create().show();
    }

    private void showProgressDialogCycle() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(MD_DIALOG_TITLE);
        progressDialog.setMessage(MD_PROGRESSDIALOG_MESSAGE);
        progressDialog.show();
    }

    private void showProgressDialogHorizontal() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(MD_DIALOG_TITLE);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(100);
        progressDialog.setProgress(77);
        progressDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        String msg = year + "-"  + monthOfYear + "-" + dayOfMonth;
        Toast.makeText(MaterialDialogActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String msg = hourOfDay + ":" + minute;
        Toast.makeText(MaterialDialogActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
