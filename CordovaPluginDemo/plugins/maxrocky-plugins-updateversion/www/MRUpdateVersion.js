var cordova = require('cordova');

var MRUpdateVersion = function() {};
MRUpdateVersion.prototype.updateVersion = function(success, error, parmars) {
    cordova.exec(success, error, 'MRUpdateVersion', 'updateVersion', parmars);
}

var MRUpdateVersion = new MRUpdateVersion();
module.exports = MRUpdateVersion;