webpackJsonp([10],{CreK:function(t,n){},ElHx:function(t,n){},NHnr:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var a=e("7+uW"),o={render:function(){var t=this.$createElement,n=this._self._c||t;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},staticRenderFns:[]};var r=e("VU/8")(null,o,!1,function(t){e("CreK")},null,null).exports,i=e("/ocq");a.default.use(i.a);var u=new i.a({routes:[{path:"/",redirect:"/dashboard"},{path:"/",component:function(t){return e.e(2).then(function(){var n=[e("MpTN")];t.apply(null,n)}.bind(this)).catch(e.oe)},meta:{title:"自述文件"},children:[{path:"/dashboard",component:function(t){return e.e(5).then(function(){var n=[e("a52u")];t.apply(null,n)}.bind(this)).catch(e.oe)},meta:{title:"系统首页"}},{path:"/addnews",component:function(t){return e.e(0).then(function(){var n=[e("5vL5")];t.apply(null,n)}.bind(this)).catch(e.oe)},meta:{title:"添加新闻"}},{path:"/setnews",component:function(t){return e.e(1).then(function(){var n=[e("8C+C")];t.apply(null,n)}.bind(this)).catch(e.oe)},meta:{title:"设置新闻"}},{path:"/tabs",component:function(t){return e.e(8).then(function(){var n=[e("kgBe")];t.apply(null,n)}.bind(this)).catch(e.oe)},meta:{title:"tab选项卡"}},{path:"/permission",component:function(t){return e.e(4).then(function(){var n=[e("5jj7")];t.apply(null,n)}.bind(this)).catch(e.oe)},meta:{title:"权限测试",permission:!0}}]},{path:"/login",component:function(t){return e.e(7).then(function(){var n=[e("GF4k")];t.apply(null,n)}.bind(this)).catch(e.oe)}},{path:"/404",component:function(t){return e.e(6).then(function(){var n=[e("3bH0")];t.apply(null,n)}.bind(this)).catch(e.oe)}},{path:"/403",component:function(t){return e.e(3).then(function(){var n=[e("KfZE")];t.apply(null,n)}.bind(this)).catch(e.oe)}},{path:"*",redirect:"/404"}]}),c=e("mtWM"),l=e.n(c),p=e("zL8q"),s=e.n(p),h=(e("ElHx"),e("j1ja"),e("p+RM"),e("U4OO")),f=e.n(h);l.a.defaults.headers.post["Content-Type"]="application/x-www-form-urlencoded;charset=utf-8",l.a.interceptors.response.use(function(t){return t},function(t){console.log(t.response.status),401==t.response.status&&u.push({path:"/login"})}),a.default.prototype.$axios=l.a,a.default.use(f.a,{locale:"zh"}),a.default.use(s.a,{size:"small"}),u.beforeEach(function(t,n,e){var o=localStorage.getItem("ms_username");o||"/login"===t.path?t.meta.permission?"admin"===o?e():e("/403"):navigator.userAgent.indexOf("MSIE")>-1&&"/editor"===t.path?a.default.prototype.$alert("vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看","浏览器不兼容通知",{confirmButtonText:"确定"}):e():e("/login")}),new a.default({router:u,render:function(t){return t(r)}}).$mount("#app")},"p+RM":function(t,n){}},["NHnr"]);