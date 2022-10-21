package com.example.phewel;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class popupsClass {

    public void showReset(final Context context, interfaceData id){
        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.reset_popup, null);

        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(popupView);

        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popupView,Gravity.CENTER, 0, 0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        EditText editText = popupView.findViewById(R.id.resetMileage);

        Button buttonEdit = popupView.findViewById(R.id.enterButton);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMileage = editText.getText().toString();
                if (TextUtils.isEmpty(newMileage)){
                    Toast.makeText(context,"Empty field", Toast.LENGTH_SHORT).show();
                } else {
                    id.createNewFile(Integer.parseInt(newMileage));
                    Toast.makeText(context,"Reset!", Toast.LENGTH_SHORT).show();
                    Activity activity = (Activity) context;
                    activity.recreate();
                }

            }
        });

        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });

    }

    public void showImport(final Context context, interfaceData id){
        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.import_popup, null);

        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(popupView);

        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popupView,Gravity.CENTER, 0, 0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button buttonEdit = popupView.findViewById(R.id.importButton);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.checkForSavedFile()){
                    id.resetFromSavedFile();
                    Toast.makeText(context, "Imported!", Toast.LENGTH_SHORT).show();
                    Activity activity = (Activity) context;
                    activity.recreate();
                }else{
                    Toast.makeText(context, "No file found in directory", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
    }

    public void showExport(final Context context, interfaceData id){
        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.export_popup, null);

        PopupWindow popupWindow = new PopupWindow(context);
        popupWindow.setContentView(popupView);

        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popupView,Gravity.CENTER, 0, 0);
        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button buttonEdit = popupView.findViewById(R.id.exportButton);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id.exportFile();
                Toast.makeText(context, "Exported!", Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();
            }
        });

        //Handler for clicking on the inactive zone of the window
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Close the window when clicked
                popupWindow.dismiss();
                return true;
            }
        });
    }

}
