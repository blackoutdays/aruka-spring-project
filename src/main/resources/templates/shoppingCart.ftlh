<#import "/spring.ftl" as spring/>
<#include "template.ftlh">
<#macro content>
	<h3>Shopping Cart</h3>
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
	<form action="/deleteItem" method="POST">
    <table class="table table-striped table-hover table-responsive ">
        <#list items>
        <tr>
            <th>Title</th>
            <th>Name</th>
            <th>Quantity</th>
            <th></th>
            </tr>
        <#items as item>
        <tr>
            <td>${item.book.title}</td>
            <td>${item.book.firstAuthor.firstName!} ${item.book.firstAuthor.lastName!}</td>
            <td>${item.quantity}</td>
            <td><button type="submit" class="btn btn-primary" name="deleteItem" value="${item?index}">Delete</button></td>
            </tr>
        </#items>
        </#list>
    </table>
	</form>
</#macro>
<@template/>