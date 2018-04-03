import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filerByStatus',
  pure: false
})
export class FilerByStatusPipe implements PipeTransform {

  transform(items: any[], value: any, args?: any): any {
    return items.filter(it => {
      return it.currentStatus == value
    });
  }

}
