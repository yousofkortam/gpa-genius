export class GradeResponse {
    constructor(
        public cumulativeGPA: number,
        public cumulativeGrade: string,
        public termGPA: number,
        public termGrade: string
    ) {}
}
