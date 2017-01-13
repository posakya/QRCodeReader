package com.example.roshan.qr_code_scanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.logging.Logger;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity  implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    private TextView txt_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mScannerView = new ZXingScannerView(this);
//        txt_view = (TextView)findViewById(R.id.txt_view);
        // Set the scanner view as the content view
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera on resume
        mScannerView.startCamera();
    }

    @Override
    public void onPause(){
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();

    }



    @Override
    public void handleResult(Result result) {


        Log.v("result", result.getText());
        // Prints the scan format (qrcode, pdf417 etc.)
        Log.v("result", result.getBarcodeFormat().toString());
        //If you would like to resume scanning, call this method below:
        mScannerView.resumeCameraPreview(this);
//        Intent intent = new Intent();
//        intent.putExtra(AppConstants.KEY_QR_CODE, result.getText());
//        setResult(RESULT_OK, intent);
//        finish();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan result");
        builder.setMessage(result.getText());
        builder.show();

//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();

    }
}
