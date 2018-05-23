//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    //向模板传入数据
    // 轮播
    index_index_scroll_tmpl: {
      images: [
        'http://127.0.0.1:8080/file/photo/34088f0a883f4b85ae573a822ff68381.jpg',
        'http://127.0.0.1:8080/file/photo/34088f0a883f4b85ae573a822ff68381.jpg',
        'http://127.0.0.1:8080/file/photo/34088f0a883f4b85ae573a822ff68381.jpg',
        'http://127.0.0.1:8080/file/photo/34088f0a883f4b85ae573a822ff68381.jpg',
        'http://127.0.0.1:8080/file/photo/34088f0a883f4b85ae573a822ff68381.jpg',
        'http://127.0.0.1:8080/file/photo/34088f0a883f4b85ae573a822ff68381.jpg',
        'http://127.0.0.1:8080/file/photo/34088f0a883f4b85ae573a822ff68381.jpg'
      ],
      indicatorDots: true,
      vertical: false,
      autoplay: true,
      interval: 3000,
      duration: 1200
    },
    
   //新闻列表内容
    news_list: {
      items: [{
        id: 1,
        context: "毋庸置疑，Spring Boot在众多.....",
        title: "我眼中的Spring Boot",
        pubdate: "2018-05-17T05:28:50.000+0000",
        author: 1,
        remark: "转载自CSDN",
        endTime: "2018-05-25T09:04:58.000+0000",
        type: "学习"
      }]
    }

  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  swiperchange: function () {

  }
  ,
  onLoad: function () {
    let that = this;
    wx.request({
      url: 'http://127.0.0.1:8080',
      success: function (res) {
        that.setData({
          "news_list.items" : res.data
        });
      }
    })
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },

  go: function (event) {
    wx.navigateTo({
      url: '../list/index?type=' + event.currentTarget.dataset.type
    })
  }
})
