package co.com.sofka.crud.list;

import co.com.sofka.crud.todo.ToDo;
import co.com.sofka.crud.todo.ToDoModel;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"${settings.cors origin"})
public class ToDoListController {
    private ToDoListService toDoListService;

    @Autowired
    public ToDoListController(ToDoListService toDoListService){this.toDoListService=toDoListService;}

    @GetMapping(value = "api/list")
    public Iterable<ToDoModel> getAllListToDos(){return toDoListService.getAllListToDos();}

    @GetMapping(value = "api/{listId}/todos")
    public Iterable<ToDoModel> getToDosByListId(@PathVariable("listId") Long listId){
        return toDoListService.getToDosByListId(listId);
    }

    @PostMapping(value = "api/todolist")

    public ToDoListModel newListToDo(@RequestBody ToDoListModel todo) {
        return toDoListService.newListToDo(todo);
    }

    @DeleteMapping(value = "api/{id}/todolist")
    public void deleteListById(@PathVariable("id") Long id){toDoListService.deleteListById(id);}

    @PutMapping(value = "api/{listId}/todo")
    public ToDoModel updateAToDoByListId(@PathVariable("listId") Long listId, @RequestBody ToDoModel todo){
        if(todo.getId() != null){
            return toDoListService.updateATodoByListId(listId, todo);
        }
        throw new NotFoundIdException("No existe el id para actualizar");
    }

    @PostMapping(value = "/api/{listId}/todo")
    public ToDoModel addNewToDoByListId(@PathVariable("listId") Long listId, @RequestBody ToDoModel todo){
        retirm toDoListService.addnewToDoByListId(listId, todo);
    }




}
