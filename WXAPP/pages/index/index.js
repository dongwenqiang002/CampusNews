//index.js
var header = getApp().globalData.header;
const app = getApp()

Page({
  data: {
    //向模板传入数据
    // 轮播
    index_index_scroll_tmpl: {
      images: [
        app.globalData.services +'/file/image/timg.jpg',
        app.globalData.services +'/file/image/tushuguan.jpg',
        app.globalData.services +'/file/image/ufe.jpg',
        app.globalData.services +'/file/image/xiaoxunbei.jpg',
        app.globalData.services +'/file/image/huahua.jpg',
        app.globalData.services +'/file/image/xingzhenglou.jpg',
        app.globalData.services +'/file/image/erjiao.jpg',
      ],
      indicatorDots: true,
      vertical: false,
      autoplay: true,
      interval: 3000,
      duration: 1200
    },
    // 轮播图下面的分类
    index_index_navs_tmpl: {
      navs: [
        {
          image: app.globalData.services +'/file/image/2.jpg',
          text: '提醒'
        }, {
          image: app.globalData.services +'/file/image/3.jpg',
          text: '公告'
        }, {
          image: app.globalData.services +'/file/image/1.jpg',
          text: '活动'
        }
      ]
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
      }, {
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

  },
  //
  toNewsDetail: function(e){
    wx.navigateTo({
      url: "/pages/newsDetail/index?id=" + e.currentTarget.dataset.id
    })
  },
  onLoad: function () {
    app.userLogin();
    let that = this;
    wx.request({
      url: app.globalData.services ,
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
