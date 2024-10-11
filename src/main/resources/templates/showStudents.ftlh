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
	<form action="/studentgridclick" method="POST">
	  <div class="form-group">
	        <input type="text"  name="search"/>
        </div>
	<button type="submit" class="btn btn-primary" name="selectedStudent" value="search-abc">Search</button>
	
  <table class="table table-striped table-hover table-responsive ">
        <#list students>
        <tr>
            <th>Registration ID</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>ShowDetails</th>
            <th>Delete</th>
            </tr>
        <#items as student>
        <tr>
            <td>${student.registrationId}</td>
            <td>${student.name}</td>
            <td>${student.gender}</td>
            <td>${student.email}</td>
            <td>${student.phoneNumber}</td>

            <td><button type="submit" class="btn btn-primary" name="selectedStudent" value="show-${student.registrationId}">Show details</button></td>
            <td><button type="submit" class="btn btn-primary" name="selectedStudent" value="delete-${student.registrationId}">Delete</button></td>
            </tr>
        </#items>
        </#list>
    </table>
	</form>
</#macro>
<@template/>