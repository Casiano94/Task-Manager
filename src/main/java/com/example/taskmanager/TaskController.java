package com.example.taskmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class TaskController {
    @FXML
    private ListView<Task> taskListView;
    @FXML
    private TextField taskNameField;
    @FXML
    private TextArea taskDescriptionField;
    @FXML
    private Button addButton;

    private ObservableList<Task> tasks;

    @FXML
    public void initialize() {
        Database.createTable();
        tasks = FXCollections.observableArrayList(Database.getTasks());
        taskListView.setItems(tasks);

        addButton.setOnAction(e -> addTask());
    }

    private void addTask() {
        String name = taskNameField.getText();
        String description = taskDescriptionField.getText();
        if (name.isEmpty()) {
            return;
        }

        Task task = new Task(0, name, description, false);
        Database.insertTask(task);
        tasks.add(task);
        taskNameField.clear();
        taskDescriptionField.clear();
    }
}
