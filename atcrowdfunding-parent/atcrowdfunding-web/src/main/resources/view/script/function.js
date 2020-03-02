function showMag(msg) {
    layer.msg(msg, {
        time: 2000,
        icon: 5,
        shift: 6
    }, function () {

    });
}

function showSuccessMag(msg) {
    layer.msg(msg, {
        time: 2000,
        icon: 6
    }, function () {

    });
}

function showLoading (msg) {
    return layer.msg(msg, {
        icon: 16
    });
}

function closeLoading(loading) {
    if(loading != null) {
        layer.close(loading);
        loading = null;
    }
}


function usernameReg(value) {
    //4至16位，以字母开头，字母，数字，减号，下划线
    const reg = /^[a-zA-Z]([-_a-zA-Z0-9]{3,15})$/;
    if (!reg.test(value)) {
        showMag('请输入正确的登陆账号');
        return true;
    }
    return false;
}

function passwordReg(value) {
    //6至20位，以字母开头，字母，数字，减号，下划线
    const reg = /^[a-zA-Z]([-_a-zA-Z0-9]{5,19})$/;
    if (!reg.test(value)) {
        showMag('请输入正确的登陆密码');
        return true;
    }
    return false;
}

function nameReg(value) {
    //2至10位，汉字，字母，数字，减号，下划线
    const reg = /^([\u4E00-\u9FA5]|[-_a-zA-Z0-9]){2,10}/;
    if (!reg.test(value)) {
        showMag('请输入正确的用户名称');
        return true;
    }
    return false;
}

function emailReg(value) {
    const reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
    if (!reg.test(value)) {
        showMag('请输入正确的邮箱');
        return true;
    }
    return false;
}

function jsonPost(app_path,url,data,okCallback) {
    post(app_path+url,data,'正在加载...',okCallback);
}

function post(url,data,sendMsg,okCallback) {
    var loadingIndex = null;
    $.ajax({
        type: "post",
        url: url,
        async: true,
        data: data,
        beforeSend: function() {
            loadingIndex = showLoading(sendMsg);
        },
        success: function(res) {
            const result = JSON.parse(res);
            if(result.code === "0") {
                okCallback(result);
            }else {
                showMag(result.msg);
            }
        },
        error: function(err) {
          showMag('网络请求异常');
        },
        complete: function(req, status) {
             closeLoading(loadingIndex);
        }
    });
}

