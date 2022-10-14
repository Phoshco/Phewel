package com.example.phewel;

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

    public void showReset(final View view, interfaceData id){
        //Create a View object yourself through inflater
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.reset_popup, null);

        //Specify the length and width through constants
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make Inactive Items Outside Of PopupWindow
        boolean focusable = true;

        //Create a window with our parameters
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //Set the location of the window on the screen
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        EditText editText = popupView.findViewById(R.id.resetMileage);

        Button buttonEdit = popupView.findViewById(R.id.enterButton);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMileage = editText.getText().toString();
                if (TextUtils.isEmpty(newMileage)){
                    Toast.makeText(view.getContext(),"Empty field", Toast.LENGTH_SHORT).show();
                } else {
                    id.createNewFile(Integer.parseInt(newMileage));
                    Toast.makeText(view.getContext(),"Reset!", Toast.LENGTH_SHORT).show();
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

    public void showImport(final View view){

    }

    public void showExport(final View view){

    }

}
