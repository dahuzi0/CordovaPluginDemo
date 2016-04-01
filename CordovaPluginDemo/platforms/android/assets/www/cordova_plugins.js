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
        "file": "plugins/com.synconset.imagepicker/www/imagepicker.js",
        "id": "com.synconset.imagepicker.ImagePicker",
        "pluginId": "com.synconset.imagepicker",
        "clobbers": [
            "plugins.imagePicker"
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
        "file": "plugins/phonegap-plugin-barcodescanner/www/barcodescanner.js",
        "id": "phonegap-plugin-barcodescanner.BarcodeScanner",
        "pluginId": "phonegap-plugin-barcodescanner",
        "clobbers": [
            "cordova.plugins.barcodeScanner"
        ]
    },
    {
        "file": "plugins/maxrocky-plugins-alipay/www/alipay.js",
        "id": "maxrocky-plugins-alipay.alipay",
        "pluginId": "maxrocky-plugins-alipay",
        "clobbers": [
            "AliPay"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.2.1",
    "com.maxrocky.plugins.speaknative": "0.0.1",
    "com.synconset.imagepicker": "1.0.7",
    "cordova-plugin-actionsheet": "2.2.2",
    "cordova-plugin-camera": "2.1.0",
    "phonegap-plugin-barcodescanner": "4.1.0",
    "maxrocky-plugins-alipay": "0.0.1"
}
// BOTTOM OF METADATA
});