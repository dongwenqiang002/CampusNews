var header = getApp().globalData.header;
const app = getApp()
var WxParse = require('../../wxParse/wxParse.js');
Page({

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    app.userLogin();
      console.log(options.id)
      let that = this;
      wx.request({
        url: app.globalData.services +'/news/detail',
        header: header,
        data: { newsId: options.id},
        success: function (res) {
          that.setData({
            "news": res.data
          });
          that.setData({
            article: WxParse.wxParse('article', 'html', res.data.context, that, 5)
          })
        },
        fail: function (r) {  
        }
      });
  },



})