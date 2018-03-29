export class Team{
    id: number = 0;
    name: string;
    imageUrl: string = '';
    employees: any[];
    editable: boolean =  true;
}

export class ServiceResponse{
    data: any;
    error: boolean;
    message: string;
}

export class Employee{
    id: number = 0;
    name: string;
    imageUrl: string;
    currentStatus: string;
    teams: any[];
    editable: boolean =  true;
}