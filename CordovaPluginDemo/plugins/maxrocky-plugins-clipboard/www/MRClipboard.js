var cordova = require('cordova');

var MRClipboard = function() {};
MRClipboard.prototype.clipboard = function(success, error, parmars) {
    cordova.exec(success, error, 'MRClipboard', 'clipboard', parmars);
}

var MRClipboard = new MRClipboard();
module.exports = MRClipboard;