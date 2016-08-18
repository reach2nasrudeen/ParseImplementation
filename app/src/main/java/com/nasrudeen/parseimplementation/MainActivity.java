package com.nasrudeen.parseimplementation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView textView,timestamp;
    private ImageView imageView;
    private String strValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        timestamp = (TextView) findViewById(R.id.timestamp);
        textView = (TextView) findViewById(R.id.test);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(Constants.PARSE_APP_ID)
                .clientKey(Constants.PARSE_APP_CLIENT_KEY)
                .server(Constants.PARSE_APP_SERVER_URL).build()
        );
//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();

        ParseQuery query = new ParseQuery("NewsAndOffers");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject dealsObject : objects) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        textView.append("\n\n\n"+dealsObject.getString("poster")
                                +"\n"+dealsObject.getString("message")
                                +"\nOffer : "+dealsObject.getBoolean("isOffer")
                                +"\nImageName : "+dealsObject.getParseFile("messageImage")
                                +"\nPosterThumbnail : "+dealsObject.getParseFile("posterThumbnailImage")
                                +"\nDate CreatedAt : "+dealsObject.getCreatedAt()
                                +"\nDate UpdatedAt : "+dealsObject.getUpdatedAt());
//                                +"\nDate Str CreatedAt : "+dealsObject.getDate("createdAt")
//                                +"\nDate Str UpdatedAt : "+getTimeAgo.getTimeAgo());
                        SimpleDateFormat sdf3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

                        Date d1 = null;
                        try {
//                            d1 = sdf3.parse(String.valueOf(dealsObject.getCreatedAt()));
                            d1 = sdf3.parse("Wed Aug 18 12:59:23 GMT+05:30 2016");
                        } catch (java.text.ParseException e1) {
                            e1.printStackTrace();
                        }
//                        textView.append("\nDate Format : "+getTimeAgo.getTimeAgo(d1,getApplicationContext()));
//                        textView.append("\nDate Format : "+getTimeAgo.getTimeAgo(new Date(),getApplicationContext()));
                        textView.append("\nDate Format : "+getTimeAgo.getTimeAgo(d1,getApplicationContext()));
                        final Handler handler = new Handler();
                        final Date finalD = d1;
                        final Runnable r = new Runnable() {
                            public void run() {
                                timestamp.setText("\nDate Format : "+getTimeAgo.getTimeAgo(finalD,getApplicationContext()));
                                Log.i("Text Date -->","\nDate Format : "+getTimeAgo.getTimeAgo(finalD,getApplicationContext()));
                                handler.postDelayed(this, 10000);
                            }
                        };

                        handler.postDelayed(r, 1000);
////                        String dateStr = String.valueOf(dealsObject.getCreatedAt());
//                        DateFormat readFormat = new SimpleDateFormat( "EEE MMM dd yyyy hh:mm aaa");
//
//                        DateFormat writeFormat = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
//                        Date date = null;
//                        try {
//                            date = readFormat.parse(dateStr);
//                        } catch (java.text.ParseException e1) {
//                            e1.printStackTrace();
//                        }
//
//                        String formattedDate = "";
//                        if( date != null ) {
//                            formattedDate = writeFormat.format( date );
//                        }
//                        textView.append("\nDate Format : "+formattedDate);
//                        System.out.println(formattedDate);
//                        try {
//                            DateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
//                            Date d = f.parse(String.valueOf(dealsObject.getCreatedAt()));
//                            DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
//                            DateFormat time = new SimpleDateFormat("hh:mm:ss a");
//                            System.out.println("Date: " + date.format(d));
//                            System.out.println("Time: " + time.format(d));
//                            textView.append("\nDate UpdatedAt : "+date.format(d));
//                            textView.append("\nDate UpdatedAt : "+time.format(d));
//                        } catch (java.text.ParseException e1) {
//                            e1.printStackTrace();
//                        }
//                        v.setReferenceTime(new Date().getTime());
//                        v.setReferenceTime(Long.parseLong(String.valueOf(d1.getTime())));
                        ParseFile postImage = dealsObject.getParseFile("messageImage");
                        String imageUrl = postImage.getUrl() ;//live url
                        Uri imageUri = Uri.parse(imageUrl);
                        Picasso.with(getApplicationContext()).load(imageUri.toString()).into(imageView);
                    }
                } else {
                    Log.d("Brand", "Error: " + e.getMessage());
                }
            }
        });

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
//        query.getInBackground("R9iUEz3jp3", new GetCallback<ParseObject>() {
//            public void done(ParseObject object, ParseException e) {
//                if (e == null) {
//                    // object will be your game score
//                    strValue = object.getString("foo");
//                    Log.i("String --->", strValue);
//                } else {
//                    // something went wrong
//                }
//            }
//        });
//        textView.setText(strValue);
    }
//    private void displayImage(ParseFile thumbnail, final ImageView img) {
//
//        if (thumbnail != null) {
//            thumbnail.getDataInBackground(new GetDataCallback() {
//
//                @Override
//                public void done(byte[] data, ParseException e) {
//
//                    if (e == null) {
//                        Bitmap bmp = BitmapFactory.decodeByteArray(data, 0,
//                                data.length);
//
//                        if (bmp != null) {
//
//                            Log.e("parse file ok", " null");
//                             img.setImageBitmap(Bitmap.createScaledBitmap(bmp,
//                                     WindowManager.LayoutParams.WRAP_CONTENT,
//                                     WindowManager.LayoutParams.MATCH_PARENT, false));
////                            img.setImageBitmap(getRoundedCornerBitmap(bmp, 10));
//                             img.setPadding(10, 10, 0, 0);
//                        }
//                    } else {
//                        Log.e("paser after downloade", " null");
//                    }
//                }
//            });
//        } else {
//
//            Log.e("parse file", " null");
//
//            // img.setImageResource(R.drawable.ic_launcher);
//
//            img.setPadding(10, 10, 10, 10);
//        }
//
//    }
}
