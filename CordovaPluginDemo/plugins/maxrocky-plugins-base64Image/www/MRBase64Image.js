
  var exec = require('cordova/exec'),
  cordova = require('cordova');
               
  function convertToDataStream() {
    this.convertToDataStream = function(successCallback,errorCallback,content) {
        exec(successCallback,errorCallback,"MRBase64Image","addressConvertedToDataStream",[content]);
    };
}
module.exports = new convertToDataStream();
               

