// pages/newsDetail/index.js
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
      options.id
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})