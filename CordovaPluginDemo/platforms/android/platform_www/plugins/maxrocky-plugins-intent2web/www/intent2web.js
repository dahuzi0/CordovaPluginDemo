cordova.define("maxrocky-plugins-intent2web.intent2web", function(require, exports, module) { var cordova = require('cordova');

var Intent2Web = function() {};
Intent2Web.prototype.intent2web = function(title, url, success, error) {
	cordova.exec(success, error, 'Intent2Web', 'go_to_webactivity', [title, url]);
}

var intent2web = new Intent2Web();
module.exports = intent2web;
});
