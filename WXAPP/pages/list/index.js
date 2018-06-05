var header = getApp().globalData.header;
const app = getApp()
Page({
  data: {
    title: '',
    //新闻列表内容
   
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
      url: app.globalData.services +'/news/get',
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

  //拉动刷新
  onPullDownRefresh: function () {
    let that = this;
    wx.request({
      url: app.globalData.services + '/news/get',
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
})
