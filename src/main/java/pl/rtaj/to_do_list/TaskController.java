package pl.rtaj.to_do_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) TaskCategory category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Task> query;
        if(category == null) {
            query = entityManager.createQuery("SELECT t FROM Task t", Task.class);
        } else {
            query = entityManager.createQuery("SELECT t FROM Task t WHERE t.category = '" + category+"'", Task.class);
        }

        List<Task> tasks = query.getResultList();
        model.addAttribute("tasks", tasks);
        //model.addAttribute("newTask", new Task());
        return "home";

    }

    @GetMapping("/add")
    public String addTask(Model model) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Task> query;
        query = entityManager.createQuery("SELECT t FROM Task t", Task.class);
        List<Task> tasks = query.getResultList();

        model.addAttribute("newTask", new Task());


        return null;
    }

    @PostMapping("/added")
    @Transactional
    public String addedTask(Task task){
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();

       return "redirect:/";
    }

}
