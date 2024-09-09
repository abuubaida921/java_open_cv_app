package com.abuubaida921.java_open_cv_app;

import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {

    // Replace with the actual path to your image file
    private static final String IMAGE_FILE_PATH = "/path/to/your/image.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (OpenCVLoader.initLocal()) {
            Log.i("OpenCV", "OpenCV successfully loaded.");
        }

        // An example: Resize an image using OpenCV
        Bitmap bitmap = BitmapFactory.decodeFile(IMAGE_FILE_PATH);  // load the image

        if (bitmap != null) {
            Bitmap resizedBitmap = getResizedBitmapCV(bitmap, 400, 600);  // image resized using OpenCV
            // Other code goes here ...
        }
    }

    private Bitmap getResizedBitmapCV(Bitmap inputBitmap, int newWidth, int newHeight) {
        // Convert the input Bitmap to a Mat
        Mat inputMat = new Mat();
        Utils.bitmapToMat(inputBitmap, inputMat);

        // Create a new Mat for the resized image
        Mat resizedMat = new Mat();
        Imgproc.resize(inputMat, resizedMat, new Size(newWidth, newHeight));

        // Convert the resized Mat back to a Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(resizedMat, resizedBitmap);

        inputMat.release();
        resizedMat.release();

        return resizedBitmap;
    }
}