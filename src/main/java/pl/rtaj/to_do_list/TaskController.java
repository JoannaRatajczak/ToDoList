package pl.rtaj.to_do_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Controller
public class TaskController {

    private final EntityManagerFactory entityManagerFactory;

    public TaskController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) boolean execution) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TypedQuery<Task> query;
        if (!execution) {
            query = entityManager.createQuery("SELECT t FROM Task t WHERE t.execution = 0", Task.class);
        } else {
            query = entityManager.createQuery("SELECT t FROM Task t WHERE t.execution = 1", Task.class);
        }

        List<Task> tasks = query.getResultList();
        model.addAttribute("tasks", tasks);

        return "home";
    }

    @GetMapping("/add")
    public String addTask(Model model) {
        model.addAttribute("newTask", new Task());

        return null;
    }

    @PostMapping("/added")
    @Transactional
    public String addedTask(Task task) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();

        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editTask(@RequestParam Long id, Model model) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Task task = entityManager.find(Task.class, id);
        model.addAttribute("task", task);

        return "edit";
    }
   /* DO DOKONCZENIA JUTRO
    @PostMapping("/edited")
    @Transactional
    public String editedTask(Task task, ){


        entityManager.getTransaction().begin();
        entityManager.persist(task);
        entityManager.getTransaction().commit();

        return "redirect:/";
    } */

}
