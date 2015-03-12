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
            <div class="large-12 column">
                <h3>Categories</h3>                
                <a class="button tiny" href="<c:url value="/category/insert"/>">Add</a>
                <table id="tblCategory">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
        <script src="<c:url value="/assets/js/vendor/jquery.js"/>"></script>
        <script src="<c:url value="/assets/js/foundation.min.js"/>"></script>
        <script>
            $(document).foundation();
            $(document).ready(function(){
                
                $.get("/spring-mvc-jpa/category/json",function(data){
                    $.each(data, function(key,value){
                        //alert(value['name']);
                        var del =  '<a href="javascript:deleteRecord('+value["id"]+')" class="alert label">Del</a>';
                        var edit = '<a href="/spring-mvc-jpa/category/edit?id='+value["id"]+'" class="success label">Edit</a>';
                        var str = '<tr><td>'+value['id']+'</td><td>'+value['name']+'</td><td>'+del+' '+edit+'</td></tr>';                       
                        $('#tblCategory tbody').append(str);
                    });
                });                
            });
            
            function deleteRecord(deletedId){
                $.get("/spring-mvc-jpa/category/delete/json",{id:deletedId},function(data){
                    if(data==true){
                        window.location.href=window.location.href;
                    }else{
                        alert('Gagal dihapus!');
                    }
                });
            }
        </script>
    </body>
</html>