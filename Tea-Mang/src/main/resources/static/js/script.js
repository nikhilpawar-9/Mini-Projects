function addTea(){
	console.log("Add Tea Clicked");

    let teaId = document.getElementById("teaId").value;
    let teaType = document.getElementById("teaType").value;
    let teaCost = document.getElementById("teaCost").value;

    if(teaId.trim() === "" ||
       teaType.trim() === "" ||
       teaCost.trim() === ""){

        alert("Please fill all fields before adding tea.");
        return;
    }

    let tea = {
        teaId: parseInt(teaId),
        teaType: teaType,
        teaCost: parseFloat(teaCost)
    };

    fetch("http://localhost:8080/breakfast/addTea",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(tea)
    })
    .then(res=>res.json())
    .then(data=>{
        alert("Tea Added Successfully");
        getAllTeas();
    });
}



function getAllTeas(){

    fetch("http://localhost:8080/breakfast/getAllTeas")
    .then(res=>res.json())
    .then(data=>{

        let html="";

        data.forEach(tea=>{

            html += `
                <div class="tea-card">
                    <h3>Tea Id : ${tea.teaId}</h3>
                    <h3>Tea Type : ${tea.teaType}</h3>
                    <h3>Cost : ₹${tea.teaCost}</h3>
                </div>
            `;
        });

        document.getElementById("output").innerHTML=html;
    });
}


function getTeaById(){

    let id = document.getElementById("teaId").value;

    if(id.trim() === ""){
        alert("Please enter Tea Id");
        return;
    }

    fetch(`http://localhost:8080/breakfast/getTea/${id}`)
    .then(res => {

        if(!res.ok){
            throw new Error("Tea data not found for Id : " + id);
        }

        return res.json();
    })
    .then(tea => {

        document.getElementById("output").innerHTML = `
            <div class="tea-card">
                <h3>${tea.teaId} - ${tea.teaType} - ₹${tea.teaCost}</h3>
            </div>
        `;
    })
    .catch(error => {

        document.getElementById("output").innerHTML = `
            <div class="tea-card">
                <h3 style="color:red">
                    Tea data not found for Id : ${id}
                </h3>
            </div>
        `;
    });
}


function updateTea(){

    let teaId = document.getElementById("teaId").value;
    let teaType = document.getElementById("teaType").value;
    let teaCost = document.getElementById("teaCost").value;

    if(teaId.trim() === "" ||
       teaType.trim() === "" ||
       teaCost.trim() === ""){

        alert("Please fill all fields before updating tea.");
        return;
    }

    let tea = {
        teaId: parseInt(teaId),
        teaType: teaType,
        teaCost: parseFloat(teaCost)
    };

    fetch("http://localhost:8080/breakfast/updateTea",{
        method:"PUT",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(tea)
    })
    .then(res=>res.json())
    .then(data=>{
        alert("Tea Updated Successfully");
        getAllTeas();
    });
}


function deleteTea(){

    let id = document.getElementById("teaId").value;

    if(id.trim() === ""){
        alert("Please enter Tea Id.");
        return;
    }

    fetch(`http://localhost:8080/breakfast/deleteTea/${id}`,{
        method:"DELETE"
    })
    .then(res=>res.text())
    .then(data=>{
        alert(data);
        getAllTeas();
    });
}