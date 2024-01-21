package com.example.urbanwheel;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.security.auth.callback.Callback;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    TextView front_photo_input, back_photo_input, left_photo_input, right_photo_input, fitness_photo_input, meter_photo_input, document_photo_input, rc_photo_input, insurance_photo_input, pollution_photo_input, permit_photo_input, bank_photo_input;
    EditText owner_nm_input, owner_no_input, owner_alt_no_input, owner_email_input;
    TextView owner_email_txt;
    Button front_photo_button;
    Button back_photo_button;
    Button left_photo_button;
    Button right_photo_button;
    Button fitness_photo_button;
    Button meter_photo_button;
    Button document_photo_button;
    Button rc_photo_button;
    Button insurance_photo_button;
    Button pollution_photo_button;
    Button bank_photo_button;
    Button permit_photo_button;
    Button details_submit_button;
    private static final int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_REQUEST = 1;
    private Uri filepath;
    private String currentPhotoField;
    private Bitmap bitmap;
    TextView tv;
    public static final String UPLOAD_URL = "http://localhost/android/index.php?add=333";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //https://www.youtube.com/watch?v=odmC3aa210Q
        //https://www.simplifiedcoding.net/android-upload-image-to-server/

//        requestStoragePermission();

        front_photo_input = findViewById(R.id.front_photo_input);
        back_photo_input = findViewById(R.id.back_photo_input);
        left_photo_input = findViewById(R.id.left_photo_input);
        right_photo_input = findViewById(R.id.right_photo_input);
        fitness_photo_input = findViewById(R.id.fitness_photo_input);
        meter_photo_input = findViewById(R.id.meter_photo_input);
        document_photo_input = findViewById(R.id.document_photo_input);
        rc_photo_input = findViewById(R.id.rc_photo_input);
        insurance_photo_input = findViewById(R.id.insurance_photo_input);
        pollution_photo_input = findViewById(R.id.pollution_photo_input);
        permit_photo_input = findViewById(R.id.permit_photo_input);
        bank_photo_input = findViewById(R.id.bank_photo_input);

        owner_email_txt = findViewById(R.id.owner_email_txt);

        front_photo_button = findViewById(R.id.front_photo_button);
        back_photo_button = findViewById(R.id.back_photo_button);
        left_photo_button = findViewById(R.id.left_photo_button);
        right_photo_button = findViewById(R.id.right_photo_button);
        fitness_photo_button = findViewById(R.id.fitness_photo_button);
        meter_photo_button = findViewById(R.id.meter_photo_button);
        document_photo_button = findViewById(R.id.document_photo_button);
        rc_photo_button = findViewById(R.id.rc_photo_button);
        insurance_photo_button = findViewById(R.id.insurance_photo_button);
        pollution_photo_button = findViewById(R.id.pollution_photo_button);
        bank_photo_button = findViewById(R.id.bank_photo_button);
        permit_photo_button = findViewById(R.id.permit_photo_button);
        details_submit_button = findViewById(R.id.details_submit_button);


        front_photo_button.setOnClickListener(v -> {
            currentPhotoField = "front";
            ShowFileChooser();
        });

        back_photo_button.setOnClickListener(v -> {
            currentPhotoField = "back";
            ShowFileChooser();
        });
        left_photo_button.setOnClickListener(v -> {
            currentPhotoField = "left";
            ShowFileChooser();
        });

        right_photo_button.setOnClickListener(v -> {
            currentPhotoField = "right";
            ShowFileChooser();
        });
        fitness_photo_button.setOnClickListener(v -> {
            currentPhotoField = "fitness";
            ShowFileChooser();
        });

        meter_photo_button.setOnClickListener(v -> {
            currentPhotoField = "meter";
            ShowFileChooser();
        });
        document_photo_button.setOnClickListener(v -> {
            currentPhotoField = "document";
            ShowFileChooser();
        });

        rc_photo_button.setOnClickListener(v -> {
            currentPhotoField = "rc";
            ShowFileChooser();
        });
        insurance_photo_button.setOnClickListener(v -> {
            currentPhotoField = "insurance";
            ShowFileChooser();
        });

        pollution_photo_button.setOnClickListener(v -> {
            currentPhotoField = "pollution";
            ShowFileChooser();
        });
        bank_photo_button.setOnClickListener(v -> {
            currentPhotoField = "bank";
            ShowFileChooser();
        });

        permit_photo_button.setOnClickListener(v -> {
            currentPhotoField = "permit";
            ShowFileChooser();
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void ShowFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //noinspection deprecation
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {

            filepath = data.getData();

            switch (currentPhotoField) {
                case "front":
                    front_photo_input.setText(filepath.toString());
                    break;
                case "back":
                    back_photo_input.setText(filepath.toString());
                    break;
                case "left":
                    left_photo_input.setText(filepath.toString());
                    break;
                case "right":
                    right_photo_input.setText(filepath.toString());
                    break;
                case "fitness":
                    fitness_photo_input.setText(filepath.toString());
                    break;
                case "meter":
                    meter_photo_input.setText(filepath.toString());
                    break;
                case "document":
                    document_photo_input.setText(filepath.toString());
                    break;
                case "rc":
                    rc_photo_input.setText(filepath.toString());
                    break;
                case "insurance":
                    insurance_photo_input.setText(filepath.toString());
                    break;
                case "pollution":
                    pollution_photo_input.setText(filepath.toString());
                    break;
                case "bank":
                    bank_photo_input.setText(filepath.toString());
                    break;
                case "permit":
                    permit_photo_input.setText(filepath.toString());
                    break;

            }
        }
    }

    private String getPath(Uri uri) {

        @SuppressLint("Recycle") Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + "=?", new String[]{document_id}, null
        );
        cursor.moveToFirst();
        @SuppressLint("Range") String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }
    private void uploadData() {
        String name = owner_nm_input.getText().toString();
        String mb_no = owner_no_input.getText().toString().trim();
        String alt_mb_no = owner_alt_no_input.getText().toString().trim();
        String email = owner_email_input.getText().toString().trim();

        if (filepath == null || name.isEmpty() || mb_no.isEmpty() || alt_mb_no.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill in all the fields and select an image", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    // Here, you can send the image and owner details to the server using OkHttp library.
    String uploadUrl = "Your_Server_URL_here"; // Replace with your actual server URL

    // Create OkHttp client
    OkHttpClient client = new OkHttpClient();

    // Create the request body with the image and other form data
    MediaType mediaType = MediaType.parse("image/*");
    String name = owner_nm_input.getText().toString();
    String mb_no = owner_no_input.getText().toString().trim();
    RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("name", name)
            .addFormDataPart("mb_no", mb_no)
            .addFormDataPart("image", UUID.randomUUID().toString(),
                    RequestBody.create(mediaType, new File(getPath(filepath))))
            .build();

    // Create the POST request
    Request request = new Request.Builder()
            .url(uploadUrl)
            .post(requestBody)
            .build();


    // Get the actual file path from the URI
//    @SuppressLint("Recycle")
//    private String getPath(Uri uri) {
//        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        String document_id = cursor.getString(0);
//        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
//        cursor = getContentResolver().query(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null,
//                MediaStore.Images.Media._ID + "=?", new String[]{document_id}, null
//        );
//        cursor.moveToFirst();
//        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//        cursor.close();
//        return path;
//    }



}