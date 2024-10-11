<#import "/spring.ftl" as spring/>
<#include "template.ftlh">
<#macro content>
	<h3>Book Details</h3>
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
	<table class="table table-responsive">
		<tr><td>Title</td><td>${book.title}</td></tr>
		<tr><td>ISBN 13</td><td>${book.isbn13}</td></tr>
		<tr><td>Publisher</td><td>${book.publisher!}</td></tr>
		<tr><td>published date</td><td>${book.publishedDate!}</td></tr>
		<tr><td>Number of pages</td><td>${book.numberOfPages!}</td></tr>
		<tr><td>Language</td><td>${book.language}</td></tr>
		<tr><td>Price</td><td>${book.price}€</td></tr>
		<tr>
			<td>Authors</td>
			<td>
				<table class="table table-responsive">
				<#list authors as author>
				<tr><td>${author.firstName} ${author.lastName}</td></tr>
				</#list>
				</table>
			</td>
		</tr>
	</table>
	<form action="/addBookToCart" method="POST">
    	<button type="submit" class="btn btn-primary" name="action" value="${book.isbn13}">Add to cart</button>
	   	<button type="submit" class="btn btn-primary" name="action" value="back">Back</button>
	</form>
</#macro>
<@template/>