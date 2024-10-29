import { useTasks } from '../hooks/useTasks';
import TaskList from '../components/TaskList';
import TaskForm from '../components/TaskForm';

export default function HomePage(){
    const { tasks, addTask, removeTask, loading } = useTasks();

    return (
        <div className="main-content">
            <h1>Task Management</h1>
            <TaskForm onCreateTask={addTask} />
            <div>
                {loading ? <p>Loading tasks...</p> : (
                    <TaskList tasks={tasks} onDeleteTask={removeTask} />
                )}
            </div>
        </div>
    );
};
