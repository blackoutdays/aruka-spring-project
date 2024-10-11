<#import "/spring.ftl" as spring/>
<#include "template.ftlh">
<#macro content>
	<h3>Search Products</h3>
	<#if successMessage??>
    	<div class="bg-success" style="margin-bottom: 10px;">
        	${successMessage}
    	</div>
	</#if>
	<#if errorMessage??>
    	<div class="bg-danger" style="margin-bottom: 10px;">
        	${errorMessage}
    	</div>
	</#if>
	<form action="/addToCart" method="POST">
    <table class="table table-striped table-hover table-responsive ">
        <#list products>
        <tr>
            <th>Title</th>
            <th>Name</th>
            <th></th>
            <th></th>
            </tr>
        <#items as book>
        <tr>
            <td>${book.title}</td>
            <td>${book.firstAuthor.firstName!} ${book.firstAuthor.lastName!}</td>
            <td><button type="submit" class="btn btn-primary" name="selectedProduct" value="show ${book.isbn13}">Show details</button></td>
            <td><button type="submit" class="btn btn-primary" name="selectedProduct" value="add ${book.isbn13}">Add to cart</button></td>
            </tr>
        </#items>
        </#list>
    </table>
	</form>
</#macro>
<@template/>
