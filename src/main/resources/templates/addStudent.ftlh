<#import "/spring.ftl" as spring/>
<#include "template.ftlh">
<#macro content>

	<h3>Add Student</h3>
	<@spring.bind "student" />
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
	<form action="/addStudent" method="POST">
	
	<div class="form-group">
        	<label for="registrationId">Registration Id</label>
        	<@spring.formInput "student.registrationId" "class='form-control'"/>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
    	
    	<div class="form-group">
        	<label for="name">Name</label>
        	<@spring.formInput "student.name" "class='form-control'"/>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
    	
    	<div class="form-group">
        	<label for="dateOfBirth">Date of birth</label>
        	<@spring.formInput "student.dateOfBirth" "class='form-control'"/>
        	<@spring.showErrors "<br>" "bg-danger"/>
    	</div>
    	
       	<div class="form-group">
	        <label for="gender">Gender</label><br>
            <@spring.formRadioButtons "student.gender" genders "&nbsp;"/>
            <@spring.showErrors "<br>" "text-danger"/>
        </div>
        	
       	<div class="form-group">
	        <label for="gender">Phone</label><br>
            <@spring.formInput "student.phoneNumber" "&nbsp;"/>
            <@spring.showErrors "<br>" "text-danger"/>
        </div>
        
        <div class="form-group">
	        <label for="email">Email</label><br>
            <@spring.formInput "student.email"  "&nbsp;"/>
            <@spring.showErrors "<br>" "text-danger"/>
        </div>
                <div class="form-group">
	        <label for="enrolledIn">Select Enrolled</label>
            <@spring.formSingleSelect "student.enrolledIn" getSubjectsOffered "class='form-control'"/>
            <@spring.showErrors "<br>" "text-danger"/>
        </div>
        <div class="form-group">
	        <label for="interests">Select Interests</label>
            <@spring.formSingleSelect "student.interests" getInterests "class='form-control'"/>
            <@spring.showErrors "<br>" "text-danger"/>
        </div>
        
	   	<button type="submit" class="btn btn-primary" name="button" value="addStudent">Add Student</button>
	</#macro>
<@template/>