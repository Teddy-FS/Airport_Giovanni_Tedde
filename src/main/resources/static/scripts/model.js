$(document).ready(function(){
    let currentPlane;
    $.get('api/airplanes/',function(planes){
       //Do something with the data
        planes.forEach(plane=>{
            let refuelPlane = "refuel"+plane.id;
            let flyPlane = "fly"+plane.id;
            let deletePlane = "delete"+plane.id;
            $('#airplanes').append(
                "<tr><td>"+plane.id+"</td>"+
                "<td>"+plane.currentAirport+"</td>"+
                "<td>"+plane.fuel+"</td>"+
                "<td><button type=\"button\" class=\"btn btn-primary\" id=\""+flyPlane+"\" data-toggle=\"modal\" data-target=\"#flyPlaneModal\">Fly</button></td>"+
                "<td><button type=\"button\" class=\"btn btn-primary\" id=\""+refuelPlane+"\">Refuel</button></td>"+
                "<td><button type=\"button\" class=\"btn btn-primary\" id=\""+deletePlane+"\">Delete</button></td>"+
                "</tr>");
            $("#"+flyPlane).click(function(){
                currentPlane = plane;
            });
            $("#"+refuelPlane).click(function(){
                if(plane.fuel == plane.fuelTank){
                    alert(plane.id+" is already full");
                    return;
                }
                $.ajax({
                    url:"api/airplane/",
                    type:"POST",
                    data: JSON.stringify(plane),
                    contentType: "application/json",
                    success: function(){
                        alert(plane.id+" has been refueled!");
                        location.reload();
                    }
                });
            });
            $("#"+deletePlane).click(function(){
                $.ajax({
                    url:"api/airplane/"+plane.id,
                    type:"DELETE",
                    success: function(){
                        alert(plane.id+ " has been Deleted!");
                        location.reload();
                    }
                });
            });
       });
    });
    $('#postButton').click(function(){
    plane = {"id":$('#newPlaneID')[0].value,"currentAirport":$('#newPlaneAirport')[0].value,"fuelTank":5};
        $.ajax({
            url:"api/",
            type:"POST",
            data: JSON.stringify(plane),
            contentType: "application/json",
            success: function(){
            alert(plane.id+ " has been saved!");
            location.reload();
            }
        });
    });
    $('#flyButton').click(function(){
        let destination = $('#flyPlaneAirport')[0].value;
        if(currentPlane.currentAirport == destination){
            alert(currentPlane.id+" is already in "+destination);
            location.reload();
            return;
        }
        $.post("api/airplane/fly?id=" + currentPlane.id + "&destination=" + destination, function (result) {
            if(result==true){
                alert(currentPlane.id+" is now in "+destination);
                location.reload();
            }else
                alert(currentPlane.id+" does not have enough fuel to fly to "+destination);
                location.reload();
            });
    });
});

