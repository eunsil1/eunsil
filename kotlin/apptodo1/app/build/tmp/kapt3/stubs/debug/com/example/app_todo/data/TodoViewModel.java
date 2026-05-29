package com.example.app_todo.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000bJ\u000e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0015"}, d2 = {"Lcom/example/app_todo/data/TodoViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "dao", "Lcom/example/app_todo/data/TodoDao;", "todos", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/app_todo/data/TodoEntity;", "getTodos", "()Lkotlinx/coroutines/flow/StateFlow;", "addTodo", "", "title", "", "toggleDone", "todo", "deleteTodo", "app_debug"})
public final class TodoViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.app_todo.data.TodoDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.app_todo.data.TodoEntity>> todos = null;
    
    public TodoViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.app_todo.data.TodoEntity>> getTodos() {
        return null;
    }
    
    public final void addTodo(@org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    public final void toggleDone(@org.jetbrains.annotations.NotNull()
    com.example.app_todo.data.TodoEntity todo) {
    }
    
    public final void deleteTodo(@org.jetbrains.annotations.NotNull()
    com.example.app_todo.data.TodoEntity todo) {
    }
}