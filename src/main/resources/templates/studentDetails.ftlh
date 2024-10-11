<#import "/spring.ftl" as spring/>
<#include "template.ftlh">
<#macro content>
	<h3>Student Details</h3>
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
		<tr><td>Registration ID: </td><td>${student.registrationId}</td></tr>
		<tr><td>Name: </td><td>${student.name}</td></tr>
		<tr><td>Date of Birth: </td><td>${student.dateOfBirth}</td></tr>
		<tr><td>Gender: </td><td>${student.gender}</td></tr>
		<tr><td>Enrolled In: </td><td>${student.enrolledIn}</td></tr>
		<tr><td>Email: </td><td>${student.email}</td></tr>
		<tr><td>Phone Number: </td><td>${student.phoneNumber}</td></tr>
		<tr>
			<td>Interests</td>
			<td>
				<table class="table table-responsive">
				<#list interests as interest>
				<tr><td>${interest}</td></tr>
				</#list>
				</table
			</td>
		</tr>
	</table>
</#macro>
<@template/>