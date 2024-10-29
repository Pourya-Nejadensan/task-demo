import { useState, useEffect } from 'react';
import { Task } from '../data/Task.ts';
import { getAllTasks, createTask, deleteTask } from '../services/TaskService';

export const useTasks = () => {
    const [tasks, setTasks] = useState<Task[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        getAllTasks()
            .then(setTasks)
            .catch(console.error)
            .finally(() => setLoading(false));
    }, []);

    const addTask = async (title: string, completed: boolean) => {
        try {
            const newTask = await createTask({ title, completed });
            setTasks([...tasks, newTask]);
        } catch (error) {
            console.error("Failed to add task:", error);
        }
    };

    const removeTask = async (id: string) => {
        try {
            await deleteTask(id);
            setTasks(tasks.filter(task => task.id !== id));
        } catch (error) {
            console.error("Failed to delete task:", error);
        }
    };

    return { tasks, addTask, removeTask, loading };
};
