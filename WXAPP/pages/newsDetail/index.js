var header = getApp().globalData.header;
const app = getApp()
var WxParse = require('../../wxParse/wxParse.js');
Page({

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
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
          var hh = WxParse.wxParse('article', 'html', res.data.context, that, 5);
          if(hh){
          that.setData({
            article: hh 
          })
          }
        },
        fail: function (r, statusCode) {  
          wx.showToast('加载失败');
        }
      });
  },



})