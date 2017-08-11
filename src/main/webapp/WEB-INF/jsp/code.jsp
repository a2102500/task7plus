<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<form action="/home" method="get" id="form1">
    <table>
        <tr>
            <td><table>手  机：</table></td>
            <td><input type="text" id="phone" name="phone" value="${phone}"/></td>
        </tr>
        <tr>
            <td><table>验证码：</table></td>
            <td><input type="text" id="code" name="code"/></td>
        </tr>
        <tr>
            <td><table><button type="button" onclick="send()">点击获取验证码</button></table></td>
        </tr>
        <tr>
            <td><input type="button" onclick="abc()" value="注册" /></td>
        </tr>
    </table>
</form>
</body>
<script type="text/javascript">
    function send(){

        var phone =document.getElementById("phone").value;
        location.href="/a/setcode?phone="+phone;
    }

    function abc(){
        if(<%=session.getAttribute("code")%>==null)
        {
            alert("请重新获取验证码！");
            form1.action="/a/setcode";
            form1.submit();
        }else if(<%=session.getAttribute("code")%> == document.getElementById("code").value)
        {

            var phone =document.getElementById("phone").value;
            form1.action="/login";
            form1.submit();
        }else{
            alert("验证码不正确");
            <%--form1.action="/home";--%>
        }

    }
</script>
</html>

<%--<script src="js/jquery-2.1.0.min.js"></script>--%>
<%--<script type="text/javascript">--%>

    <%--$('#btn1').click(function () {--%>
        <%--var phone = $('#mobile').val()--%>
        <%--$.ajax({--%>
            <%--type: "get",--%>
            <%--async: true,--%>
            <%--url: "/a/setcode",--%>
            <%--data: phone,--%>
            <%--success: function (a) {--%>
                <%--console.log(a)--%>
                <%--$('#test').val(a)--%>
            <%--})--%>
    <%--})--%>
    <%--})--%>
    <%--$('#btn2').click(function () {--%>
        <%--var phone = $('#mobile').val()--%>
        <%--$.ajax({--%>
            <%--type: "get",--%>
            <%--async: true,--%>
            <%--url: "/a/welcome",--%>
            <%--data: test,--%>
            <%--success: function (a) {--%>
                <%--alert('登陆成功！')--%>

            <%--}--%>

            <%--})--%>

    <%--})--%>
    <%--})--%>
