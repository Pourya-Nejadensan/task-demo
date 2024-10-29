import { Task } from '../data/Task.ts';

interface TaskListProps {
    tasks: Task[];
    onDeleteTask: (id: string) => void;
}

export default function TaskList ({ tasks, onDeleteTask }: Readonly<TaskListProps>){
    return(
        <ul>
            {tasks.map(task => (
                <li key={task.id}>
                    {task.title}
                    <span style={{ color: task.completed ? 'green' : 'red' }}>
                        ({task.completed ? 'Completed' : 'Not Completed'})
                    </span>
                    <button onClick={() => onDeleteTask(task.id)}>Delete</button>
                </li>
            ))}
        </ul>
    );
}
