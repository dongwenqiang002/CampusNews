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
          wx.request({
            url: that.globalData.services +'/login/wxlogin',
            data: {
              code: logRes.code
            },
            success: function (res) {
              console.log(res.data)
              if(res.data){
                that.globalData.header.Cookie = 'JSESSIONID=' + res.data;
                that.globalData.userInfo = { nickName:'登录成功'};  // .userInfo;
                typeof cb == "function" && cb(that.globalData.userInfo)
              }else{
                wx.redirectTo({
                  url: '/pages/reg/reg',
                })
              }
            },
            fail: function (e) {
              wx.redirectTo({
                url: '/pages/reg/reg',
              
              })
            }
          })
        }
      },
      fail: function(e){
        console.log(e);
      
      }
    })
  },
  WXlogin: function () {
    var that = this;
    wx.login({
      success: function (logRes) {
        //获取code
        if (logRes.code) {
          console.log(logRes.code);
          wx.request({
            url: that.globalData.services + '/login/wxlogin',
            data: {
              code: logRes.code
            },
            success: function (res) {
              console.log(res.data)
              if (res.data) {
                that.globalData.header.Cookie = 'JSESSIONID=' + res.data;
                that.globalData.userInfo = { nickName: '登录成功' };  // .userInfo;
                typeof cb == "function" && cb(that.globalData.userInfo)
              } else {
                wx.redirectTo({
                  url: '/pages/reg/reg',
                })
              }
            },
            fail: function (e) {
              wx.redirectTo({
                url: '/pages/reg/reg',
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
  onError: function (msg) {
    console.log(msg)
  },
  onHide: function () {
    console.log('App Hide')
  },
  globalData: {
    //---------||-----------
    //---------||-----------
    //---------||------------
    //---------\/-------------
    //就是这里 修改服务器的 IP地址           //------//----------------------
                                          //------//-----------------------
    services: 'http://111.230.72.117:8088',   //----<<<<<<=====================-
  //                                     \\-------\\-----------------------
    //services: 'http://127.0.0.1:8088',
    //就是这里 修改服务器的 IP地址          \\-------\\----------------------
    //---------/\-----------               \\-------\\---------------------
    //---------||-----------
    //---------||------------
    //---------||-------------
    userInfo: null,
    header: { Cookie: '' }
  }
})