<#import "/spring.ftl" as spring/>
<#include "template.ftlh">
<#macro content>
	<h3>Add Book</h3>
	<@spring.bind "bookBean" />
	<#if successMessage??>
    	<div class="bg-success">
        	${successMessage}
    	</div>
	</#if>
	<#if errorMessage??>
    	<div class="bg-danger" style="margin-bottom: 10px;">
        	${errorMessage}
    	</div>
	</#if>
	<form action="/addBook" method="POST">
    	<div class="form-group">
        	<label for="title">Title</label>
        	<@spring.formInput "bookBean.title" "class='form-control'"/>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
    	<div class="form-group">
	        <label for="isbn13">ISBN 13</label>
        	<div><@spring.formInput "bookBean.isbn13" "class='form-control'"/></div>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
    	<div class="form-group">
	        <label for="publisher">Publisher</label>
        	<div><@spring.formInput "bookBean.publisher" "class='form-control'"/></div>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
    	<div class="form-group">
	        <label for="numberOfPages">Number of pages</label>
        	<div><@spring.formInput "bookBean.numberOfPages" "class='form-control'"/></div>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
     	<div class="form-group">
	        <label for="publishedDate">Published at</label>
        	<div><@spring.formInput "bookBean.publishedDate" "class='form-control'"/></div>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
        <div class="form-group">
	        <label for="language">Language</label><br>
            <@spring.formRadioButtons "bookBean.language" languages "&nbsp;"/>
            <@spring.showErrors "<br>" "text-danger"/>
        </div>
        
    	<div class="form-group">
	        <label for="price">Price</label>
        	<div><@spring.formInput "bookBean.price" "class='form-control'"/></div>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
        <div class="form-group">
	        <label for="authorId">Single Select</label>
            <@spring.formSingleSelect "bookBean.authorId" authors "class='form-control'"/>
            <@spring.showErrors "<br>" "text-danger"/>
        </div>
    	<button type="submit" class="btn btn-primary" name="button" value="addAuthor">Add Author</button>
	    <table class="table table-striped table-hover table-responsive ">
	        <#list selectedAuthors>
        	<tr>
            	<th>Id</th>
            	<th>First name</th>
            	<th>Last name</th>
            	<th></th>
            </tr>
        	<#items as author>
        	<tr>
            	<td>${author.id}</td>
            	<td>${author.firstName}</td>
            	<td>${author.lastName}</td>
            	<td><button type="submit" class="btn btn-primary" name="button" value="${author.id}">Delete Author</button></td>
            </tr>
        	</#items>
        	</#list>
        </table>
   	<button type="submit" class="btn btn-primary" name="button" value="addBook">Add Book</button>
	</form>
</#macro>
<@template/>