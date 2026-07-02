package com.sit.example.layer5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sit.example.layer2.Tea;
import com.sit.example.layer4.BreakfastService;
import com.sit.example.layer4.TeaAlreadyExistsException;
import com.sit.example.layer4.TeaNotFoundException;

// http://localhost:8090/breakfast/getAllTeas
@RestController
@RequestMapping("/breakfast")
@CrossOrigin(origins = "*")
public class BreakfastController {

	public BreakfastController() {
		System.out.println("BreakfastController()....");
	}
	
	@Autowired
	BreakfastService breakfastService;
	
	// http://localhost:8080/breakfast/getAllTeas
	@GetMapping("/getAllTeas")
	public List<Tea> getAllTeasFromService() {
		return breakfastService.selectAllTeas();
	}
	
	//http://localhost:8080/breakfast/addTea
   @PostMapping("/addTea") 
   public ResponseEntity<?>  createNewTea(@RequestBody Tea tea) {
	   System.out.println("Tea Id = " + tea.getTeaId());
	    System.out.println("Tea Type = " + tea.getTeaType());
	    System.out.println("Tea Cost = " + tea.getTeaCost());
			Tea newTea = null;
			try {
				newTea = breakfastService.insertTea(tea);
			}
			catch(TeaAlreadyExistsException e) {
		        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

			}
	        return ResponseEntity.status(HttpStatus.CREATED).body(newTea);
			
		}

 //http://localhost:8080/breakfast/updateTea
   @PutMapping("/updateTea")
   public ResponseEntity<?> updateTea(@RequestBody Tea tea) {

       Tea updatedTea = null;

       try {
           updatedTea = breakfastService.updateTea(tea);
       }
       catch(TeaNotFoundException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(e.getMessage());
       }

       return ResponseEntity.status(HttpStatus.OK)
                            .body(updatedTea);
   }
   
     //http://localhost:8080/breakfast/deleteTea/5
     @DeleteMapping("/deleteTea/{id}") 
 	 public ResponseEntity<?>  deleteExistingTea(@PathVariable int id) { // CONTROLLER LAYER
 			Tea deletedTea = null;
 			try {		
 				deletedTea = breakfastService.deleteTea(id);	
 				}
 			catch(TeaNotFoundException e) {
 		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
 			}
 	        return ResponseEntity.status(HttpStatus.CREATED).body(deletedTea);
 		}
     
   //http://localhost:8080/breakfast/getTea/2
   		@GetMapping("/getTea/{id}") 
   		public ResponseEntity<?>  findAnExistingTea(@PathVariable int id) { // CONTROLLER LAYER
   			Tea teaFound = null;
   			try {			teaFound = breakfastService.findATea(id);	}
   			catch(TeaNotFoundException e) {
   		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
   			}
   	        return ResponseEntity.status(HttpStatus.CREATED).body(teaFound);
   		}
}



