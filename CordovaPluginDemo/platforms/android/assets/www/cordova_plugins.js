cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/cordova-plugin-whitelist/whitelist.js",
        "id": "cordova-plugin-whitelist.whitelist",
        "pluginId": "cordova-plugin-whitelist",
        "runs": true
    },
    {
        "file": "plugins/com.maxrocky.plugins.speaknative/www/SpeakNative.js",
        "id": "com.maxrocky.plugins.speaknative.speaknative",
        "pluginId": "com.maxrocky.plugins.speaknative",
        "clobbers": [
            "SpeakNative"
        ]
    },
    {
        "file": "plugins/cordova-plugin-actionsheet/www/ActionSheet.js",
        "id": "cordova-plugin-actionsheet.ActionSheet",
        "pluginId": "cordova-plugin-actionsheet",
        "clobbers": [
            "window.plugins.actionsheet"
        ]
    },
    {
        "file": "plugins/cordova-plugin-camera/www/CameraConstants.js",
        "id": "cordova-plugin-camera.Camera",
        "pluginId": "cordova-plugin-camera",
        "clobbers": [
            "Camera"
        ]
    },
    {
        "file": "plugins/cordova-plugin-camera/www/CameraPopoverOptions.js",
        "id": "cordova-plugin-camera.CameraPopoverOptions",
        "pluginId": "cordova-plugin-camera",
        "clobbers": [
            "CameraPopoverOptions"
        ]
    },
    {
        "file": "plugins/cordova-plugin-camera/www/Camera.js",
        "id": "cordova-plugin-camera.camera",
        "pluginId": "cordova-plugin-camera",
        "clobbers": [
            "navigator.camera"
        ]
    },
    {
        "file": "plugins/cordova-plugin-camera/www/CameraPopoverHandle.js",
        "id": "cordova-plugin-camera.CameraPopoverHandle",
        "pluginId": "cordova-plugin-camera",
        "clobbers": [
            "CameraPopoverHandle"
        ]
    },
    {
        "file": "plugins/maxrocky-plugins-alipay/www/alipay.js",
        "id": "maxrocky-plugins-alipay.alipay",
        "pluginId": "maxrocky-plugins-alipay",
        "clobbers": [
            "AliPay"
        ]
    },
    {
        "file": "plugins/maxrocky-plugins-base64Image/www/MRBase64Image.js",
        "id": "maxrocky-plugins-base64Image.MRBase64Image",
        "pluginId": "maxrocky-plugins-base64Image",
        "clobbers": [
            "MRBase64Image"
        ]
    },
    {
        "file": "plugins/maxrocky-plugins-clipboard/www/MRClipboard.js",
        "id": "maxrocky-plugins-clipboard.MRClipboard",
        "pluginId": "maxrocky-plugins-clipboard",
        "clobbers": [
            "MRClipboard"
        ]
    },
    {
        "file": "plugins/maxrocky-plugins-intent2web/www/intent2web.js",
        "id": "maxrocky-plugins-intent2web.intent2web",
        "pluginId": "maxrocky-plugins-intent2web",
        "clobbers": [
            "Intent2Web"
        ]
    },
    {
        "file": "plugins/maxrocky-plugins-deviceId/www/MRDeviceId.js",
        "id": "maxrocky-plugins-deviceId.MRDeviceId",
        "pluginId": "maxrocky-plugins-deviceId",
        "clobbers": [
            "MRDeviceId"
        ]
    },
    {
        "file": "plugins/maxrocky-plugins-updateversion/www/MRUpdateVersion.js",
        "id": "maxrocky-plugins-updateversion.MRUpdateVersion",
        "pluginId": "maxrocky-plugins-updateversion",
        "clobbers": [
            "MRUpdateVersion"
        ]
    },
    {
        "file": "plugins/cordova-plugin-app-version/www/AppVersionPlugin.js",
        "id": "cordova-plugin-app-version.AppVersionPlugin",
        "pluginId": "cordova-plugin-app-version",
        "clobbers": [
            "cordova.getAppVersion"
        ]
    },
    {
        "file": "plugins/phonegap-plugin-barcodescanner/www/barcodescanner.js",
        "id": "phonegap-plugin-barcodescanner.BarcodeScanner",
        "pluginId": "phonegap-plugin-barcodescanner",
        "clobbers": [
            "cordova.plugins.barcodeScanner"
        ]
    },
    {
        "file": "plugins/cordova-plugin-image-picker/www/imagepicker.js",
        "id": "cordova-plugin-image-picker.ImagePicker",
        "pluginId": "cordova-plugin-image-picker",
        "clobbers": [
            "plugins.imagePicker"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.2.1",
    "com.maxrocky.plugins.speaknative": "0.0.1",
    "cordova-plugin-actionsheet": "2.2.2",
    "cordova-plugin-camera": "2.1.0",
    "maxrocky-plugins-alipay": "0.0.1",
    "maxrocky-plugins-base64Image": "0.0.1",
    "maxrocky-plugins-clipboard": "1.0.0",
    "maxrocky-plugins-intent2web": "0.0.1",
    "maxrocky-plugins-deviceId": "0.0.1",
    "maxrocky-plugins-updateversion": "1.0.0",
    "cordova-plugin-app-version": "0.1.8",
    "cordova-plugin-compat": "1.0.0",
    "phonegap-plugin-barcodescanner": "6.0.1",
    "cordova-plugin-image-picker": "1.1.1"
}
// BOTTOM OF METADATA
});