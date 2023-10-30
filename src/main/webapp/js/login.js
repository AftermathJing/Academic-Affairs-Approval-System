// 验证用户名是否符合规则
// 获取用户名的输入框
var usernameInput = document.getElementById("username");

// 绑定onblur事件 失去焦点
usernameInput.onblur = checkUsername;

function checkUsername() {
    // 获取用户输入的用户名
    var username = usernameInput.value.trim();

    // 判断用户名是否符合规则：必须由 8 位数字组成
    var reg = /^\d{8}$/;
    var flag = reg.test(username);

    if (flag) {
        //符合规则
        document.getElementById("username_err").style.display = 'none';
    } else {
        //不合符规则
        document.getElementById("username_err").style.display = 'block';
    }

    return flag;
}


// 验证密码是否符合规则
// 获取密码的输入框
var passwordInput = document.getElementById("password");

// 绑定onblur事件 失去焦点
passwordInput.onblur = checkPassword;

function checkPassword() {
    // 获取用户输入的密码
    var password = passwordInput.value.trim();

    // 判断密码是否符合规则： 6~12 位，且必须包含数字、大小写字母和下划线
    var reg = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*_)[a-zA-Z0-9_]{6,12}/;
    var flag = reg.test(password);

    if (flag) {
        //符合规则
        document.getElementById("password_err").style.display = 'none';
    } else {
        //不合符规则
        document.getElementById("password_err").style.display = 'block';
    }

    return flag;
}

// 获取表单对象
var regForm = document.getElementById("login-form");

// 绑定onsubmit 事件
regForm.onsubmit = function () {
    //挨个判断每一个表单项是否都符合要求，如果有一个不合符，则返回false

    var flag = checkUsername() && checkPassword();

    if (!flag)
        alert("登陆失败")

    return flag;
}