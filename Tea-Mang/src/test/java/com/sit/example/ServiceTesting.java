package com.sit.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sit.example.layer2.Tea;
import com.sit.example.layer4.BreakfastService;
import com.sit.example.layer4.TeaAlreadyExistsException;
import com.sit.example.layer4.TeaNotFoundException;

@SpringBootTest
public class ServiceTesting {

    @Autowired
    BreakfastService breakfastService;

    
    public void addTeaTest() throws TeaAlreadyExistsException {

        Tea tea = new Tea();

        tea.setTeaId(10);
        tea.setTeaType("Ginger Tea");
        tea.setTeaCost(25);

        Tea addedTea = breakfastService.insertTea(tea);

        System.out.println("Tea Added Successfully");
        System.out.println(addedTea.getTeaId());
        System.out.println(addedTea.getTeaType());
        System.out.println(addedTea.getTeaCost());
    }

    
    public void updateTeaTest() throws TeaNotFoundException {

        Tea tea = new Tea();

        tea.setTeaId(1);
        tea.setTeaType("Special Masala Tea");
        tea.setTeaCost(40);

        Tea updatedTea = breakfastService.updateTea(tea);

        System.out.println("Tea Updated Successfully");
        System.out.println(updatedTea.getTeaId());
        System.out.println(updatedTea.getTeaType());
        System.out.println(updatedTea.getTeaCost());
    }

    
    public void deleteTeaTest() throws TeaNotFoundException {

        Tea deletedTea = breakfastService.deleteTea(7);

        System.out.println("Tea Deleted Successfully");
        System.out.println(deletedTea.getTeaId());
        System.out.println(deletedTea.getTeaType());
    }

    @Test
    public void getTeaByIdTest() throws TeaNotFoundException {

        Tea tea = breakfastService.findATea(1);

        System.out.println("Tea Found");
        System.out.println(tea.getTeaId());
        System.out.println(tea.getTeaType());
        System.out.println(tea.getTeaCost());
    }

    
    public void getAllTeaTest() {

        breakfastService.selectAllTeas()
                .forEach(tea -> {

                    System.out.println(
                            tea.getTeaId() + " | " +
                            tea.getTeaType() + " | " +
                            tea.getTeaCost()
                    );
                });
    }
}