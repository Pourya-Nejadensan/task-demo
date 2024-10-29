import axios from 'axios';
import { TaskDTO } from "../dto/TaskDTO.ts";

const API_URL = '/api/task';

export const getAllTasks = async () => {
    try {
        const response = await axios.get(`${API_URL}/all`);
        return response.data;
    } catch (error) {
        console.error("Failed to fetch tasks:", error);
        throw error;
    }
};

export const createTask = async (taskDTO: TaskDTO) => {
    try {
        const response = await axios.post(`${API_URL}/create`, taskDTO);
        return response.data;
    } catch (error) {
        console.error("Failed to create task:", error);
        throw error;
    }
};

export const deleteTask = async (id: string) => {
    try {
        await axios.delete(`${API_URL}/delete/${id}`);
    } catch (error) {
        console.error("Failed to delete task:", error);
        throw error;
    }
};
