angular.module('wandaClient.app', [
        'ionic',
        'ngCordova',
        'wanda.common.base64Encode'
    ])
    .controller('CordovaDemoController', ['$scope', '$cordovaBarcodeScanner', '$cordovaCamera', '$cordovaImagePicker', '$ionicPopup', '$cordovaAppVersion', '$http', 'Base64EncodeService', function($scope, $cordovaBarcodeScanner, $cordovaCamera, $cordovaImagePicker, $ionicPopup, $cordovaAppVersion, $http, Base64EncodeService) {

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
                maximumImagesCount: 2,
                width: 800,
                height: 800,
                quality: 80
            };

            $cordovaImagePicker.getPictures(options)
                .then(function(results) {
                    for (var i = 0; i < results.length; i++) {
                        console.log('Image URI: ' + results[i]);
                        var image = document.getElementById('immg');
                        image.src = results[i];
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
                'buttonLabels': ['语音', '相机', '二维码', '图库'],
                'addCancelButtonWithLabel': '取消',
                'androidEnableCancelButton': true,
                'winphoneEnableCancelButton': true
                    //'addDestructiveButtonWithLabel' : 'Delete it'
            };
            window.plugins.actionsheet.show(options, callback);
        }

        $scope.zhiFu = function() {
                AliPay.pay(false, false);
            }
            //微信支付
        $scope.weiXinzhiFu = function() {
            alert('无插件');
        }

        //剪切板
        $scope.clipBoard = function() {
            sharlLink = "http://www.baidu.com";
            MRClipboard.clipboard(function success() {}, function failed(message) {}, sharlLink);
        }

        //版本更新+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //版本比较
        var compareVersion = function(version) {

            var deviceInfo = window.navigator.appVersion;
            var getDevice = deviceInfo.substr(5, 6);
            if (getDevice == 'iPhone') {
                var appVersionType = 1;
            } else {
                var appVersionType = 2;
            }
            $http.get('http:/www.baidu.com').then(function(res) { //把服务地址写在此处，获取版本
                // $scope.versionData = res.data;
                // if ($scope.versionData.appVersion && $scope.versionData.appVersion != version) {
                //     fristCommit = true;
                //     showVersionPop('发现新版本是否更新？', '更新', '取消', $scope.versionData.downUrl, 1);
                // }
                showVersionPop('发现新版本是否更新？', '更新', '取消', 'url', 1);
            }, function(err) {
                fristCommit = true;
                showVersionPop('检测失败，请重试', '更新', '取消', '');
            });
        }

        //获取app版本号
        var getAppVersion = function() {
            document.addEventListener("deviceready", function() {
                $cordovaAppVersion.getVersionNumber().then(function(version) {
                    $scope.appVersion = version;
                });
            }, false);
        }
        var fristCommit = true;
        //检测版本
        $scope.updateVersion = function() {
            getAppVersion();
            document.addEventListener("deviceready", function() {
                if (fristCommit) {
                    fristCommit = false;
                    $cordovaAppVersion.getVersionNumber().then(function(version) {
                        compareVersion(version);
                    });
                }
            }, false);
        }

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
        var showVersionPop = function(template, leftText, rightText, downUrl, num) {
            var messagePopup = $ionicPopup.confirm({
                template: template,
                okText: leftText,
                cancelText: rightText
            });
            messagePopup.then(function(res) {
                if (res) {
                    if (num == 1) {
                        console.log(downUrl);
                        MRUpdateVersion.updateVersion(function success() {}, function failed(message) {}, "http://www.baidu.com");
                    }
                }
            })
        }

        /*title为标题，url为链接*/
        $scope.goTo = function(title, url) {
            document.addEventListener("deviceready", onDeviceReady, false);

            function onDeviceReady() {
                Intent2Web.intent2web(title, url, function(result) {
                    alert('success');
                }, function(error) {
                    alert('error');
                });
            };

        }

        $scope.base64Encode = function(str) {
            alert("加密后：" + Base64EncodeService.base64encode(str));
        }

    }])