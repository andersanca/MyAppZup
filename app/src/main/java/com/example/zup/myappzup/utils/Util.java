package com.example.zup.myappzup.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Anderson on 09/03/2016.
 */
public class Util {

    private enum RecordingTypes {

        POINTS_BRAND, POINTS_MODEL, POINTS_LOCATION, POINTS_PRESSION_START, POINTS_PRESSION_END, POINTS_DIAMETER, POINTS_ACESSORIES, MEASURES_COMMENT;

    }

    public static boolean copyFileToInternalStorage(Context context, String sourceFileName,
                                                    String destDirName, String destFileName) {
        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(sourceFileName);

            if (destDirName != null) {
                File dirFile = context.getDir(destDirName, Context.MODE_PRIVATE);
                out = new FileOutputStream(new File(dirFile, destFileName));
            } else {
                out = context.openFileOutput(destFileName, Context.MODE_PRIVATE);
            }

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String convertToMinutesSeconds(int time) {
        int totalSeconds = time / 1000;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds - (60 * minutes);
        String sMinutes;
        String sSeconds;
        if (String.valueOf(minutes).length() == 1) {
            sMinutes = "0" + String.valueOf(minutes);
        } else {
            sMinutes = String.valueOf(minutes);
        }
        if (String.valueOf(seconds).length() == 1) {
            sSeconds = "0" + String.valueOf(seconds);
        } else {
            sSeconds = String.valueOf(seconds);
        }

        return sMinutes + ":" + sSeconds;
    }

    public static boolean hasTwoOrMore(boolean[] booleanArray, boolean b) {
        int count = 0;
        for (int i = 0; i < booleanArray.length; i++) {
            if (booleanArray[i]) {
                count++;
            }
        }
        if (count >= 2) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean booleanArrayContains(boolean[] booleanArray, boolean b) {
        for (boolean value : booleanArray) {
            if (value == b) {
                return true;
            }
        }
        return false;
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager)context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean[] resetBooleanArray(int length) {
        boolean[] booleanArray = new boolean[length];
        for (int i = 0; i < booleanArray.length; i++) {
            booleanArray[i] = false;
        }
        return booleanArray;
    }

    public static int dpToPx(Context context, int dpValue) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int)((dpValue * displayMetrics.density) + 0.5);
    }

    public static Bitmap bitmapFromFile(String path) {
        Log.i("showImage", "loading:" + path);
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inDither = false; // Disable Dithering mode
        bfOptions.inPurgeable = true; // Tell to gc that whether it needs free
        // memory, the Bitmap can be cleared
        bfOptions.inInputShareable = true; // Which kind of reference will be
        // used to
        // recover the Bitmap data after being clear, when it will be used in
        // the future
        bfOptions.inTempStorage = new byte[16 * 1024];

        File file = new File(path);
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Bitmap bm = null;
        try {
            if (fs != null) {
                bm = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // bm=BitmapFactory.decodeFile(path, bfOptions); This one causes error:
        // java.lang
        // .OutOfMemoryError: bitmap size exceeds VM budget

        return bm;
    }

    public static int booleanArrayIndexOf(boolean[] booleanArray, boolean b) {
        int index = -1;
        for (int i = 0; i < booleanArray.length; i++) {
            if (booleanArray[i]) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int booleanArrayGetCount(boolean[] booleanArray, boolean b) {
        int count = 0;
        for (int i = 0; i < booleanArray.length; i++) {
            if (booleanArray[i]) {
                count++;
            }
        }
        return count;
    }



    public static boolean arrayListStringContains(ArrayList<String> arrayList, String itemToFind) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equalsIgnoreCase(itemToFind)) {
                return true;
            }
        }
        return false;
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,
                    Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1)
            sb.append((char)cp);
        return sb.toString();
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        } else {
            if (networkInfo.isConnected()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void restartApp(Context context) {
        Intent i = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        System.exit(2);
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"), 8);
        // BufferedReader bufferedReader = new BufferedReader(new
        // InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public static JSONObject readJsonFromHttpResponse(InputStream response){

        InputStream is = response;
        // InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,
                    Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            jsonText = jsonText.replaceAll("[\uFEFF-\uFFFF]", "").replaceAll("\"", "'")
                    .replaceAll("\n", "").trim();
            Log.d("Erro Read JSON!", "JSON from response: " + jsonText);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean validateEmail(CharSequence emailTovalidate) {
        if (emailTovalidate == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailTovalidate).matches();
        }
    }

    public static void CopyStream(InputStream is, OutputStream os)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
                int count=is.read(bytes, 0, buffer_size);
                if(count==-1)
                    break;
                os.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }
}
