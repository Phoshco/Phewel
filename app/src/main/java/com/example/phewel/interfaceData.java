package com.example.phewel;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class interfaceData {

    Context context;

    interfaceData(Context context){
        this.context = context;
    }

    void addData(Entry toEnter){
        List<String> list = readFile();
        try {
            FileOutputStream fos = context.openFileOutput("testing", Context.MODE_PRIVATE);
            for (int i=0; i<list.size(); i++){
                String toWrite = list.get(i);
                fos.write(toWrite.getBytes(StandardCharsets.UTF_8));
                fos.write('\n');
            }
            fos.write(toEnter.getString().getBytes(StandardCharsets.UTF_8));
            fos.write('\n');
            fos.close();
        }catch (IOException err){
            err.printStackTrace();
        }
    }

    void delData(){
        List<String> list = readFile();
        try {
            FileOutputStream fos = context.openFileOutput("testing", Context.MODE_PRIVATE);
            for (int i=0; i<list.size()-1; i++){
                String toWrite = list.get(i);
                fos.write(toWrite.getBytes(StandardCharsets.UTF_8));
                fos.write('\n');
            }
            fos.close();
        }catch (IOException err){
            err.printStackTrace();
        }
    }

    boolean checkForSavedFile(){
        File file = null;
        String root = Environment.getExternalStorageDirectory().toString();
        if (isStoragePermissionGranted()) { // check or ask permission
            File myDir = new File(root + "/" + Environment.DIRECTORY_DOCUMENTS);
            if (!myDir.exists()) {
                myDir.mkdirs();
            }
            String fname = "/phewel_output.csv";
            file = new File(myDir + fname);
        }
        if (file == null){
            return false;
        } else {
            return file.exists();
        }
    }

    void resetFromSavedFile(){
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.mileage), StandardCharsets.UTF_8));
        String line;
        List<String> entire = new ArrayList<>();
        File file = null;

        String root = Environment.getExternalStorageDirectory().toString();
        if (isStoragePermissionGranted()) { // check or ask permission
            File myDir = new File(root + "/" + Environment.DIRECTORY_DOCUMENTS);
            if (!myDir.exists()) {
                myDir.mkdirs();
            }
            String fname = "/phewel_output.csv";
            file = new File(myDir + fname);
        }

        try {
            if (file.exists()) {
                // br = new BufferedReader(new FileReader(file.toString()));
                FileInputStream fin = new FileInputStream(file);
                br = new BufferedReader(new InputStreamReader(fin, StandardCharsets.UTF_8));
            }
            while ((line = br.readLine()) != null){
                entire.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = context.openFileOutput("testing", Context.MODE_PRIVATE);
            for (int i=0; i<entire.size(); i++){
                fos.write(entire.get(i).getBytes(StandardCharsets.UTF_8));
                fos.write('\n');
            }
            fos.close();
        }catch (IOException err){
            err.printStackTrace();
        }
    }

    void createNewFile(int startOdo){
        BufferedReader br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.default_mileage), StandardCharsets.UTF_8));
        String line;
        List<String> entire = new ArrayList<>();
        String startLine = String.valueOf(startOdo)+",,,,,,,";
        try {
            while ((line = br.readLine()) != null){
                entire.add(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = context.openFileOutput("testing", Context.MODE_PRIVATE);
            for (int i=0; i<entire.size(); i++){
                fos.write(entire.get(i).getBytes(StandardCharsets.UTF_8));
                fos.write('\n');
            }
            fos.write(startLine.getBytes(StandardCharsets.UTF_8));
            fos.write('\n');
            fos.close();
        }catch (IOException err){
            err.printStackTrace();
        }
    }

    List<String> readFile(){
        List<String> entire = new ArrayList<>();
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput("testing"), StandardCharsets.UTF_8));
            while ((line = reader.readLine()) != null) {
                entire.add(line);
            }
            reader.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
        return entire;
    }

    List<String> readData() {

        List<String> entire = new ArrayList<>();
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput("testing"), StandardCharsets.UTF_8));
            line = reader.readLine();
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                entire.add(line);
            }
            reader.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
        return entire;
    }

    String firstOdo(){
        String firstOdo = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput("testing"), StandardCharsets.UTF_8));
            reader.readLine();
            String line = reader.readLine();
            firstOdo = line.split(",")[0];
            reader.close();
        } catch (IOException err){
            err.printStackTrace();
        }
        return firstOdo;
    }

    List<Entry> processData() {
        List<Entry> list = new ArrayList<>();
        List<String> entire = readData();
        String firstOdo = firstOdo();

        for (int i=0;i<entire.size();i++){
            String[] tokens = entire.get(i).split(",",-1);
//            for (int j=0;j<tokens.length;j++){Log.d("OGAY",tokens[j]);}
//            Log.d("LMAO", ""+tokens.length);
            String prevOdo;
            if (i==0){prevOdo = firstOdo;}
            else{
                prevOdo = list.get(i-1).getOdoAfter()+"";
            }
            list.add(new Entry(prevOdo, tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]));
        }
        Collections.reverse(list);
        return list;
    }

    void exportFile(){
        List<String> list = readFile();
        String root = Environment.getExternalStorageDirectory().toString();
        if (isStoragePermissionGranted()) { // check or ask permission
            File myDir = new File(root + "/" + Environment.DIRECTORY_DOCUMENTS);
            if (!myDir.exists()) {
                myDir.mkdirs();
            }
            String fname = "/phewel_output.csv";
            File file = new File(myDir + fname);
            if (file.exists()) {
                file.delete();
            }

            try {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                for (int i=0; i<list.size(); i++){
                    String toWrite = list.get(i);
                    fos.write(toWrite.getBytes(StandardCharsets.UTF_8));
                    fos.write('\n');
                }
                fos.close();
            }catch (IOException err){
                err.printStackTrace();
            }
        }
    }

    public boolean isStoragePermissionGranted() {
        String TAG = "Storage Permission";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (context.checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted 1");
                return true;
            } else if (!Environment.isExternalStorageManager()){
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                context.startActivity(intent);
                Log.v(TAG, "Permission is granted 2");
                return true;
            } else if (Environment.isExternalStorageManager()) {
                Log.v(TAG, "Permission is granted 4");
                return true;
            } else {
                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions( (Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                ActivityCompat.requestPermissions( (Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted 3");
            return true;
        }
    }

}
