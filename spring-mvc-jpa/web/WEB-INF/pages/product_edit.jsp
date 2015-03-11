<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Spring MVC</title>
        <link rel="stylesheet" href="<c:url value="/assets/css/foundation.css"/>" />
        <script src="<c:url value="/assets/js/vendor/modernizr.js"/>"></script>
    </head>
    <body>
        <div class="contain-to-grid sticky">
            <nav class="top-bar" data-topbar role="navigation">
                <ul class="title-area">
                    <li class="name"><h1>
                            <a href="<c:url value="/home"/>">Homepage</a>
                        </h1></li>
                    <li class="toggle-topbar menu-icon"><a href="#"><span>Menu</span></a></li>
                </ul>
                <section class="top-bar-section">
                    <ul class="right">
                        <li class="has-dropdown"><a href="<c:url value="#"/>">Category</a>
                            <ul class="dropdown">
                                <li><a href="<c:url value="/category"/>">Category List</a></li>
                                <li><a href="<c:url value="/category/insert"/>">Input Category</a></li>
                            </ul>
                        </li> 
                        <li class="has-dropdown"><a href="<c:url value="#"/>">Product</a>
                            <ul class="dropdown">
                                <li><a href="<c:url value="/product"/>">Product List</a></li>
                                <li><a href="<c:url value="/product/insert"/>">Input Product</a></li>
                            </ul>
                        </li>                          
                    </ul>
                </section>
            </nav>
        </div>

        <div class="row">
            <div class="large-8 column">
                <h3>Update Product</h3>
                <form action="<c:url value="/product/update"/>" method="post">
                    <input type="hidden" value="${product.id}" name="id"/>
                    <p>Product Code:<input type="text" name="txtCode" value="${product.code}"/></p>
                    <p>Product Name<input type="text" name="txtName" value="${product.name}"/></p>
                    <p>
                        Product Category:
                        <select name="txtCategory">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}" <c:if test="${category.id==product.category.id}">selected="true"</c:if>>${category.name}</option>
                            </c:forEach>
                        </select>
                    </p>
                    <p>Product Price:<input type="text" name="txtPrice" value="${product.price}"/></p>
                    <p>Product Description<input type="text" name="txtDesc" value="${product.description}"/></p>
                    <p><input type="submit" value="Update" class="button tiny"/></p>
                </form>
            </div>
        </div>
        <script src="<c:url value="/assets/js/vendor/jquery.js"/>"></script>
        <script src="<c:url value="/assets/js/foundation.min.js"/>"></script>
        <script>
            $(document).foundation();
        </script>
    </body>
</html>