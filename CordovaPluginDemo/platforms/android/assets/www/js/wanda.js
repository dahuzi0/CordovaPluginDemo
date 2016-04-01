angular.module('wandaClient.app', [
		'ionic',
		'ngCordova'

	])
	.controller('CordovaDemoController', ['$scope', '$cordovaBarcodeScanner', '$cordovaCamera', '$cordovaImagePicker', function($scope, $cordovaBarcodeScanner, $cordovaCamera, $cordovaImagePicker) {

		//讯飞语音插件调用
		$scope.xunFei = function() {
				SpeakNative.startrecognize(function(result) {
					alert(result);
				}, function(error) {
					alert("错误了");
				});
			}
			//二维码插件调用
		$scope.erWeiMa = function() {
				$cordovaBarcodeScanner
					.scan()
					.then(function(barcodeData) {
						alert(barcodeData.text);
					}, function(error) {
						// An error occurred
						// alert(error);
					});
			}
			//相机调用
		$scope.takePhone = function() {
				var options1 = {
					quality: 80,
					destinationType: Camera.DestinationType.DATA_URL,
					sourceType: Camera.PictureSourceType.CAMERA,
					allowEdit: false,
					encodingType: Camera.EncodingType.JPEG,
					//targetWidth: 100,
					//targetHeight: 100,
					//popoverOptions: CameraPopoverOptions,
					saveToPhotoAlbum: true
					//correctOrientation: true
				};

				$cordovaCamera.getPicture(options1).then(function(imageData) {
					var image = document.getElementById('immg');
					image.src = "data:image/jpeg;base64," + imageData;
					//image.ng-src="data:image/jpeg;base64," + imageData;
					//alert(imageData);
				}, function(err) {
					// error
				});


			}
			//图库调用
		$scope.pic = function() {
			var options = {
				maximumImagesCount: 1,
				width: 800,
				height: 800,
				quality: 80
			};

			$cordovaImagePicker.getPictures(options)
				.then(function(results) {
					for (var i = 0; i < results.length; i++) {
						console.log('Image URI: ' + results[i]);
						var image = document.getElementById('immg');
						image.src =results[i];
					}
				}, function(error) {
					// error getting photos
				});
		}

		//ActionSheet
		var callback = function(buttonIndex) {
			setTimeout(function() {
				
				// like other Cordova plugins (prompt, confirm) the buttonIndex is 1-based (first button is index 1)
				switch (buttonIndex) {
					case 1:
						$scope.xunFei();
						break;
					case 2:
						$scope.takePhone();
						break;
					case 3:
						$scope.erWeiMa();
						break;
					case 4:
						$scope.pic();
						break;
				}
			});
		};

		$scope.testShareSheet = function() {
			var options = {
				'androidTheme': window.plugins.actionsheet.ANDROID_THEMES.THEME_TRADITIONAL,
				//'title': 'What do you want with this image?',
				'buttonLabels': ['语音', '相机','二维码',  '图库'],
				'addCancelButtonWithLabel': '取消',
				'androidEnableCancelButton': true,
				'winphoneEnableCancelButton': true
					//'addDestructiveButtonWithLabel' : 'Delete it'
			};
			window.plugins.actionsheet.show(options, callback);
		}

		function testDeleteSheet() {
			var options = {
				'addCancelButtonWithLabel': 'Cancel',
				'addDestructiveButtonWithLabel': 'Delete note'
			};
			window.plugins.actionsheet.show(options, callback);
		}

		function testLogoutSheet() {
			var options = {
				'buttonLabels': ['Log out'],
				'androidEnableCancelButton': true,
				'winphoneEnableCancelButton': true,
				'addCancelButtonWithLabel': 'Cancel'
			};
			window.plugins.actionsheet.show(options, callback);
		}
		
		
		$scope.zhiFu=function(){
			AliPay.pay(false,false);
		}
		//微信支付
		$scope.weiXinzhiFu=function(){
			
		}




	}])