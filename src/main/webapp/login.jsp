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
  
  
  function Car() {
	    this.speed = 0;
	    this.speedUp = function (speed) {
	        this.speed = speed;
	        setTimeout(function () {
	            console.log("this.speed  = "+this.speed); // undefined
	        }, 1000);

	    };
	}

    window.speed=9019;
	let car = new Car();
	car.speedUp(50);
  
  function papa(){
	  console.log(x); 
	  for(var x=1;x<=10;x++){
	    	 
	  }
  }
  papa();
     //var has function scope
     //let has block scope
     for(var  x=1;x<=10;x++){
    	 
     }
     console.log("X = "+x);
     
     for(let y=1;y<=10;y++){
    	 
     }
     console.log("Y = "+y);
  
     wow(); 
     function wow() {
    	 console.log(this);
     }
   
     window.name="Ashish Kumar!";
     var customer = {
				name : "Nagendra",
				email : 'nagen@gmail.com',
				sprint : function() {
					console.log(this.name); //Nagendra
					console.log(name);
					console.log(this.email);
				}
		};
		
	   customer.sprint();
	   console.log("______________!!!!!!!!!!!!!!!!!!!!____________");
	   var acustomer = {
				name : "Ashna Tech",
				email : 'ashna@gmail.com',
				sprint : ()=> {
					//this , super , arguments , target.new
					console.log(this.name);
					console.log(self.name);
					console.log(name);
					//console.log(this.email);
				}
		};
		
	   acustomer.sprint();
	   
	   function show(teeth,address) {
		    console.log("!name = "+this.name);
        	console.log("!tail = "+this.tail);
        	console.log("!color = "+this.color);
        	console.log("!teeth = "+teeth);
        	console.log("!address = "+address);
	   }
	   
	   var dog = {
			        name : "tommy",
	                tail : 1,
	                color:'white'  
	             }
	   
	   show.call(dog,2,"Fremont");
	   show.apply(dog,[10,"Fremont"]);
	   
	   var cat = {
		        name : "chai",
                tail : 1,
                color:'pink',
                details: function() {
                	console.log("name = "+this.name);
                	console.log("tail = "+this.tail);
                	console.log("color = "+this.color);
                } 
            }
	   cat.details();
	   
	   //calling cat function on dog object
	   cat.details.call(dog);
	   
	   console.log("Binding cat method inside dog");
	   dog.nisha=cat.details.bind(dog);
	   dog.dog();
	   
	</script>

</head>
<body>

<header style="height: 30px;background-color: #7b99ff;">
</header>

<div class="container">
	 <h2>User Login  form</h2>

	 <img style="height: 120px;" src="https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcR2v8jGQFEHwDE0bEIm2Sofs-0n5RUWyiNtY_JQw46IozVB-YPU"/>
	 	 <img style="height: 120px;" src="https://images.unsplash.com/photo-1511367461989-f85a21fda167?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZmlsZXxlbnwwfHwwfHx8MA%3D%3D&w=1000&q=80"/>
	 <img style="height: 120px;" src="https://i.pinimg.com/originals/13/c9/cf/13c9cf501cc135841abeea0fc0584318.png"/>
	<hr>
	<span style="font-weight: bold;font-size: 18px;color:red">${message}</span>
	 <hr/>
	 
	<form action="auth" method="post">
	
	 <div class="form-group" style="width: 50%;">
  	           <b>Username</b>
  	           <input type="text" name="email" class="form-control">
	    </div>
	    
	     <div class="form-group" style="width: 50%;">
  	           <b>Password</b>
  	           <input type="password" name="password" class="form-control">
	    </div>
	    
	     <div class="form-group" style="width: 50%;margin-top: 20px;">
  	           <button type="submit"  class="btn btn-primary">SignIn</button>
	    </div>
	</form>    
	    <hr/>
	     <img style="height: 60px;" src="https://www.iconpacks.net/icons/1/free-user-group-icon-307-thumb.png"/>

</div>


</body>
</html>