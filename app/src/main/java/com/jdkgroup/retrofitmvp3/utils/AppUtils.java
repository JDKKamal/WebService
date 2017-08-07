package com.jdkgroup.retrofitmvp3.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.AppCompatEditText;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jdkgroup.webservice.R;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AppUtils {
    private static String TAG = "data";
    private static int screenWidth = 0;
    public static String getText(TextView textView) {
        return textView.getText().toString().trim();
    }
    public static String getText(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static boolean isEmplty(EditText editText) {
        if (getText(editText).equalsIgnoreCase("")) {
            return true;
        }
        return false;
    }

    public static void showToast(Context context, String message) {
        //  Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if (v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

    public static void showToastById(Context context, int id) {
        //  Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        Toast toast = Toast.makeText(context, getStringFromId(context, id), Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if (v != null) v.setGravity(Gravity.CENTER);
        toast.show();

    }

    public static String getStringFromId(Context mContext, int id) {
        String str = null;
        try {
            str = mContext.getString(id);
        } catch (Exception e) {
        }
        return str;
    }

    public static void showToastValidation(Context context, int message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showValidationToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void log(String text) {
        Log.d(TAG, text);
    }

    public static void loge(String text) {
        Log.e(TAG, text);
    }

    public static void logd(String tag, String s) {

    }

    public static android.support.v7.app.AlertDialog createAlertDialog(Activity activity, String message, String positiveText, String negativeText, DialogInterface.OnClickListener mDialogClick) {
        android.support.v7.app.AlertDialog.Builder builder =
                new android.support.v7.app.AlertDialog.Builder(activity).setPositiveButton(positiveText, mDialogClick)
                        .setNegativeButton(negativeText, mDialogClick)
                        .setMessage(message);
        return builder.create();
    }

    public static boolean hasInternet(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (!(networkInfo != null && networkInfo.isConnectedOrConnecting())) {
            showToast(context, context.getString(R.string.no_internet_message));
            return false;
        }
        return true;
    }

  /*  public static boolean hasInternetConnection(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }*/

    public static boolean hasInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Network[] networks = connectivityManager.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network mNetwork : networks) {
                networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                    return true;
                }
            }
        } else {
            if (connectivityManager != null) {
                NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo anInfo : info) {
                        if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void hideKeyboard(Activity ctx) {
        try {
            ctx.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(ctx.getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
            imm.hideSoftInputFromWindow(ctx.getWindow().getDecorView().getApplicationWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
            imm.hideSoftInputFromWindow(ctx.getWindow().getDecorView().getWindowToken(), 0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void showKeyboard(Activity activity, AppCompatEditText view) {
        Context context = activity;
        try {
            if (context != null) {
                InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        } catch (Exception e) {
            Log.e("Exception on  show", e.toString());
        }
    }

    public static void requestEdittextFocus(Activity activity, AppCompatEditText view) {
        view.requestFocus();
        showKeyboard(activity, view);
    }

    public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }


//    public static FileUri createImageFile(String prefix) {
//        FileUri fileUri = new FileUri();
//
//        File image = null;
//        try {
//            image = File.createTempFile(prefix + String.valueOf(System.currentTimeMillis()), ".jpg", getWorkingDirectory());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (image != null) {
//            fileUri.setFile(image);
//            fileUri.setImageUrl(Uri.parse("file:" + image.getAbsolutePath()));
//        }
//        return fileUri;
//    }

    private static File getWorkingDirectory() {
        File directory = new File(Environment.getExternalStorageDirectory(), "Mowadcom");
        return createDirectory(directory);
    }

    private static File createDirectory(File file) {
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }

    public static String setImage(String imageBaseUrl) {
        // return RestConstant.IMAGE_BASE_URL.concat(imageBaseUrl);
        return null;
    }


   /* public static RequestBody getRequestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }
    public static String setImage(String url)
    {
        return RestConstant.IMAGE_URL+url;
    }*/


    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }
        return screenWidth;
    }


    public static void startActivity(Context context, Class className) {
        Intent intent = new Intent(context, className);
        context.startActivity(intent);
    }

    public  static SpannableString timestampToDate(String strTimestamp){
        try{
            long timestamp = Long.parseLong(strTimestamp) * 1000L;
            DateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm aaa");
            Date netDate = (new Date(timestamp));
            String suffix = getDayOfMonthSuffix(netDate.getDate());

            String[] str = sdf.format(netDate).split(" ");
            String strDate="";
            for(int i=0;i<str.length;i++){
                if(i==0)
                    strDate = str[i] + suffix + " ";
                else if(i == str.length -1)
                    strDate = strDate + str[i];
                else
                    strDate = strDate + str[i] +" ";
            }

            SpannableString styledString = new SpannableString(strDate);
            styledString.setSpan(new SuperscriptSpan(),2,4,0);
            styledString.setSpan(new RelativeSizeSpan(0.7f),2,4,0);
            styledString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, strDate.length(), 0);
            return styledString;
        }
        catch(Exception ex){
            return new SpannableString("");
        }
    }

    static String getDayOfMonthSuffix(final int n) {

        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }

    public static String getDateFromTime(long mTimestamp, String mDateFormate) {
        String date;
        SimpleDateFormat dateFormatter = new SimpleDateFormat(mDateFormate);
        dateFormatter.setTimeZone(TimeZone.getDefault());

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        cal.setTimeInMillis(mTimestamp);

        date = dateFormatter.format(cal.getTime());
        return date;
    }

    public static String getDateString(long miliis, String mDateFormate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.setTimeInMillis(miliis);
        SimpleDateFormat dateFormatter = new SimpleDateFormat(mDateFormate, Locale.ENGLISH);
        return dateFormatter.format(calendar.getTime());
    }

    public static long getUtC(long millis) {
        return millis + TimeZone.getDefault().getRawOffset() + TimeZone.getDefault().getDSTSavings();
    }

    public static long getGMT(long millis) {
        return millis - TimeZone.getDefault().getRawOffset() - TimeZone.getDefault().getDSTSavings();
    }

    public static String getDate(long timeStamp) {

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
            Date netDate = (new Date(timeStamp * 1000));
            return sdf.format(netDate);
        } catch (Exception ex) {
            return "xx";
        }
    }

    public static String getFormatedNumber(String val)
    {
        double d=0;
        try{
            d = Double.parseDouble(val);
        }
        catch (Exception e){
            d =0.00;
        }

       // return String.format("%.2f",NumberFormat.getNumberInstance(Locale.UK).format(d));

       return NumberFormat.getNumberInstance(Locale.UK).format(d) +"";
    }

//    public static String stringToSHA1(String text) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            byte[] sha1hash = new byte[40];
//            md.update(text.getBytes("iso-8859-1"), 0, text.length());
//            sha1hash = md.digest();
//            return convertToHex(sha1hash);
//        } catch (Exception e) {
//
//        }
//        return "";
//    }

    public static String removeAllWhiteSpace(String value) {
        return value.replaceAll("\\s+", "");
    }

    /* VALIDATION */
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isValidationRegularExpression(final String regularexpression, final String value) {
        return value.matches(regularexpression);
    }
}
