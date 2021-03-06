var header = getApp().globalData.header;
const app = getApp()
// pages/reg/reg.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    schoolOSS:[[],[],[]],
    stuClass: {name:'未选择'},
    time: '2015-9-00',
    userInfo: undefined,
    userDetail: undefined,
    studentCode: undefined,
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  getUserInfo: function (e) {
    console.log(e)
    this.setData({
      userInfo: e.detail.userInfo,
      userDetail:e.detail,
      hasUserInfo: true
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  changeTime: function(e){
    this.setData({
      time:e.detail.value
    });
  },
  onChangSchoolOK: function(e){
    console.log("选择成功")
    let cc = this.data.schoolOSS[2][e.detail.value[2]];
    console.log(cc)
    this.setData({
      stuClass: cc
    })
  },
  onChangSchool:function(v){
    let that = this;
     // column: column, value: value
    //console.log(v.detail.column);
    if (v.detail.column == 2) return;
    let sc = this.data.schoolOSS[v.detail.column][v.detail.value];
    console.log("一次改变")
    if(sc == undefined)return;
    wx.request({
      url: app.globalData.services+'/reg/school',
      data: { schoolID: sc.id},
      success: function (res) {
        if(res.data){
          let scho = that.data.schoolOSS;
          scho[v.detail.column+1] = res.data;
          that.setData({
            'schoolOSS': scho
          })
          console.log(that.data.schoolOSS)
          v.detail.column = v.detail.column+1;
          v.detail.value = 0;
          that.onChangSchool(v)
        }else{
          let scho = that.data.schoolOSS;
          for (let i = v.detail.column + 1;i<scho.length;i++){
            scho[i] = [];
          }
          
          that.setData({
            schoolOSS: scho
          })
        }
      }
    });
    console.log(sc);
    console.log(v.detail.value);
    console.log(this.data.schoolId) 
  },
  studentCodeInput:function(e){
    this.setData({
      studentCode: e.detail.value
    })
  },
  nameInput: function (e) {
    this.setData({
      name: e.detail.value
    })
  },
  onLoad: function (options) {
    let that = this;
    wx.request({
      url: app.globalData.services +'/reg/school',
      success: function (res) {
        let scc = that.data.schoolOSS;
        console.log(res.data);
        scc.push( res.data);
        that.setData({
          "schoolOSS": [res.data,[],[]]
        });
        let v={detail:{column:0,value:0}};
        that.onChangSchool(v)
      }
    })
  },
  //提交
  /**
   * data: {
    schoolOSS:[[],[]],
    stuClass: undefined,
    time: '2015-9-00',
    userInfo: undefined,
    userDetail: undefined,
    studentCode: undefined,
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
   * 
   */
  submitff: function(dd){
    if (!this.data.stuClass){
      wx.showToast({
        title: '班级未选择',
        icon: 'none',
        duration: 2000
      })  
      return;
    }
    if (!this.data.studentCode){
      wx.showToast({
        title: '学号未填写',
        icon: 'none',
        duration: 2000
      })  
      return;
    }
    if (!this.data.name) {
      wx.showToast({
        title: '姓名未填写',
        icon: 'none',
        duration: 2000
      })
      return;
    }
    let that = this;
    wx.login({
      //login - begin
      success: function (logRes) {
        if (logRes.code) {
          console.log(that.data.userInfo);
          wx.getUserInfo({
            success: function (uuu) {
          //服务器 登录
          wx.request({
            url: app.globalData.services +'/reg/reg',
            header:{
              'content-type': 'application/x-www-form-urlencoded'
            },
            method:'POST',
            data: {
              studentCode: that.data.studentCode,
              code: logRes.code,
              iv: uuu.iv,
              name: that.data.name,
              encryptedData: uuu.encryptedData,
              schoolTime: that.data.time,
              classId: that.data.stuClass.id
            },
            success: function (res) {
              console.log(res);
              if(res.data){
                console.log('注册成功');
                app.globalData.header.Cookie = 'JSESSIONID=' + res.data;
                app.globalData.userInfo = { nickName: '登录成功' };  // .userInfo;
                typeof cb == "function" && cb(app.globalData.userInfo)
                wx.switchTab({
                  url: '/pages/index/index',
                })
              }else{
                wx.showToast({
                  title: '注册信息有误',
                  icon: 'none',
                  duration: 2000
                })
                return;
              }
            },
            fail: function(ree){
              console.log(ree);
              wx.showToast({
                title: '注册信息有误',
                icon: 'none',
                duration: 2000
              })
              return;
            }
          });
          //服务器登录结束
          }})
        }
      }
       //login - end
    })
   
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