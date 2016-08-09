var cordova = require('cordova');

var MRDeviceId = function() {};
MRDeviceId.prototype.getDeviceId = function(success, error) {
	cordova.exec(success, error, 'MRDeviceId', 'getDeviceId', []);
}

var MRDeviceId = new MRDeviceId();
module.exports = MRDeviceId;
