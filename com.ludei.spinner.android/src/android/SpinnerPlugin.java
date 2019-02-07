package com.ludei.spinner.android;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

import java.io.IOException;

import io.cordova.hellocordova.R;

public class SpinnerPlugin extends CordovaPlugin  {

    private static Dialog spinnerDialog;


    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    @Override
    public void initialize(final CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        spinnerDialog = new Dialog(cordova.getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
        // check to see if the splash screen should be full screen
        if ((cordova.getActivity().getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN) {
            spinnerDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        ImageView img = new ImageView(cordova.getActivity());
        img.setBackgroundResource(R.drawable.spinner);
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        frameAnimation.start();

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER);

        FrameLayout layout = new FrameLayout(cordova.getActivity());
        layout.setLayoutParams(layoutParams);
        layout.setBackgroundColor(Color.TRANSPARENT);

        FrameLayout background = new FrameLayout(cordova.getActivity());
        background.setLayoutParams(layoutParams);
        background.setBackgroundColor(Color.BLACK);
        background.setAlpha(0.75f);

        FrameLayout.LayoutParams spinnerParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        img.setLayoutParams(spinnerParams);

        layout.addView(background);
        layout.addView(img);
        spinnerDialog.setContentView(layout);
        spinnerDialog.setCancelable(false);
    }

    /**
     * Called when a message is sent to plugin.
     *
     * @param id            The message id
     * @param data          The message data
     * @return              Object to stop propagation or null
     */
    @Override
    public Object onMessage(String id, Object data) {
        if (id.equalsIgnoreCase("onPageStarted")) {
            if (spinnerDialog != null) {
                cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        spinnerDialog.show();
                    }
                });
            }

        } else if (id.equalsIgnoreCase("onPageFinished") || id.equalsIgnoreCase("onReceivedError")) {
            if (spinnerDialog != null) {
                cordova.getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        spinnerDialog.dismiss();
                    }
                });
            }
        }

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        spinnerDialog.dismiss();
        spinnerDialog = null;
    }

}