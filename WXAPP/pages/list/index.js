var header = getApp().globalData.header;
Page({
  data: {
    title: '',
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
    },
    index_index_navs_tmpl: {
      navs: [
        {
          image: 'http://127.0.0.1:8080/file/image/2.jpg',
          text: '提醒'
        }, {
          image: 'http://127.0.0.1:8080/file/image/3.jpg',
          text: '公告'
        }, {
          image: 'http://127.0.0.1:8080/file/image/1.jpg',
          text: '活动'
        }
      ]
    }
  },
  toNewsDetail: function (e) {
    wx.navigateTo({
      url: "/pages/newsDetail/index?id=" + e.currentTarget.dataset.id
    })
  },
  //
  //事件处理函数
  onLoad: function (options) {
    let that = this;
    this.title = options.type || '列表'
    console.log("访问新闻")
    wx.request({
      url: 'http://127.0.0.1:8080/news/get',
      header: header,
      success: function (res) {
        that.setData({
          "news_list.items": res.data
        });
      },
      fail: function (r) {
      }
    });

  },
  onReady: function () {
    wx.setNavigationBarTitle({
      title: this.title
    })
  },
})
