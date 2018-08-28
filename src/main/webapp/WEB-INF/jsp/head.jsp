<%@ page contentType="text/html;charset=UTF-8" language="java" autoFlush="true" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-inverse">
    <div class="container">

        <%--brand--%>
        <div class="navbar-header">
            <a href="#" class="navbar-brand">Siren Store</a>
        </div>

        <ul class="nav navbar-nav navbar-right">
            <%--not login yet--%>
            <c:if test="${empty sessionScope.user}">
                <li><a href="${pageContext.request.contextPath}/user/login.do">login</a></li>
                <li><a href="${pageContext.request.contextPath}/user/register.do">register</a></li>
            </c:if>

            <%--if login --%>
            <c:if test="${not empty sessionScope.user}">
                <li><a href="#">${sessionScope.user.username}</a></li>
                <li><a href="${pageContext.request.contextPath}/order/showOrderList.do?curPage=1">my order</a></li>
                <li><a href="${pageContext.request.contextPath}/user/doLogout.do">log out</a></li>
            </c:if>

            <li><a href="${pageContext.request.contextPath}/cart/showCart.do">Cart  <span class="badge">${sessionScope.cart.totalQuantity}</span></a></li>

        </ul>

    </div>
</nav>

<%--category--%>
<div class="center-block clearfix" style="width: 900px; margin-bottom: 20px">
    <ul class="nav navbar-nav navbar-right" id="category">
        <li ><a href="${pageContext.request.contextPath}/home.do">home</a></li>
    </ul>
</div>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/getAjax.js"></script>
<script type="text/javascript">
    var request = getXMLHttpRequest();
    var category = document.getElementById("category");

    request.onreadystatechange = function () {
        if (request.readyState == 4 && request.status == 200) {

            var json = request.response;
            var obj = eval(json);

            for (var i=0;i<obj.length;i++) {
                var a = document.createElement("a");
                var li = document.createElement("li");
                a.setAttribute("href", "${pageContext.request.contextPath}/product/showProductList.do?curPage=1&cid="+obj[i].cid+"");
                a.innerText = obj[i].cname;
                li.appendChild(a);

                category.appendChild(li);
            }
        }
    };

    console.log("aaa");
    request.open("get", "${pageContext.request.contextPath}/showCategory.do");
    request.setRequestHeader('Accept', 'application/json');
    request.send();
    console.log("bbb");

</script>