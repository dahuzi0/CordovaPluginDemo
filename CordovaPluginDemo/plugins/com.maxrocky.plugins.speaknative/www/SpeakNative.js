var cordova = require('cordova');

var SpeakNative = function() {};
SpeakNative.prototype.startrecognize = function(success,error) {
	cordova.exec(success, error, 'SpeakNative', 'start_to_speak',[]);
}

var speaknative = new SpeakNative();
module.exports = speaknative;












