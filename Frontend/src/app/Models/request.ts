import { Grade } from "./grade";

export class Request {
    constructor(
        private page: string,
        private grades: Grade[]
    ) {}
}
