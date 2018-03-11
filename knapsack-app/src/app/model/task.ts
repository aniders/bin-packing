import { TaskTimes } from "./tasktimes";
export interface ITask {
    task: string;
    status: string;
    timestamp: TaskTimes;
}