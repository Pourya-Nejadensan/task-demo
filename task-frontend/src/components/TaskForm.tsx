import { useState } from 'react';

interface TaskFormProps {
    onCreateTask: (title: string, completed: boolean) => void;
}

export default function TaskForm({ onCreateTask }: Readonly<TaskFormProps>){
    const [title, setTitle] = useState('');
    const [completed, setCompleted] = useState(false); // Completed state

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        if (!title.trim()) return;
        onCreateTask(title, completed);
        setTitle('');
        setCompleted(false); // Reset form after adding
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                placeholder="New task title"
            />
            <label>
                <input
                    type="checkbox"
                    checked={completed}
                    onChange={(e) => setCompleted(e.target.checked)}
                />
                Completed
            </label>
            <button type="submit">Add Task</button>
        </form>
    );
};
