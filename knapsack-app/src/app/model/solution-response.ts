import { IProblem } from "./problem";
import { ISolution } from "./solution";
export interface ISolutionResponse {
    task: string;
    problem: IProblem;
    solution: ISolution;
}