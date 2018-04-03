import { Injectable } from '@angular/core';


@Injectable()
export class UtilService {
    
    public static getIndex(id: any, list: Array<any>) {
        let index = list.map(function (e) {
            return e.id
        }).indexOf(id);
        return index;
    }
}
