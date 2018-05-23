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
    // nav
    index_index_navs_tmpl: {
      navs: [
        {
          image: 'https://m.youcai.xin/static/img/gravida.png',
          text: '孕妇'
        }, {
          image: 'https://m.youcai.xin/static/img/confinement.png',
          text: '月子'
        }, {
          image: 'https://m.youcai.xin/static/img/baby.png',
          text: '幼儿'
        }, {
          image: 'https://m.youcai.xin/static/img/old.png',
          text: '老人'
        }
      ]
    },
    // item
    index_index_items_tmpl: {
      items: [
        { image: 'https://hamlet.b0.upaiyun.com/1609/22111/fe8765fa7f2f48cd87760c10ddd20ae6.jpg' },
        { image: 'https://hamlet.b0.upaiyun.com/1609/22111/84439174cad04497beda3a076663beb4.jpg' },
        { image: 'https://hamlet.b0.upaiyun.com/1609/22111/1987d8eb8b1748368b7f2382332c9718.jpg' },
        { image: 'https://hamlet.b0.upaiyun.com/1609/22111/fe8765fa7f2f48cd87760c10ddd20ae6.jpg' },
        { image: 'https://hamlet.b0.upaiyun.com/1609/22111/fe8765fa7f2f48cd87760c10ddd20ae6.jpg' },
        { image: 'https://hamlet.b0.upaiyun.com/1609/22111/fe8765fa7f2f48cd87760c10ddd20ae6.jpg' },
        { image: 'https://hamlet.b0.upaiyun.com/1609/22111/fe8765fa7f2f48cd87760c10ddd20ae6.jpg' },
        { image: 'https://hamlet.b0.upaiyun.com/1609/22111/fe8765fa7f2f48cd87760c10ddd20ae6.jpg' }
      ]
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
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {

        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      app.getUserInfo(function (userInfo) {
        //更新数据
        console.log("-------------------")
        console.log(userInfo)
        if (userInfo) {
          that.setData({
            userInfo: userInfo
          })
        } else {
          wx.redirectTo({
            url: '/reg',
          })
        }
      })
    }



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