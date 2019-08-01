package pl.rtaj.to_do_list;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortcut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TaskCategory category;
    private String description;
    private boolean execution;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAcc;
    private Integer timescale;

    public Task() {
    }

    public Task(String shortcut, LocalDate date, TaskCategory category, String description, boolean execution) {
        this.shortcut = shortcut;
        this.date = date;
        this.category = category;
        this.description = description;
        this.execution = execution;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", shortcut='" + shortcut + '\'' +
                ", date=" + date +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", execution=" + execution +
                ", dateOfAcc=" + dateOfAcc +
                ", timescale=" + timescale +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExecution() {
        return execution;
    }

    public void setExecution(boolean execution) {
        this.execution = execution;
    }

    public LocalDate getDateOfAcc() {
        return dateOfAcc;
    }

    public void setDateOfAcc(LocalDate dateOfAcc) {
        this.dateOfAcc = dateOfAcc;
    }

    public Integer getTimescale() {
        return timescale;
    }

    public void setTimescale(Integer timescale) {
        this.timescale = timescale;
    }
}
