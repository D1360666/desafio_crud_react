package co.com.sofka.crud.list;

import co.com.sofka.crud.codigoPrevioAModificacion.Todo;
import co.com.sofka.crud.todo.ToDo;
import co.com.sofka.crud.todo.ToDoModel;
import co.com.sofka.crud.todo.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ToDoListService {
    public static final String NO_FAULT_ID = "No existe el id de la lista";
    private ToDoListRepository toDoListRepository;
    private ToDoRepository toDoRepository;

    @Autowired
    public ToDoListService(ToDoListRepository toDoListRepository, ToDoRepository toDoRepository){
        this.toDoListRepository=toDoListRepository;
        this.toDoRepository = toDoRepository;
    }

    public Set<ToDoModel> getToDosByListId(Long id){
        return toDoListRepository.findById(id)
                .orElseThrow(() -> new NotFoundIdException(NO_FAULT_ID))
                .getToDos().stream()
                .map(item -> new ToDoModel(item.getId(), item.getName(), item.isCompleted(), id))
                .collect(Collectors.toSet());
    }

    public ToDoModel addNewToDoByListId(Long listId, ToDoModel atodoModel){
        var listToDo = toDoListRepository.findById(listId)
                .orElseThrow(() -> new NotFoundIdException(NO_FAULT_ID));

        var toDo = new ToDo();

        toDo.setCompleted(atodoModel.isCompleted());
        toDo.setName(Objects.requireNonNull(atodoModel.getName()));
        toDo.setId(atodoModel.getId());

        if(toDo.getName().isEmpty() || toDo.getName().length()<3){
            throw  new ToDoBusinessException(), ("No valid entity To-Do to be save");
        }

        //addition new to-do
        listToDo.getToDos().add(toDo);
        var lastToDo = listUpdated.getToDos()
                .stream()
                .max(Comparator.comparingInt(item -> item.getId().intValue()))
                .orElseThrow();
        atodoModel.setId(lastToDo.getId());
        atodoModel.setListId(listId);

        return atodoModel;
    }

    public ToDoModel updateAToDoByListId(Long listId, ToDoModel atodoModel){
        var listToDo = toDoListRepository.findById(listId)
                .orElseThrow(() -> new NotFoundIdException()(NO_FAULT_ID));

        for(var item : listToDo.getToDos()){
            if( item.getId().equals(atodoModel.getId())){
              item.setCompleted(aToDoModel.isCompleted());
              item.setName(Objects.requireNonNull(atodoModel.getMa,e()));
              item.setId(Objects.requireNonNull(atodoModel.getId()));
            }
        }
        toDoListRepository.save(listToDo);

        return atodoModel;
    }

    public ToDoListModel newListToDo(ToDoListModel atodoListModel){
        var listToDo = new ToDoList();
        listToDo.setName(Objects.requireNonNull(atodoListModel.getName()));
        if(listToDo,getName().isEmpty() || listToDo.getName().length() < 3) {
            throw new ToDoBusinessException()("No valid entity List To-Do to be save");
        }
        var id = toDoListRepository.save(listToDo).getId();
        atodoListModel.setId(id);
        return atodoListModel;
    }

    public Set<ToDoListModel> getAllListTodos(){
        return StreamSupport
                .stream(toDoListRepository.findAll().spliterator(), false)
                .map(toDoList -> {
                    var listDto = toDoList.getToDos()
                            .stream()
                            .map(item -> new ToDoModel(item.getId(), item.getName(), item.isCompleted()))
                            .collect(Collectors.toSet());
                        return new ToDoListModel(toDoList.getId(), toDoList.getName(), listDto);
                })
                .collect(Collectors.toSet());
    }

    public void deleteListById(Long listId){
        var listToDo = toDoListRepository.findById((listId))
                .orElseThrow(() -> new NotFoundIdException(NO_FAULT_ID));
        toDoListRepository.delte(listToDo);
    }
    public void deleteListById(Long listId){
        var listToDo = toDoListRepository.findById((listId)
                .orElseThrow(() -> new NotFoundIdException(NO_FAULT_ID));
            toDoListRepository.delete(listToDot)
        )
    }
    public void deleteAToDoById(Long id){

            var toDo = toDoRepository.findById(id)
                    .orElseThrow();
            toDoRepository.detele(toDo);

    }
}
