<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Sigup Page!</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>

    function clearMessage(){
    	//<span>WOWOWOWOWO</span>  
    	document.getElementById("usernameError").innerHTML="";
    	document.getElementById("passwordError").innerHTML="";
    	document.getElementById("nameError").innerHTML="";
    }

    function validateMyForm() {
    	//signup - >> ID of signup button
    	let loginObj= document.getElementById("signup");
    	loginObj.innerHTML="Magic Signup!!!!!!!!!!!!!!!!!!!!!!";
    	//https://static.vecteezy.com/system/resources/thumbnails/024/095/208/small/happy-young-man-smiling-free-png.png
    	document.getElementById("img1").src="https://static.vecteezy.com/system/resources/thumbnails/024/095/208/small/happy-young-man-smiling-free-png.png";
    	document.getElementById("img3").src="https://static.vecteezy.com/system/resources/thumbnails/024/095/208/small/happy-young-man-smiling-free-png.png";
    	
    	
    	let usernameValue= document.getElementById("email").value;
    	if(usernameValue.length==0) {
    		document.getElementById("usernameError").innerHTML="username cannot be blank";
    		document.getElementById("email").focus();
    		return;
    	}
    	
    	let passwordValue= document.getElementById("password").value;
    	if(passwordValue.length==0) {
    		document.getElementById("passwordError").innerHTML="password cannot be blank";
    		document.getElementById("password").focus();
    		return;
    	}
    	
    	let nameValue= document.getElementById("name").value;
    	if(nameValue.length==0) {
    		document.getElementById("nameError").innerHTML="Name cannot be blank";
    		document.getElementById("name").focus();
    		return;
    	}
    	
    	//If my control comes here means everythig is ok
    	
    	document.forms[0].submit();
    	
    	
    }  
    //data type define by value 
    var  num1=true;
    num1=false;
    console.log(typeof num1);
</script>
</head>
<body>

<header style="height: 30px;background-color: #7b99ff;">
</header>

<div class="container">
	 <h2>User Sign up form</h2>

	 <img id="img1" style="height: 120px;" src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcR2v8jGQFEHwDE0bEIm2Sofs-0n5RUWyiNtY_JQw46IozVB-YPU"/>
	 <img id="img2" style="height: 120px;" src="https://images.unsplash.com/photo-1511367461989-f85a21fda167?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZmlsZXxlbnwwfHwwfHx8MA%3D%3D&w=1000&q=80"/>
	 <img id="img3" style="height: 120px;" src="https://i.pinimg.com/originals/13/c9/cf/13c9cf501cc135841abeea0fc0584318.png"/>
	<hr>
	<span style="font-weight: bold;font-size: 18px;color:blue">${message}</span>
	 <hr/>
	 
	<form action="${pageContext.request.contextPath}/ui/addSignup" method="post">
	
	 <div class="form-group" style="width: 50%;">
  	           <b>Username*</b>
  	           <input type="text" id="email" name="email" class="form-control" onkeydown="clearMessage();">
  	           <span style="color:red;font-size: 18px;font-weight: bold;" id="usernameError"></span>
	    </div>
	    
	     <div class="form-group" style="width: 50%;">
  	           <b>Password*</b>
  	           <input id="password" type="password" name="password" class="form-control" onkeydown="clearMessage();">
  	             <span style="color:red;font-size: 18px;font-weight: bold;" id="passwordError"></span>
	    </div>
	     <div class="form-group" style="width: 50%;">
  	           <b>Name*</b>
  	           <input id="name" type="text" name="name" class="form-control" onkeydown="clearMessage();">
  	           <span style="color:red;font-size: 18px;font-weight: bold;" id="nameError"></span>
	    </div>
	    
	     <div class="form-group" style="width: 50%;margin-top: 20px;">
  	           <button id="signup" onclick="validateMyForm();" type="button"  class="btn btn-primary">Signup</button>
	    </div>
	</form>    
	    <hr/>
	     <img style="height: 60px;" src="https://www.iconpacks.net/icons/1/free-user-group-icon-307-thumb.png"/>

</div>


</body>
</html>