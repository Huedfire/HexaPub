package Classes;


import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.util.List;

public class ImplementacionTable {


    @Given("^user navigated to the online calculator$")
    public void user_navigated_to_the_online_calculator()  {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("choice");
    }

    @When("^user performs the following operations the calculator must display the results$")
    public void user_performs_the_following_operations_the_calculator_must_display_the_results(DataTable arg1) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        List<List<String>> rows = arg1.raw();
        rows.stream().forEach(row -> row.stream().forEach(System.out::println));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        List<Operation> operations = arg1.asList(Operation.class);
        operations.stream().forEach(System.out::println);
    }
}
