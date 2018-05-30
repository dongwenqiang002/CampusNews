var header = getApp().globalData.header;
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
   news:{
     id: 1,
     context: "毋庸置疑，Spring Boot在众多.....",
     title: "我眼中的Spring Boot",
     pubdate: "2018-05-17T05:28:50.000+0000",
     author: 1,
     remark: "转载自CSDN",
     endTime: "2018-05-25T09:04:58.000+0000",
     type: "学习"
   }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      options.id;
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
        },
        fail: function (r) {  
        }
      });
  },



})