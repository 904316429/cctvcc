/**

 @Name：layuiAdmin 用户登入和注册等
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License: LPPL
    
 */
 
layui.define('form', function(exports){
  var $ = layui.$
  ,layer = layui.layer
  ,laytpl = layui.laytpl
  ,setter = layui.setter
  ,view = layui.view
  ,admin = layui.admin
  ,form = layui.form;

  var $body = $('body');
  
  //自定义验证
  form.verify({
    nickname: function(value, item){ //value：表单的值、item：表单的DOM对象
      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
        return '用户名不能有特殊字符';
      }
      if(/(^\_)|(\__)|(\_+$)/.test(value)){
        return '用户名首尾不能出现下划线\'_\'';
      }
      if(/^\d+\d+\d$/.test(value)){
        return '用户名不能全为数字';
      }
    }
    
    //我们既支持上述函数式的方式，也支持下述数组的形式
    //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
    ,pass: [
      /^[\S]{6,12}$/
      ,'密码必须6到12位，且不能出现空格'
    ] 
  });

  //发送短信验证码
  admin.sendAuthCode({
    elem: '#LAY-user-getsmscode'
    ,elemPhone: '#LAY-user-login-cellphone'
    ,elemVercode: '#LAY-user-login-vercode'
    ,ajax: {
      url: layui.setter.base + 'json/user/sms.js' //实际使用请改成服务端真实接口
    }
  });
  
  //更换图形验证码
  $body.on('click', '#LAY-user-get-vercode', function(){
    var othis = $(this);
    this.src = 'https://www.oschina.net/action/user/captcha?t='+ new Date().getTime()
  });

  form.render();
  //提交
  form.on('submit(LAY-user-login-submit)', function (obj) {
    // 请求公钥
    admin.req({
      url: layui.setter.ctx + '/auth/getPublicKey/'+ obj.field.username //实际使用请改成服务端真实接口
      , type: 'get'
      , done: function (res) {
        obj.field.password = encryptRequest( obj.field.password, res.data);
        doLogin(obj);
      }
    });
  });

  function doLogin(obj){
    //请求登入接口
    admin.req({
      url: layui.setter.ctx + '/auth/login' //实际使用请改成服务端真实接口
      , type: 'post'
      , data: obj.field
      , done: function (res) {

        //请求成功后，写入 access_token
        layui.data(setter.tableName, {
          key: setter.request.tokenName
          , value: res.data.access_token
        });

        //登入成功的提示与跳转
        layer.msg('登入成功', {
          offset: '15px'
          , icon: 1
          , time: 1000
        }, function () {
          location.href = '../'; //后台主页
        });
      }
    });
  }

  // 使用jsencrypt类库加密js方法，
  function encryptRequest(data, publicKey) {
    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(publicKey);
    // ajax请求发送的数据对象
    return encrypt.encrypt(data);
  }

  //对外暴露的接口
  exports('login', {});
});