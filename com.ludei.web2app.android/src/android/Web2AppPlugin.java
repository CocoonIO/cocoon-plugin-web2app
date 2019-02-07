package com.ludei.web2app.android;
import org.apache.cordova.CordovaPlugin;


public class Web2AppPlugin extends CordovaPlugin {

    @Override
    protected void pluginInitialize() {

    }

    @Override
    public Object onMessage(String id, Object data) {
        if (id.equalsIgnoreCase("onPageFinished")) {
            cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    String script = "(function(){if (window.cordova) return; var script = document.createElement(\"script\"); script.src = \"cordova.js\"; document.head.appendChild(script);})();";
                    Web2AppPlugin.this.webView.getEngine().loadUrl("javascript:" + script, false);
                }
            });
        }

        return null;
    }


    public Boolean shouldAllowRequest(java.lang.String url) {
        return true;
    }

    public Boolean shouldAllowNavigation(java.lang.String url) {
        return true;
    }

    public Boolean shouldAllowBridgeAccess(java.lang.String url) {
        return true;
    }

    public Boolean shouldOpenExternalUrl(java.lang.String url) {
        return true;
    }
}