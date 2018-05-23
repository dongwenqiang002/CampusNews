//app.js
App({
  onLaunch: function () {
    console.log('App Launch')
    //调用API从本地缓存中获取数据
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

  },
  userLogin: function () {
    var that = this;
    wx.login({
      success: function (logRes) {
        //获取code
        if (logRes.code) {
          console.log(logRes.code);
          wx.getUserInfo({
            withCredentials: true,
            success: function (res) {
              console.log({ encryptedData: res.encryptedData, iv: res.iv, code: logRes.code })
              wx.request({
                url: 'http://172.17.0.6:8080/login/wxlogin',
                data: {
                  code: logRes.code, encryptedData: res.encryptedData, iv: res.iv
                },
                success: function (res) {
                  that.globalData.userInfo = res.data;  // .userInfo;
                  typeof cb == "function" && cb(that.globalData.userInfo)
                }
              })
            },
            fail: function(ee){
              console.log(ee);
              console.log("未授权登录")
              wx.redirectTo({
                url: "/pages/reg/authorized",
              })
            }
          })
        }
      }
    })
  },

  getUserInfo: function (cb) {
    var that = this
    if (this.globalData.userInfo) {
      typeof cb == "function" && cb(this.globalData.userInfo)
    } else {
      //调用登录接口
     that.userLogin();
    }
  },
  onShow: function () {
    console.log('App Show')
  },
  onHide: function () {
    console.log('App Hide')
  },
  globalData: {
    userInfo: null
  }
})