<#import "/spring.ftl" as spring/>
<#include "template.ftlh">
<#macro content>
	<h3>Add Author</h3>
	<@spring.bind "authorBean" />
	<#if successMessage??>
    	<div class="bg-success">
        	${successMessage}
    	</div>
	</#if>
	<form action="/addAuthor" method="POST">
    	<div class="form-group">
        	<label for="firstName">First name</label>
        	<@spring.formInput "authorBean.firstName" "class='form-control'"/>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
    	<div class="form-group">
	        <label for="lastName">Last name</label>
        	<div><@spring.formInput "authorBean.lastName" "class='form-control'"/></div>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
    	<button type="submit" class="btn btn-primary">Add</button>
	</form>
</#macro>
<@template/>