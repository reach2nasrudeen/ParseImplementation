package com.nasrudeen.parseimplementation;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;
    private String strValue;
    RelativeTimeTextView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        v = (RelativeTimeTextView)findViewById(R.id.timestamp); //Or just use Butterknife!

        textView = (TextView) findViewById(R.id.test);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("AppID")
                .clientKey("key")
                .server("url").build()
        );
//        ParseObject testObject = new ParseObject("TestObject");
//        testObject.put("foo", "bar");
//        testObject.saveInBackground();

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("NewsAndOffers");
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");
//        Log.i("Came -->","Came");
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> postList, ParseException e) {
//                Log.i("Test","Tesxt");
//                if (e == null) {
//                    // If there are results, update the list of posts
//                    // and notify the adapter
//                    strValue = postList.get(0).getString("foo");
//                    Log.i("String --->", strValue);
////                    textView.setText((CharSequence) postList);
////                    for (ParseObject post : postList) {
////                        Log.i("String --->", String.valueOf(postList));
////                    }
//                } else {
//                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
//                }
//            }
//        });


        ParseQuery query = new ParseQuery("NewsAndOffers");

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    for (ParseObject dealsObject : objects) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
//                        Toast.makeText(getApplicationContext(),"Object -->"+dealsObject.getString("poster"),Toast.LENGTH_SHORT).show();
                        textView.append("\n\n\n"+dealsObject.getString("poster")
                                +"\n"+dealsObject.getString("message")
                                +"\nOffer : "+dealsObject.getBoolean("isOffer")
                                +"\nImageName : "+dealsObject.getParseFile("messageImage")
                                +"\nPosterThumbnail : "+dealsObject.getParseFile("posterThumbnailImage")
                                +"\nDate : "+dealsObject.getCreatedAt());
//                        v.setReferenceTime(new Date().getTime());
//                        v.setReferenceTime(Long.parseLong(String.valueOf(dealsObject.getCreatedAt())));
//                        Toast.makeText(getApplicationContext(),"Date --> "+dealsObject.getCreatedAt(),Toast.LENGTH_SHORT).show();
                        ParseFile postImage = dealsObject.getParseFile("messageImage");
                        String imageUrl = postImage.getUrl() ;//live url
//                        Toast.makeText(getApplicationContext(),"URL --> "+imageUrl,Toast.LENGTH_SHORT).show();
                        Uri imageUri = Uri.parse(imageUrl);
                        Picasso.with(getApplicationContext()).load(imageUri.toString()).into(imageView);

//                        ParseFile image = (ParseFile) dealsObject.getParseFile("messageImage");
//                        //call the function
//                        displayImage(image, imageView);
//                        imageView.setImageDrawable(Drawable.createFromPath(String.valueOf(dealsObject.getParseFile("messageImage"))));
//                        Ion.with(imageView)
//                                .fitXY()
//                                .load(dealsObject.getParseFile("messageImage"));
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
