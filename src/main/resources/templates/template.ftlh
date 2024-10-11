 <#macro template>
 <!DOCTYPE html>
<html>
    <head>
        <title>Aruzhan Nurlan</title>
        <meta charset="windows-1252">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet"/>
        <script src="/webjars/jquery/3.3.1-1/jquery.min.js"></script>
        <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
        	<div class ="row">
	        <nav class="navbar navbar-default">
   				<div class="navbar-header">
   					<img src="/images/Books.png" class="img-responsive" alt="Books" width="40" height="40">
   				</div>
   				<ul class="nav navbar-nav">
   					<li><a href="/">Home</a></li>
      				<li><a href="/products">Search Products</a></li>
   	  				<li><a href="/shoppingCart">Shopping Cart</a></li>
   					<li><a href="/addBook">Add Book</a></li>
   					<li><a href="/addAuthor">Add Author</a></li>
   					<li><a href="/addStudent">Add Student</a></li>
   					<li><a href="/searchStudents">Search Student</a></li>   					<li><a href="/searchStudents">Search Student</a></li>
                    <li><a href="news">Search news</a></li>


                </ul>
			</nav>
			</div>
            <div class="row">
                <@content/>
            </div>
            <div class="row">
	            <h4>&copy; IITU - Aruzhan Nurlan 2024</h4>
            </div>
        </div>
    </body>
</html>
</#macro>
<#macro content>This is content from base template for example</#macro>

