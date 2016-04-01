cordova.define("maxrocky-plugins-alipay.alipay", function(require, exports, module) { var cordova = require('cordova');

var AliPay = function() {};
AliPay.prototype.pay = function(success,error) {
	cordova.exec(success, error, 'AliPay', 'start_to_alipay', []);
}

var alipay = new AliPay();
module.exports = alipay;
});
