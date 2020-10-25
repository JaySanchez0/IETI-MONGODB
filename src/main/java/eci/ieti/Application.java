package eci.ieti;

import eci.ieti.data.CustomerRepository;
import eci.ieti.data.ProductRepository;
import eci.ieti.data.TodoRepository;
import eci.ieti.data.UserRepository;
import eci.ieti.data.model.Customer;
import eci.ieti.data.model.Product;

import eci.ieti.data.model.Todo;
import eci.ieti.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //customerRepository.deleteAll();

        /*customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Marley"));
        customerRepository.save(new Customer("Jimmy", "Page"));
        customerRepository.save(new Customer("Freddy", "Mercury"));
        customerRepository.save(new Customer("Michael", "Jackson"));

        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        
        customerRepository.findAll().stream().forEach(System.out::println);
        System.out.println();
        
        productRepository.deleteAll();

        productRepository.save(new Product(1L, "Samsung S8", "All new mobile phone Samsung S8"));
        productRepository.save(new Product(2L, "Samsung S8 plus", "All new mobile phone Samsung S8 plus"));
        productRepository.save(new Product(3L, "Samsung S9", "All new mobile phone Samsung S9"));
        productRepository.save(new Product(4L, "Samsung S9 plus", "All new mobile phone Samsung S9 plus"));
        productRepository.save(new Product(5L, "Samsung S10", "All new mobile phone Samsung S10"));
        productRepository.save(new Product(6L, "Samsung S10 plus", "All new mobile phone Samsung S10 plus"));
        productRepository.save(new Product(7L, "Samsung S20", "All new mobile phone Samsung S20"));
        productRepository.save(new Product(8L, "Samsung S20 plus", "All new mobile phone Samsung S20 plus"));
        productRepository.save(new Product(9L, "Samsung S20 ultra", "All new mobile phone Samsung S20 ultra"));
        
        System.out.println("Paginated search of products by criteria:");
        System.out.println("-------------------------------");
        
        productRepository.findByDescriptionContaining("plus", PageRequest.of(0, 2)).stream()
        	.forEach(System.out::println);
   
        System.out.println();*/
        Todo t1 = todoRepository.save(new Todo(new Date(2021,12,15),"Nuevo",6));
        Todo t2 = todoRepository.save(new Todo(new Date(2012,12,15),"Nuevo",7));
        Todo t3 = todoRepository.save(new Todo(new Date(2021,12,15),"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",4));
        Todo t4 = todoRepository.save(new Todo(new Date(2011,12,15),"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",4));

        User u1 = new User();
        List<Todo> todos = new ArrayList<>();
        todos.add(t1);
        todos.add(t3);
        todos.add(t2);
        u1.setTodos(todos);
        userRepository.save(u1);
        todos=new ArrayList<>();
        todos.add(t4);
        User user2 = new User();
        user2.setTodos(todos);
        userRepository.save(user2);


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        MongoOperations mongoOperation = (MongoOperations) applicationContext.getBean("mongoTemplate");

        /*Query query = new Query();
        query.addCriteria(Criteria.where("firstName").is("Alice"));
        Customer customer = mongoOperation.findOne(query, Customer.class);
        System.out.println(customer.getFirstName());*/

        //Where DueDate is Expired
        Query query = new Query();
        query.addCriteria(Criteria.where("dueDate").lt(new Date()));
        todos =  mongoOperation.find(query,Todo.class);
        for(Todo t: todos){
            System.out.println();
        }

        query = new Query();
        query.addCriteria(Criteria.where("priority").gt(5));
        todos =  mongoOperation.find(query,Todo.class);
        System.out.println("Todos con prioridad mayor a 5");
        for(Todo t: todos){
            System.out.println("Priority: "+t.getPriority());
        }
        query = new BasicQuery("{'todos.2':{$exists:true}}");
        List<User> users =  mongoOperation.find(query,User.class);
        System.out.println("Mas de 2 Todos");
        System.out.println(users);
        for(User u: users){
            System.out.println("Numero de todos: "+u.getTodos().size());
        }

        query = new Query();
        query.addCriteria(Criteria.where("description").regex("[a-z,A-Z,0-9]{30,}"));
        todos =  mongoOperation.find(query,Todo.class);
        System.out.println("size mayor a 30");
        for(Todo t: todos){
            System.out.println(t.getDescription()+" size: "+t.getDescription().length());
        }

        //userRepository.deleteAll();
        //todoRepository.deleteAll();


    }

}