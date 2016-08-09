cordova.define("maxrocky-plugins-base64Image.MRBase64Image", function(require, exports, module) { 
  var exec = require('cordova/exec'),
  cordova = require('cordova');
               
  function convertToDataStream() {
    this.convertToDataStream = function(successCallback,errorCallback,content) {
        exec(successCallback,errorCallback,"MRBase64Image","addressConvertedToDataStream",[content]);
    };
}
module.exports = new convertToDataStream();
               


});
